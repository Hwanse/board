package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostVO;
import kr.or.ddit.board.service.IPostService;
import kr.or.ddit.board.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/postCreate")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class PostCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(PostCreateController.class);
	
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = PostServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_seq = Integer.parseInt(request.getParameter("board"));
		
		request.setAttribute("board_seq", board_seq);
		request.getRequestDispatcher("/board/postCreatePage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		
		if(userVO != null){
			// 제목, 내용, 작성자id
			
			int board_seq = Integer.parseInt(request.getParameter("boardseq"));
			String title = request.getParameter("title");
			String content	= request.getParameter("smarteditor");
			String userId = userVO.getUserId();
			// 작성시간
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String wt_dt = sdf.format(dt);
			// 첨부파일 리스트
			Collection<Part> parts = request.getParts();
			logger.debug("board_seq:{}, title: {} , content : {}", board_seq,title, content);
			
			PostVO postVO = new PostVO(board_seq, userId, title, content, wt_dt);
			// 게시글이 생성되었을 때 첨부파일 insert실행
			if(postService.insertPost(postVO) > 0){
				
				int po_seq = postService.getPostSeq();
				for(Part part : parts){
					// 받아온 요청들 중에 첨부파일만 선택
					if( "files".equals(part.getName()) && part.getSize() > 0 ){
						logger.debug("part.getHeader : {}", part.getName());
						logger.debug("part.contentType: {}", part.getContentType());
						logger.debug("part.header : {}", part.getHeader("content-disposition")  );
						String contentDisposition = part.getHeader("content-disposition");
						String fileName = PartUtil.getFileName(contentDisposition);
						String ext = PartUtil.getExt(fileName);
						
						String uploadPath = PartUtil.getUploadPath();
						File uploadFolder = new File(uploadPath);
						if(uploadFolder.exists()){
							String filePath = uploadPath + File.separator +UUID.randomUUID().toString() + ext;
							AttachFileVO fileVO = new AttachFileVO(po_seq, filePath, fileName);
							
							if(postService.insertAttachFile(fileVO) > 0){
								part.write(filePath);
								part.delete();
							} else{
								break;
							}
							
						}
						
					}
				}
				
				response.sendRedirect(request.getContextPath()+ "/post?postId="+ po_seq +"&boardSeq="+board_seq);
				
			} else{
				response.sendRedirect(request.getContextPath()+ "/postCreate?board="+board_seq);
			}

			
		}
	}

}

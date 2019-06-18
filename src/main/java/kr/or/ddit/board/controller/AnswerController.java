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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostVO;
import kr.or.ddit.board.service.IPostService;
import kr.or.ddit.board.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/answer")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(AnswerController.class);
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = PostServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int parent_seq = Integer.parseInt(request.getParameter("parentSeq"));
		int board_seq = Integer.parseInt(request.getParameter("boardSeq"));
		int group_seq = Integer.parseInt(request.getParameter("groupSeq"));
		
		request.setAttribute("parentSeq", parent_seq);
		request.setAttribute("boardSeq", board_seq);
		request.setAttribute("groupSeq", group_seq);
		request.getRequestDispatcher("/board/answerCreatePage.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");

		int parent_seq 	= Integer.parseInt(request.getParameter("parentSeq"));
		int board_seq 	= Integer.parseInt(request.getParameter("boardSeq"));
		int group_seq 	= Integer.parseInt(request.getParameter("groupSeq"));

		
		if(userVO != null){
			
			String title 	= request.getParameter("title");
			String content	= request.getParameter("smarteditor");
			String userId 	= userVO.getUserId();

			// 작성시간
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String wt_dt = sdf.format(dt);
			
			// 첨부파일 리스트
			Collection<Part> parts = request.getParts();
			logger.debug("board_seq:{}, title: {} , content : {}", board_seq,title, content);
		
			PostVO postVO = new PostVO(board_seq, parent_seq, userId, title, content, wt_dt, group_seq);
			
			if(postService.insertAnswer(postVO) > 0){
				
				int po_seq = postService.getPostSeq();
				for(Part part : parts){
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
				
				response.sendRedirect(request.getContextPath() + "/postPaging?board=" + board_seq);
			}
		
		} else{
			response.sendRedirect(request.getContextPath() + "/answer?parentSeq="+parent_seq+"&boardSeq="+board_seq+"&groupSeq="+group_seq);
		}
		
	}

	
	
}

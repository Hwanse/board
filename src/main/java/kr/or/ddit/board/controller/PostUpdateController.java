package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import kr.or.ddit.board.service.FileServiceImpl;
import kr.or.ddit.board.service.IFileService;
import kr.or.ddit.board.service.IPostService;
import kr.or.ddit.board.service.PostServiceImpl;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PostUpdateController
 */
@WebServlet("/postUpdate")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(PostUpdateController.class);
	
	private IPostService postService;
	private IFileService fileService;
	
	@Override
	public void init() throws ServletException {
		postService = PostServiceImpl.getInstance();
		fileService = FileServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int po_seq = Integer.parseInt(request.getParameter("postId"));
		int seq = Integer.parseInt(request.getParameter("boardSeq"));
		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("po_seq", po_seq);
		param.put("seq", seq);
		
		PostVO postVO = postService.getPostInfo(param);
		if(postVO != null){
			List<AttachFileVO> fileList = postService.getAttatchFiles(po_seq);
			Map<String, Object> postInfos = new HashMap<String, Object>();
			postInfos.put("postInfo", postVO);
			postInfos.put("fileList", fileList);
			
			request.setAttribute("postInfos", postInfos);
			request.getRequestDispatcher("/board/postUpdatePage.jsp").forward(request, response);
			
		} else{
			response.sendRedirect(request.getContextPath() + "/post?postId="+ po_seq + "&boardSeq=" + seq);
		}
		
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int po_seq = Integer.parseInt(request.getParameter("postId"));
		int seq = Integer.parseInt(request.getParameter("boardSeq"));
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		// 기존의 첨부되어 있던 첨부파일들의 id값 배열
		String[] fileSeqArray = request.getParameterValues("existingfiles"); 
		List<Integer> existingFilesList = new ArrayList<Integer>();
		
		if(fileSeqArray != null){
			for(String fileSeq : fileSeqArray){
				existingFilesList.add(Integer.parseInt(fileSeq));
				logger.debug("filesSeq : {}", fileSeq);
			}
		} 
			
		
		if(userVO != null){
//			postService.deleteAttachFile(po_seq);
			
			
			String title = request.getParameter("title");
			String content	= request.getParameter("smarteditor");
			// 첨부파일 리스트
			Collection<Part> parts = request.getParts();
			
			PostVO postVO = new PostVO();
			postVO.setPo_seq(po_seq);
			postVO.setTitle(title);
			postVO.setContent(content);
			
			// 기존 첨부파일 목록중 삭제된 항목들에서 삭제할 요소들을 셋팅
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("po_seq", po_seq);
			param.put("existingFilesList", existingFilesList);
			
			if(postService.updatePostInfo(postVO) > 0 ){
				logger.debug("isDelete: {}",fileService.deleteExistingFile(param));
				
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
					
				} // test = Part part : parts - end
			 														
				response.sendRedirect(request.getContextPath() + "/post?postId=" + po_seq+ "&boardSeq=" + seq);
				
			} //test = postService.updatePostInfo(postVO) > 0 - end
		} //test = userVO != null  - end 
	} // post end
	
	
}

package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.AttachFileVO;
import kr.or.ddit.board.model.PostVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.IPostService;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.PostServiceImpl;
import kr.or.ddit.board.service.ReplyServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(PostController.class);
	
	private IPostService postService;
	private IReplyService replyService;
	@Override
	public void init() throws ServletException {
		postService = PostServiceImpl.getInstance();
		replyService = ReplyServiceImpl.getInstance();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int po_seq 	= Integer.parseInt( request.getParameter("postId"));
		int seq = Integer.parseInt( request.getParameter("boardSeq"));
		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("po_seq", po_seq);
		param.put("seq", seq);
		
		PostVO postVO = postService.getPostInfo(param);
		logger.debug("po_seq", po_seq);
		if(postVO != null ){
			List<AttachFileVO> fileList = postService.getAttatchFiles(po_seq);
			Map<String, Object> postInfos = new HashMap<String, Object>();
			postInfos.put("postInfo", postVO);
			postInfos.put("fileList", fileList);
			
			List<ReplyVO> replyList = replyService.getReplys(po_seq);
			
			request.setAttribute("postInfos", postInfos);
			request.setAttribute("replyList", replyList);
			request.getRequestDispatcher("/board/postPage.jsp").forward(request, response);
		} else{
			response.sendRedirect(request.getContextPath() + "/postPaging");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}

package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.IPostService;
import kr.or.ddit.board.service.PostServiceImpl;

@WebServlet("/postDelete")
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(PostDeleteController.class);
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = PostServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int po_seq = Integer.parseInt(request.getParameter("postId"));
		int board_seq = Integer.parseInt(request.getParameter("boardSeq"));
		
		if(postService.deletePost(po_seq) > 0){
			response.sendRedirect(request.getContextPath() + "/postPaging?board="+board_seq);
		} else{
			response.sendRedirect(request.getContextPath()+"/post?postId="+po_seq +"&boardSeq="+board_seq);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	}

}

package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class RepleDeleteController
 */
@WebServlet("/repleDelete")
public class RepleDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(RepleDeleteController.class);
	
	private IReplyService repleService;
	
	@Override
	public void init() throws ServletException {
		repleService = ReplyServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int po_seq = Integer.parseInt(request.getParameter("postId"));
		int board_seq = Integer.parseInt(request.getParameter("boardSeq"));
		int re_seq = Integer.parseInt(request.getParameter("reSeq"));
	
		if(repleService.deleteReply(re_seq) > 0){
			response.sendRedirect(request.getContextPath() + "/post?postId="+po_seq+"&boardSeq="+board_seq);
		} else{
			response.sendRedirect(request.getContextPath() + "/post?postId="+po_seq+"&boardSeq="+board_seq);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}

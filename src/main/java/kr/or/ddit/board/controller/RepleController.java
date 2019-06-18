package kr.or.ddit.board.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.user.model.UserVO;

@WebServlet("/reple")
public class RepleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IReplyService replyService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(RepleController.class);
	
	@Override
	public void init() throws ServletException {
		replyService = ReplyServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("USER_INFO");
		
		int board_seq 		= Integer.parseInt(request.getParameter("boardSeq"));
		int po_seq 			= Integer.parseInt(request.getParameter("postId"));
		String userId 		= request.getParameter("userId");
		String repleContent = request.getParameter("repleContent");
		
		// 작성시간
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String wt_dt = sdf.format(dt);

		ReplyVO replyVO = new ReplyVO(po_seq, repleContent, userId, wt_dt);
		
		if(userVO != null){
			if(replyService.insertReply(replyVO) > 0){
				
				request.setAttribute("boardSeq", board_seq);
				request.setAttribute("postId", po_seq);
				request.getRequestDispatcher("/post").forward(request, response);
			}else{
				response.sendRedirect(request.getContextPath() + "/post?boardSeq=" + board_seq + "&postId="+po_seq);
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}

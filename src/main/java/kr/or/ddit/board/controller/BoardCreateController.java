package kr.or.ddit.board.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class BoardCreateController
 */
@WebServlet("/boardCreate")
public class BoardCreateController extends HttpServlet {
	private static final Logger logger = LoggerFactory
			.getLogger(BoardCreateController.class);
	
	private static final long serialVersionUID = 1L;
     
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = BoardServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/board/createBoard.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		if(userVO != null){
			String userId 	= userVO.getUserId();
			String name 	= request.getParameter("boardname");
			String use_yn 	= request.getParameter("use_yn");
	
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			logger.debug("userId : {} , name : {}, user_yn : {}", userId, name, use_yn);
			logger.debug("dt : {} , sdf.format(dt) : {}", dt, sdf.format(dt));
			
			BoardVO boardVO = new BoardVO(userId, name, use_yn, sdf.format(dt));
			boardService.insertBoard(boardVO);

			getServletContext().setAttribute("boardList", boardService.getAllBoardList());
			response.sendRedirect(request.getContextPath()+"/boardCreate");
			
		}
		
	}

}

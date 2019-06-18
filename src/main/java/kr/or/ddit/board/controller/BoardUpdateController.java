package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/boardUpdate")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private static final Logger logger = LoggerFactory
			.getLogger(BoardUpdateController.class);
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = BoardServiceImpl.getInstance();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int seq			= Integer.parseInt(request.getParameter("seq"));
		String name		= request.getParameter("boardname");
		String use_yn	= request.getParameter("use_yn");
		
		UserVO userVO 	= (UserVO) request.getSession().getAttribute("USER_INFO");
		
		logger.debug("seq : {} / name : {} / use_yn : {}" , seq, name, use_yn);
		if(userVO != null){
			BoardVO boardVO = new BoardVO();
			boardVO.setSeq(seq);
			boardVO.setName(name);
			boardVO.setUse_yn(use_yn);

			if(boardService.updateBoard(boardVO) > 0){
				
				logger.debug("b.seq : {} / b.name : {} / b.use_yn : {}" , boardVO.getSeq(), boardVO.getName(), boardVO.getUse_yn());
				getServletContext().setAttribute("boardList", boardService.getAllBoardList());
				response.sendRedirect(request.getContextPath() + "/boardCreate");
			} else{
				response.sendRedirect(request.getContextPath() + "/boardCreate");
			}
		} else{
			response.sendRedirect(request.getContextPath() + "/boardCreate");
		}
	
	}

}

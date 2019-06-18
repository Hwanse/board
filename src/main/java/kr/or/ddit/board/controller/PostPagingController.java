package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.PostPageVO;
import kr.or.ddit.board.model.PostVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IPostService;
import kr.or.ddit.board.service.PostServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class BoardPagingController
 */
@WebServlet("/postPaging")
public class PostPagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(PostPagingController.class);
	
	private IBoardService boardService;
	private IPostService postService;
	@Override
	public void init() throws ServletException {
		boardService = BoardServiceImpl.getInstance();
		postService = PostServiceImpl.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int board_seq  		= Integer.parseInt(request.getParameter("board"));
		
		BoardVO boardVO = boardService.getBoardInfo(board_seq);
		String board_name	= boardVO.getName();
//		String board_use 	= request.getParameter("use");
		
		String pageString 		= request.getParameter("page");
		String pageSizeString 	= request.getParameter("pageSize");
		int page  				= pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize 			= pageSizeString == null ? 10 : Integer.parseInt(pageSizeString);
		
		PostPageVO postPageVO = new PostPageVO(page, pageSize, board_seq);

		Map<String, Object> resultMap = postService.postPagingList(postPageVO);
		
		List<PostVO> postsList = (List<PostVO>) resultMap.get("postList");
		int postsCnt = (int) resultMap.get("postsCnt");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		request.setAttribute("board_seq", board_seq);
		request.setAttribute("boardname", board_name);
		
		request.setAttribute("postsList", postsList);
		request.setAttribute("postsCnt", postsCnt);
		request.setAttribute("postPageVO", postPageVO);
		request.setAttribute("paginationSize", paginationSize);
		request.getRequestDispatcher("/board/postPagingList.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}

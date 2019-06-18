package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class PageController
 */
@WebServlet("/userPagingList")
public class PageController extends HttpServlet {
	
	private static final Logger logger = LoggerFactory
			.getLogger(PageController.class);
	
	private static final long serialVersionUID = 1L;
    
	private IUserService userService;
	
	private int PAGESIZE;
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PAGESIZE = 10;
		
		// 파라미터가 있을 경우 / 없을경우
		/*String pageString = request.getParameter("page");
		String pageSizeString = request.getParameter("pageSize");
		
		int page  = pageString == null ? 1 : Integer.parseInt(pageString);
		int pageSize = pageSizeString == null ? PAGESIZE : Integer.parseInt(pageSizeString);
		
		PageVO pageVO = new PageVO(page, pageSize);*/
		
		PageVO pageVO = new PageVO(1, PAGESIZE);
		
		if(request.getParameter("page") != null && request.getParameter("pageSize") != null){
			int page = Integer.parseInt(request.getParameter("page") );
			int pageSize = Integer.parseInt( request.getParameter("pageSize") );
			pageVO = new PageVO(page, pageSize);
		}
		
		
		Map<String,Object> resultMap = userService.userPagingList(pageVO);
//		logger.debug("Page  : {} , pageSize : {}", pageVO.getPage() , pageVO.getPageSize() );
		request.setAttribute("userList", resultMap.get("userList") );
		request.setAttribute("pageSize", pageVO.getPageSize());
//		request.setAttribute("userPage", userService.userPagingList(pageVO));
		request.setAttribute("usersCnt", resultMap.get("usersCnt"));
		request.setAttribute("paginationSize", resultMap.get("paginationSize"));
		request.setAttribute("pageVO", pageVO);
		// userList객체를 이용하여 사용자 화면을 생성하는 jsp
		request.getRequestDispatcher("/user/pageUserList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

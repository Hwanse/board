package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
* MulCalculationController.java
*
* @author PC14
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC14 최초 생성
*
* </pre>
*/
@WebServlet("/mulCalculation")
public class MulCalculationController extends HttpServlet {
	
	private static final Logger logger = LoggerFactory
			.getLogger(MulCalculationController.class);
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/jsp/mulInput.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int param1 = Integer.parseInt(request.getParameter("param1"));
		int param2 = Integer.parseInt(request.getParameter("param2"));
		
		long mulResult = param1 * param2;		
	
		logger.debug("mul result : {}", mulResult);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("mulResult", mulResult);
		
		request.getRequestDispatcher("/jsp/mulResult.jsp").forward(request, response);
		
	}

	
	
}

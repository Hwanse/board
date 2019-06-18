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
* SumCalculation.java
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
@WebServlet("/sumCalculation")
public class SumCalculateController extends HttpServlet {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SumCalculateController.class);
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/jsp/sumInput.jsp").forward(request, response);;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int start = Integer.parseInt(request.getParameter("startParam"));
		int end = Integer.parseInt(request.getParameter("endParam"));
		
		int sum = 0;
		
		for(int i = start; i <= end; i++){
			sum += i;
		}
		logger.debug("sum result: {} " , String.valueOf(sum) );
		
		
		HttpSession session = request.getSession();
		session.setAttribute("sumResult", sum);
		
		request.getRequestDispatcher("/jsp/sumResult.jsp").forward(request, response);

	}

}

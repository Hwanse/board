<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginProcess</title>
</head>
<body>
	
	loginProcess.jsp
	<%-- post방식 한글 파라미터를 받기 위한 처리 --%>
	<% request.setCharacterEncoding("utf-8"); %>
	
	<h2>request객체의 getParameter, getParameterValue</h2>
	request.getParameter("userId") : <%=request.getParameter("userId") %> <br>
	request.getParameterValues("userId") : 
	
	<%
		String[] userIds = request.getParameterValues("userId");
		
		for(String userId: userIds){%>
			
			<%=userId%> <br> 
	  
	  <%}%>
	
<%-- 	<%=request.getParameterValues("userId") %> <br> --%>
	request.getParameter("password") : <%=request.getParameter("password") %> <br>


	<h2>request객체의 getParameterMap</h2>
	<% Map<String, String[]> parameterMap = request.getParameterMap();  
		
		//parameter : userId, password
		String[] userIdsFromMap = parameterMap.get("userId");
		String[] passwords = parameterMap.get("password");
		
		for(String userId : userIdsFromMap){%>
			userIdsFromMap : <%=userId %><br>
			
	<% }%>
		
	<% for(String password : passwords){%>
			passwordsFromMap : <%=password %><br>
			
	<% }%>
	
	request.getParameterMap() : <%=request.getParameterMap() %> <br>
	
	
	<h2>request객체의 getParameterNames()</h2>
	<% Enumeration<String> parameterNames = request.getParameterNames();
		
		while(parameterNames.hasMoreElements()){%>
			
			parameterNames : <%=parameterNames.nextElement() %> <br>
			
	  <%} %>	
	
	
	
	
</body>
</html>
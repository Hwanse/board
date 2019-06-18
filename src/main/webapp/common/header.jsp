<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">JSP/SPRING 
				<%-- <c:set var="userName" value="${USER_INFO.name }"/>
				<c:if test="${userName == null }">
					<c:set var="userName" value="접속하지 않은 사용자 입니다"/>
				</c:if>
				${userName } --%>
				<c:choose>
					<%-- <c:when test="${empty USER_INFO }">
						접속 전입니다.
					</c:when>
					<c:when test="${!empty USER_INFO}">
						${USER_INFO.name }
					</c:when> --%>
					<c:when test="${!empty USER_INFO }">
						${USER_INFO.alias }
					</c:when>
					<c:otherwise>
						접속 전입니다.
					</c:otherwise>
				</c:choose> 	
			</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">Dashboard</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
</nav>
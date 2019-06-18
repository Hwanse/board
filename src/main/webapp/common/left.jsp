<%@page import="kr.or.ddit.board.service.BoardServiceImpl"%>
<%@page import="kr.or.ddit.board.service.IBoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${pageContext.request.contextPath }/main.jsp">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a
			href="${pageContext.request.contextPath}/boardCreate">게시판 생성</a></li>
		<c:forEach items="${boardList }" var="boardVO">
			<c:if test="${boardVO.use_yn eq 'Y' }">
				<li class="active"><a
					href="${pageContext.request.contextPath}/postPaging?board=${boardVO.seq}&use=${boardVO.use_yn}">${boardVO.name }</a></li>
			</c:if>
		</c:forEach>
		
	</ul>
</div>
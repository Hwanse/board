<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자리스트</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp" %>
<style>
	.userTr:hover{
		cursor: pointer;
	}
</style>
<script>
	$(document).ready(function(){
		// 사용자 tr 태그 이벤트 등록
		$(".userTr").on("click",function(){
			// 클릭한 행에 사용자 id를 가져오는 방법
// 			console.log( $(this).find(".userId").html() );
// 			console.log( $(this).attr("id") )
// 			console.log( $(this).data("userid") )

			// 사용자id를 #userId 값으로 설정해주고
			var userId = $(this).find(".userId").text();
			$("#userId").val(userId);

			//#frm을 이용하여 submit()
			$("#frm").submit();

		});	
	
	});
</script>
</head>

<body>


	<!-- header -->
	<%@include file="/common/header.jsp" %>
	
	<div class="container-fluid">

		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp" %>
			
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


			<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						
						<!-- 사용자 상세조회 : userId가 필요 -->
						<form id="frm" action="${pageContext.request.contextPath }/user"
							  method="get">
							  <input type="hidden" id="userId" name="userId"/>
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>생년월일</th>
								</tr>
							
								<%
// 									Map<String,Object> resultMap = (Map<String,Object>)request.getAttribute("userPage");
								%>
									<c:forEach items="${userList }" var="user">
										<tr class="userTr" id="${user.userId }" data-userid="${user.userId }" data-name="${user.name }">
											<td class="userId">${user.userId }</td>										
											<td>${user.name }</td>										
											<td>${user.alias }</td>										
<%-- 											<td><%=LocalDate.now() %></td>										 --%>
											<td><fmt:formatDate value="${user.birth }" pattern="yyyy.MM.dd"/></td>
										</tr>
									</c:forEach>

							</table>
						</div>

						<a href="${pageContext.request.contextPath }/userForm" class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination" >
								 <%
// 									PageVO pageVO = (PageVO)request.getAttribute("pageVO");
									//if(pageVO.getPage() == 1){ %>
<!-- 										<li class="previous disabled"> -->
<!-- 											<span>«</span> -->
<!-- 										</li> -->
									<%
									//}else{%>
<!-- 										<li class="previous"> -->
<%-- 											<a href="${pageContext.request.contextPath}/userPagingList?page=<%=pageVO.getPage()-1 %>&pageSize=<%=pageVO.getPageSize() %>">«</a> --%>
<!-- 										</li> -->
								  <%//}  
								  %> 
								
								
							
								<% 
// 									int pageSize = Integer.parseInt( request.getAttribute("pageSize").toString() );	
								
// 									int usersCnt = (Integer)resultMap.get("usersCnt");
									
// 									int pageLength = (Integer)resultMap.get("paginationSize");
									/* int pageLength = 0;
									if(usersCnt % pageSize ==0){
										pageLength = usersCnt / pageSize;
									} else{
										pageLength = usersCnt / pageSize + 1;
									} */
// 									PageVO pageVO = (PageVO)request.getAttribute("pageVO");
									
									//for(int i=1; i <= pageLength; i++){%>
										<%// if( pageVO.getPage() == i ) {%>
<!-- 												<li class = "active"> -->
<%-- 													<span><%=i %></span> --%>
<!-- 												</li> -->
										<% 	//	continue; 
											//} %>
											
<%-- 										<li><a href="${pageContext.request.contextPath}/userPagingList?page=<%=i %>&pageSize=<%=pageVO.getPageSize() %>"><%=i %></a></li> --%>
								<% 	//} %>
								<%// if(pageVO.getPage() == pageLength){%>
<!-- 									<li class="next disabled"> -->
<!-- 										<span>»</span> -->
<!-- 									</li>	 -->
								 <%//} else{%>
<!-- 								 	<li class="next"> -->
<%-- 								 		<a href="${pageContext.request.contextPath}/userPagingList?page=<%=pageVO.getPage() +1 %>&pageSize=<%=pageVO.getPageSize() %>">»</a> --%>
<!-- 								 	</li> -->
								 <%//} %>
								
								<c:choose>
									<c:when test="${pageVO.page == 1 }">
										<li class="previous disabled">
											<span>«</span>
										</li>
									</c:when>
									<c:otherwise>
										<li class="previous">											
											<a href="${pageContext.request.contextPath}/userPagingList?page=${pageVO.page-1 }&pageSize=${pageVO.pageSize}">«</a>
										</li>
									</c:otherwise>
								</c:choose>								 

								<c:set var="usersCnt" value="${usersCnt }"/>
								<c:set var="pageLength" value="${paginationSize }"/>
								<c:forEach var="i" begin="1" step="1" end="${pageLength }">
									<c:choose>
										<c:when test="${pageVO.page == i }">
											<li class = "active">
													<span>${i }</span>
											</li>
										</c:when>
										<c:otherwise>															
											<li><a href="${pageContext.request.contextPath}/userPagingList?page=${i }&pageSize=${pageVO.pageSize}">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								 <c:choose>
								 	<c:when test="${pageVO.page == pageLength }">
								 		<li class="next disabled">
											<span>»</span>
										</li>	
								 	</c:when>
								 	<c:otherwise>
								 		<li class="next">													
								 			<a href="${pageContext.request.contextPath}/userPagingList?page=${pageVO.page + 1 }&pageSize=${pageVO.pageSize}">»</a>
								 		</li>
								 	</c:otherwise>
								 </c:choose>		
								 
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

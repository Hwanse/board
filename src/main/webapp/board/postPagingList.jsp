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
	.postTr:hover{
		cursor: pointer;
	}
</style>
<script>
	$(document).ready(function(){
		
		// 클릭한 행(tr태그) 이벤트 등록
		$(".postTr").on("click", function(){
			var poId = $(this).find(".postId").text();
			$("#postId").val(poId);
			
			$("#frm").submit();
		})
		
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
						<h2 class="sub-header">${boardname }</h2>
						
						<!-- 사용자 상세조회 : userId가 필요 -->
						<form id="frm" action="${pageContext.request.contextPath }/post"
							  method="get">
							  <input type="hidden" id="postId" name="postId"/>
							  <input type="hidden" id="boardSeq" name="boardSeq" value="${board_seq}"/>
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>게시글 번호</th>
									<th>제목</th>
									<th>작성자 아이디</th>
									<th>작성일</th>
								</tr>
							
								<c:forEach items="${postsList }" var="post">
									<c:choose>
									<c:when test="${post.delete_yn eq 'N' }">
										<tr class="postTr" id="${post.po_seq }" data-poseq="${post.po_seq }">
											<td class="postId">${post.po_seq }</td>										
											<td>
												<c:if test="${post.level > 1 }">
													<c:forEach var="i" begin="1" end="${post.level+1 }" step="1">
														&nbsp;&nbsp;
													</c:forEach>
												</c:if>
												${post.title }
											</td>										
											<td>${post.userId }</td>										
											<td>${post.wt_dt }</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>${post.po_seq }</td>
											<td colspan="3">삭제된 게시글입니다. </td>
										</tr>
									</c:otherwise>
									</c:choose>
								</c:forEach>

							</table>
						</div>

						<a href="${pageContext.request.contextPath }/postCreate?board=${board_seq}" class="btn btn-default pull-right">게시글 등록</a>

						<div class="text-center">
							<ul class="pagination" >
								
								<c:choose>
									<c:when test="${postPageVO.page == 1 }">
										<li class="previous disabled">
											<span>«</span>
										</li>
									</c:when>
									<c:otherwise>
										<li class="previous">											
											<a href="${pageContext.request.contextPath}/postPaging?board=${board_seq }&page=${postPageVO.page-1 }&pageSize=${postPageVO.pageSize}">«</a>
										</li>
									</c:otherwise>
								</c:choose>								 

								<c:set var="usersCnt" value="${postsCnt }"/>
								<c:forEach var="i" begin="1" step="1" end="${paginationSize }">
									<c:choose>
										<c:when test="${postPageVO.page == i }">
											<li class = "active">
													<span>${i }</span>
											</li>
										</c:when>
										<c:otherwise>															
											<li><a href="${pageContext.request.contextPath}/postPaging?board=${board_seq }&page=${i }&pageSize=${postPageVO.pageSize}">${i }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								 <c:choose>
								 	<c:when test="${postPageVO.page == paginationSize }">
								 		<li class="next disabled">
											<span>»</span>
										</li>	
								 	</c:when>
								 	<c:otherwise>
								 		<li class="next">													
								 			<a href="${pageContext.request.contextPath}/postPaging?board=${board_seq }&page=${postPageVO.page + 1 }&pageSize=${postPageVO.pageSize}">»</a>
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
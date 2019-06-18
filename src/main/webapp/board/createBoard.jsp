<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>Jsp</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp" %>
<script>

	$(document).ready(function(){
	
		$("#createBtn").on("click",function(){
			// 유효성검사
			if( $("#boardname").val().trim().length == 0){
				alert("게시판 명을 입력해주세요.");
				$("#boardname").focus();
				return;
			}
			
			$(this).parent().parent().submit();	
		})
		
		$(".modifyBtn").on("click",function(){
			
			console.log($(this).parent().parent());
			console.log($(this).parent().parent().find("#seq").val());
			
			$(this).parent().parent().submit();
		})
		
	});

</script>

</head>

<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp" %>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="blog-header">
					<h1>게시판 생성</h1>
				</div>

				<div class="row">

					<div class="col-sm-8 blog-main">

						<div class="blog-post">

							<form id="cFrm" class="form-horizontal" role="form"
								  action="${pageContext.request.contextPath }/boardCreate"
						 		  method="post">
								<label for="boardname" class="col-sm-2 control-label">게시판이름</label>
								
								<div class="col-sm-5">
									<input type="text" class="form-control" id="boardname"
										name="boardname" placeholder="게시판명" >
								</div>
								
								<div class="col-sm-3">
									<select id="use_yn" name="use_yn" class="form-control">
										<option value="Y" selected>사용</option>
										<option value="N">미사용</option>
									</select>
								</div>
								
								<div class="col-sm-2">
									<button id="createBtn" class="btn btn-default pull-right" type="button">생성</button>
								</div>
							</form>
							
							<br>
							<br>
							<hr>
							<div class="form-group">
								
								<h1>게시판 목록</h1><br>
												
								<c:forEach items="${boardList }" var="boardVO">
									<form id="mFrm" class="form-horizontal" role="form"
										  action="${pageContext.request.contextPath }/boardUpdate"
								 		  method="post">
										
										
										<input type="hidden" id="seq" name="seq" value="${boardVO.seq }"/>
										<label for="boardname" class="col-sm-2 control-label">게시판이름</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="boardname"
												name="boardname" placeholder="게시판명" value="${boardVO.name }">
										</div>
										
										<div class="col-sm-3">
											<select name="use_yn" class="form-control">
												<c:choose>
													<c:when test="${boardVO.use_yn eq 'Y'}">
														<option value="Y" selected="selected">사용</option>
														<option value="N">미사용</option>
													</c:when>
													<c:otherwise>
														<option value="Y">사용</option>
														<option value="N" selected="selected">미사용</option>
													</c:otherwise>
												</c:choose>
											</select>
										</div>
										
										<div class="col-sm-2">
											<button class="btn btn-default pull-right modifyBtn" type="button">수정</button>
										</div>
									</form>
									<br><br>
									<br>
								</c:forEach>
								
							</div>
						</div>
					</div>
					<!-- /.blog-main -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>사용자 상세조회</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
<script>
	$(document).ready(function(){
		
		$("#modBtn").on("click", function(){
			
			$("#postfrm").submit();
		}) 
		
		$("#repleBtn").on("click", function(){
			
			$("#replefrm").submit();
		})
		
		$(".answerBtn").on("click", function(){
			
			$("#answerfrm").submit();				
		})
		
		
	});
	
</script>
<style>
	.delReple {
		background: white;
		color : red;
		border: none;
	}

	.attachedfile{
		cursor: pointer;
	}	
</style>
</head>

<body>


	<!-- header -->
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid">

		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp"%>


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="row">
					<div class="col-sm-10 blog-main">
						<h2 class="sub-header">게시글 상세</h2>

						<form id="postfrm" class="form-horizontal" role="form"
							  action="${pageContext.request.contextPath }/postUpdate"
							  method="get">

							<c:set var="vo" value="${postInfos.postInfo }" scope="request"/>

							<div class="form-group">
								<input type="hidden" id="postId" name="postId" value="${vo.po_seq }"/>
								<input type="hidden" id="boardSeq" name="boardSeq" value="${vo.seq }"/>
								<input type="hidden" id="userId" name="userId" value="${vo.userId }"/>
							</div>
							
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" id="" 
										   value="${vo.title}" readonly="readonly" style="width:550px;"/>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-3">
									<label for="userNm" class="col-sm-8 control-label">첨부파일</label>
								</div>
								<div class="col-sm-7">
									<c:forEach items="${postInfos.fileList }" var="file" >
										<a href="${pageContext.request.contextPath }/file?fileSeq=${file.file_seq}">
											<label class="control-label attachedfile">${file.filename}</label>
										</a> <br>
										<input type="hidden" value="${file.file_seq }"/>									
									</c:forEach>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">내용</label>
								<div class="col-sm-10">
<%-- 									<textarea cols="75" rows="10" contenteditable="false" readonly="readonly">${vo.content }</textarea> --%>
									${vo.content }
								</div>
							</div>
						</form>

						<div class="form-group">
							<c:choose>
								<c:when test="${USER_INFO.userId eq vo.userId }">
									<div class="col-sm-offset-6 col-sm-1">
										<button id="modBtn" type="button" class="btn btn-default">수정</button>
									</div>
									<div class="col-sm-1">
										<a href="${pageContext.request.contextPath }/postDelete?postId=${vo.po_seq}&boardSeq=${vo.seq}">
											<button id="delBtn" type="button" class="btn btn-default">삭제</button>
										</a>
									</div>
									<div class="col-sm-1">
										<button type="button" class="btn btn-default answerBtn">답글</button>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-sm-offset-8 col-sm-1">
										<button type="button" class="btn btn-default answerBtn">답글</button>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<form id="answerfrm" role="form"
							  action="${pageContext.request.contextPath }/answer"
							  method="get">
							<input type="hidden" name="parentSeq" value="${vo.po_seq }"/>
							<input type="hidden" name="boardSeq" value="${vo.seq }"/>
							<input type="hidden" name="groupSeq" value="${vo.group_seq }"/>
						</form>
						
						<div class="col-sm-10 form-group">
						</div>
												
						<div class="form-group">
							<label for="userNm" class="col-sm-offset-1 col-sm-10 control-label">댓글</label>
						</div>
						<div class="form-group">
							<c:forEach items="${replyList }" var="reply">
								<c:choose>
									<c:when test="${reply.delete_yn eq 'N' }">
										<div class="col-sm-offset-1 col-sm-5">
											<label class="col-sm-offset-1 control-label" id="repleContent" >${reply.content}</label>
										</div>
										<div class="col-sm-offset-0 col-sm-2">
											<label for="userNm" class="col-sm-10 control-label" id="repleInfo">[${reply.userId} / ${reply.wt_dt}]</label>
										</div>
										<c:if test="${reply.userId eq USER_INFO.userId }">
											<div class="col-sm-offset-0 col-sm-1">
												<a href="${pageContext.request.contextPath }/repleDelete?postId=${vo.po_seq }&boardSeq=${vo.seq }&reSeq=${reply.re_seq}">
												<button type="button" class="delReple">x</button>
												</a>
											</div>
										</c:if>
									</c:when>
									<c:otherwise>
										<div class="col-sm-offset-1 col-sm-10">
											<label class="col-sm-offset-1 control-label">삭제된 댓글입니다. </label>
										</div>
										<div class="col-sm-offset-1 col-sm-10">
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						
						<div class="col-sm-10 form-group">
						</div>
						<form id="replefrm" class="form-horizontal" role="form"
						  action="${pageContext.request.contextPath }/reple"
						  method="get">
							<div class="form-group">
								<div class="col-sm-offset-1 col-sm-6">
									<input class="col-sm-offset-1 form-control" type="text" id="repleContent" 
										   name="repleContent" style="width:450px;"/>
									<input type="hidden" name="boardSeq" value="${vo.seq }"/>
									<input type="hidden" name="postId" value="${vo.po_seq}"/>
									<input type="hidden" name="userId" value="${USER_INFO.userId }"/>
								</div>
								<div class="col-sm-offset-0 col-sm-1">
									<button id="repleBtn" type="button" class="btn btn-default">댓글저장</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
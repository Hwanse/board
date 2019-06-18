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

<title>사용자 상세조회</title>
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>   
<script src="${pageContext.request.contextPath }/SE2/js/HuskyEZCreator.js"></script>
<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>

<script>
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
	$(document).ready(function(){
		
		// Editor Setting
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors, // 전역변수 명과 동일해야 함.
			elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
			sSkinURI : "${pageContext.request.contextPath }/SE2/SmartEditor2Skin.html", // Editor HTML
			fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true, 
			}
		});

		
		$("#addFileBtn").on("click",function(){
			if($(".files").length < 5){
				$("#attatchedFile").append("<input type='file' class='files' name='files' style='width:200px; display:inline-block;'><button type='button' class='cancelBtn deleteBtn'>x</button>");
			} 
			
		})
		
		$("#modBtn").on("click", function(){
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#postfrm").submit();
			}
		}) 
		
		
		$(document).on("click",".cancelBtn",function(){
			$(this).prev().remove();
			$(this).remove();
		})	
		$(".existingFileDelBtn").on("click", function(){
			$(this).prev().prev().remove();
			$(this).prev().remove();
			$(this).next().remove();
			$(this).remove();
		})
		
	});
	
	// 필수값 Check
	function validation(){
		var contents = $.trim(oEditors[0].getContents());
		if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}

		return true;
	}
	
</script>
<style>
	.files{
		margin-bottom: 10px;
	}
	
	.deleteBtn{
		background:none;
		color:red; 
		border:none;
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
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 상세</h2>

						<form id="postfrm" class="form-horizontal" role="form"
							  action="${pageContext.request.contextPath }/postUpdate"
							  method="post" enctype="multipart/form-data">

							<c:set var="vo" value="${postInfos.postInfo }" scope="request"/>

							<div class="form-group">
								<input type="hidden" id="postId" name="postId" value="${vo.po_seq }"/>
								<input type="hidden" id="boardSeq" name="boardSeq" value="${vo.seq }"/>
								<input type="hidden" id="postId" value="${vo.userId }"/>
							</div>
							
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<input class="form-control" type="text" id="title" name="title" 
										   value="${vo.title}" style="width:550px;"/>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-3">
									<label for="userNm" class="col-sm-8 control-label">첨부파일</label>
								</div>
								<div class="col-sm-5" id="attatchedFile">
									<c:forEach items="${postInfos.fileList }" var="file" >
										<label class="control-label files" style="margin-bottom:10px;">${file.filename}</label>
										<input type="hidden" name="existingfiles" value="${file.file_seq }"/>
										<button type="button" class="existingFileDelBtn deleteBtn">x</button><br>
<%-- 										<input type="hidden" value="${file.file_seq }"/>							 --%>
<%-- 										<input type="hidden" name="filepath" value="${file.path }"/>									 --%>
									</c:forEach>
								</div>

								<div class="col-sm-1">
									<button type="button" class="btn btn-default" id="addFileBtn">파일추가</button>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">내용</label>
								<div class="col-sm-10">
									<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:600px; height:412px;">${vo.content }</textarea> 
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-8 col-sm-1">
									<button id="modBtn" type="button" class="btn btn-default">수정</button>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>게시글 등록</title>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<%@include file="/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>    


<script src="${pageContext.request.contextPath }/SE2/js/HuskyEZCreator.js"></script>
<script>
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
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

	// 전송버튼 클릭이벤트
	$("#answerRegBtn").click(function(){
		if(confirm("등록하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
	
	$(document).on("change", ".files", function(){
		if($(".files").length < 5){
			$(this).after("<br><input type='file' class='files' name='files'/>");
		}
		
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
	
function isEmpty(item, msg, isTrim){
	if($(item) == null){
		alert("검사하는 항목이 존재하지 않습니다.");
		return true;
	}
	if(isTrim) $(item).val($(item).val().trim());
	
	if($(item).val().length == 0){
		alert(msg);
		$(item).focus();
		return true;
	}
	
	return false;
}
</script>
<style>
	.form-horizontal .control-label {
	    padding-top: 7px;
	    margin-bottom: 10px;
	    text-align: center;
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
					<div class="col-sm-6 blog-main">
						<h2 class="sub-header">게시글 등록</h2>

						<form id="frm" class="form-horizontal" role="form"
							  action="${pageContext.request.contextPath }/answer"
							  method="post" enctype="multipart/form-data">
						
							<input type="hidden" name="parentSeq" value="${parentSeq }"> 
							<input type="hidden" name="boardSeq" value="${boardSeq }"/>
							<input type="hidden" name="groupSeq" value="${groupSeq }"/>
							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">게시글 제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title"
										name="title" placeholder="게시글 제목 입력.." >
								</div>
							</div>

							<textarea name="smarteditor" id="smarteditor" rows="10" cols="100" style="width:740px; height:412px;"></textarea> 
							
							<br>
							<br>
							<div class="form-group">
								<label for="profile" class="col-sm-2 control-label">첨부 파일</label>
								<div class="col-sm-9">
									<input type="file" class="files" name="files"/>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-5">
									<button id="answerRegBtn" type="button" class="btn btn-default pull-right">게시글 등록</button>
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
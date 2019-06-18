<%@page import="kr.or.ddit.user.service.UserServiceImpl"%>
<%@page import="kr.or.ddit.user.service.IUserService"%>
<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.ZoneOffset"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>사용자 정보 수정</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>

	// 주소찾기 버튼 클릭 이벤트 처리
	$(document).ready(function(){
		
// 		if($("#msg").val() != ""){
// 			alert($("#msg").val());
// 		}
		
		
		$("#addrSearchBtn").on("click",function(){
			 new daum.Postcode({
		        oncomplete: function(data) {
					
		        	//주소 input value에 설정 data.roadAddress
		        	//우편번호 input value에 설정 data.zonecode
		        	console.log(data);
		        	$("#addr1").val(data.roadAddress);
		        	$("#zipcd").val(data.zonecode);
		        	
		        }
		    }).open();
		});

		// 사용자 등록버튼 클릭 이벤트 핸들러
		$("#userModBtn").on("click", function(){
			//유효성 체크
			if(isEmpty("#name", "이름을 입력해주세요.",1)){
				return;
			}
			
			if(isEmpty("#alias", "별명을 입력해주세요.")){
				return;
			}
			if(isEmpty("#pass", "비밀번호를 입력해주세요", 1)){
				return;
			}
			//여기까지 도달하면 유효성 검사완료(submit)
			$("#frm").submit();
		});
		
	});
	
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
	
	function dataInit(){
		$("#userId").val("userTest");		
		$("#name").val("고양이");		
		$("#alias").val("키우고싶다");		
		$("#addr1").val("대전광역시 동구 홍도동");		
		$("#addr2").val("빌딩");		
		$("#zipcd").val("14125");		
		$("#birth").val("2019-05-31");		
		$("#pass").val("userTest1234");		
	}
	
</script>
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
						<h2 class="sub-header">사용자 수정</h2>

						<form id="frm" class="form-horizontal" role="form"
							  action="${pageContext.request.contextPath }/userModify"
							  method="post" enctype="multipart/form-data">

							<div class="form-group">
								<label for="profile" class="col-sm-2 control-label">사용자 사진</label>
								<div class="col-sm-10">
									<input id="file" type="file" name="profile"/>
								</div>
							</div>

							<div class="form-group">
								<label for="userId" class="col-sm-2 control-label">사용자아이디</label>
								<div class="col-sm-10">
									<label class="control-label" >${vo.userId}</label>
								</div>
								<input type="hidden" class="form-control" id="userId"
										name="userId" placeholder="사용자 이름" value="${vo.userId }"/>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자이름</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="name"
										name="name" placeholder="사용자 이름" value="${vo.name }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="alias" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="alias"
										name="alias" placeholder="별명" value="${vo.alias }">
								</div>
							</div>

							<div class="form-group">
								<label for="addr1" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="addr1"
										name="addr1" placeholder="주소" readonly value="${vo.addr1 }">
								</div>

								<div class="col-sm-2">
									<button id="addrSearchBtn" type="button" class="btn btn-default pull-right">주소검색</button>
								</div>

							</div>

							<div class="form-group">
								<label for="addr2" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="addr2"
										name="addr2" placeholder="상세주소" value="${vo.addr2 }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="zipcd" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="zipcd"
										name="zipcd" placeholder="우편번호" readonly value="${vo.zipcd }">
								</div>
							</div>

							<div class="form-group">
								<label for="birth" class="col-sm-2 control-label">생일</label>
								<div class="col-sm-10">
									<input type="date" class="form-control" id="birth"
										name="birth" placeholder="생일" value="${birth}">
								</div>
							</div>

							<div class="form-group">
								<label for="pass" class="col-sm-2 control-label">비밀번호</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="pass"
										name="pass" placeholder="비밀번호" value="${vo.pass }">
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="userModBtn" type="button" class="btn btn-default">사용자 정보 수정</button>
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

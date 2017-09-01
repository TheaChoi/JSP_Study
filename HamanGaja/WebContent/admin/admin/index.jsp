<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<link href="../css/mystyle.css" rel="stylesheet">
	<link href="../css/font-awesome.min.css" rel="stylesheet">
	<script>
		function logcheck() {
			if(myform.id.value=="") {
				alert("아이디입력");
				myform.id.focus();
				return;
			}
			if(myform.pw.value=="") {
				alert("패스워드를 입력하시오");
				myform.pw.focus();
				return;
				}
			myform.submit();
		}
	</script>
	<%
		String realpath = request.getRealPath("");
		System.out.println("톰캣이 실제 사용하는 경로:"+realpath);
	%>
</head>
<body>
	<header class="header">
		<a href="#" class="btn_gnb" id="menu-toggle"><i class="fa fa-navicon headericon"></i></a>
		<h1 class="logo"><span>함안</span>가자</h1>
		<a href="#" class="btn_search"><i class="fa fa-search headericon"></i></a>
		<div class="bar-search">
			<form name="mysearch" action="List.board">
				<div class="inner">
				
					<label for="search" class="readonly">검색단어 입력</label>
					<input type="text" name="search" class="search" id="search" placeholder="찾고 싶은 정보를 검색하세요">
					<input type="hidden" name="pageNo" value="0">  
					<input type="hidden" name="pageSize" value="5">
					<input type="hidden" name="type" value="관광지">
					<input type="hidden" name="viewStart" value="0">
					<input type="hidden" name="viewEnd" value="5">
<!-- 					hidden 태그는 보이지 않지만 파라미터로 넘어간다. form태그는 파라미터로 값이 안넘어감. -->
					
				
				</div>
				<input type="submit" value="search" name="search" style="width:50px;">
			</form>
		</div>
	</header>
	<%@ include file="../sidebar-nav.jsp" %>
	<!-- sub contents start -->
	<div class="loginbox">
		<p>
			<span class="p1">"아름다운 경남의 푸른 고장"</span>
			<span class="p2">회원가입 하시고
			"<span class="p3">함안가자 쿠폰</span>" <br>받아 가세요</span>
		</p>
		<h3 class="readonly">관리자로그인</h3>
		<form name="myform" method="post" action="login.admin" onsubmit="logcheck();">
			<ul class="login pdt20">
				<li>
					<label for="idd" class="readonly">아이디 입력</label><input type="text" name="id" placeholder="&#xf2c1;  Id" id="idd" title="아이디를 입력해주세요" style="font-family:Arial,FontAwesome">  
				</li><!--label의 for은 라벨을 클릭했을때 이동하고자 하는 위치(input의 id). input의 name은 기억장소의 이름-->
				<li>
					<label for="pww" class="readonly">패스워드 입력</label><input type="password" name="pw" placeholder="&#xf084;  Password" id="pww" title="비밀번호를 입력해주세요" style="font-family:Arial,FontAwesome">
				</li>
			<!--   <lababel for="fff">업로드 파일 선택</label><input type="file" name="upfile" id="fff">  //  <form enctype="multipart/form-data"> -->
			
				<li><input class="loginBtn" type="submit" value="로그인"></li>
				<li><a href="../index.html">돌아가기</a></li>
				<li><a href="member-search.html">아이디/패스워드 찾기</a></li>
			</ul>
		</form>
		
	</div>
	<!-- sub contents end -->
	
	<footer class="footer">
		<p>COPYRIGHT  JANGSEM ALL RIGHTS RESERVED</p>
	</footer>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#slidebar-wrapper").toggleClass("active");
		});
		$("#menu-close").click(function(e) {
			e.preventDefault();
			$("#slidebar-wrapper").toggleClass("active");
		});
		$(".btn_search").click(function() {
			$(".bar-search").toggle();
		});
	</script>
</body>
</html>

















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/mystyle.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	
	<script>
		function logcheck() {
			if(myform.id.value=="") {
				alert("아이디입력");
				myform.id.focus();
				
			}
			else if(myform.pw.value=="") {
				alert("패스워드를 입력하시오");
				myform.pw.focus();
				
			}else{
				myform.submit();
				
// 				<!--return true 아님; -->
			}
			
			
		}
	</script>
</head>
<body>
	<header class="header">
		<a href="#" class="btn_gnb" id="menu-toggle"><i class="fa fa-navicon headericon"></i></a>
		<h1 class="logo"><span>함안</span>가자</h1>
		<a href="#" class="btn_search"><i class="fa fa-search headericon"></i></a>
		<div class="bar-search">
			<div class="inner">
				<form name="mysearch">
					<label for="search" class="readonly">검색단어 입력</label>
					<input type="text" name="search" class="search" id="search" value="찾고 싶은 정보를 검색하세요">
				</form>
			</div>
		</div>
	</header>
	<%@ include file="sidebar-nav.jsp" %>
	
	<!-- sub contents start -->
	<div class="loginbox">
		<p>
			<span class="p1">"아름다운 경남의 푸른 고장"</span>
			<span class="p2">회원가입 하시고
			"<span class="p3">함안가자 쿠폰</span>" <br>받아 가세요</span>
		</p>
		<h3 class="readonly">회원로그인</h3>
		<form name="myform" method="post" action="login.mem" onsubmit="logcheck(); return false;">
			<ul class="login pdt20">
				<li>
					<label for="idd" class="readonly">아이디 입력</label><input type="text" name="id" placeholder="&#xf2c1;  Id" id="idd" title="아이디를 입력해주세요" style="font-family:Arial,FontAwesome">  
				</li><!--label의 for은 라벨을 클릭했을때 이동하고자 하는 위치(input의 id). input의 name은 기억장소의 이름-->
				<li>
					<label for="pww" class="readonly">패스워드 입력</label><input type="password" name="pw" placeholder="&#xf084;  Password" id="pww" title="비밀번호를 입력해주세요" style="font-family:Arial,FontAwesome">
				</li>
			<!--   <label for="fff">업로드 파일 선택</label><input type="file" name="upfile" id="fff">  //  <form enctype="multipart/form-data"> -->
				
				<li><input type="submit" value="로그인"></li> 
				<!-- submit으로 넘길때 함수 적용하기 :  
				form태그에 onsubmit="함수; return false";
				서브밋 성공하면 바로 액션, 실패하면 액션 작동 안됨"-->
				<li><a href="join.jsp">회원가입</a></li>
				<li><a href="member-search.jsp">아이디/패스워드 찾기</a></li>
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

















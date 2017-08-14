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
</head>
<body>
	<header class="header">
		
		<h1 class="logo"><span>함안</span>가자</h1>

	
	</header>
	
	<!-- sub contents start -->
	<div class="loginbox">
		<p>
			<span class="p1">"아름다운 경남의 푸른 고장"</span>
			<span class="p2">회원가입 하시고
			"<span class="p3">함안가자 쿠폰</span>" <br>받아 가세요</span>
		</p>
		<h3 class="readonly">관리자로그인</h3>
		<form name="myform" method="post" action="main.html">
			<ul class="login pdt20">
				<li>
					<label for="idd" class="readonly">아이디 입력</label><input type="text" name="id" placeholder="&#xf2c1;  Id" id="idd" title="아이디를 입력해주세요" style="font-family:Arial,FontAwesome">  
				</li><!--label의 for은 라벨을 클릭했을때 이동하고자 하는 위치(input의 id). input의 name은 기억장소의 이름-->
				<li>
					<label for="pww" class="readonly">패스워드 입력</label><input type="password" name="pw" placeholder="&#xf084;  Password" id="pww" title="비밀번호를 입력해주세요" style="font-family:Arial,FontAwesome">
				</li>
			<!--   <lababel for="fff">업로드 파일 선택</label><input type="file" name="upfile" id="fff">  //  <form enctype="multipart/form-data"> -->
			
				<li><a onClick="javascript:logcheck();">로그인</a></li>
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

















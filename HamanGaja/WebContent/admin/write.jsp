<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="../css/mystyle.css" rel="stylesheet">
	<link href="../css/font-awesome.min.css" rel="stylesheet">
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
	<%@ include file="../sidebar-nav.jsp" %>
	<!-- sub contents start -->
	<div class="adminbox">
		<h2 class="readonly">관광지 글쓰기</h2>
		<form name="myform" method="post" action="member.html">
				<ul class="admin pdt20">
					<li>
						<label for="idd" class="readonly">여행지 입력</label>
						<input type="text" name="id" placeholder="여행지 입력" id="idd" title="여행할 장소의 이름을 입력해주세요" style="font-family:Arial,FontAwesome">  
					</li>
					<li>
						<label for="idd" class="readonly">여행지 소개 입력</label>
						<textarea name="content" style="width:100%; height:200px;">
						</textarea>
					</li>
					<li>
						<label for="pic" class="filebox">사진업로드파일</label>
						<input type="file" name="pic" id="pic">
					</li>
					<li>
						<button type="button" onClick="javascript:check();" class="ok">확인</button>
						<button type="button" onClick="javascript:history.back();" class="cancel">취소</button>
					</li>
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

















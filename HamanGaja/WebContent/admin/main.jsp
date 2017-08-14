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
	<h2 class="readonly">관광지목록</h2>
		<table class="list">
			<caption class="hidden">관광지 목록으로 번호, 제목, 작성일, 조회수를 보여줌</caption>
			<colgroup>
				<col width="10%">
				<col width="*">
				<col width="30%">
				<col width="10%">
			</colgroup>
			<thead>
				<th scope="col">번호</th>
				<th scope="col">관광여행지</th>
				<th scope="col">작성일</th>
				<th scope="col">조회수</th>
			</thead>
			<tbody>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
				<tr>
					<td>43</td>
					<td>강주마을</td>
					<td>2017-05-27</td>
					<td>120</td>
				</tr>
			</tbody>
		</table>
		
		<div class="paging">
			<a href="#">
					<i class="fa fa-angle-left"></i>
				</a>
			<strong>1</strong>
			<a href="#">2</a>
			<a href="#">3</a>
			<a href="#">4</a>
			<a href="#">5</a>
			<a href="#">
				<i class="fa fa-angle-right"></i>
			</a>
			<div class="write">
				<a href="write.html">글쓰기</a>
			</div>
		</div>
		
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

















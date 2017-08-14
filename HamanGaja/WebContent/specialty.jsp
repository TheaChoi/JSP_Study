<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
	<link href="css/mystyle.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
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
	<div class="sub-title-group">
		<h2 class="sub-title1">함안가자 특산물</h2>
	</div>
	<div class="sub-contents">
		<ul>
			<li>
				<a href="#">
				<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/s1.jpg" alt="곶감">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							곶감
						</span>
						<span class="sub-content">
							타닌성분(설사를 멎게 하고 배탈을 낫게 함), 칼륨성분, 비타민 C 등이 다량 함유된 식품이다 군내의 명물인 파수곶감이 함안면 파수에서 생산돼 왔다. 
						</span>
					</div>
				</div>
				</a>
				
			</li>
			<li>
				<a href="#">
				<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/s2.jpg" alt="메주">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							메주
						</span>
						<span class="sub-content">
							순수 우리콩으로 전통 재래방법으로 만들고 있으며 된장을 만들었을 때 더욱 고소한 맛을 즐길 수 있음
						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/s3.jpg" alt="파프리카">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							파프리카
						</span>
						<span class="sub-content">
							파프리카는 채소류 중 열매를 먹는 과채류에 속하며 흔히 볼 수 있는 피망과 같은 식물이다. 노란색, 자주색, 백색 등 다양한 색을 가지고 있다. 
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/s4.jpg" alt="함안수박">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							함안수박
						</span>
						<span class="sub-content">
							수박씨를 제외한 수박을 쌀과 엿기름으로 당화시켜 조청을 만든 다음 갱엿이 될 때까지 농축을 시켜 엿을 만들어 엿이 이빨에 붙지 않으면서 단맛이 부드러움
						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/s5.jpg" alt="감식초">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							감식초
						</span>
						<span class="sub-content">
						감 100%이며 장기간 자연발효 숙성되어 각종 유기산이 풍부하며 순한 맛을 지니고 있는 고급식초입니다. 
보존료, 방부제 등을 일체 사용하지 않습니다.
						</span>
					</div>
				</div>
				</a>
			</li>
		</ul>
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
			<div class="top">
				<a href="#top"><i class="fa fa-chevron-up"></i><br>맨 위로</a>
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

















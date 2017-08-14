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
		<h2 class="sub-title1">함안가자 맛집</h2>
	</div>
	<div class="sub-contents">
		<ul>
			<li>
				<a href="#">
				<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/r1.jpg" alt="국보삼계탕">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							국보삼계탕
						</span>
						<span class="sub-content">
							뼈가 녹을 만큼 푹 고와서 고기가 연하고 부드러운 함안 전통 삼계탕집.
복날이 되면 항상 사람이 붐비는 곳.

						</span>
					</div>
				</div>
				</a>
				
			</li>
			<li>
				<a href="#">
				<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/r2.jpg" alt="아기돼지">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							아기돼지
						</span>
						<span class="sub-content">
							이름부터 아기돼지! 워낙 유명해 말이 필요 없는 고기와 칼국수, 못 먹어봤다면 당장 먹어봐야 할 맛집!
						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/r3.jpg" alt="한마음 식당">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							한마음 식당
						</span>
						<span class="sub-content">
							임금님의 수라상에 오르던 참게! 참게는 특유의 독특한 향기와 고소한 맛으로 한국인의 대표적인 기호식품으로 손꼽힌다. 
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/r4.jpg" alt="아라한우">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							아라한우
						</span>
						<span class="sub-content">
							 이곳은 화학조미료와 설탕, 물엿 등을 사용하지 않고 직접 제조한 맛간장과 새우표가루를 사용하여 ...
						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/r5.jpg" alt="대구식당">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							대구식당
						</span>
						<span class="sub-content">
							아담하지만 깔끔한 내부는 공간을 잘 활용한 인테리어로 편안한 식사가 가능하다. 
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

















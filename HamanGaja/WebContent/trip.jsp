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
		<h2 class="sub-title1">함안가자 여행지</h2>
	</div>
	<div class="sub-contents">
		<ul>
			<li>
				<a href="trip-view.html">
				<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/trip1.jpg" alt="입곡마을">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							입곡마을
						</span>
						<span class="sub-content">
							함안군 산인면에는 뱀이 기어가듯, 구불구불 흐르는 입곡저수지가 있다. 저수지 상류에는 자연생태 그대로 보존된 ...
						</span>
					</div>
				</div>
				</a>
				
			</li>
			<li>
				<a href="#">
				<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/trip2.jpg" alt="꽃양귀비 거리">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							꽃양귀비 거리
						</span>
						<span class="sub-content">
							새발자국화석이 발견된 곳에는 '함안한국새(Koreanaornis hamanensis)'라고 이름 붙여진 새발자국과 진동새발자국 및 초식..
						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/trip3.jpg" alt="입곡저수지">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							입곡 저수지
						</span>
						<span class="sub-content">
							관군들은 돌아가는 길에 그의 덕을 칭송하여 마을 입구에 '창의사적비(倡義事蹟碑)'를 세우고 서당 앞 넓은 마당에 연못을 ...

						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/trip4.jpg" alt="해바라기 공원">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							해바라기 공원
						</span>
						<span class="sub-content">
							광주 안씨가 정착하면서 풍수지리에 근거하여 후손의 번창을 위해 늪지대를 보존하여 왔기 때문에 지금까지...
						</span>
					</div>
				</div>
				</a>
			</li>
			<li>
				<a href="#">
					<div class="sub-table">
					<div class="sub-table-cell1">
						<img src="images/trip5.jpg" alt="강주벽화거리">
					</div>
					<div class="sub-table-cell2">
						<span class="sub-title2">
							강주벽화거리
						</span>
						<span class="sub-content">
							오랜 세월동안 조상들의 관심과 보살핌 속에 자라왔으며, 문화적 · 생물학적 자료로서의 가치가 높아 천연기념물로 지정하여..
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

















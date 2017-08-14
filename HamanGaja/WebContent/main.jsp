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
	
	<section class="main_visual">	
		<header>
			<h2 class="readonly">main visual</h2>
		</header>
		<div class="inner">
			<div class="backgroundchange">
				<div class="backgroundimg" id="back1"></div>
				<div class="backgroundimg" id="back2"></div>
				<div class="backgroundimg" id="back3"></div>
				<div class="backgroundimg" id="back4"></div>
				<div class="backgroundimg" id="back5"></div>
			</div>
		</div>
	</section>
	<section class="rec_cont">
		<h2>이달의 추천 여행지</h2>
		<div class="rec_cont_txt">
			<p class="rec_cont_p">
				  <span>강주 해바라기 축제</span><br><br>
				함안 법수산권역의 마을 주민들이 힘을 합쳐 자체적으로 조성한 
				해바라기 꽃이 장관을 이루는 법수산권역 강주 해바라기 축제가 올해로 4회째를 맞는다.
				 전체 해바라기 식재면적은 법수면 일대 8만㎡에 이르며 전통과 문화를 부흥시켜 보자는
				 취지로 마을마다 지닌 끼를 모아 축제 한마당도 함께 펼쳐진다.올해 해바라기 축제는 남해 고속도로변과 가야읍 함주공원 등지에서 출발하는 셔틀버스를 운행해 축제장을 찾는 관광객들의 불편을 최대한 줄일 방침이다. 

아울러 함안군은 축제기간에 맞춰 해바라기 축제현장과 법수 악양둑, 함주공원내 연꽃테마공원, 함안박물관, 말이산 고분군을 연계하는 관광코스도 마련한다. 

한편 올해는 보다 풍성한 축제를 마련하기 위해 축제입장권을 발행한다.
			</p>
			<p class="more"><a href="trip-view2.html">더보기 <i class="fa fa-arrow-right moreicon"></i></a></p>
		</div>
	</section>
	<section class="best_cont">
		<h2>Best 여행지</h2>
		<ul>
			<li><a href="#"><img src="images/h1.jpg" alt="강주마을" class="imgw50" ><span>강주마을</span><p>해바라기와 벽화가 아름다운 함안의 대표적 고장. 함안은 전국 최대의 수박 주산지로 우선 수박과 관련된 가공식품인 엿, 주스, 잼, 조청 등 관광상품 개발에 좋은 여건을 갖추고 있지만 변화에 미온적이라는 게 대체적인 평가다.</p></a></li>  <!--class="imgw100"-->
			<li><a href="#"><img src="images/h2.jpg" alt="입곡다리" class="imgw50"><span>입곡다리</span><p>유서깊은 입곡 저수지을 가로지르는 입곡 다리. 볼거리의 하나로 일선 학교에 흩어져 있는 조류, 동물 박제품과 천연기념물 큰소쩍새 ,독수리, 원앙, 황조롱이, 수리부엉이 등이 있다.</p></a></li>
			<li><a href="#"><img src="images/h3.jpg" alt="악양루꽃길" class="imgw50"><span>악양루 꽃길</span><p>아름다운 꽃양귀비와 들꽃이 지천으로 피어있는 악양루 꽃길. 이와 더불어 함안에서 서식하는 나비, 벌 등 각종 곤충을 한곳에 모아 관람할 수 있다</p></a></li>
			<li><a href="#"><img src="images/h4.jpg" alt="악양저수지"class="imgw50"><span>악양 저수지</span><p>악양루의 자랑 아름다운 숲과 강이 어우러진 함안의 저수지. 함안을 방문하는 바이어와 고객들이 관광지를 돌아보고 지역을 자랑할 수 있는 특색 있는 볼거리와 더불어 음식명소를 개발하고 있다</p></a></li>
		</ul>
	</section>
	<section class="tab_bar">
		<div class="tab_cont">     		<!--article로 하니 h2~h6쓰라는 경고가 나네 -->
			<header>
				<h2 class="readonly">탭메뉴</h2>
			</header>
			<ul>
				<li class="active"><a href="#">전체 메뉴 보기</a></li>
				<li><a href="#">전체 리뷰 보기</a></li>
			</ul>
		</div>
	</section>
	<footer class="footer">
		<p>COPYRIGHT HAMANGAJA RIGHTS RESERVED</p>
	</footer>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	jQuery(document).ready(function() {

	// slide navigation
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

// 	<!--tab bar -->
	
// 		<!-- $(".tab_cont .tab_links  a").on("click",function(e) { -->
// 			<!-- var currentAttrvalue = $(this).attr("href"); -->
// 			<!-- $(currentAttrvalue).show().siblings().hide(); -->
// 			<!-- $(this).parent('li').addClass('active').siblings().removeClass('active'); -->
// 			<!-- e.preventDefault(); -->
// 		<!-- }); -->
// 	<!-- --> 
	
	});
	</script>
</body>
</html>
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
	<div class="one-contents">
		<img src="images/k9.jpg" alt="해바라기축제">
		<div class="one-contents-p">
			<span>강주 해바라기 축제</span>
			<p>더운 여름 날씨에 지친 분들을 위한
이색적인 여름축제!

예쁜 꽃들과 함께한다면
더위가 싹 날라가지 않을까요?

노란물결로 가득한 함안으로 함께 가보시죠!

봄이면 벚꽃 가을이면 코스모스,
그런데 여름을 대표하는 꽃 어떤 꽃일까요?
제 뒤로 펼쳐져 있는 노란 해바라기인데요.
함안은 지금 노랗게 물들이는 해바라기가
장관이라고 합니다. 함께 가보시죠!
 
콜롬버스가 신대륙을 발견한 후 유럽으로 전해져
태양과 가장 관련이 깊은
여름을 대표하는 꽃, 해바라기!<br><br>

하늘높이 자라있는 해바라기 밭을 품고 있는
이곳은 함안군 법수면 윤외리인데요.

해바라기로 유명한 <함안 강주마을>과 인접한
이곳에는개인이 운영하는 깡수농장이라는
해바라기밭이 자리하고 있습니다.
마을 입구를 지나 길을 따라 들어서면
끝없이 펼쳐진 노란물결이 모습을 드러냅니다.

특히 해바라기가 만개한 깡수마을은
지나가는 관광객과 사진작가들, 그리고 어린이들의
자연학습장으로도 잘 활용되고 있는데요.

해바라기를 카메라에 담으며 여름 정취를 만끽합니다.<br><br>


특히 바로 옆 함안군 법수면 강주리 강주문화마을은
5월이면 청보리밭으로 유명하지만
8월에서 9월에는 해바라기 축제가 펼쳐져
관광객들이 찾는 곳인데요.

마을 주민들이 직접 해바라기를 심기 시작해
3년 전부터 열린 축제는 지난해 60여만송이의
꽃을 피워 전국 7만여명의 관광객들이 찾아
전국적인 명소가 되었다고 합니다.

강주마을 담벼락에도 탐스러운 해바라기가
가득 피어있습니다.<br><br>

마을 입구를 지나 길을 따라 들어서면
알록달록 벽화들이 나타나는데요.
강주마을은 벽화마을로 유명한 곳이기도 하다는 사실!
마을 담벽 곳곳에는 동화 속 세상 같은
예쁜 벽화가 그려져 또 다른 볼거리를 제공합니다.<br><br>

이번 강주문화 마을 해바라기는 파종시기가 늦어서
꽃이 무더기로 피어나는 장관을 보려면,
8월말까지 조금 더 시간을 기다려야 한다고 합니다.

			</p>

			<div class="sns-box">
				<span><i class="fa fa-thumbs-o-up"></i>132</span>
				<span><i class="fa fa-share-alt"></i></span>
				<span><i class="fa fa-map-marker"></i></span>
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

















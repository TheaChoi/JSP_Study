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
		<img src="images/main2.jpg" alt="">
		<div class="one-contents-p">
			<span>입곡마을</span>
			<p>함안군 산인면에는 뱀이 기어가듯, 구불구불 흐르는 입곡저수지가 있다. 저수지 상류에는 자연
				태 그대로 보존된 '입곡군립공원'이 형성돼 있어, 군민과 시민들에게 쉼터를 제공하고 있다. <br><br>

					일제시대에 농업용수로 사용하기 위해 협곡을 가로막은 입곡저수지는 폭 4km에, 저수지 양 끝이 보이지 않을
					 만큼 제법 큰 규모를 자랑한다. 저수지를 중심으로 왼편에는 깎아지른 절벽에 우거진 송림이, 오른편으로는 완만한 경사지에 활엽수림과 침엽수림이 멋진 조화를 이룬다. 

				크고 작은 산봉우리들이 저수지를 중심으로 협곡을 이루고 있는 이곳에는 수려한 자연풍광과 함께 전설을 간
				직하고 있는 형형색색의 바위와 기암절벽이 그대로 보존돼 있어, 신비로움을 더한다. <br><br>

				공원 입구에 들어서면 은빛으로 반짝이는 저수지가 제일 먼저 눈에 들어온다. 저수지 중앙을 가로 지르는 길
				이 112m, 폭 1.5m의 출렁다리를 건너 산책로 일주는 그야말로 일품이다. 버드 나뭇잎이 수면에 길게 늘어져 있고, 
				이름모를 꽃과 나무들이 저수지를 끼고 산책로를 따라 둘러쳐져 있다. 간혹 백로가 수려한 자태를 뽐내기도 하는데, 한 폭의 그림이 따로 없다. <br><br>

				산책로를 돌아나와 운동장에 들어서면 절벽쪽에 인공폭포가 설치되어 있다. 인공폭포는 높이 35m(2단)와
					 높이 10m(1단) 2개소가 설치 되어 있으며, 하절기에 주로 가동되고 있어 여름철에도 방문객이 끊이지 않는 곳이다.
			</p>

			<div id="map" class="map">
				<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d6515.262013360132!2d128.44912327301512!3d35.265434598770824!3m2!1i1024!2i768!4f13
				.1!3m3!1m2!1s0x0%3A0x289f3a7588c6200e!2z7J6F6rOh6rWw66a96rO17JuQ!5e0!3m2!1sko!2skr!4v1496278299055"
				width="400" height="300" frameborder="0" style="border:0" allowfullscreen>
				</iframe>
			</div>
					
			<div class="sns-box">
				<div class="list">
					<a href="javascript:history.back();">목록</a>
				</div>
				<div class="sns-icon">
					<span><a><i class="fa  fa-thumbs-o-up"></i>&nbsp;&nbsp;</a> 287&nbsp;<i class="fa   fa-eye">&nbsp;&nbsp;320</i></span>
				</div>
				<div class="top">
					<a href="#top"><i class="fa fa-chevron-up"></i><br>맨 위로</a>
				</div>
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
		
		$("#map").click(function() 
			$(".map").show();
		}
	</script>


 
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ko"></script>

</body>
</html>

















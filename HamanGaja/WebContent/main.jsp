<%@page import="java.util.ArrayList"%>
<%@page import="dto.ListMainDTOOut"%>
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
	<%
		String USER = (String) session.getAttribute("USERID");
		
	%>
	<script>
		function loginCheck(){
			
			if(<%=USER%>!=null){
				return true;
			}else{
				alert("로그인이 필요합니다.");
				location.href="login.jsp";
				return false;
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
					<input type="text" name="search" class="search" id="search" placeholder="찾고 싶은 정보를 검색하세요">
				</form>
			</div>
		</div>
	</header>
<%
	ArrayList<ListMainDTOOut> list = (ArrayList<ListMainDTOOut>) request.getAttribute("LISTMAINDTO");
	
%>
	<%@ include file="sidebar-nav.jsp" %>
	
	<section class="main_visual">	
		<header>
			<h2 class="readonly">
			n visual</h2>
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
			<p class="more"><a href="trip-view2.jsp">더보기 <i class="fa fa-arrow-right moreicon"></i></a></p>
		</div>
	</section>
	<section class="best_cont">
		<h2>Best 여행지</h2>
		<ul>
		<%
		
			for(int i=0; i<list.size(); i++){
				int num = list.get(i).getNum();
				String title = list.get(i).getTitle();
				String photo = list.get(i).getPhoto();
				String content = list.get(i).getContent();
				String type = list.get(i).getType();
				if(photo==null){
					photo="image/default.png";
				}else{
					photo="upload/"+photo;
				}
			%>
			
 				<li><a href="read.user?num=<%=num%>&pageNo=0&pageSize=5&viewStart=0&viewEnd=5&type=<%=type%>&search=" onClick="return loginCheck();">  <%--return 함수앞에써주면 값이 false일때 다음으로 안넘어감 --%>
				<img src="<%=photo%>" alt="<%=title %>" class="imgw50" >
				<!-- 위에 a 태그에서 readMain.user에 search를 안넘겨줬더니 userView거쳐서 list.user 갈때 오류남 -->
				<span><%=title %></span><p><%=content %></p></a></li>  <!--class="imgw100"-->
				
			<%
			}
		
		%>
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
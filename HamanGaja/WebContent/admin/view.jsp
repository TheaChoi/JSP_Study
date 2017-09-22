<%@page import="java.net.URLEncoder"%>
<%@page import="dto.BoardDTOOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
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
					<input type="text" name="search" class="search" id="search" placeholder="찾고 싶은 정보를 검색하세요">
				</form>
			</div>
		</div>
	</header>
	<%@ include file="../sidebar-nav.jsp" %>
	
	<%
		BoardDTOOut dto = (BoardDTOOut) request.getAttribute("BOARDDTOOUT");
							
		int num = dto.getNum();
		String type = dto.getType();
		String title = dto.getTitle();
		String content = dto.getContent();
		String photo = dto.getPhoto();
		String map = dto.getMap();
		int nice = dto.getNice();
		int count = dto.getCount();
		String reg_date = dto.getReg_date();
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));  //pageNo을 ctrl가 줬어도 getParameter로 받아오지 않고 코드에 쓰면 오류난다
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int viewStart = Integer.parseInt(request.getParameter("viewStart"));
		int viewEnd = Integer.parseInt(request.getParameter("viewEnd"));
		String word = request.getParameter("search");  //목록에 되돌아갈때 필요하다
		
		System.out.println("글제목"+ title);
		System.out.println("사진경로"+ photo);
		System.out.println("지도"+ map); 
		System.out.println("word:"+word);
							
	%>
	
	<!-- sub contents start -->
	<div class="sub-title-group">
		<h2 class="sub-title1">함안의 <%=type %></h2>
	</div>
	<div class="one-contents">
		<% if(photo==null) {%>
			<img src="../images/default.png" alt="default">
		<%}else{ %>
			<img src="../upload/<%=photo %>" alt="">
		<%} %>
		<div class="one-contents-p">
			<span><%=title %></span>
			<p style="border:1px solid #ddd; padding:10px;"><%=content %>
			</p>
			<br><br><br>
			<div id="map" class="map">
				<iframe src="<%=map %>"
				width="400" height="300" frameborder="0" style="border:0" allowfullscreen>
				</iframe>
			</div>
			<div><p style="text-align:right;">작성시간 : <%=reg_date %></p></div>	
			<div class="sns-box">
				<div class="list">
					<a href="List.board?pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>">목록</a>
				</div>
				<%System.out.println("타입은 "+type);
			type = URLEncoder.encode(type, "UTF-8"); 
			System.out.println("타입은 "+type);%>
				<div class="list">
					<a href="delete.board?num=<%=num%>&pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>">삭제</a>
				</div>
			
				<div class="list">
					<a href="contents.board?num=<%=num%>&pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>">수정</a>
				</div>
				<div class="sns-icon">
					<span><a><i class="fa  fa-thumbs-o-up"></i>&nbsp; &nbsp;</a> <%=nice +"  "%>&nbsp;<i class="fa   fa-eye">&nbsp; &nbsp;<%=count %></i></span>
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
		
// 		$("#map").click(function() 
// 			$(".map").show();
// 		}
	</script>


 
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ko"></script>

</body>
</html>

















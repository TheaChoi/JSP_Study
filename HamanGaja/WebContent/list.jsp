<%@page import="dto.UserDTOOut"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.PageDTOOut"%>
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
	<%
	PageDTOOut pg =(PageDTOOut) request.getAttribute("PAGEOUT");
	ArrayList<UserDTOOut> list = (ArrayList<UserDTOOut>) request.getAttribute("LIST");
						
	String type = list.get(0).getType();
	%>
	<%
			int pageNum = pg.getPageNum(); //전체 페이지 개수
			System.out.println(pageNum);
			int pageNo = pg.getPageNo(); //현재 페이지 번호
			int pageSize = pg.getPageSize();
			
			String word = pg.getWord();
			
		
			int viewStart=pg.getViewStart(); //보여지는 페이지 번호 시작과 끝
			int viewEnd=pg.getViewEnd();
		%>	
		
		
	<div class="sub-title-group">
		<h2 class="sub-title1">함안가자 <%=type %></h2>
	</div>
	<div class="sub-contents">
	
		<ul>
		
		<% 
	for(int i=0; i<list.size();i++){
		
		UserDTOOut dto = list.get(i);
	
		int num = dto.getNum();
		String title = dto.getTitle();
		String photo = dto.getPhoto();
		String reg_date = dto.getReg_date();
		String content = dto.getContent();
		int count = dto.getCount();
		
		System.out.println("type="+type);
		System.out.println("title="+title);
		System.out.println("photo="+photo);
		System.out.println("reg_date="+reg_date);
		System.out.println("content="+content);
	%>
			<li>
				<a href="read.user?num=<%=num%>&pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>">
				<div class="sub-table">
					<div class="sub-table-cell1">
					<% 
					if(photo==null || photo.equals("")==true){
						%>
							<img style="width:200px;height:150px;" src="images/default.png" alt="default">
						<%
					
					}else{
						%>				
							<img style="width:200px;height:150px;" src="upload/<%=photo %>" alt="">
						<%
					}
					%>
					</div>
					<div class="sub-table-cell2" style="padding-top:8px;padding-bottom:8px;width:1000px;">
						<span class="sub-title2">
							<%=title %>
						</span>
						<span class="sub-content" style="overflow: hidden;
							overflow: hidden; 

							text-overflow: ellipsis; 
							
							display: -webkit-box;
							
							-webkit-line-clamp: 5; /* 라인수 */	
							
							-webkit-box-orient: vertical; 
							
							word-wrap:break-word;
							">
							<%=content %>
						</span>
						<br>
						<p style="font-size:12px;display:inline-block;float:left;">작성일 <%=reg_date.substring(0, 10) %> </p>    
						<p style="font-size:12px; display:inline-block;float:right;">조회수  <%=count %></p>
					</div>
				</div>
				</a>
				
			</li>
		<%
		}
	
		%>
		</ul>
		<div class="paging">
			
		<%if (pageNo==0){ %>
			<i class="fa fa-angle-left" style="color:#999;"></i>
		<%}else{ %>	
			<a href="list.user?pageNo=<%=pageNo-1%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><i class="fa fa-angle-left"></i></a>
		<%} %>
		
	<%for(int i=viewStart; i<viewEnd ; i++){ %>		
		<%if(i==pageNo){ %>  
<!-- 		현재 페이지 링크 안됨 -->
			<strong><%=i+1 %></strong>
		<%}else{ %>
			<a href="list.user?pageNo=<%=i%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><%=i+1 %></a>
		<%} 
		}%>
		
		
			<%if (pageNo==pageNum-1){ %>
			<i class="fa fa-angle-right" style="color:#999;"></i>
		<%}else{ %>	
			<a href="list.user?pageNo=<%=pageNo+1%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><i class="fa fa-angle-right"></i></a>
		<%} %>
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

















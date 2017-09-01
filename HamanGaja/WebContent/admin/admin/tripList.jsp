
<%@page import="dto.PageDTOOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="dto.ListDTOOut"%>
<%@page import="java.util.ArrayList"%>
<%@ include file = "admin-check.jsp"%>

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
			<form name="mysearch" action="List.board">
				<div class="inner">
				
					<label for="search" class="readonly">검색단어 입력</label>
					<input type="text" name="search" class="search" id="search" placeholder="찾고 싶은 정보를 검색하세요">
					<input type="hidden" name="pageNo" value="0">  
					<input type="hidden" name="pageSize" value="5">
					<input type="hidden" name="type" value="관광지">
					<input type="hidden" name="viewStart" value="0">
					<input type="hidden" name="viewEnd" value="5">
<!-- 					hidden 태그는 보이지 않지만 파라미터로 넘어간다 -->
					
				
				</div>
				<input type="submit" value="search" name="search" style="width:50px;">
			</form>
		</div>	</header>
	<%@ include file="../sidebar-nav.jsp" %>
	
	<%
		ArrayList<ListDTOOut> list = (ArrayList<ListDTOOut>)request.getAttribute("TRIP");
		
		PageDTOOut pg = (PageDTOOut) request.getAttribute("PAGEOUT");
	%>
			
		
	
		
	<!-- sub contents start -->
	<div class="adminbox">
	<h1 style="font-size:20px; color:#f26522; padding:10px; font-weight:bold;">관광지 목록</h1>
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
			<% ListDTOOut dto=null;
		
			for(int i=0; i<list.size() ; i++){
			dto = list.get(i);
			int num = dto.getNum();
			String title = dto.getTitle();
			String reg_date = dto.getReg_date().substring(0,10);
			int count = dto.getCount();
			String type = dto.getType();
		%>
	
		
		<%
			int pageNum = pg.getPageNum(); //전체 페이지 개수
			int pageNo = pg.getPageNo(); //현재 페이지 번호
			int pageSize = pg.getPageSize();
			
			String word = pg.getWord();
			
			
			int viewStart=pg.getViewStart(); //보여지는 페이지 번호 시작과 끝
			int viewEnd=pg.getViewEnd();
		%>	
		
				<tr>
					<td><%=num %></td>
					<td><a href="read.board?num=<%=num%>&pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><%=title %></a></td>
					<td><%=reg_date %></td>
					<td><%=count %></td>
				</tr>
		<%} %>
		
			</tbody>
		</table>
		
		<div class="paging">
		<%
			int pageNum = pg.getPageNum(); //전체 페이지 개수
			System.out.println(pageNum);
			int pageNo = pg.getPageNo(); //현재 페이지 번호
			int pageSize = pg.getPageSize();
			
			String word = pg.getWord();
			
			String type=dto.getType();
			
			int viewStart=pg.getViewStart(); //보여지는 페이지 번호 시작과 끝
			int viewEnd=pg.getViewEnd();
		%>	
		
		
		<%if (pageNo==0){ %>
			<i class="fa fa-angle-left" style="color:#999;"></i>
		<%}else{ %>	
			<a href="List.board?pageNo=<%=pageNo-1%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><i class="fa fa-angle-left"></i></a>
		<%} %>
		
	<%for(int i=viewStart; i<viewEnd ; i++){ %>		
		<%if(i==pageNo){ %>  
<!-- 		현재 페이지 링크 안됨 -->
			<strong><%=i+1 %></strong>
		<%}else{ %>
			<a href="List.board?pageNo=<%=i%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><%=i+1 %></a>
		<%} 
		}%>
		
		
			<%if (pageNo==pageNum-1){ %>
			<i class="fa fa-angle-right" style="color:#999;"></i>
		<%}else{ %>	
			<a href="List.board?pageNo=<%=pageNo+1%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>"><i class="fa fa-angle-right"></i></a>
		<%} %>
			<div class="write">
				<a href="write.jsp">글쓰기</a>
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

















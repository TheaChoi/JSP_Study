<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%
		String USERID = (String)session.getAttribute("USERID");
		String USERPW = (String)session.getAttribute("USERPW");
		String ADMIN = (String)session.getAttribute("ADMIN");
		
		String uri = request.getRequestURI();
		//System.out.println("현재경로: "+uri);
		String jspPage = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		System.out.println("현재 JSP page: "+jspPage);
	%>
	

	<%						
		if(USERID!=null){
			if(ADMIN != null){
	%>
		<nav id="slidebar-wrapper">
		<a href="#" ><i class="fa fa-close btn-close" id="menu-close"></i></a>
		<div class="slidebarwrap">
			<div class="slidebar-brand" style="background:url(/HamanGaja/images/prof.jpg) no-repeat center center;
			background-size:cover;">
			</div>
			<p><span><%=USERID%> </span> 님, 함안가자에 로그인되었습니다</p>
		</div>
		<ul class="sidebar-nav">
			<li id="main"><i class="fa fa-home navicon"></i><a href="/HamanGaja/main.jsp">HOME</a></li>
			<li id="main"><i class="fa fa-pencil-square-o navicon"></i><a href="/HamanGaja/admin/write.jsp">글쓰기</a></li>
			<li id="trip"><i class="fa fa-fort-awesome navicon"></i><a href="/HamanGaja/admin/List.board?pageNo=0&pageSize=5&viewStart=0&viewEnd=5&type=관광지">관광지</a></li>
<!-- 			HOME에서 넘어가려면 절대경로여야 오류없이 찾아간다. -->
			<li id="restaurant"><i class="fa fa-cutlery navicon"></i><a href="/HamanGaja/admin/List.board?pageNo=0&pageSize=5&viewStart=0&viewEnd=5&type=맛집">맛집</a></li>
			<li id="specialty"><i class="fa fa-shopping-basket navicon"></i><a href="/HamanGaja/admin/List.board?pageNo=0&pageSize=5&viewStart=0&viewEnd=5&type=특산물">특산물</a></li>
			<li><i class="fa fa-user-o navicon"></i><a href="logout.admin">로그아웃</a></li>
		</ul>
	</nav>
	<%
			}else{
	%>
	<nav id="slidebar-wrapper">
		<a href="#" ><i class="fa fa-close btn-close" id="menu-close"></i></a>
		<div class="slidebarwrap">
			<div class="slidebar-brand" style="background:url(/HamanGaja/images/prof.jpg) no-repeat center center;
			background-size:cover;">
			</div>
			<p><span><%=USERID%> </span> 님, 함안가자에 로그인되었습니다</p>
		</div>
		<ul class="sidebar-nav">
			<li id="main"><i class="fa fa-home navicon"></i><a href="main.jsp">HOME</a></li>
			<li id="trip"><i class="fa fa-fort-awesome navicon"></i><a href="trip.jsp">관광지</a></li>
			<li id="restaurant"><i class="fa fa-cutlery navicon"></i><a href="restaurant.jsp">맛집</a></li>
			<li id="specialty"><i class="fa fa-shopping-basket navicon"></i><a href="specialty.jsp">특산물</a></li>
			<li><i class="fa fa-user-o navicon"></i><a href="info.mem">회원정보수정</a></li>
			<li><i class="fa fa-user-o navicon"></i><a href="logout.mem">로그아웃</a></li>
			<li><i class="fa fa-user-o navicon"></i><a href="del.jsp">회원탈퇴</a></li>
		</ul>
	</nav>
	<%
			}
	 }else{
	%>
	<nav id="slidebar-wrapper">
		<a href="#"><i class="fa fa-close btn-close" id="menu-close"></i></a>
		<div class="slidebarwrap">
			<div class="slidebar-brand" style="background:url(/HamanGaja/images/lg.png) no-repeat center center;background-size:cover;">
			</div>
			<p>함안에 오신 것을 환영합니다</p>
		</div>
		<ul class="sidebar-nav">
			<li id="main"><i class="fa fa-home navicon"></i><a href="main.jsp">HOME</a></li>
			<li id="join"><i class="fa fa-user-plus navicon"></i><a href="join.jsp">회원가입</a></li>
			<li id="login"><i class="fa fa-user-o navicon"></i><a href="login.jsp">로그인</a></li>
			<li id="trip"><i class="fa fa-fort-awesome navicon"></i><a href="trip.jsp">관광지</a></li>
			<li id="restaurant"><i class="fa fa-cutlery navicon"></i><a href="restaurant.jsp">맛집</a></li>
			<li id="specialty"><i class="fa fa-shopping-basket navicon"></i><a href="specialty.jsp">특산물</a></li>
		</ul>
	</nav>
	<%
		}
	%>
	
	
	
	<script>
	<%--  		document.getElementById("<%=jspPage%>").className = "active";		이러면 logoutSuccess에서 작동안됨  --%>
	var Lhref = window.location.href;  //"...mem 과같은 링크주소가 나타남. uri는 현재 페이지 주소"
	var e = Lhref.lastIndexOf('/');
	var e1 = Lhref.lastIndexOf('.');
	Lhref = Lhref.substring(e+1, e1);
	
	//alert(Lhref);
	
	document.getElementById(Lhref).className = "active";
		
	</script>
<!--jQurey:  $("#id1").attr("name") -->
	
</body>
</html>
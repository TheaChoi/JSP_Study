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
		
		String uri = request.getRequestURI();
		//System.out.println("현재경로: "+uri);
		String jspPage = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		System.out.println("현재 JSP page: "+jspPage);
		
							
		if(USERID!=null && USERPW !=null){
	%>
	<nav id="slidebar-wrapper">
		<a href="#"><i class="fa fa-close btn-close" id="menu-close"></i></a>
		<div class="slidebarwrap">
			<div class="slidebar-brand" style="background:url(./images/prof.jpg) no-repeat center center;
			background-size:cover;">
			</div>
			<p><span><%=USERID%> </span> 님, 함안가자에 로그인되었습니다</p>
		</div>
		<ul class="sidebar-nav">
			<li id="main"><i class="fa fa-home navicon"></i><a href="main.jsp">HOME</a></li>
			<li id="trip"><i class="fa fa-fort-awesome navicon"></i><a href="trip.jsp">관광지</a></li>
			<li id="restaurant"><i class="fa fa-cutlery navicon"></i><a href="restaurant.jsp">맛집</a></li>
			<li id="specialty"><i class="fa fa-shopping-basket navicon"></i><a href="specialty.jsp">특산물</a></li>
			<li><i class="fa fa-user-o navicon"></i><a href="logout.mem">로그아웃</a></li>
		</ul>
	</nav>
	<%
	 }else{
	%>
	<nav id="slidebar-wrapper">
		<a href="#"><i class="fa fa-close btn-close" id="menu-close"></i></a>
		<div class="slidebarwrap">
			<div class="slidebar-brand" style="background:url(./images/lg.png) no-repeat center center;background-size:cover;">
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
		document.getElementById("<%=jspPage%>").className = "active";		
		
	</script>
<!--jQurey:  $("#id1").attr("name") -->
	
</body>
</html>
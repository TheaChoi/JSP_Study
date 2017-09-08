<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="admin-check.jsp" %>

<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="../css/mystyle.css" rel="stylesheet">
	<link href="../css/font-awesome.min.css" rel="stylesheet">
	
	<script>
		function check() {
			if(myform.title.value==""){
				alert("제목을 입력하세요");
				return;
			}
			if(myform.content.value.trim()==""){  //빈공간 검사할땐 trim()으로 양옆의 빈공간을 없애줘야한다
				alert("글내용 입력하세요");
				return;
			}
			myform.submit();
		}
		
		function fileupload(){
			var pic = document.getElementById("pic");
			var filename = document.getElementById("filename");
			filename.innerHTML = pic.value;
			
			var div = document.getElementById("filediv");
			div.style.display="inline-block";
		}
		
		function filedelete() {
			var filename = document.getElementById("filename");
			//filename.innerHTML="";
			
// 			file input 태그 초기화
			var pic = document.getElementById("pic");
			pic.type="";
			pic.type="file";
			
			var div = document.getElementById("filediv");
			
			div.style.display="none";
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
					<input type="text" name="search" class="search" id="search" value="찾고 싶은 정보를 검색하세요">
				</form>
			</div>
		</div>
	</header>
	<%@ include file="../sidebar-nav.jsp" %>
	<!-- sub contents start -->
	<div class="adminbox">
		<h2 class="readonly">글쓰기</h2>
		
		<form name="myform" method="post" enctype="multipart/form-data" action="write.board">  
			<select name="type">
				<option value="관광지">관광지 글쓰기</option>
				<option value="맛집">맛집 글쓰기</option>
				<option value="특산물">특산물 글쓰기</option>
			</select>
	<!-- 		enctype="multipart/form-data" 인코딩타입 정해줘야 파일업로드 가능 -->
<!-- 		"multipart request" 쓰려면 C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\cos.jar 있어야 업로드 기능 쓸 수 있다 -->
		
				<ul class="admin pdt20">
					<li>
						<label for="title" class="readonly">제목 입력</label>
						<input type="text" name="title" placeholder="제목 입력" id="title" title="제목을 입력해주세요" style="font-family:Arial,FontAwesome">  
					</li>
					<li>
						<label for="content" class="readonly" >내용 입력</label>
						<textarea name="content" style="width:100%; height:200px;" placeholder="내용을 이곳에 작성하세요."></textarea>
					</li>
					 <li>
 						<label for="pic" class="filebox">파일 업로드</label>  
						<input type="file" name="pic" id="pic" onChange="fileupload()">
						<span id="filename"></span>
						<div id="filediv" style="display:none;">
							
							<input type="button" value="삭제" onClick="filedelete()" id="delete">
						</div> 
 					</li>
						
					<li class="writebtn">
						
						<button type="button" onClick="javascript:check();" class="ok">확인</button>
						<button type="button" onClick="javascript:history.back();" class="cancel">취소</button>
					</li>
				</ul>
		</form>	
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

















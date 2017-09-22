<%@page import="java.io.File"%>
<%@page import="dto.BoardModDTOOut"%>
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
	var fileChanged=false;
	//var fc = $("#fc").val(); 는 $("#fc").val()와 fc에 넣어준 값이 다르다. 각각 다른걸 fc에넣어준거야  

		function check() {
			if(myform.title.value==""){
				alert("제목을 입력하세요");
				return;
			}
			if(myform.content.value.trim()==""){  //빈공간 검사할땐 trim()으로 양옆의 빈공간을 없애줘야한다
				alert("글내용 입력하세요");
				return;
			}
			if(fileChanged==false){
				
				
				$("#fc").val(-1);    
			}else if(fileChanged==true){
				
				$("#fc").val(0); 
			}
	
			myform.submit();
		}
		
		
		
		function fileupload(){
			var pic = document.getElementById("pic");
			var filename = document.getElementById("filename");
			filename.innerHTML = pic.value;
			
			var div = document.getElementById("filediv");
			div.style.display="inline-block";
			
			fileChanged=true;
			alert(fileChanged);
		}
		
		function filedelete() {
			var filename = document.getElementById("filename");
			filename.innerHTML="";
			
// 			file input 태그 초기화
			var pic = document.getElementById("pic");
			pic.type="";
			pic.type="file";
			
			var div = document.getElementById("filediv");
			
			div.style.display="none";
		}
	</script>
	
	<%
	 int num = Integer.parseInt(request.getParameter("num")); 
	BoardModDTOOut dto = (BoardModDTOOut) request.getAttribute("BOARDMODDTOOUT");

	
	String type = dto.getType();
	String title = dto.getTitle();
	String content = dto.getContent();
	String photo = dto.getPhoto();
	String map = dto.getMap();
	
	String prevPhoto = request.getSession().getServletContext().getRealPath("/")+"upload\\"+photo;
	
	
	//System.out.println("!!!!num="+num);
	//System.out.println("prevPhoto="+prevPhoto);
	//System.out.println("content="+content);
	
	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	int viewStart = Integer.parseInt(request.getParameter("viewStart"));
	int viewEnd = Integer.parseInt(request.getParameter("viewEnd"));
	
	System.out.println("pageno="+pageNo);
	System.out.println("pageSize="+pageSize);
	System.out.println("viewStart="+viewStart);
%>


<script>
	//1.읽어온 콘텐츠 타입 보여주기
	var typeTag = document.getElementById("type");
	typeTag.value = "<%=type%>";
	

// 	function delPhoto() {
		
<%-- 		var num=<%=num%>;   --%>
		
// 		$.ajax({
// 			async: false,
// // 			기본 ajax를 사용하게 되면 데이터 할당에 시간차이가 발생한다.
// // 			ajax의 속성중, async(비동기) 속성이 기본 true이기 때문이다.
// 			type:"GET",
// 			url:"delPhoto.board",
// 			data:"num="+num,
// 			dataType:"JSON",
// 			success:function(res){
				
// 			},
// 			error:function(){
// 				alert("사진 삭제 에러");
// 			}
// 		});
// 	}

	function modPhoto() {
	
		var formTag = $("form[name=fileupload]")[0]; //사진 전송 폼태그를 찾음
		var formData = new FormData(formTag);  //폼태그=>폼데이타로 변경,  
	                                         	//사진데이타=>폼데이타에 포함		
		formData.append("pic", $("input[name=picture]")[0].files[0]);
		formData.append("num", "<%=num%>");
		
		$.ajax({
			type:"POST",
			encType:"multipart/form-data", //MultipartRequest 전송
			url:"modPhoto.board",
			data:formData,  //FormData 전송
			dataType:"JSON",
			processData:false,
			contentType:false,
			cache:false,
			timeout:6000,
			success:function(res){
				if(res.ret==true){
					alert("사진이 변경되었습니다.");
					var newphoto = document.getElementById("newPhoto");  //사진띄우기장소
					//alert(res.pic);
					newphoto.src = "../upload/"+res.pic;  		
				}else{
					alert("사진이 변경되지 않았습니다.");
				}
			},
			error:function(){
				alert("사진 ajax 수정 에러");
			}
		});
	}
	
// 	function modText(){
		
// 		var param = $("form[name=content]").serialize();
// 		alert(param);
// 	}

function modMap(){
	
	var tag=document.getElementById("newMap");
	var newMap = tag.value; 
	var num = "<%=num%>";
	
	$.ajax({
		
		type:"GET",
		url:"modMap.board",
		data:"map="+newMap+"&num="+num,
		dataType:"json",
		success:function(res){
			if(res.ret==true){
				alert("지도가 변경되었습니다.");
				var frame = document.getElementById("mapFrame");
				frame.src = newMap;
			}else{
				alert("지도가 변경되지 않았습니다.");
			}
		},
		error:function(){
			alert("지도 변경 ajax에러");
		}
	})
	
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
		
		<form name="myform" method="post" enctype="multipart/form-data" action="mod.board">  
			<select name="type" id="type">
				<option value="관광지">관광지 글쓰기</option>
				<option value="맛집">맛집 글쓰기</option>
				<option value="특산물">특산물 글쓰기</option>
			</select>
	<!-- 		enctype="multipart/form-data" 인코딩타입 정해줘야 파일업로드 가능 -->
<!-- 		"multipart request" 쓰려면 C:\Program Files\Apache Software Foundation\Tomcat 9.0\lib\cos.jar 있어야 업로드 기능 쓸 수 있다 -->
		
				<ul class="admin pdt20">
					<li>
						<label for="title" class="readonly">제목 입력</label>
						<input type="text" name="title" placeholder="제목 입력" id="title" title="제목을 입력해주세요" style="font-family:Arial,FontAwesome" value="<%=title %>">  
					</li>
					<li>
						<label for="content" class="readonly" >내용 입력</label>
						<textarea name="content" id="content" style="width:100%; height:200px;" placeholder="내용을 이곳에 작성하세요."><%=content %></textarea>
						

 					
					</li>
					 <li>
					 	<!-- 					사진 수정/삭제 하기					 -->
 					<%if(photo!=null){ %> 
 						<img id="newPhoto" src="../upload/<%=photo %>" alt="uploadFile" style="width:50%; overflow:cover;"> 
 					<%}else{ %>
 						<img src="../images/default.png" alt="defaultFile" style="width:50%; overflow:cover;"> 
 					<%} %>
	 					
			 		</li>
			 		<li>
			 			<form name="fileupload" method="post" enctype="multipart/form-data" action="modPhoto.board">
							<input type="button" value="수정" onClick="modPhoto()" style="width:18%; margin:20px 4px; text-align:center;padding:10px;">
							<input type="button" value="삭제" onClick="delPhoto()" style="width:18%; margin:20px 4px; text-align:center;padding:10px;">
							<label for="picture" class="filebox" style="width:50%;">현재페이지 파일업로드</label>  
							<input type="file" name="picture" id="picture">
						</form>
			 		</li>
			 		<li>								
 						<label for="pic" class="filebox">사진 수정</label>  
						<input type="file" name="pic" id="pic" onChange="fileupload()">
						<span id="filename"><%=photo %></span>
						<div id="filediv" style="display:none;">
							
							<input type="button" value="삭제" onClick="filedelete()" id="delete">
						</div> 
					</li>
					<li>
					
						<input type="hidden" name="prevPhoto" value=<%=prevPhoto %>>
						<input type="hidden" name="fc" id="fc">
						
						<!-- 나중에글목록을만드는정보 -->
						<input type="hidden" name="pageNo" value="<%=pageNo%>">
						<input type="hidden" name="pageSize" value="<%=pageSize%>">
						<input type="hidden" name="viewStart" value="<%=viewStart%>">
						<input type="hidden" name="viewEnd" value="<%=viewEnd%>">
 					</li>
 					<br><br>
 					<li>
 						<div id="map">
 							<iframe id="mapFrame" src="<%=map %>" height="300px"></iframe>
 						</div>
 						<p>New Map <input type="text" size="40" id="newMap" name="newMap" style="width:50%; margin-right:3%;">
 							<input type="button" value="지도수정" onClick="modMap()" style="width:20%;padding:4px;">
 						</p>
 						
					</li>	
					<li class="writebtn">
						
						<input type="hidden" name="num" value="<%=num %>">
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
	
	
<script>
 
 	window.onload = function() {
 		var JSType = document.getElementById("type");
 		JSType.value = "<%=type%>";
 		var JSTitle = document.getElementById("title");
 		JSTitle.value = "<%=title%>"
 		var JSContent = document.getElementById("content");
 		JSContent.value= "<%=content%>" ;
 		
		//var JSPhoto = document.getElementById("pic");
<%--  		JSPhoto.value = <%=request.getSession().getServletContext().getRealPath("/")+"upload\\"+photo %>; --%>
<%--  		<%System.out.println(request.getSession().getServletContext().getRealPath("/")+"upload\\"+photo);%> --%>
		
// 		var filename = document.getElementById("filename");
// 		filename.innerHTML = JSPhoto.value;
// 		var div = document.getElementById("filediv");
// 		div.style.display="inline-block";
 	}
 	
 	
		
</script>
	
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
		})
	</script>
</body>
</html>

















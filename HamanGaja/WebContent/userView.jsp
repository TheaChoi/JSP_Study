<%@page import="dto.ReplyDTOOut"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="dto.BoardDTOOut"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width initial-scale=1 user-scalable=no">
	<link href="css/mystyle.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	
	
	<script>
	
	//빈 함수 방치해놓으면 오류남 ㅠㅠㅠ
	
	function delCheck(rpNum, i){
      
		var replyOne = document.getElementById("replyOne"+i);
          var check = confirm("댓글을 삭제하시겠습니까?");
          if(check == true){
        	  
        	  $.ajax({
      			type:"GET",  //파라미터 전송 방식 GET/POST
      			url:"replyDel.user", //링크시킬 페이지 url주소
      			data:"rpNum="+rpNum, //GET/POST 보낼 파라미터들
      			dataType:"json", //처리 결과가 반환되는 방식
      			success:function(result)  //ajax가 처리를 성공적으로 끝내고 결과를 반환함 (result는 json객체 이름(객체안에 {이름:값}))
      			{
      				
      				if(result.ret==true){
      					replyOne.remove();
      					
      				}else{
      				
      				}
      			
      			},
      			error:function()
      			{
      				alert("리플 수정 실패");
      			}
      		});
        	  
          }else{
             return false;
          }
		}
	
	function showMod(i){
		
		var replyList = document.getElementById("replyList"+i);
		replyList.style.display = "none";
		
		var modArea = document.getElementById("mod"+i);
		modArea.style.display = "block";
	}
	
	function hideMod(i){
		
		var replyList = document.getElementById("replyList"+i);
		replyList.style.display = "block";
		
		var modArea = document.getElementById("mod"+i);
		modArea.style.display = "none";
	}
	
	function replyMod(rpNum, i){
		
		var rpContent = document.getElementById("rpContent"+i).value;
		//alert(rpContent);
		var rp = document.getElementById("rp"+i);
		
		
		$.ajax({
			type:"POST",  //파라미터 전송 방식 GET/POST
			url:"replyMod.user", //링크시킬 페이지 url주소
  			data:"rpNum="+rpNum+"&content="+rpContent, //GET/POST 보낼 파라미터들
			dataType:"json", //처리 결과가 반환되는 방식
			success:function(result)  //ajax가 처리를 성공적으로 끝내고 결과를 반환함 (result는 json객체 이름(객체안에 {이름:값}))
			{
				
				if(result.ret==true){
				
					rp.innerHTML = "<font>"+rpContent+"</font>";
					hideMod(i);
				}else{
				
				}
			
			},
			error:function()
			{
				alert("리플 수정 실패");
			}
		});
		
	}
	


		
	function nice(num, type, userid){
		
		
		var niceNum = document.getElementById("nice");  //p값 가져올때는 innerText!!!, parseInt()해줘야 정수로 인식
		
		if(niceChecked == false){  //좋아요 안한 상태일때
			
			$.ajax({
				type:"GET",  //파라미터 전송 방식 GET/POST			 
				url:"nice.user", //링크시킬 페이지 url주소
	  			data:"num="+num+"&type="+type+"&id="+userid, //GET/POST 보낼 파라미터들
				dataType:"json", //처리 결과가 반환되는 방식
				success:function(result)  //ajax가 처리를 성공적으로 끝내고 결과를 반환함 (result는 json객체 이름(객체안에 {이름:값}))
				{
					if(result.ret==true){
					
						niceChecked = true;  //좋아요 클릭했는지
						
	// 					niceNum = parseInt(niceNum.innerText)+1;
	// 					niceNum += "";
	// 					alert(niceNum);
						
						var niceNumPrev = parseInt(niceNum.innerText);  //좋아요 하기 전의 숫자
						
						//alert("좋아요!!!!");
	 					niceNum.innerHTML = "<font>"+(niceNumPrev+1)+"</font>";
						
					}else{
					
					}
				
				},
				error:function()
				{
					alert("좋아요 실패");
				}
			});
		
		}else{  //이미 좋아요 한 상태일때
			
			$.ajax({
				type:"GET",  //파라미터 전송 방식 GET/POST			 
				url:"niceDel.user", //링크시킬 페이지 url주소
	  			data:"num="+num+"&niceNum="+niceNum, //GET/POST 보낼 파라미터들
				dataType:"json", //처리 결과가 반환되는 방식
				success:function(result)  //ajax가 처리를 성공적으로 끝내고 결과를 반환함 (result는 json객체 이름(객체안에 {이름:값}))
				{
					if(result.ret==true){
					
						niceChecked = false;  //좋아요 클릭했는지

						var niceNumPrev = parseInt(niceNum.innerText);  
						
						//alert("안좋거든!!!!");
	 					niceNum.innerHTML = "<font>"+(niceNumPrev-1)+"</font>";
						
					}else{
					
					}
				
				},
				error:function()
				{
					alert("좋아요 취소 실패");
				}
			});
			
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
			<form name="mysearch" action="list.user">
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
				<input type="submit" value="search" name="search" style="width:50px;"/>
			</form>
		</div>
	</header>
		<%@ include file="sidebar-nav.jsp" %>
		
	<%
		
		String userid = (String) session.getAttribute("USERID");
		
	
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
			<img src="images/default.png" alt="default">
		<%}else{ %>
			<img src="upload/<%=photo %>" alt="">
		<%} %>
		<div class="one-contents-p">
			<span><%=title %></span>
			<p style="border:1px solid #ddd; padding:10px;"><%=content %>
			</p>
			<br><br><br>
			<div id="map" class="map">
				<iframe src="<%=map %>" width="400" height="300" frameborder="0" style="border:0" allowfullscreen>
				</iframe>
			</div>
			<div><p style="text-align:right;">작성시간 : <%=reg_date %></p></div>	
			
			<!-- 댓글달기 -->
			  <form name="reply" method="post" action="replyWrite.user?num=<%=num%>" style="margin-top:30px;">                                
                  <textarea name="content" style="width:85%; height:50px; border:1px solid #ddd;padding:4px; "></textarea>
                  <input type="submit" value="댓글 달기" style="width :12%; height:50px; float:right;">
                  <input type="hidden" name="pageNo" value="<%=pageNo%>">  
					<input type="hidden" name="pageSize" value="<%=pageSize%>">
					<input type="hidden" name="type" value="<%=type%>">
					<input type="hidden" name="viewStart" value="<%=viewStart%>">
					<input type="hidden" name="viewEnd" value="<%=viewEnd%>">
              </form>
			<div style="border:1px solid #ddd; padding:10px;">
				<ul>
				<%
					//댓글 가져오기
						ArrayList<ReplyDTOOut> list= (ArrayList<ReplyDTOOut>) request.getAttribute("ReplyList");
				
						for(int i=0; i<list.size(); i++){
							ReplyDTOOut reply = list.get(i);
							
							String rp = reply.getContent();
							String id = reply.getId();
							String rp_date = reply.getReg_date().substring(0,16);
							int rpNum = reply.getNum();
						
				%>
				
					<li id="replyOne<%=i %>" style="border-bottom:1px solid #ddd;padding:8px 0;overflow:hidden;">
						<div id="replyList<%=i %>" style="display:block;">
							<p style="float:left;font-weight:bold;"><%=id %></p>
							<p style="float:right;color:#888;"><%=rp_date %></p>
							
							<p id="rp<%=i%>" style="clear:both;padding:10px 0;"><%=rp %></p>
						
						
						
				<%
					if(userid != null && userid.equals(id)==true){
				%>
<%-- 				href="replyMod.user?pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>&num=<%=num%>&rpNum=<%=rpNum%>" --%>
<%-- href="replyDel.user?&pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>&num=<%=num%>&rpNum=<%=rpNum%>"  --%>
						<p style="text-align:right;"><a onclick="showMod(<%=i %>);">수정</a> | <a onclick="delCheck(<%=rpNum%>, <%=i %>)" >삭제</a></p>
				
					</div>	
				<%  }else{ %>
						
				<%	} %>
						
						<div id="mod<%=i %>" style="display:none;">
							<p style="float:left;font-weight:bold;"><%=id %></p>
							<p style="float:right;"><a onclick="replyMod(<%=rpNum%>, <%=i%>)">수정</a> | <a onclick="hideMod(<%=i %>)">취소</a></p>		
											
							<textarea id="rpContent<%=i%>" style="clear:both;width:95%; height:60px; border:1px solid #ddd;padding:4px; margin-top:10px;"><%=rp %></textarea>
						</div>
						
					</li>
				<%
						}
				%>
				</ul>
			</div>
			
			<div class="sns-box">
				<div class="list">
					<a href="list.user?pageNo=<%=pageNo%>&pageSize=<%=pageSize%>&type=<%=type %>&search=<%=word%>&viewStart=<%=viewStart%>&viewEnd=<%=viewEnd%>">목록</a>
				</div>
			
				<div class="sns-icon">
					<a onclick="nice(<%=num%>, '<%=type%>', '<%=userid%>');" ><i id="niceIcon" class="fa  fa-thumbs-o-up"></i></a> <span id="nice"><%=nice%></span>
					<i id="countIcon" class="fa  fa-eye"></i> <span id="count"><%=count%></span>
				</div>
				<div class="top">
					<a href="#top"><i class="fa fa-chevron-up"></i><br>맨 위로</a>
				</div>
			</div>
		
		</div>
	</div>
	<!-- sub contents end -->
	
	 
	<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=ko"></script>
	
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
		
 		
		var niceChecked = false;   //좋아요
		
		
 		//alert(niceChecked);
		
		if(niceChecked==false){
			
			var niceIcon = document.getElementById("niceIcon");
			niceIcon.style.color="black";
			
		}else{
			niceIcon.style.color="red";
		}
		
		
		
		
	</script>



</body>
</html>

















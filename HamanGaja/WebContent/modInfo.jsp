<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.infoDTOOut" %>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/mystyle.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
	<script>
		var pwChecked = false;
		
		$(function() {
			 $(".modInput").prop( "disabled", true ); //Disable  //위치에 따라 한번 실행
		});
		
		
		function pwCheck() {
			
			var pw = document.getElementById("pw").value;
			var pwmsg = document.getElementById("pwM");
			
				$.ajax({
					type:"GET",
					url:"pwCheck.mem", //담부턴 아이디도 넘겨줘야 확실히 할수있다
					data:"pw="+pw,
					dataType:"json",
					success:function(data){
						if(data.ret==true){
							pwmsg.style.color="blue";
							pwmsg.innerHTML="<font>현재 비밀번호 일치</font>";
							 pwChecked = true;
							$(".modInput").prop( "disabled", false ); //Enable
							
						}else if(data.ret==false){
						
							pwmsg.style.color="red";
							pwmsg.innerHTML="<font>현재 비밀번호 불일치</font>";  //괄호안에쓰면안된다..
							 pwChecked = false;
							 $(".modInput").prop( "disabled", true ); //Disable
							
						}
					},
					error:function(){
						alert("오류");
					}
					
				});
				
		}
		
	
		
		 function modcheck() 
	      {        
			 
			 
			 var pw1 = document.getElementById('pw1').value;
			var pw2= document.getElementById('pw2').value;
			
				if(pw1!=pw2){
					alert("새 비밀번호 확인 입력이 틀렸습니다. \n다시 확인하세요");
					modform.pw1.focus(); 
					return;
				}
		 
	         //(3) 전화번호 입력 확인
	         var tel2=document.getElementById("tel2");
	         var tel3=document.getElementById("tel3");
	         
	         //tel2입력 형식 지정
	         var exp2=/[0-9]{3,4}$/;
	         
	         if( (tel2.value.length!=3 && tel2.value.length!=4) || exp2.test(tel2.value) != true)
	         {
	            alert("두번째 전화 번호 입력 형식이 잘못되었습니다.\n숫자로 3~4자리로 입력하세요");
	            return; 
	    		
	         }
	        
	         //tel3입력 형식 지정
	         var exp3=/[0-9]{4}$/;
	         if(tel3.value.length!=4 || exp3.test(tel3.value) != true)
	         {
	            alert("세번째 전화 번호 입력형식이 잘못되었습니다.\n숫자 4자리로 입력하세요 ");
	            return;
	         }
	         
	         
	         //alert("tel2:"+tel2.value+" tel3:"+tel3.value);
	         
	         //(4)이메일 이력 형식 체크
	         var emailid  = document.getElementById("emailid");
	         var emailser = document.getElementById("emailser");
	         
	         var exp4 =/[a-zA-Z0-9]+/ig;
	         
	         if(exp4.test(emailid.value) != true)
	         {
	            alert("이메일 입력 형식이 잘못 되었습니다.\n영어알파벳, 숫자로 입력해 주세요");
	            return;
	         }
	        
	         if(emailser.value =="")
	         {
	            alert("이메일 서버가 선택되지 않았습니다.");
	            return;
	         }
	         
	         //alert("email :"+email.value+" "+"email2 :"+email2.value);
	         if(pwChecked==false){
	        	 alert("현재 비밀번호 입력이 확인되지 않습니다. 다시 확인해주세요");
	        	 modform.pw.focus();
	         	return;
	         }
	         
	         //modform.submit();
	         
	         //AJAX로 서블릿에 데이터를 전달함
	         var param = $('form[name=modform]').serialize();
	        // alert("회원정보입력: "+param);
	        $.ajax({
	        	type:"POST",
	        	url:"mod.mem",
	        	data:param,
	        	dataType:"JSON",
	        	success:function(result){
	        		if(result.ret==true){
	        			alert("회원정보수정:"+result);
		        		location.href="main.jsp";
	        		}else{
	        			alert("회원정보수정실패")
	        		}
	        		
	        	},
	        	error:function(){
	        		alert("회원정보수정 처리중 에러가 발생하였습니다.");
	        	}
	        });
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
	<%@ include file="sidebar-nav.jsp" %>
	
	<!-- sub contents start -->
	<div class="loginbox">
		<p>
			<span class="p1"><span class="p3">회원정보</span>수정</span>
		</p>
		<h3 class="readonly">회원정보수정</h3>
		<form name="modform" method="post" action="mod.mem" onsubmit="modcheck() ; return false;">
			<ul class="join pdt20">
				
				<li>
					<label for="pww" class="readonly">현재 패스워드 입력</label>
					<input class="emailid" type="password" name="pw" placeholder="&#xf084;  현재 비밀번호 입력" id="pw" 
					title="비밀번호를 입력해주세요" style="font-family:Arial,FontAwesome;" >
					<input style="width:10%;" type="button" value="확인" onclick="pwCheck()">
					<span style="width:100px; height:20px; display:inline;margin-left:30px;" id="pwM"></span>
				</li>
				
				
				<li>
					<label for="pww" class="readonly">패스워드 입력</label>
					<input type="password" name="pw1" placeholder="&#xf084; 새 패스워드 입력"  
					id="pw1"  style="font-family:Arial,FontAwesome" class="modInput">
				</li>
		
				<li>
					<label for="pww2" class="readonly">패스워드 확인</label>
					<input type="password" name="pw2" placeholder="&#xf084; 새 패스워드 확인" 
					id="pw2" style="font-family:Arial,FontAwesome" class="modInput">
					
				</li>
				<li>
					<label for="tel1" class="readonly">전화번호 입력</label>
					<select id="tel1" name="tel1" class="modInput">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
					</select>
					<input type="text" id="tel2" name="tel2" class="tel2 modInput" maxlength="4" >
					<input type="text" id="tel3" name="tel3" class="tel3 modInput" maxlength="4" >
				</li>
				<li>
					<label for="email" class="readonly">메일주소입력</label>
					<input type="text" name="emailid" class="emailid modInput" id="emailid" >
					<select name="emailser" id="emailser" class="modInput">
						<option value="">메일선택</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmali.net">hanmeil.net</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</li>
				
				<li><input type="submit" value="정보수정완료" class="ok"></li>
				<li class="cancel"><a href="main.jsp">취소</a></li>
			
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
	
<%
	infoDTOOut dto = (infoDTOOut) request.getAttribute("infoDTOOut");

	
	String tel1 = dto.getTel1();
	String tel2 = dto.getTel2();
	String tel3 = dto.getTel3();
	String emailid = dto.getEmailid();
	String emailser = dto.getEmailser();
	//System.out.print(emailid+emailser);
%>
<script>
 
 	window.onload = function() {
 		var JStel1 = document.getElementById("tel1");
 		JStel1.value = "<%=tel1%>";
 	
 	var JStel2 = document.getElementById("tel2");
 	JStel2.value = "<%=tel2%>";
 	var JStel3 = document.getElementById("tel3");
 	JStel3.value = "<%=tel3%>";
 	var JSemilid = document.getElementById("emailid");
 	JSemilid.value = "<%=emailid%>";
 	var JSemilser = document.getElementById("emailser");
 	JSemilser.value = "<%=emailser%>";
 	}
 	
 	
		
</script>

  
<%--        %> --%>
<!--           window.onload = function() { -->
<%--              var phone = "<%=userPhone1%>"; --%>
<!--              document.myform.phoneNum1.value = phone; -->
<!--           } -->
</body>
</html>

















<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<link href="css/mystyle.css" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<script>
		function joincheck() {
			if(myform.id.value=="") {
				alert("아이디를 입력하시오");
				myform.id.focus();
				return;
			}
			if(myform.pw1.value=="") {
				alert("패스워드를 입력하시오");
				myform.pw1.focus();
				return;
				}
			if(myform.pw2.value=="") {
				alert("패스워드 확인을 입력하시오");
				myform.pw2.focus();
				return;
				}
			if(myform.tel2.value=="" || myform.tel3.value=="") {
				alert("전화번호를 입력하시오");
				myform.tel2.focus();
				return;
				}
			if(myform.emailid.value=="" || myform.emailser.value=="") {
				alert("이메일을 입력하시오");
				myform.emailid.focus();
				return;
				}
			
			myform.submit();
		}
	</script>

	
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
 function SearchAddr()
 {
    
    var width = 300;
    var height = 400;
    var themeObj = {
            bgColor: "#162525", //바탕 배경색
            searchBgColor: "#162525", //검색창 배경색
            contentBgColor: "#162525", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
            pageBgColor: "#162525", //페이지 배경색
            textColor: "#FFFFFF", //기본 글자색
            queryTextColor: "#FFFFFF", //검색창 글자색
            //postcodeTextColor: "", //우편번호 글자색
            //emphTextColor: "", //강조 글자색
            outlineColor: "#444444" //테두리
         };
 
   new daum.Postcode({
      width: width,
      height: height,
      theme: themeObj,
        oncomplete: function(data) {
           
           
          // var zip = document.getElementById("zipcode");
          // zip.value = data.zonecode;
           
           var addr = document.getElementById("addr");
           addr.value = data.address;
           //alert(data.address); //선택주소를 출력함
        }
    }).open({
        left:(window.screen.width/2)-(width/2),
        top:(window.screen.height/2)-(height/2)
    });
    
 }   
 
</script>

<script>

	var isIdChecked = false; //중복체크가 안되었거나 사용불가 아이디가 입력됨
   
	
	function idCheck() 
	{
		//alert("idCheck");
		var id = document.getElementById("id");
		
		$.ajax({
			type:"get",
			url:"Controller",
			dataType:"json",
			data:"cmd=idCheck&id="+id.value,  //id=의 id는 name="id"의 id , id.value의 id는 javascript의 id이다 
							//(&name=value)   var id의 value가 필요한건 원래 비어있는건데 브라우저에서 입력하는 값이기 때문이다.
			complete: function(data){
				
			},
			success: function(data){
				
				var e = document.getElementById("idMsg");
				
				if(data.ret==true){
					//사용할 수 있는 아이디
					 e.style.color = "blue";
					e.innerHTML = "사용할 수 있는 아이디 입니다";
					isIdChecked = true;
					
				}else{
					 e.style.color = "red";
					
					e.innerHTML = "사용할 수 없는 아이디 입니다";
					isIdChecked = false;
				}
				//alert(data.ret);
			}
		});
	}
	
	function changeID() {
		
		var pattern = /^[a-zA-Z0-9]{8}$/gi;
		var id =$("#id").val().trim();
		var i = document.getElementById("idMsg2");
		
		if(pattern.test(id)){
			i.innerHTML ="";
		}else{
			i.innerHTML ="정규표현식에 맞지 않습니다.";
			
		}
		
		//alert("입력된 아이디가 변경됨");
		var e = document.getElementById("idMsg");
		 e.style.color = "grey";
		e.innerHTML = "<font size='-1' > 아이디 중복체크 하세요</font>";
		isIdChecked = false;
	}
	
	function pwCheck() {
		
		
		//2.비밀번호와 확인 입력이 일치하는지
		var pw1 = document.getElementById("pw1").value;
		var pw2 = document.getElementById("pw2").value;
		
		 if(pw2==null){
	    	   document.getElementById("pid").style.color = "green";
	           document.getElementById("pid").innerHTML = '비밀번호를 확인을 입력하세요';
	           isIdChecked = false;
	       }
	        else if(pw1==pw2){
	              document.getElementById("pid").style.color = "blue";
	              document.getElementById("pid").innerHTML = "암호가 확인 되었습니다.";
	              isIdChecked = true;
	              
	       }else if(pw1 != pw2 && pw2 != null) {
	           document.getElementById("pid").style.color = "red";
	           document.getElementById("pid").innerHTML = '비밀번호가 일치하지 않습니다. 다시 입력해 주세요';
	           isIdChecked = false;
	       }
	}
	
	
	
	function inputCheck() {
		//1.아이디체크를 해서 사용가능한지
		if(isIdChecked == false){
			alert("아이디를 또는 비밀번호를 확인하세요");
			return false;
		}
		//2.비밀번호와 확인 입력이 일치하는지
		
		 return true;
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
	<nav id="slidebar-wrapper">
		<a href="#"><i class="fa fa-close btn-close" id="menu-close"></i></a>
		<div class="slidebarwrap">
			<div class="slidebar-brand">
			</div>
		</div>
		<ul class="sidebar-nav">
			<li><i class="fa fa-home navicon"></i><a href="main.html">HOME</a></li>
			<li class="active"><i class="fa fa-user-plus navicon"></i><a href="member.html">회원가입</a></li>
			<li><i class="fa fa-user-o navicon"></i><a href="login.html">로그인</a></li>
			<li><i class="fa fa-fort-awesome navicon"></i><a href="trip.html">관광지</a></li>
			<li><i class="fa fa-cutlery navicon"></i><a href="restaurant.html">맛집</a></li>
			<li><i class="fa fa-shopping-basket navicon"></i><a href="specialty.html">특산물</a></li>
		</ul>
	</nav>
	
	<!-- sub contents start -->
	<div class="loginbox">
		<p>
			<span class="p1">"아름다운 경남의 푸른 고장"</span>
			<span class="p2">회원가입 하시고
			"<span class="p3">함안가자 쿠폰</span>" <br>받아 가세요</span>
		</p>
		<h3 class="readonly">회원가입</h3>
		
		
			

		<form name="myform" method="post" action="Controller" onsubmit="return inputCheck();">
		
			<ul class="join pdt20">
			
					
				<li>
					<label for="idd" class="readonly">아이디 입력</label>
					<input type="text" id="id" name="id" onkeyup="changeID()" placeholder="아이디 입력"
					 style="width:67%; margin-right:2%; float:left" pattern="[a-zA-Z0-9]{8}" required 
					 title="영문자 소문자를 입력하시오">
					<input type="button" value="중복확인" onClick="idCheck()" style="width:30%" >
					<span id="idMsg" style="padding:2px"> </span>
					<span id="idMsg2"> </span>
				</li><!--label의 for은 라벨을 클릭했을때 이동하고자 하는 위치(input의 id). input의 name은 기억장소의 이름-->
				
				<li>
					<input type="text" name="name" placeholder="이름">
				</li>
				<li>
					<label for="pww" class="readonly">패스워드 입력</label>
					<input type="password" name="pw1" required placeholder="패스워드 입력" 
					id="pw1" title="비밀번호를 입력해주세요" style="font-family:Arial,FontAwesome"
					 onkeyup="pwCheck()">
				</li>
			<!--   <label for="fff">업로드 파일 선택</label><input type="file" name="upfile" id="fff">  //  <form enctype="multipart/form-data"> -->
				<li>
					<label for="pww2" class="readonly">패스워드 확인</label>
					<input type="password" name="pw2" required placeholder="패스워드 확인" 
					id="pw2" title="패스워드 확인을 입력해주세요" style="font-family:Arial,FontAwesome"  
					onkeyup="pwCheck()">
				</li>
				<li>
					<p id="pid" style="padding-left:10px; text-align:left;">  </p>
				</li>
				<li>
					<label for="tell" class="readonly">전화번호 입력</label>
					<select id="tell" name="tell">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
					</select>
					<input type="text" name="tel2" class="tel2">
					<input type="text" name="tel3" class="tel3">
				</li>
				<li>
					<label for="email" class="readonly">메일주소입력</label>
					<input type="text" name="emailid" class="emailid" id="email">
					<select name="emailser">
						<option value="">메일선택</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmali.net">hanmeil.net</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</li>
				<li>
						<input type="text" id="addr" name="addr" placeholder="주소" style="width:78%; margin-right:2%">
						<input type="button" value="주소검색" name="btnAddr"
						 onClick="SearchAddr()" style="width:18%; background-color:skyblue">
					
				</li>
				
			<li class="ok">
<!-- 				<a onClick="javascript:joincheck();">가입을 완료합니다</a> -->
				<input type="submit" value="가입을 완료합니다" name="go">
			</li> 
		
<!-- 				<li style="width:48%; margin-right:2%; float:left;"><input type="submit" value="가입을 완료합니다" style="background-color:pink"></li> -->
			<li class="cancel">
				<a href="login.html">취소</a>
			
			</li>
			<input type="hidden" name="cmd" value="reg"/>
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




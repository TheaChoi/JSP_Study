<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ko">
<head>
<title>회원가입</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1, user-scalaboe-no">
<link href="css/mystyle.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">


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
           
        	
        	//var zip = document.getElementById("zipcode");
        	//zip.value = data.zonecode;
        	
        	var addr = document.getElementById("address1");
        	addr.value = data.address;
        	//alert(data.address); //선택주소를 출력함
        }
    }).open({
        left:(window.screen.width/2) - (width/2),
        top:(window.screen.height/2) - (height/2)
    });
    
 }  
</script>

<script>

var isIdChecked = false; //중복 체크가 안되었거나 사용할 수 없는 아이디임
						 //true면 사용할 수 있는 아이디임
						 
						 
var chg = false; //정규표현식 맞는 경우

function idCheck()
{
	if(chgId==true){
	var id=document.getElementById("id_input");
	
	$.ajax({
		type:"GET",
		url:"Controller", 
		dataType:"json",
		data:"cmd=idCheck&id="+id.value,  //"id="의 id는 name="id"의 id
		complete:function(data)
		{
			
		},
		success:function(data)
		{
			var e = document.getElementById("idMsg");
			
			if(data.ret==true) //사용할 수 있는 아이디
			{
				e.style.color ="blue";
				e.innerHTML ="<font size='-1' >사용할 수 있는 아이디 입니다.</font>";
				isIdChecked = true;
			}
			else //사용할 수 없는 아이디
			{
				e.style.color ="red";
				e.innerHTML ="<font size='-1' >사용할 수 없는 아이디 입니다.</font>";
				isIdChecked = false;
			}
			//alert(data.ret);
			
		}

	                                                                                                                
	
});
	}else{
		return null;
	}
}
	//alert("입력 아이디 : "+id.value);
	
function chgId()
	{
		var e=document.getElementById("idMsg");
		
		
		var pattern = /^[a-zA-z][a-zA-z0-9]{5,15}$/gi;
		var input = $("#id_input").val().trim();
		
		e.style.color = "red";
		e.innerHTML ="<font size='-1' >영어숫자 8자로 작성후 중복체크해주세요.</font>";
		isIdChecked=false;
		//alert("입력된 아이가 변경됨");
		
		if (pattern.test(input)) 
		{
			e.style.color="blue";
			e.innerHTML="<font size='-3' >올바른 형식입니다.</font>"
			chg = true;
			
		} else {
			
    		e.innerHTML="<font size='-3' >ID는 영문자로 시작하여 숫자를 포함(한글 및 특수문자 제외), 6자리 이상 16자리 이하 작성</font>";
   		 	chg = false;
   		
		}

		
	}


	
function inputCheck()
	{
		//(1) 아이디 체크를 해서 사용가능한 아이디인지 확인
		
		if(isIdChecked == false)
			{
				alert("아이디 중복 체크를 해주세요.");
				return false;
			}
		else
			{
				alert("폼 입력 체크")
				return true; //form의 onsubmit에 false가 넘어가서 가입이 되지 않는다.
			}
	}
	

</script>
<script>
 function PwCheck()
 {
     var pw = document.getElementById("pw_input").value;
     var pw2 = document.getElementById("pw_input2").value;
	 
	
		   if (pw != pw2) {
	        	 document.getElementById("inputkey").style.color = "red";
	            document.getElementById("inputkey").innerHTML = "비밀번호가 일치하지 않습니다. 다시 입력해 주세요.";
	           
	        }else{
	        	   document.getElementById("inputkey").style.color = "blue";
	        	   document.getElementById("inputkey").innerHTML = "비밀번호가 일치합니다.";
	    	}
	

				
 }
</script>

<script>
// 		function logcheck()
// 		{
// 			if(myform.id.value=="")
// 			{
// 				alert("아이디를 입력해주세요.");
// 				myform.id.focus();
// 				return;
// 			}else if(myform.name.value=="")
// 			{
// 				alert("이름을 입력해주세요.");
// 				myform.name.focus();
// 				return;
// 			}
// 			else if(myform.pw.value=="")
// 			{
// 				alert("비밀번호를 입력해주세요.");
// 				myform.pw.focus();
// 				return;
// 			}else if(myform.pw2.value=="")
// 			{
// 				alert("비밀번호를 한번 더 입력해주세요.");
// 				myform.pw2.focus();
// 				return;
// 			}else if(myform.cellnum.value=="")
// 			{
// 				alert("전화번호를 입력해주세요.");
// 				myform.cellnum.focus();
// 				return;
// 			}else if(myform.email.value=="")
// 			{
// 				alert("메일주소를 입력해주세요.");
// 				myform.email.focus();
// 				return;
// 			}else if(myform.address1.value=="")
// 			{
// 				alert("주소를 입력해주세요.");
// 				myform.address.focus();
// 				return;
// 			}
// 			myform.submit();
// 		}
	</script>
</head>
<body id="top1">


	<header class="header"> <a href="#" class="btn_gnb"
		id="menu-toggle"><i class="fa fa-navicon headericon1"></i></a>
	<h1 class="logo">
		<span>제주</span>여행
	</h1>
	<a href="login.html" class="btn_search"><i
		class="fa fa-long-arrow-left"></i></a> </header>
	<nav id="slidebar-wrapper"> <a href="#" id="menu-close"
		class="btn-close"><i class="fa fa-close"></i></a>
	<div class="slidebarwrap">
		<div class="slidebar-brand"></div>
		<a href="login.html"><p class="nickname">로봇왕위잉치킹</p></a> <a href="#"><p
				class="nickname1">로그아웃</p></a>
		<div class="slidebar-bottomline"></div>
	</div>
	<ul class="sidebar-nav">

		<li><i class="fa fa-comments-o"></i><a href="message.html">메세지</a></li>
		<li><i class="fa fa-bell-o"></i><a href="notice.html">알림</a></li>
		<li><i class="fa fa-umbrella"></i><a href="weather.html">날씨</a></li>
		<li><i class="fa fa-map-marker"></i><a href="location.html">내
				위치</a></li>
		<li><i class="fa fa-star-o"></i><a href="favorites.html">즐겨찾기</a></li>
		<li><i class="fa fa-map-signs"></i><a href="help.html">도움말</a></li>
		<li><i class="fa fa-gear"></i> <a href="set.html">설정</a></li>
	</ul>

	</nav>

	<!-- contents start -->

	<section class="loginbox">
	<h2>
		<span>회원</span>가입
	</h2>
	<p class="readonly">회원가입</p>
	<form name="myform" class="form_join" method="post" action="Controller" onsubmit="return inputCheck()">
		<!--텍스트가 아닌 파일을 넘길때는 post가 아니라 enctype="multipart/form-data"라고 해야함 -->

		<div class="id_join">
			<h4 class="idtxt">생성할 아이디를 입력해주세요.</h4>
			<label for="id_input" class="readonly">아이디를 입력하세요</label> 
			<input
				type="text" name="id" onkeyup="chgId()"id="id_input" class="id_input_join"
				placeholder="&#xf2bc;    아이디입력"  
				style="font-family: Arial, FontAwesome"  title="영문자 소문자를 입력하세요." />  
		</div>
<!-- required = 필드에 어떠한것이든 값을 입력해야함 -->
		<a href="#" class="idcheck" onclick="idCheck()">중복확인 </a>
		<span id="idMsg" style="padding-left:20px;" ></span>
		<h4 class="pwtxt">이름을 입력해주세요.</h4>
		<div class="pw_join">

			<label for="name" class="readonly">이름을 입력하세요. </label> 
			<input type="text" name="name" id="name"placeholder="이름을 입력해주세요" class="pw_input_join" />

		</div>
		<h4 class="pwtxt">생성할 비밀번호를 입력하시고 확인해주세요.</h4>
		<div class="pw_join">

			<label for="pw_input" class="readonly">패스워드를 입력하세요 </label> 
			<input
				type="password" name="pw" id="pw_input" class="pw_input_join"
				onkeyup="PwCheck()" placeholder=" &#xf023;     비밀번호입력" 
				style=" font-family: Arial, FontAwesome" title="패스워드를 입력해주세요."
				 required/>
		</div>
	
		<div class="pw2_join">
			<label for="pw_input" class="readonly">패스워드를 한번 더 입력하세요 </label>
			<input
				type="password" name="pw2" id="pw_input2" onkeyup="PwCheck()"
				class="pw2_input_join" placeholder=" &#xf023;     비밀번호확인"
				style="font-family: Arial, FontAwesome" title="비밀번호를 입력해주세요." required/>
		</div>
		<p id="inputkey" style="padding-left:20px;"> </p>
		
		
		<div class="tell">
			<h4 class="phone">전화번호를 입력해주세요.</h4>
			<label for="tell" class="readonly">전화번호 입력 </label> <select
				class="tell_join" id="tell" name="tell">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="017">017</option>
			</select>
			<p class="s1">-</p>
			<label for="cellnum" class="readonly">전화번호 앞자리입력</label> <input
				type="text" id="cellnum" name="tel2" class="tel2">
			<p class="s2">-</p>
			<label for="cellnum1" class="readonly">전화번호 뒷자리입력</label> <input
				type="text" id="cellnum1" name="tel3" class="tel3">
		</div>

		<div class="e-mail">
			<h4 class="mail-e">메일주소를 입력해주세요.</h4>
			<label for="email" class="readonly">메일주소 입력</label> <input
				type="text" name="emailid" class="emailid" id="email">

			<p class="s3">@</p>
			<label for="emailser" class="readonly">이메일 도메인 선택</label> <select
				name="emailser" class="email_sel" id="emailser">
				<option value="">메일선택</option>
				<option value="naver.com">naver.com</option>
				<option value="hanmail.net">hanmail.net</option>
				<option value="gmail.com">gmail.com</option>
			</select>


		</div>

		<div class="e-mail">
			<h4 class="idtxt">주소를 입력해주세요.</h4>
			<input type="text" id="address1" name="address1" class="emailid">
			<input type="button" class="idcheck" value="주소검색"
				onclick="SearchAddr()">

		</div>
		<input type="hidden" name="cmd" value="reg"> <input
			type="submit" class="idcheck" value="회원가입" onclick="logcheck()">
		<input type="reset" class="idcheck" value="다시쓰기">

	</form>
	</section>
	<!-- contents end -->
	<footer class="footer">
	<p>COPYRIGHT SANGMIN ALL RIGHTS RESERVED</p>
	</footer>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
			$("#menu-toggle").click(function(e)
			{
				e.preventDefault();
				$("#slidebar-wrapper").toggleClass("active");
			});
			$("#menu-close").click(function(e)
			{
				e.preventDefault();
				$("#slidebar-wrapper").toggleClass("active");
			});
			
			$(".btn_search").click(function()
			{
				$(".bar-search").toggle();
			});
		</script>

</body>
</html>
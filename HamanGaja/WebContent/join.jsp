<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
	<title>함안가자</title>
	<meta charset="utf-8">
	<link href="css/mystyle.css" rel="stylesheet">
		<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<script>
	
		var isExpOk = false;
		var isIdChecked = false; //중복체크가안되었거나 사용할수없는 아이디임
		var checkResult = false; //중복체크의 결과
		var isPwChecked = false;
		
		function joincheck() {
			/*****************
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
			alert("가입이 완료되었습니다. 로그인 창으로 되돌아갑니다.");
			***************/

			if(isIdChecked == false){  //아직 중복체크 하지 않음
				alert("아이디 중복체크를 해주세요");
				return false;
			}
			if(checkResult == false) //중복체크 했지만 ... 중복 아이디임.
			{
				alert("중복된 아이디 입니다. 다른 아이디를 입력하세요.");
				return false;
			}
			if(isIdChecked == true && checkResult == true){
				
			myform.submit();
			}
		}
		
		
		function idCheck(){
		
			var tag = document.getElementById("idd");
			var id = tag.value;
			var idmsg = document.getElementById("idMsg"); //이게 함수 바깥에 있으면 값을 못가져오지
			//아이디 중복체크
			
			if( idExpOk==true){
							
								
				$.ajax({
						type:"GET",  //파라미터 전송 방식 GET/POST
						url:"idCheck.mem", //링크시킬 페이지 url주소
						data:"id="+id, //GET/POST 보낼 파라미터들
						dataType:"json", //처리 결과가 반환되는 방식
						success:function(result)  //ajax가 처리를 성공적으로 끝내고 결과를 반환함 (result는 json객체 이름(객체안에 {이름:값}))
						{
							
							if(result.ret==true){
								idmsg.style.color="blue";
								idmsg.innerHTML =  "<font>사용 가능한 아이디입니다.</font>";
								checkResult = true;
							}else{
								idmsg.style.color="red";
								idmsg.innerHTML = "<font>중복된 아이디입니다.</font>";
								checkResult = false;
							}
						
							
							isIdChecked = true;
						},
						error:function()
						{
							alert("아이디 중복 체크 실패");
						}
				});
			
			}
		}
		
		function idChanged(){  //중복검사후 아이디변경
			
			var tag = document.getElementById("idd");
			var id = tag.value.trim();
			var idmsg = document.getElementById("idMsg"); //이게 함수 바깥에 있으면 값을 못가져오지
			
			isIdChecked = false;
			checkResult = false;
			
			//아이디 정규표현식 검사
			var exp = /^[a-z][a-z0-9]{5,15}$/gi;  
			
		// 표현할 규칙 
		// /^This/:시작   /end$/:끝  /This/:완전일치   /se*/:e를0번이상 반복   /se?/:0 or 1번  /se{n,m}/:e를 n이상m이하반복
			if(exp.test(id)==true) //규칙에 맞는지 테스트
			{
				idmsg.style.color="grey";
				idmsg.innerHTML =  "<font>올바른형식</font>";
				isExpOk = true;
				return;
				
			}else{
				idmsg.style.color="grey";
				idmsg.innerHTML =  "<font>아이디를 영문자/숫자를 섞어 5~15자 이내로 입력해주세요</font>";
				isExpOk = false;
				return;
			}
			
			
		}
		
		function pwCheck(){  //패스워드 확인 검사
			var pwmsg = document.getElementById("idMsg");
			var pw1 = document.getElementById("pw1").value;
			var pw2 = document.getElementById("pw2").value;
			
			if(pw1 == pw2){
				idmsg.style.color="red";
				idmsg.innerHTML = "<font>중복된 아이디입니다.</font>";
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
			<span class="p1">"아름다운 경남의 푸른 고장"</span>
			<span class="p2">회원가입 하시고
			"<span class="p3">함안가자 쿠폰</span>" <br>받아 가세요</span>
		</p>
		<h3 class="readonly">회원가입</h3>
		<form name="myform" method="post" action="reg.mem">
			<ul class="join pdt20">
				<li>
					<label for="idd" class="readonly">아이디 입력</label>
					<input class="id" type="text" name="id" placeholder="&#xf2c1;  아이디 입력" 
					id="idd" title="아이디를 입력해주세요" style="font-family:Arial,FontAwesome" onKeyup="idChanged()">  
					<a href="#" class="idcheck" onClick="idCheck()">중복확인</a>
					<span class="idmsg" id="idMsg" ></span>
				</li><!--label의 for은 라벨을 클릭했을때 이동하고자 하는 위치(input의 id). input의 name은 기억장소의 이름-->
				<li>
					<label for="pww" class="readonly">패스워드 입력</label>
					<input type="password" name="pw1" placeholder="&#xf084;  패스워드 입력" 
					id="pw1" title="비밀번호를 입력해주세요" style="font-family:Arial,FontAwesome">
				</li>
			<!--   <label for="fff">업로드 파일 선택</label><input type="file" name="upfile" id="fff">  //  <form enctype="multipart/form-data"> -->
				<li>
					<label for="pww2" class="readonly">패스워드 확인</label>
					<input type="password" name="pw2" placeholder="&#xf084;  패스워드 확인" 
					id="pw2" title="패스워드 확인을 입력해주세요" style="font-family:Arial,FontAwesome">
					<span class="pwmsg" id="pwMsg" ></span>
				</li>
				<li>
					<label for="tel1" class="readonly">전화번호 입력</label>
					<select id="tel1" name="tel1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
					</select>
					<input type="text" name="tel2" class="tel2">
					<input type="text" name="tel3" class="tel3">
				</li>
				<li>
					<label for="email" class="readonly">메일주소입력</label>
					<input type="text" name="emailid" class="emailid" id="email" required>
					<select name="emailser">
						<option value="">메일선택</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmali.net">hanmeil.net</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</li>
				<li>
					<input type="text" id="sample6_postcode" placeholder="우편번호" style="width:70%;">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" style="width:27%;"><br>
					<input type="text" id="sample6_address" name="address1" placeholder="주소">
					<input type="text" id="sample6_address2" name="address2" placeholder="상세주소">
					
					<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
					<script>
					    function sample6_execDaumPostcode() {
					        new daum.Postcode({
					            oncomplete: function(data) {
					                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
					
					                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
					                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					                var fullAddr = ''; // 최종 주소 변수
					                var extraAddr = ''; // 조합형 주소 변수
					
					                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					                    fullAddr = data.roadAddress;
					
					                } else { // 사용자가 지번 주소를 선택했을 경우(J)
					                    fullAddr = data.jibunAddress;
					                }
					
					                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
					                if(data.userSelectedType === 'R'){
					                    //법정동명이 있을 경우 추가한다.
					                    if(data.bname !== ''){
					                        extraAddr += data.bname;
					                    }
					                    // 건물명이 있을 경우 추가한다.
					                    if(data.buildingName !== ''){
					                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					                    }
					                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
					                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
					                }
					
					                // 우편번호와 주소 정보를 해당 필드에 넣는다.
					                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
					                document.getElementById('sample6_address').value = fullAddr;
					
					                // 커서를 상세주소 필드로 이동한다.
					                document.getElementById('sample6_address2').focus();
					            }
					        }).open();
					    }
					</script>
				</li>
				<li><input type="submit" onsubmit="joincheck(); return false" value="가입을 완료합니다" class="ok"></li>
				<li class="cancel"><a href="login.html">취소</a></li>
				
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

















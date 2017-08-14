<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>회원가입</title>

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
</head>

<body>
	<h1>회원가입</h1>
	<form method="post" action="">
		<table border='1'>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id"><input type="button" value="중복검사" name="overlap"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="pw1"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="addr" name="addr"><input type="button" 
				value="주소검색" name="btnAddr" onClick="SearchAddr()"></td>
			</tr>
		</table>
	</form>
</body>
</html>
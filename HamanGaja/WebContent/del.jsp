<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script>
	var pwChecked = false;

	function pwCheck(){
		
		var pw = document.getElementById("delPw").value;
		//alert(pw);
	
		$.ajax({
			type:"POST",
			url:"pwCheck.mem",
			data:"pw="+pw,
			dataType:"json",
			success:function(data){
				if(data.ret==true){
					pwChecked=true;
					var ret = confirm("정말로 탈퇴하시겠습니까?");
					if(ret==true){
						alert("회원탈퇴를 완료하였습니다.");
					}else{
						alert("회원탈퇴를 취소합니다.");
						location.href="main.jsp";
						return;
					}
					delform.submit();
		
				}else if(data.ret==false){
					pwChecked=false;
					alert("잘못된 비밀번호를 입력하였습니다.");
					return;
				}
			},
			error:function(){
				alert("패스워드 오류");
			}
			
		});
		
	}
	
// 	function delUser(){
		
// 		if(pwChecked==true){
// 			var ret = confirm("정말로 탈퇴하시겠습니까?");
			
// 			if(ret==true){
// 				$.ajax({
// 					type:"GET",
// 					url:"del.mem",
// 					dataType:"JSON",
// 					success:function(){
// 						if(result.ret == true){
// 							alert("회원탈퇴를 완료하였습니다.");
// 							delform.submit();
// 						}else{
// 							alert("회원탈퇴를 취소합니다.");
// 							return;
// 						}
// 					},
// 					error:function(){
						
// 					}
// 				});
				
// 			}else{
// 				alert("회원탈퇴를 취소합니다");
// 			}
			
// 		}else{
// 			alert("잘못된 비밀번호를 입력하였습니다.");
// 			return;
// 		}
// 	}
</script>
</head>
<body>
<form name="delform" action="del.mem" method="POST" onsubmit="pwCheck();return false;">
	<h3>탈퇴하시려면 비밀번호를 한번 더 입력하세요</h3>
	<input type="text" id="delPw" >
	<h4>탈퇴를 진행하시면 모든 데이터가 삭제됩니다. 동의하시겠습니까?</h4>
	<input type="submit" value="네, 동의합니다">
	<button type="button" onclick="location.href='main.jsp'"> 동의하지 않습니다.</button>
</form>
</body>
</html>
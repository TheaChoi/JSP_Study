<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajax 예제</title>

<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

	function xhttp() {
		
		//location.href="test.jsp";
		
		var x = document.getElementById("x");
		var y = document.getElementById("y");
		
		var xvalue = x.value;
		var yvalue = y.value;
		
		$.ajax({
			type:"get",
			url:"test.jsp",
			dataType:"json",
			data:"x="+xvalue+"&y="+yvalue,
			complete: function(data){
				
			},
			success: function(data){
				//alert(data);	
				var res = document.getElementById("result");
				res.value = data.plus;
				var res1 = document.getElementById("result1");
				res1.value = data.mul;
			}
		})
		
	}
</script>
</head>

<body>
x = <input type="text" id="x">
y = <input type="text" id="y">
	<input type="button" value="실행하기" onClick="xhttp()"><br>
더하기 <input type="text" id="result">
곱하기 <input type="text" id="result1">
</body>

</html>
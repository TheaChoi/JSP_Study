<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일업로드</title>

<script>
function CheckFileType() {
	
	var file = document.getElementById("file");
	var name = file.value;///////////
	var idx = name.lastIndexOf(".");
	var ext = name.substring(idx).toLowerCase();
	//.alert(ext);
	
	if(ext == ".jpg" || ext == ".bmp" || ext == ".png") {
		
		return true;
	}else{
		alert("업로드 할 수 없는 파일 형식입니다.");
		return false;
	}
	
}
</script>

</head>
<body>

<h1>파일 업로드 하기</h1>

<form action="upload.jsp" method="post" enctype="multipart/form-data"
		 OnSubmit="return CheckFileType()">
	<input type="file" id="file" value='파일선택' name="file" >
	<input type="submit" value="전송">
</form>

</body>
</html>
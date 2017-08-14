<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm.jsp</title>
</head>
<body>
	<h1>글쓰기</h1>
	<form method="post" action="write.bo">
		<table border='1'>
			<tr><td>제목</td>
				<td><input type="text" name="title" required></td>
			</tr>
			<tr><td>내용</td>
				<td><textarea name="content" cols='40' rows='32' maxlength='200' required></textarea></td>
			</tr>
		</table>
			<input type="submit" value="저장">
			<input type="reset" value="다시쓰기">
	</form>
</body>
</html>
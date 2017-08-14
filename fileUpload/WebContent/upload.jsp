<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.FileRenamePolicy"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일업로드</title>
</head>
<body>

<h1>파일업로드</h1>

<%
	//업로드된 파일 저장 JSP코드
	String savePath = "c:/upload"; //파일 저장 폴더
	int maxSize = 1024*1024*10;     //1024바이트=1킬로바이트, 1024*1024=1메가바이트
	
	String encType = "UTF-8";   //스트링의 인코딩 형식
	FileRenamePolicy rename = new DefaultFileRenamePolicy();
	
	MultipartRequest MR = new MultipartRequest(request, savePath, maxSize, encType, rename);
	
	/////////////////////////////
	String orgName=MR.getOriginalFileName("file");
	String saveName=MR.getFilesystemName("file");
	
	System.out.println("업로드 파일 이름 : "+orgName);
	System.out.println("저장된 파일 이름 : "+saveName);
	
%>

<img src="<%=savePath%>/<%=saveName%>">

</body>
</html>
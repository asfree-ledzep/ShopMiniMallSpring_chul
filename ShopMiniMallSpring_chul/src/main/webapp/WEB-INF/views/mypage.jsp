<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
</head>
<body>
<h3>mypage화면입니다.</h3>
<jsp:include page='common/top.jsp'></jsp:include><br>
<jsp:include page='common/menu.jsp'></jsp:include><br>
<hr>
<jsp:include page='member/mypage.jsp'></jsp:include>
</body>
</html>
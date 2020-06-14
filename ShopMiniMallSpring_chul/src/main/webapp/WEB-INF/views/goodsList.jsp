<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쇼핑리스트화면입니다</title>
</head>
<body>
<h3>쇼핑리스트화면입니다</h3>
<jsp:include page='common/top.jsp'></jsp:include><br>
<jsp:include page='common/menu.jsp'></jsp:include><br>
<hr>
<jsp:include page='goods/goodsList.jsp'></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.dto.MemberDTO" %>

<c:if test= "${! empty login }">
<% 
	MemberDTO dto = (MemberDTO)session.getAttribute("login");
	String username= dto.getUsername();
%>
 <%= username %>님 환영합니다. 
<a href="logout">로그아웃</a>
<a href="cartList">장바구니</a>
<a href="mypage">마이페이지</a>
</c:if>
<c:if test= "${ empty login }">
<a href="loginUI">로그인</a>
<a href="memberForm">회원가입</a>
</c:if>

    
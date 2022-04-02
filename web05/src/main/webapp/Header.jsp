<%@ page import="spms.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member"
			scope="session"
			class="spms.vo.Member" />
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
SPMS(Simple Project Management System)
<%if (member.getEmail() != null) { 
/* 	System.out.println("email : " + member.getEmail() +
			", name : " + member.getName() +
			", 경로 : " + request.getContextPath()); */
%>
<span style="float:right;">
<%=member.getName() %>
<a style="color:white;"
	href="<%=request.getContextPath() %>/auth/logout">로그아웃</a>
</span>
<% } %>
</div>
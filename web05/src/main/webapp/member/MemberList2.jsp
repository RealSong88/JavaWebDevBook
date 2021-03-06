<%@page import="spms.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"></jsp:include>
<h1>회원목록</h1>
<p><a href='add'>신규 회원</a></p>
<jsp:useBean id="members"
			scope="request" 
			class="java.util.ArrayList"
			type="java.util.ArrayList<spms.vo.Member>"
			/>
			
<%
for(Member member : members) {
%>
<%=member.getNo()%>,
<a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
<%=member.getEmail()%>,
<%=member.getCreateDate()%>
<a href='delete?no=<%=member.getNo()%>'>[삭제]</a><br>
<%} %>

<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>
<%-- <%@page import="bundle.MyResourceBundle"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html" charset="UTF-8">
<title>Hello</title>
</head>
<body>
	<c:out value="테스트1"></c:out>
	<c:out value="${null }">test2...</c:out>
	<c:out value="테스트3">test3??</c:out>
	<h3>프로퍼티 값 변경</h3>
	<%!public static class MyMember {
		int no;
		String name;

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
%>
<%
MyMember member = new MyMember();
member.setNo(111);
member.setName("Faker");
pageContext.setAttribute("member", member);
%>
${member.name }<br>
<c:set target="${member }" property="name" value="Faker2" />
${member.name }

</body>
</html>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="spms.vo.Member"%>
<%@page import="spms.vo.MyResourceBundle" %>
<%@page import="spms.vo.MyResourceBundle_ko_KR" %>
<%-- <%@page import="bundle.MyResourceBundle"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-type" content="text/html" charset="UTF-8">
<title>Hello</title>	
</head>
<body>
<%
pageContext.setAttribute("scores", new int[]{90,80,760, 4});
%>
<p>
${scores[2] }
</p>
<%
List<String> nameList = new LinkedList<String>();
nameList.add("홍길동");
nameList.add("임꺽정");
nameList.add("일지매");
pageContext.setAttribute("nameList", nameList);
%>
<h1>
${nameList[2] }
</h1>
<%
Map<String, String> map = new HashMap<String, String>();
map.put("so1", "홍길동");
map.put("so2", "임꺽정");
map.put("so3", "일지매");
pageContext.setAttribute("map", map);
%>
<h3>
${map.so1}
</h3>
<%
pageContext.setAttribute("member", 
		new Member()
		.setNo(333)
		.setName("nadayong")
		.setEmail("nada@yong.com")
		);
%>
<h1>
${member.getNo() } <br>
${member.getName() } <br>
${member.getEmail() } <br>
</h1>

<%
pageContext.setAttribute("myRb", ResourceBundle.getBundle("MyResourceBundle_ko_KR"));
%>
<p>
${myRb.Test }
</p>
<p>
${myRb.OK }
</p>
</body>
</html>
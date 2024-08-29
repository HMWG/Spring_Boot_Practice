<%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-20
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 작성화면</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<br>
<%
    String action = (String) request.getAttribute("action");
    Integer no = (Integer) request.getAttribute("no");
%>
<form action="<%=request.getContextPath()%>/chat/<%=action%><% if(action.equals("update")) { %>?no=<%=no%><% } %>" method="post" enctype="multipart/form-data">
    채팅방 이름 : <input type="text" name="name"><br>
    설명 : <textarea name="description"></textarea><br>

    <input type="submit" value="작성완료">
</form>

</body>
</html>

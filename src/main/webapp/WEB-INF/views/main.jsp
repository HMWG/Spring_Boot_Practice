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
    <title>마이톡</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<%@ include file="common/alert.jsp"%>
<h2>마이톡 시스템</h2>
<a href="<%=request.getContextPath()%>/chat/create">[채팅방 만들기]</a>
<a href="<%=request.getContextPath()%>/chat/list">[채팅방 목록으로]</a>
</body>
</html>
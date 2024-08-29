<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.grepp.spring_practice.model.dto.ChatRoomDTO" %><%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-20
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판목록</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<a href="<%=request.getContextPath()%>/chat/create">[채팅방 만들기]</a><br>
<table border="1">
    <%
        Map<String, Object> pageData = (Map<String, Object>) request.getAttribute("pageData");
        List<ChatRoomDTO> cList = (List<ChatRoomDTO>) pageData.get("cList");
        for(ChatRoomDTO c: cList){
    %>
    <tr>
        <td><%=c.getChatRoomNo()%></td>
        <td><a href="<%=request.getContextPath()%>/chatting/<%=c.getChatRoomNo()%>"><%=c.getName()%></a></td>
        <td><%=c.getDescription()%></td>
        <td><%=c.getCreatedAt()%></td>
    </tr>
    <%
        }
    %>
    <tr>
        <td colspan="5">
            <%
                int nowPage = (int) pageData.get("page");
                int startPage = (int) pageData.get("startPage");
                int endPage = (int) pageData.get("endPage");
                int totalPage = (int) pageData.get("totalPageCount");
                if (1<startPage){%>
            <a href="<%=request.getContextPath()%>/chat/list?page="<%=startPage-1%>>[이전]</a>
            <%
                }
                for (int i = startPage; i <= endPage; i++) {
            %>
            <a href="<%=request.getContextPath()%>/chat/list?page=<%=i%>"><%=i%> </a>
            <%
                }
                if (endPage < totalPage){
            %>
            <a href="<%=request.getContextPath()%>/chat/list?page="<%=endPage+1%>>[다음]</a>
            <%
                }
            %>
        </td>
    </tr>
</table>

</body>
</html>

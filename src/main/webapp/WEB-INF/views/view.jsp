<%@ page import="com.grepp.spring_practice.model.dto.ChatRoomDTO" %>
<%@ page import="com.grepp.spring_practice.model.dto.ChatDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 관리자
  Date: 2024-08-20
  Time: 오전 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>글 상세화면</title>
</head>
<body>
<%@ include file="common/header.jsp"%>
<%@ include file="common/alert.jsp"%>

<%
    ChatRoomDTO chatRoomDTO = (ChatRoomDTO) request.getAttribute("chatRoom");
    List<ChatDTO> chatList = (List<ChatDTO>) request.getAttribute("chatList");
%>
<table border="1">
    <tr>
        <td>채팅방 번호 : </td>
        <td><%=chatRoomDTO.getChatRoomNo()%></td>
    </tr>
    <tr>
        <td>채팅방 이름 : </td>
        <td><%=chatRoomDTO.getName()%></td>
    </tr>
    <tr>
        <td>채팅방 설명 : </td>
        <td><%=chatRoomDTO.getDescription()%></td>
    </tr>
    <tr>
        <td>생성일시 : </td>
        <td><%=chatRoomDTO.getCreatedAt()%></td>
    </tr>
    <tr>
        <td colspan="2">채팅 내역</td>
    </tr>
    <%
        if(chatList != null && !chatList.isEmpty()){
    %>
            <%
                for (ChatDTO c : chatList) {
            %>
    <tr>
        <td>
            작성자 : <%=c.getUserNo()%>
        </td>
        <td>
            내용 : <%=c.getChatText()%>
        </td>
    </tr>
            <%
            }
            %>
    <%
        }
    %>
</table>
<form action="<%=request.getContextPath()%>/chatting/<%=chatRoomDTO.getChatRoomNo()%>" method="post">
    채팅하기 <input type="text" name="message"/>
    <input type="submit" value="전송">
</form>

<a href="<%=request.getContextPath()%>/chat/delete?no=<%=chatRoomDTO.getChatRoomNo()%>">[채팅방 삭제하기]</a>
<a href="<%=request.getContextPath()%>/chat/update?no=<%=chatRoomDTO.getChatRoomNo()%>">[채팅방 수정하기]</a><br>
<a href="<%=request.getContextPath()%>/chat/list">[게시판 목록으로]</a>


</body>
</html>

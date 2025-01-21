<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String title = "Ошибка 404 - ничего не найдено";
    String message = "buy wrld! \uD83D\uDC80";
%>
<jsp:include page="error.jsp">
    <jsp:param name="title" value="<%= title %>" />
    <jsp:param name="message" value="<%= message %>" />
</jsp:include>

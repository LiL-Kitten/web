<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String title = "Ошибка 403 - доступ запрещен";
    String message = "куда полез?";
%>
<jsp:include page="error.jsp">
    <jsp:param name="title" value="<%= title %>" />
    <jsp:param name="message" value="<%= message %>" />
</jsp:include>

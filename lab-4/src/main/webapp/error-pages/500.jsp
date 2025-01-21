<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String title = "500 - внутренняя ошибка сервера";
    String message = "все плохо, очень плохо, вызывайте скорую!!!!";
%>
<jsp:include page="error.jsp">
    <jsp:param name="title" value="<%= title %>" />
    <jsp:param name="message" value="<%= message %>" />
</jsp:include>

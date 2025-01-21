<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String title = "Ошибка 400 - плохой запрос, очень плохой";
    String message = "ваш запрос очень важен для нас но он не может быть обработан(( Пожалуйста, проверьте введенные данные.";
%>
<jsp:include page="error.jsp">
    <jsp:param name="title" value="<%= title %>" />
    <jsp:param name="message" value="<%= message %>" />
</jsp:include>

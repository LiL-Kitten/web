<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String title = request.getParameter("title");
    String message = request.getParameter("message");

    if (title == null || title.isEmpty()) {
        title = "Ошибка";
    }
    if (message == null || message.isEmpty()) {
        message = "Произошла ошибка. Пожалуйста, попробуйте позже.";
    }
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/error.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <title><%= title %></title>
</head>
<body>
<header>
    <h1><%= title %></h1>
</header>
<main>
    <p><%= message %></p>
    <p>
        <a href="${pageContext.request.contextPath}">нажми на меня и я верну тебя домой =)</a>
    </p>
</main>
</body>
</html>

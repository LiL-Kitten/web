<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>lab-1</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <header class="fio">Иевлев Ринат Андреевич номер группы: P3230 <br>Вариант лабораторной: 377912</header>
        <main>
            ваша точка попала туда-то ниже будет представлена таблица результатов

            <div class="picture">
                <img id="mySvg" src="img/graph.svg" alt="graph">
            </div>
            <a class="button" href="${pageContext.request.contextPath}/index.jsp"> я верну тебя домой! </a>

        </main>
        <jsp:include page="table.jsp"/>
    </body>
</html>
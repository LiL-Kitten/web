<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>lab-1</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <script src="${pageContext.request.contextPath}/js/index.js" async></script>
    </head>
    <body>
        <header class="fio">Иевлев Ринат Андреевич номер группы: P3230 <br>Вариант лабораторной: 377912</header>
        <main>
            ваша точка попала туда-то ниже будет представлена таблица результатов

            <div class="picture">
                <svg id="mySvg" width="400" height="400" xmlns="http://www.w3.org/2000/svg">
                    <rect width="400" height="400" fill="white"></rect>
                    <line x1="200" y1="0" x2="200" y2="400" stroke="black" stroke-width="2"></line>
                    <line x1="0" y1="200" x2="400" y2="200" stroke="black" stroke-width="2"></line>
                    <path d="
                M 50,200
                L 50,275
                L 200,275
                L 200,350
                L 275,200
                Q 275 125 200 125
                L 200,200
                Z"
                          fill="lightblue"
                          stroke="blue"></path>
                    <polygon points="195,5 205,5 200,0" fill="black"></polygon>
                    <text x="208" y="8" font-family="Arial" font-size="12" fill="black">
                        y
                    </text>
                    <polygon points="400,200 395,205 395,195" fill="black"></polygon>
                    <text x="393" y="190" font-family="Arial" font-size="12" fill="black">
                        x
                    </text>


                    <line x1="50" y1="195" x2="50" y2="205" stroke="black" stroke-width="2"></line>
                    <text x="43" y="190" font-family="Arial" font-size="12" fill="black">
                        -R
                    </text>
                    <line x1="125" y1="195" x2="125" y2="205" stroke="black" stroke-width="2"></line>
                    <text x="112" y="190" font-family="Arial" font-size="12" fill="black">
                        -R/2
                    </text>
                    <line x1="275" y1="195" x2="275" y2="205" stroke="black" stroke-width="2"></line>
                    <text x="266" y="190" font-family="Arial" font-size="12" fill="black">
                        R/2
                    </text>
                    <line x1="350" y1="195" x2="350" y2="205" stroke="black" stroke-width="2"></line>
                    <text x="346" y="190" font-family="Arial" font-size="12" fill="black">
                        R
                    </text>

                    <line x1="195" y1="50" x2="205" y2="50" stroke="black" stroke-width="2"></line>
                    <text x="207" y="53" font-family="Arial" font-size="12" fill="black">
                        R
                    </text>
                    <line x1="195" y1="125" x2="205" y2="125" stroke="black" stroke-width="2"></line>
                    <text x="207" y="128" font-family="Arial" font-size="12" fill="black">
                        R/2
                    </text>
                    <line x1="195" y1="275" x2="205" y2="275" stroke="black" stroke-width="2"></line>
                    <text x="207" y="278" font-family="Arial" font-size="12" fill="black">
                        -R
                    </text>
                    <line x1="195" y1="350" x2="205" y2="350" stroke="black" stroke-width="2"></line>
                    <text x="207" y="353" font-family="Arial" font-size="12" fill="black">
                        -R/2
                    </text>
                </svg>
            </div>
            <a class="button" href="index.jsp"> я верну тебя домой! </a>

            <jsp:include page="table.jsp"/>
        </main>
    </body>
</html>
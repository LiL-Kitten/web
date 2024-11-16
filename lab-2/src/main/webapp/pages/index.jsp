<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>lab-1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/js/validate.js" async></script>
    <script src="${pageContext.request.contextPath}/js/index.js" async></script>
    <script src="${pageContext.request.contextPath}/js/sender.js" async></script>

</head>
<body>
<header class="fio">Иевлев Ринат Андреевич номер группы: P3230 <br>Вариант лабораторной: 377912</header>

<div class="error" id="error">
    <div class="error-content">
        <span class="close-btn" id="closeBtn">&times;</span>
        <h3 id="errorHeader"></h3>
        <p id="errorMessage"></p>
    </div>
</div>

<main>
    <form id="values">
        <p>Выбери координату X</p>
        <div class="value-x">
            <hr>
            <button type="button" value="-4">-4</button>
            <button type="button" value="-3">-3</button>
            <button type="button" value="-2">-2</button>
            <button type="button" value="-1">-1</button>
            <button type="button" value="0">0</button>
            <button type="button" value="1">1</button>
            <button type="button" value="2">2</button>
            <button type="button" value="3">3</button>
            <button type="button" value="4">4</button>
        </div>

        <p>Выбери координату Y</p>
        <div class="value-y">
            <label>
                <input type="text" placeholder="от -3 до 3" name="y" maxlength="7">
            </label>
        </div>

        <p>Выбери координату R</p>
        <div class="value-r">
            <label>
                <input type="radio" name="value" value="1">
                <span class="label-radio">1</span>
            </label>
            <label>
                <input type="radio" name="value" value="1.5">
                <span class="label-radio">1,5</span>
            </label>
            <label>
                <input type="radio" name="value" value="2">
                <span class="label-radio">2</span>
            </label>
            <label>
                <input type="radio" name="value" value="2.5">
                <span class="label-radio">2,5</span>
            </label>
            <label>
                <input type="radio" name="value" value="3">
                <span class="label-radio">3</span>
            </label>
        </div>


        <button class="result">получить</button>

    </form>
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
        <div class="eSheep">
            <button type="button"> добавить овечку </button>
        </div>
    </div>
</main>
<hr>
<table class="history">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>время выполнения</th>
        <th>дата</th>
        <th>результат</th>
    </tr>
</table>

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>lab-2</title>
    <link rel="stylesheet" href="resources/css/style.css">
    <script src="resources/js/validate.js" defer></script>
    <script src="resources/js/sender.js" defer></script>
    <script src="resources/js/svgAction.js" defer></script>

</head>
<body>
<header class="fio">Иевлев Ринат Андреевич номер группы: P3230 <br>Вариант лабораторной: 377954</header>

<div class="error" id="error">
    <div class="error-content">
        <span class="close-btn" id="closeBtn">&times;</span>
        <p id="errorHeader"></p>
        <p id="errorMessage"></p>
    </div>
</div>

<main>
    <form id="values">
        <div class="value-x">
            <label for="coordinate-x">Выбери координату X</label>
            <input type="text" id="coordinate-x" placeholder="от -3 до 3" name="x" maxlength="7">
        </div>

        <p>Выбери координату Y</p>
        <div class="value-y">
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

        <br>
        <div class="value-r">
            <label for="coordinate-r">Выбери координату R</label>
            <input type="text" id="coordinate-r" placeholder="от 2 до 5" name="r" maxlength="7">
        </div>

        <button class="result">получить</button>

    </form>

    <div class="picture">
        <svg id="mySvg" width="400" height="400" xmlns="http://www.w3.org/2000/svg">
            <rect width="400" height="400" fill="white"></rect>
            <line x1="200" y1="0" x2="200" y2="400" stroke="black" stroke-width="2"></line>
            <line x1="0" y1="200" x2="400" y2="200" stroke="black" stroke-width="2"></line>
            <path d="
                M 200,125
                L 350,200
                L 350,350
                L 200,350
                L 200,275
                Q 125,275 125,200
                L 200,200
                L 200,125
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


</main>
<hr>

<jsp:include page="table.jsp"/>

</body>
</html>

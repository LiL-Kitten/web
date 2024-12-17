const SVG = document.querySelector('.mySvg')

SVG.addEventListener('click', function(event) {
    let svg = event.currentTarget,
        svgRect = svg.getBoundingClientRect(),
        svgX = event.clientX - svgRect.left,
        svgY = event.clientY - svgRect.top;

    if (isNaN(r)) {
        viewTrouble(new NotChooseValueError('r', 'Выберите R'));
        return;
    }

    xValues = ((svgX - 200) / 75 * r) / 2;
    y = ((200 - svgY) / 75 * r) / 2;

    xValues = xValues.toFixed(2);
    y = y.toFixed(2);

    console.log(`Координаты: X = ${xValues}, Y = ${y}`);

    document.getElementById('formId:x').value = xValues;
    document.getElementById('formId:y').value = y;

    document.querySelector('.result').click();

    xValues = null;
    y = null;
});



function readTableData() {
    let dataObjects = [],
        rows = document.querySelectorAll('.history tr')

    for (let i = 1; i < rows.length; i++) {
        let cells = rows[i].querySelectorAll('td'),
            tableX = parseFloat(cells[0].textContent),
            tableY = parseFloat(cells[1].textContent),
            tableR = parseFloat(cells[2].textContent)

        let newData = new Data(tableX, tableY, tableR)
        dataObjects.push(newData)

        console.log(`Координаты из таблицы: X = ${tableX}, Y = ${tableY}, R = ${tableR}`)
    }
    return dataObjects
}

function drawPoints(dataObjects) {
    let points = SVG.querySelectorAll('.point')

    points.forEach(point => point.remove())

    dataObjects.forEach(data => {
        let pixelX = (data.x * 2 / data.r) * 75 + 200,
            pixelY = 200 - (data.y * 2 / data.r) * 75

        console.log(`Drawing point at: pixelX = ${pixelX}, pixelY = ${pixelY}`)

        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle")
        circle.setAttribute("cx", pixelX.toString())
        circle.setAttribute("cy", pixelY.toString())
        circle.setAttribute("r", "4")

        // if (data.getCondition()) {
        //     circle.setAttribute("fill", "green")
        // } else {
            circle.setAttribute("fill", "red")
        // }

        SVG.appendChild(circle)
        console.log(`Rectangle added at: x = ${pixelX - 2}, y = ${pixelY - 2}`)
    })
}



function clearPoints() {
    let svg = document.querySelector('.mySvg'),
        points = svg.querySelectorAll('circle')

    points.forEach(point => point.remove())
}


function drawOverlay(r) {
    const svgContainer = document.querySelector('.mySvg');
    const existingPaths = svgContainer.querySelectorAll('.overlay-path');
    existingPaths.forEach(path => path.remove());

    const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
    path.setAttribute("d", `
            M 200,200
            L 200,167
            L 134,200
            L 134,236
            L 200,236
            Q 233,233 233,200
            Z
        `);
    path.setAttribute("fill", "lightblue");
    path.setAttribute("stroke", "blue");
    path.setAttribute("class", "overlay-path"); // Добавляем класс для идентификации

    path.setAttribute("transform", `translate(200, 200) scale(${r-r/2}) translate(-200, -200)`);

    svgContainer.appendChild(path);
}
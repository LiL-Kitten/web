const SVG = document.querySelector('.mySvg');

SVG.addEventListener('click', function(event) {
    let svg = event.currentTarget,
        svgRect = svg.getBoundingClientRect(),
        svgX = event.clientX - svgRect.left,
        svgY = event.clientY - svgRect.top;

    if (r === null) {
        viewTrouble(new NotChooseValueError('r', 'Выберите R'));
        return;
    }

    let valueX = (svgX - 200) / 33,
        valueY = (200 - svgY) / 33

    valueX = valueX.toFixed(2);
    valueY = valueY.toFixed(2);

    console.log(`Координаты: X = ${valueX}, Y = ${valueY}`);

    setX(valueX)
    setY(valueY)
    setFlag(false)

    document.querySelector('.result').click();
});

function clearPoints() {
    let points = SVG.querySelectorAll('circle');
    points.forEach(point => point.remove());
}

function readTableData() {
    let dataObjects = [],
        rows = document.querySelectorAll('.history tr')

    setX(null)

    for (let i = 1; i < rows.length; i++) {
        let cells = rows[i].querySelectorAll('td'),
            tableX = parseFloat(cells[0].textContent),
            tableY = parseFloat(cells[1].textContent),
            tableR = parseFloat(cells[2].textContent),
            tableCondition = cells[5].textContent.trim() === 'true' ? 'true' : 'false';

        let newData = {
            x: tableX,
            y: tableY,
            r: tableR,
            condition: tableCondition
        }

        dataObjects.push(newData)
    }
    return dataObjects
}

function drawPoints(dataObjects) {
    let points = SVG.querySelectorAll('.point');
    points.forEach(point => point.remove());

    dataObjects.forEach(data => {
        let pixelX = data.x * 33 + 200,
            pixelY = 200 - data.y * 33;

        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", pixelX.toString());
        circle.setAttribute("cy", pixelY.toString());
        circle.setAttribute("r", "4");

        if (data.condition === 'true') {
            circle.setAttribute('fill', 'green')
        } else if (data.condition === 'false') {
            circle.setAttribute('fill', 'red')
        }

        SVG.appendChild(circle);
        // console.log(`Circle added at: x = ${pixelX}, y = ${pixelY}`);
    });
}



function drawOverlay(r) {
    const svgContainer = document.querySelector('.mySvg')
    const existingPaths = svgContainer.querySelectorAll('.overlay-path')
    existingPaths.forEach(path => path.remove())

    const path = document.createElementNS("http://www.w3.org/2000/svg", "path")
    path.setAttribute("d", `
            M 200,200
            L 200,167
            L 134,200
            L 134,236
            L 200,236
            Q 233,233 233,200
            Z
        `)
    path.setAttribute("fill", "lightblue")
    path.setAttribute("stroke", "blue")
    path.setAttribute("class", "overlay-path")

    path.setAttribute("transform", `translate(200, 200) scale(${r / 2}) translate(-200, -200)`)

    svgContainer.appendChild(path)

    clearPoints()
}


document.getElementById('mySvg').addEventListener('click', function(event) {
    let svg = event.currentTarget,
        svgRect = svg.getBoundingClientRect(),
        svgX = event.clientX - svgRect.left,
        svgY = event.clientY - svgRect.top,
        valueX = ((svgX - 200) / 75 * r) / 2,
        valueY = ((200 - svgY) / 75 * r) / 2


    console.log(`Координаты: X = ${valueX}, Y = ${valueY}`)

    let data = new Data(valueX, valueY, r)

    try {
        data.checker()
    } catch (err) {
        viewTrouble(err)
        return;
    }

    send(data).then( () => {
        console.log("все оке!")
    } ).catch( () => {
        console.error("все бэд")
    } )
} )

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

        console.log(`Координаты из таблицы: X = ${tableX}, Y = ${tableY}, R = ${tableR}`);
    }
    return dataObjects;
}

function drawPoints(dataObjects) {
    let svg = document.getElementById('mySvg'),
        points = svg.querySelectorAll('.point')

    points.forEach(point => point.remove())

    dataObjects.forEach(data => {
        let pixelX = (data.x * 2 / data.r) * 75 + 200,
            pixelY = 200 - (data.y * 2 / data.r) * 75

        console.log(`Drawing point at: pixelX = ${pixelX}, pixelY = ${pixelY}`)

        let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle")
        circle.setAttribute("cx", pixelX.toString())
        circle.setAttribute("cy", pixelY.toString())
        circle.setAttribute("r", "4" )
        circle.setAttribute("fill", "red")

        svg.appendChild(circle);
        console.log(`Rectangle added at: x = ${pixelX - 2}, y = ${pixelY - 2}`);
    });
}

drawPoints(readTableData());

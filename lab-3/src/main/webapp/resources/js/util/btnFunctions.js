function changeStyle(checkbox) {
    let label = checkbox.labels[0]
    if (checkbox.checked) {
        console.log('choose ' + label.innerText)
        label.style.background = 'black'
        label.style.color = 'white'
        xValues.push(Number.parseInt(label.innerText))
    } else {
        console.log('no choose')
        label.style.background = 'white'
        label.style.color = 'black'
        xValues = xValues.filter(value => value !== label.innerText)
    }
}


function clearForm() {
    console.log('clearForm - worked')
    clear()
}

function clear() {
    console.log('start clearing')

    let checkboxes = document.querySelectorAll('input[type="checkbox"]')

    checkboxes.forEach(checkbox => {
        checkbox.checked = false
        changeStyle(checkbox)
    })

    xValues = []

    setY('')
    setR(null)
}

function random() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]'),
    randomCount = Math.floor(Math.random() * (checkboxes.length + 1))


    clear()

    let selectedCheckboxes = new Set()

    while (selectedCheckboxes.size < randomCount) {
        let randomIndex = Math.floor(Math.random() * checkboxes.length)
        selectedCheckboxes.add(randomIndex)
    }

    selectedCheckboxes.forEach(index => {
        checkboxes[index].checked = true
        changeStyle(checkboxes[index])
    })

    let randomY = (Math.random() * 6 - 3).toFixed(2)

    console.log('its random y ' + randomY)
    setY(randomY)

    let minR = 2,
        maxR = 5,
        step = 0.25,
        range = (maxR - minR) / step,
        randomIndexR = Math.floor(Math.random() * (range + 1)),
        randomR = (minR + randomIndexR * step).toFixed(2)
    console.log(randomR)

    setR(randomR)
}



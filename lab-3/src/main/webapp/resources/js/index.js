const CLEAR_BTN = document.querySelector('.clear-btn'),
      RNDM_BTN = document.querySelector('.random-btn'),
      BTN = document.querySelector('.result'),
      DELETE_BTN = document.querySelector('.delete-btn')

let xValues = []
let y = null
let r = null

function changeStyle(obj) {
    let label = obj.parentElement
    if (obj.checked) {
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

CLEAR_BTN.addEventListener('click', clear)

function clear() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]'),
        textInputs = document.querySelectorAll('input[type="text"]'),
        slider = document.getElementById('slider')

    checkboxes.forEach(checkbox => {
        checkbox.checked = false
        changeStyle(checkbox)
    })

    textInputs.forEach(text => text.value = '')

    xValues = []
    y = null
    r = null

    if (slider) {
        slider.value = slider.min
        setR(slider)
    }

    console.log('all good')
}

function setY(obj) {
    y = getValue(obj)
    console.log('ты ввел ' + y)
}

function setR(obj) {
    r = getValue(obj)
    console.log('ты ввел ' + r)
}

function getValue(obj) {
    return validate(obj.value)
}

function validate(str) {
    str = str.replace(',', '.')
    return parseFloat(str)
}

function createData() {
    let dataArray = []
    for (const x of xValues) {
        let data = new Data(x, y, r)
        try {
            data.checker()
            dataArray.push(data)
        } catch (error) {
            console.error(error.message)
            viewTrouble(error)
            break
        }
    }
    return dataArray
}

DELETE_BTN.addEventListener('click', () => {clearPoints()})

RNDM_BTN.addEventListener('click', random)

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
    document.querySelector('.value-y').value = randomY
    y = randomY

    let minR = 2,
        maxR = 5,
        step = 0.25,
        range = (maxR - minR) / step,
        randomIndexR = Math.floor(Math.random() * (range + 1)),
        randomR = (minR + randomIndexR * step).toFixed(2)

    document.querySelector('.value-r').value = randomR
    r = randomR
}

BTN.addEventListener('click', () => drawPoints(createData()))

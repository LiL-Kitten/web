const CLEAR_BTN = document.querySelector('.clear-btn')
const RNDM_BTN = document.querySelector('.random-btn')
const BTN = document.querySelector('.result')
const DELETE_BTN = document.querySelector('.delete-btn')

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

function clear() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]')
    let textInputs = document.querySelectorAll('input[type="text"]')
    let slider = document.getElementById('slider')

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


function createData() {
    let dataArray = []
    for (const x of xValues) {
        let data = new Data(x, y, r)
        try {
            dataArray.push(data)
        } catch (error) {
            console.error(error.message)
            viewTrouble(error)
            break
        }
    }
    return dataArray
}

function random() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]')
    let randomCount = Math.floor(Math.random() * (checkboxes.length + 1))

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

    let minR = 2
    let maxR = 5
    let step = 0.25
    let range = (maxR - minR) / step
    let randomIndexR = Math.floor(Math.random() * (range + 1))
    let randomR = (minR + randomIndexR * step).toFixed(2)

    document.querySelector('.slider').value = randomR
    document.getElementById('formId:valueR').value = randomR
    console.log(randomR)
    setR(randomR)
    r = randomR
    drawOverlay(randomR)
}

function handleSubmit() {
    try {
        checker(xValues, y, r)
        return true
    } catch (err) {
        viewTrouble(err)
        return false
    }
}

CLEAR_BTN.addEventListener('click', clear)
DELETE_BTN.addEventListener('click', () => {
    clearPoints()
})
RNDM_BTN.addEventListener('click', random)
BTN.addEventListener('click', () => {
    try {
        checker(xValues, y, r)
    } catch (err) {
        viewTrouble(err)
    }
    drawPoints(createData())
})
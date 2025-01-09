class NotChooseValueError extends Error {
    constructor(value, message) {
        super(message)
        this.name = 'NotChooseValueError'
        this.value = value
    }
}

document.getElementById('closeBtn').addEventListener('click', () => {
    let error = document.getElementById('error')
    error.style.display = 'none'
})

function viewTrouble(err) {
    let error = document.getElementById('error'),
        errorHeader = document.getElementById('errorHeader'),
        errorMessage = document.getElementById('errorMessage')

    errorHeader.textContent = `Ошибка ${err.name} связанная с переменной ${err.value}`
    errorMessage.textContent = err.message

    error.style.display = 'block'

    setTimeout(() => {
        error.style.display = 'none'
    }, 2500)
}

class Data {
    constructor(x, y, r) {
        this.x = x
        this.y = y
        this.r = r
    }
}

let condition = null
let xValues = []
let y = null
let r = null

function setCondition(bool) {
    this.condition = bool
}

function setY(obj) {
    y = getValue(obj)
    console.log('ты ввел ' + y)
}

function setR(obj) {
    r = obj.value
    drawOverlay(r)
    console.log('ты ввел ' + r)
}

function getValue(obj) {
    return validate(obj.value)
}

function validate(str) {
    str = str.replace(',', '.')
    return parseFloat(str)
}

function checker(valuesX, valueY, valueR) {
    if (!Array.isArray(valuesX)) {
        valuesX = [valuesX];
    }

    if (valuesX.length === 0) throw new NotChooseValueError('x', 'Выберите Х');
    if (valueY === null || valueY === undefined || isNaN(valueY)) {
        throw new NotChooseValueError('y', 'Поле ввода Y пустое, пожалуйста, введите значение в диапазоне от -3 до 3');
    }
    if (valueY <= -3 || valueY >= 3) throw new NotChooseValueError('y', 'Y должен быть в диапазоне от -3 до 3(((');
    if (!valueR) throw new NotChooseValueError('r', 'Выберите R');

    return true;
}

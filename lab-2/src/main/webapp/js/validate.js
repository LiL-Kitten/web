'use strict'

class NotChooseValueError extends Error {
    constructor(value, message) {
        super(message)
        this.name = 'NotChooseValueError'
        this.value = value
    }
}

document.getElementById('closeBtn').addEventListener('click', () => {
    const error = document.getElementById('error')

    error.style.display = 'none' 
});

function viewTrouble(err) {
    const error = document.getElementById('error')
    const errorHeader = document.getElementById('errorHeader')
    const errorMessage = document.getElementById('errorMessage')

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

    checker() {
        if ( this.x < -3 || this.x > 3 ) throw new NotChooseValueError('x', 'X должен быть в диапозоне от -3 до 3(((')
        if ( this.x === null || this.x === undefined || isNaN(this.x)) {
            throw new NotChooseValueError('x', 'Поле ввода X пустое, пожалуйста, введите значение в диапазоне от -3 до 3')
        }
        if ( typeof this.y !== 'number' ) throw new NotChooseValueError ('y', 'Выбери Y!')
        this.checkR()
    }

    checkR() {
        if ( this.r === null || this.r === undefined || isNaN(this.r)) {
            throw new NotChooseValueError('r', 'Поле ввода R пустое, пожалуйста, введите значение в диапазоне от 2 до 5')
        }
        if ( this.r < 2 || this.r > 5 ) throw new NotChooseValueError('r', 'R должен быть в диапозоне от 2 до 5(((')
    }

}

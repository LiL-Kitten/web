'use strict'

class NotChooseValueError extends Error {
    constructor(value, message) {
        super(message)
        this.name = 'NotChooseValueError'
        this.value = value
    }
}

try {
    var m = null  
} catch (err) {
    console.log(err)
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
}


class Data {
    constructor(x, y, r) {
        this.x = x
        this.y = y
        this.r = r
    }


    parseToJson() {
        return JSON.stringify({
            x: this.x,
            y: this.y,
            r: this.r
        });
    }

    checker() {
        if (typeof this.x !== 'number') throw new NotChooseValueError('x', 'Выберите Х')
        if (this.y === null || this.y === undefined || isNaN(this.y)) {
            throw new NotChooseValueError('y', 'Поле ввода Y пустое, пожалуйста, введите значение в диапазоне от -3 до 3')
        }
        if ( this.y < -3 || this.y > 3 ) throw new NotChooseValueError ('y', 'Y должен быть в диапозоне от -3 до 3(((') 
        if (!this.r) throw new NotChooseValueError('r', 'Выберите R')
    }

}
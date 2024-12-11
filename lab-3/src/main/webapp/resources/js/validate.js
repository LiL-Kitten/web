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
        this.condition = this.setCondition()
    }

    checker() {
        if (typeof this.x !== 'number') throw new NotChooseValueError('x', 'Выберите Х')
        if (this.y === null || this.y === undefined || isNaN(this.y)) {
            throw new NotChooseValueError('y', 'Поле ввода Y пустое, пожалуйста, введите значение в диапазоне от -3 до 3')
        }
        if (this.y < -3 || this.y > 3) throw new NotChooseValueError('y', 'Y должен быть в диапозоне от -3 до 3(((')
        if (!this.r) throw new NotChooseValueError('r', 'Выберите R')
    }

    setCondition() {
        let rHalf = this.r / 2

        let inRectangle = (this.x >= 0 && this.x <= this.r &&
            this.y >= -this.r && this.y <= 0)

        let inTriangle = (this.x >= 0 && this.x <= this.r &&
            this.y >= 0 && this.y <= rHalf &&
            this.y <= rHalf - (this.x * 0.5))

        let inCircle = (this.x < 0 && this.y < 0 &&
            (this.x * this.x + this.y * this.y <= rHalf * rHalf))

        return inRectangle || inTriangle || inCircle
    }

    getCondition() {
        return this.condition
    }
}

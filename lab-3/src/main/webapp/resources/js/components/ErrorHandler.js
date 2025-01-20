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


const ELEM_X = document.querySelectorAll('.value-x button'),
      ELEM_Y = document.querySelector('input[type="text"]'),
      ELEM_R = document.querySelectorAll('input[type="radio"]')

let x = null, 
    y = null,
    r = null

ELEM_X.forEach(button => {
    button.addEventListener('click', () => {
        ELEM_X.forEach(otherButton => {
            otherButton.classList.remove('selected')
        })
            
        button.classList.add('selected')
            
        console.log(button.value)
        x = getValue(button)
    })
})

ELEM_Y.addEventListener('input', () => {
    y = getValue(ELEM_Y)
})

ELEM_R.forEach(element => {
    element.addEventListener('change', () => {
        r = getValue(element)
        console.log(element.value)
    })
})
   
function getValue(obj) {
    return validate(obj.value)
}

function validate(str) {
    str = str.replace(',', '.')
    return parseFloat(str)
}

function createData() {
    return new Data(x, y, r)
}

function loadPrikol(url, func) {
    let script = document.createElement('script')
    script.type = 'text/javascript'
    script.src = url

    script.onload = function () {
        if (func) func()
    }

    document.head.appendChild(script)
}

function initSheep() {
    let btn = document.querySelector('.eSheep')

    btn.addEventListener('click', () => {
        let sheep = new eSheep()
        sheep.Start()
    })
}

loadPrikol('https://adrianotiger.github.io/web-esheep/dist/esheep.min.js', initSheep);



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
}; 

function deleteBtn() {
    let newX = document.querySelector('.value-x')
    setTimeout(() => {
        setInterval( () => {
            setTimeout( () => {
                newX.innerHTML = ''
            }, 5000 )
            newX.innerHTML = '<button value="1">1</button>'
        }, 6000)
    }, 6000)


}   


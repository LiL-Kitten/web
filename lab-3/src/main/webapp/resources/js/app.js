let xValues = [],
y = null,
r = null,
checkerFlag = true

function setX(num) {
    document.getElementById('formId:hiddenX').value = num
    xValues.push(num)
}

function setFlag(bool) {
    checkerFlag = bool
}

function setY(str) {
    document.getElementById('formId:valueY').value = str

    y = (str.trim() === '') ? null : validate(str);
    document.getElementById('formId:hiddenY').value = y
    console.log('y =  ' + y)
}

function setR(num) {
    r = num
    document.querySelector('.slider').value = num
    document.getElementById('formId:hiddenR').value = num

    drawOverlay(r)


    console.log('r =  ' + r)
}

function validate(str) {
    str = str.replace(',', '.')
    return parseFloat(str)
}


function handleSubmit() {
    try {
        if (checkerFlag === true) checker(xValues, y, r)
        return true;
    } catch (err) {
        viewTrouble(err)
        return false
    } finally {
        setFlag(true)
    }
}


document.addEventListener('DOMContentLoaded', clear)
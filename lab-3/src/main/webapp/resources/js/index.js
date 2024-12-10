const ELEM_X = document.querySelectorAll('input[type="text"][name="x"]'),
      ELEM_Y = document.querySelectorAll('input[type="checkbox"]'),
      // ELEM_Y = document.querySelectorAll('.label-checkbox'),
      ELEM_R = document.querySelectorAll('input[type="text"][name="r"]');

let x = null,
    y = null,
    r = null;

ELEM_X.forEach(input => {
    input.addEventListener('input', () => {
        x = getValue(input);
        console.log(`X value: ${x}`);
    });
});

ELEM_R.forEach(input => {
    input.addEventListener('input', () => {
        r = getValue(input);
        console.log(`R value: ${r}`);
    });
});

ELEM_Y.forEach(element => {
    element.addEventListener('change', () => {

        y = getValue(element);
        console.log(`Y value: ${y}`);
    });
});

function getValue(obj) {
    return validate(obj.value);
}

function validate(str) {
    str = str.replace(',', '.');
    return parseFloat(str);
}

function createData() {
    return new Data(x, y, r);
}

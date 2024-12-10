const CLEAR_BTN = document.querySelector('.clear-btn'),
      RNDM_BTN = document.querySelector('.random-btn')

let xValues = []; // Массив для хранения значений чекбоксов
let y = null;
let r = null;

function changeStyle(obj) {
    let label = obj.parentElement;

    if (obj.checked) {
        console.log('choose ' + label.innerText);
        label.style.background = 'black';
        label.style.color = 'white';
        xValues.push(Number.parseInt(label.innerText)); // Добавляем значение в массив
    } else {
        console.log('no choose');
        label.style.background = 'white';
        label.style.color = 'black';
        xValues = xValues.filter(value => value !== label.innerText);
    }
}

CLEAR_BTN.addEventListener('click', () => {
    clear();
});

function clear() {
    let checkboxes = document.querySelectorAll('input[type="checkbox"]');

    checkboxes.forEach(function (checkbox) {
        checkbox.checked = false;
        changeStyle(checkbox);
    });

    let textInputs = document.querySelectorAll('input[type="text"]');

    textInputs.forEach(function (text) {
        text.value = '';
    });

    xValues = []; // Очищаем массив xValues
    y = null;
    r = null;

    let slider = document.getElementById('slider');
    if (slider) {
        slider.value = slider.min;
        setR(slider);
    }

    console.log('all good');
}

function setY(obj) {
    y = getValue(obj);
    console.log('ты ввел ' + y);
}

function setR(obj) {
    r = getValue(obj);
    console.log('ты ввел ' + r);
}

function getValue(obj) {
    return validate(obj.value);
}

function validate(str) {
    str = str.replace(',', '.');
    return parseFloat(str);
}

function createData() {
    const dataArray = []; // Массив для хранения валидных объектов Data

    for (const x of xValues) {
        const data = new Data(x, y, r); // Создаем объект Data

        try {
            data.checker(); // Проверяем валидность данных
            dataArray.push(data); // Если все валидно, добавляем в массив
        } catch (error) {
            console.error(error.message); // Обрабатываем ошибку, выводим сообщение
            viewTrouble(error)
            break
        }
    }

    return dataArray
}

RNDM_BTN.addEventListener('click', () => {
    random()
})

function random() {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    const randomCount = Math.floor(Math.random() * (checkboxes.length + 1));

    // Сначала снимаем все отметки
    clear()

    // Случайно выбираем чекбоксы для отметки
    const selectedCheckboxes = new Set();
    while (selectedCheckboxes.size < randomCount) {
        const randomIndex = Math.floor(Math.random() * checkboxes.length);
        selectedCheckboxes.add(randomIndex);
    }

    selectedCheckboxes.forEach(index => {
        checkboxes[index].checked = true;
        changeStyle(checkboxes[index]);
    });

    // Генерируем случайное значение для valueY от -3 до 3
    const randomY = (Math.random() * 6 - 3).toFixed(2); // Генерируем значение от -3 до 3
    document.querySelector('.value-y').value = randomY;
    y = randomY

    // Генерируем случайное значение для valueR от 2 до 5
    const minR = 2;
    const maxR = 5;
    const step = 0.25;
    const range = (maxR - minR) / step; // Количество возможных значений
    const randomIndexR = Math.floor(Math.random() * (range + 1)); // Случайный индекс
    const randomR = (minR + randomIndexR * step).toFixed(2); // Генерируем значение с шагом 0.25
    document.querySelector('.value-r').value = randomR;
    r = randomR
}

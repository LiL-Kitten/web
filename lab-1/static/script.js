const errMsg = document.querySelector('.error'),
FORM = document.getElementById('values'),
ELEM_X = document.querySelectorAll('.value-x'),
ELEM_Y = document.querySelectorAll('.value-y'),
ELEM_R = document.querySelectorAll("input[name='value-r']")

let x = null,
y = null,
r = null

ELEM_X.forEach(element => {
  element.addEventListener('click', function selectButton() {
    ELEM_X.forEach(el => el.classList.remove('selected'));
    element.classList.add('selected');
    x = element.value;
    console.log(`x = ${x}`)
  })
})

ELEM_Y.forEach(element => {
  element.addEventListener('input', function setY() {
    y = element.value;
    console.log(`y = ${y}`)
  })
})

ELEM_R.forEach(element => {
  element.addEventListener('change', function chooseR() {
    r = element.value;
    console.log(`r = ${r}`)
  })
})

const normalizeY = y => y.replace(',', '.');

const validateY = y => {
  const normalizedY = normalizeY(y)
  const numberY = parseFloat(normalizedY)
  return !isNaN(numberY) && numberY >= -3 && numberY <= 3
}

FORM.addEventListener('submit', event => {
  event.preventDefault();
  errMsg.textContent = '';

  if (x === null) {
    errMsg.textContent = 'выберите значение X!';
    return
  }
  if (y === null || y.trim() === '') {
    errMsg.textContent = 'введите значение Y от -3 до 3.';
    return
  }
  if (r === null || r === undefined) {
    errMsg.textContent = 'выберите значение R!';
    return
  }

  const formatNumber = num => (num % 1 === 0 ? Math.round(num) : num);

  if (validateY(y)) {
    fetch('http://localhost:8080/fcgi-bin/lab-1.jar', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        x,
        y: normalizeY(y),
        r,
      }),
    })
      .then(response => response.json())
      .then(result => {
        
        const formatX = formatNumber(result.x),
        formatY = formatNumber(result.y),
        formatR = formatNumber(result.r),
        newRow = `
          <tr>
            <td>${formatX}</td>
            <td>${formatY}</td>
            <td>${formatR}</td>
            <td>${result.time}</td>
            <td>${result.date}</td>
            <td>${result.result ? 'Попал' : 'Не попал'}</td>
          </tr>
        `;
        document.querySelector('.history table').insertAdjacentHTML('beforeend', newRow)
      })
      .catch(error => {
        errMsg.textContent = `ошибка при отправке данных: ${error.message}. Попробуйте снова.`
      })
  } else {
    errMsg.textContent =
      'координата Y должна быть числом в диапазоне от -3 до 3 и может быть дробным числом с точкой или запятой'
  }
})
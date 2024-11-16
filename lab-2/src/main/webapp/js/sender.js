const BTN_SUBMIT = document.querySelector('.result');

function send(obj) {
    console.log('x:', obj.x, 'y:', obj.y, 'r:', obj.r);


    const params = new URLSearchParams({
        x: encodeURIComponent(obj.x),
        y: encodeURIComponent(obj.y),
        r: encodeURIComponent(obj.r)
    });

    fetch(`http://localhost:8080/lab-2-1/controller?${params.toString()}`, {
        method: 'GET'
    })
        .then(response => {
            console.log('Статус ответа:', response.status);
            if (!response.ok) {
                throw new Error(`что то как то плохо: ${response.statusText}`);
            }
            return response.text();
        })
        .then(text => {
            console.log('Ответ от сервера:', text)
            try {
                const result = JSON.parse(text)
                console.log('Результат:', result)
                let newRow = `
            <tr>
              <td>${result.x}</td>
              <td>${result.y}</td>
              <td>${result.r}</td>
              <td>${result.time}</td>
              <td>${result.date}</td>
              <td>${result.condition ? 'Попал' : 'Не попал'}</td>
            </tr>
          `
                document.querySelector('table').insertAdjacentHTML('beforeend', newRow)
            } catch (jsonError) {
                console.error('Ошибка при разборе JSON:', jsonError)
            }
        })
        .catch(error => {
            console.error('Ошибка:', error)
            viewTrouble(error);
        });
}

function checkAndSend(obj) {
    try {
        obj.checker();
        send(obj);
    } catch (err) {
        if (err.name === 'NotChooseValueError') viewTrouble(err);
        console.error('Ошибка в checkAndSend:', err);
    }
}

BTN_SUBMIT.addEventListener('click', (event) => {
    event.preventDefault();
    let data = createData();
    checkAndSend(data);
});

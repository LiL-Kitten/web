
const BTN_SUBMIT = document.querySelector('.result');


function send(obj) {
    fetch('http://localhost:8080/fcgi-bin/lab-1.jar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: obj.parseToJson()
      })
        .then(response => response.json())
        .then(result => {
          // const formatX = formatNumber(result.x),
          // formatY = formatNumber(result.y),
          // formatR = formatNumber(result.r),
          newRow = `
            <tr>
              <td>${result.x}</td>
              <td>${result.y}</td>
              <td>${result.r}</td>
              <td>${result.time}</td>
              <td>${result.date}</td>
              <td>${result.result ? 'Попал' : 'Не попал'}</td>
            </tr>
          `;
          document.querySelector('table').insertAdjacentHTML('beforeend', newRow);
        })
        .catch(error => {
          viewTrouble(error)
          console.log(error)
        });
} 

 


function checkAndSend(obj) {

  try{
    obj.checker()
    send(obj)
  } catch(err) {
    if(err.name = 'NotChooseValueError') viewTrouble(err)
      
    throw err
  }
}

BTN_SUBMIT.addEventListener('click', (event) => {  
  event.preventDefault()

  let data = createData()
  checkAndSend(data)
});


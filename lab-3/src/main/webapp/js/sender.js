const BTN_SUBMIT = document.querySelector('.result')

async function send(obj) {
    console.log('x:', obj.x, 'y:', obj.y, 'r:', obj.r)

    const params = new URLSearchParams({
        x: encodeURIComponent(obj.x),
        y: encodeURIComponent(obj.y),
        r: encodeURIComponent(obj.r)
    })

    try {
        const response = await fetch(`/app/controller?${params.toString()}`, {
            method: 'GET'
        })

        window.location.href = response.url

    } catch (error) {
        console.error('Ошибка:', error)
        console.error('Текст ошибки от сервера:', error.message)
        viewTrouble(error)
    }
}

async function checkAndSend(obj) {
    try {
        obj.checker()
        await send(obj)
    } catch (err) {
        if (err.name === 'NotChooseValueError') viewTrouble(err)
        console.error('Ошибка в checkAndSend:', err)
    }
}

BTN_SUBMIT.addEventListener('click', (event) => {
    event.preventDefault()
    let data = createData()
    checkAndSend(data).then( () => {
        console.log( "все оке!" )
    } ).catch( () => {
        console.error( "какой то трабл во время отправки" )
    } )
})

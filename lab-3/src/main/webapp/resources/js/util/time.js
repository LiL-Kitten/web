const CLOCK = document.querySelector('.time')

updateTime()

setInterval(updateTime, 10000)

function updateTime() {
    let time = new Date,
        totalTime = time.toLocaleTimeString('ru-RU', {
            hour: "2-digit",
            minute: "2-digit",
            second: "2-digit"
        })

    CLOCK.textContent = `${totalTime}`
}


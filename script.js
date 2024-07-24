const burger = document.querySelector('.burger');
const overlay = document.getElementById('myNav');

burger.addEventListener('click', () => {
    burger.classList.toggle('active');
    if (overlay.style.height === '100%') {
        overlay.style.height = '0%';
    } else {
        overlay.style.height = '100%';
    }
});

document.addEventListener("DOMContentLoaded", ()=> {
    const header = document.querySelector('header');
    const logo = document.querySelector('.logo');

    const localLogo = localStorage.getItem('logo');

    if (localLogo === 'move') {
        logo.classList.add('move');
        logo.style.opacity = 1; // логотип уже видим
    }


    header.classList.add('start');

    setTimeout(() => {
        logo.style.opacity = 1;
    },1000);

    setTimeout(()=> {
        logo.classList.add('move');
    }, 4000);
    
    setTimeout(()=>{
        burger.classList.add('activete');
    }, 8000 );


    setTimeout(()=> {
    header.classList.add('move');
    }, 6000);

    logo.addEventListener('click', () => {
        if (burger.classList.contains('activete')) {
            burger.classList.remove('activete');
            burger.classList.add('reverse');
        }
       if (header.classList.contains('move')) {
                header.classList.remove('move');
            header.classList.add('reverse');
        }
       
        setTimeout(()=> {
            if (logo.classList.contains('move')) {
                logo.classList.remove('move');
                logo.classList.add('reverse');
            }
        }, 1500 );

        setTimeout(() => {
            location.reload(); // Обновление страницы после завершения анимации
        }, 6000);
    });
}) ;

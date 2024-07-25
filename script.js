const burger = document.querySelector('.burger');
const overlay = document.getElementById('myNav');
const header = document.querySelector('header');
const logo = document.querySelector('.logo');
const localLogo = localStorage.getItem('logo');

burger.addEventListener('click', () => {
    burger.classList.toggle('active');
    if (overlay.style.height === '100%') {
        overlay.style.height = '0%';
    } else {
        overlay.style.height = '100%';
    }
});

document.addEventListener("DOMContentLoaded", ()=> {

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
    }, 2000);
    
    setTimeout(()=>{
        burger.classList.add('activete');
    }, 4000 );


    setTimeout(()=> {
    header.classList.add('move');
    }, 3000);

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



document.addEventListener('DOMContentLoaded', () => {
    const switcher = document.querySelector('.theme-switcher');
  
    switcher.addEventListener('click', function () {
        applyTheme();
    });

    function applyTheme() {
        let themURL;
        if(switcher.checked){
            themURL = 'css/dark-theme.css';
            document.querySelector('[title="theme"]').setAttribute("href", themURL);
        } else {
             themURL = 'css/light-theme.css'
             document.querySelector('[title="theme"]').setAttribute("href", themURL);
        }   
    }
  });

  document.addEventListener('DOMContentLoaded', () => {
    const switcher = document.querySelector('.theme-switcher');
    const themeLink = document.querySelector('[title="theme"]');
  
    // Функция для применения темы
    function applyTheme(themeName) {
        if (themeName === 'dark') {
            themeLink.setAttribute('href', 'css/dark-theme.css');
            switcher.checked = true;
        } else {
            themeLink.setAttribute('href', 'css/light-theme.css');
            switcher.checked = false;
        }
        localStorage.setItem('theme', themeName);
    }

    // Проверка сохраненной темы при загрузке страницы
    const savedTheme = localStorage.getItem('theme');
    applyTheme(savedTheme || 'light'); // По умолчанию светлая тема
  
    // Обработчик переключения темы
    switcher.addEventListener('click', function () {
        const theme = switcher.checked ? 'dark' : 'light';
        applyTheme(theme);
    });
});

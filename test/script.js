document.addEventListener("DOMContentLoaded", function () {
  // const logoBox = document.querySelector('header');
  const logoText = document.querySelector('h1');
  const content = document.querySelectorAll('button, .visual-content');

  setTimeout(() => {
    logoText.classList.add('open');
  }, 1000);

  setTimeout(() => {
    logoText.classList.add('start');
  }, 2000);

  // setTimeout(() => {
  //   logoBox.classList.add('move');
  // }, 4000);

  // Show menu and content after logo animation
  setTimeout(() => {
    content.forEach(element => {
      element.style.opacity = '1';
    });

    logoText.addEventListener('click', function () {
      location.reload();
    });
  }, 3000);
});

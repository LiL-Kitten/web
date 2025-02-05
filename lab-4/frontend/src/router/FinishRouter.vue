<template>
  <div class="about">
    <h1>О проекте</h1>
    <router-link to="/" class="nav-button">
      Вернуться на главную
    </router-link>
    <p>Этот проект демонстрирует базовую настройку Vue Router</p>
  </div>
</template>

<style scoped>
.about {
  text-align: center;
  padding: 20px;
}
.nav-button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #35495e;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  margin: 10px;
}
</style>
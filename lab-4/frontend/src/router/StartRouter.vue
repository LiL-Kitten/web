<template>
  <div class="home">
    <h1>Главная страница</h1>
    <router-link to="/about" class="nav-button">
      Перейти к описанию
    </router-link>
    <p>Добро пожаловать в стартовый шаблон Vue SPA!</p>
  </div>
</template>

<style scoped>
.home {
  text-align: center;
  padding: 20px;
}
.nav-button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #42b983;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  margin: 10px;
}
</style>
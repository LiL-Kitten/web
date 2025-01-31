<template>
  <div class="register">
    <h1>Регистрация</h1>
    <form @submit.prevent="register">
      <div>
        <label for="username">Имя пользователя:</label>
        <input type="text" v-model="username" required />
      </div>
      <div>
        <label for="password">Пароль:</label>
        <input type="password" v-model="password" required />
      </div>
      <button type="submit">Зарегистрироваться</button>
    </form>
    <p>Уже зарегистрированы? <router-link to="/input">Войти</router-link></p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    register() {
      // Здесь можно добавить логику для регистрации пользователя
      alert(`Пользователь ${this.username} зарегистрирован!`);
      this.$router.push('/input'); // Перенаправление на страницу ввода
    }
  }
};
</script>

<style scoped>
/* Добавьте стили для страницы регистрации */
</style>

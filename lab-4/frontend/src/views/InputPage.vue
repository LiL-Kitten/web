<template>
  <div class="input-page">
    <h1>Поля ввода</h1>
    <form @submit.prevent="submit">
      <div>
        <label for="inputField">Введите что-то:</label>
        <input type="text" v-model="inputValue" required />
      </div>
      <button type="submit">Отправить</button>
    </form>
    <p>Вернуться на <router-link to="/">страницу регистрации</router-link></p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      inputValue: ''
    };
  },
  methods: {
    submit() {
      // Здесь можно добавить логику для обработки введенных данных
      alert(`Вы ввели: ${this.inputValue}`);
    }
  }
};
</script>

<style scoped>
/* Добавьте стили для страницы ввода */
</style>

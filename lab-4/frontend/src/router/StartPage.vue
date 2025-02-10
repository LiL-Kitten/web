<template>
  <div>
    <form @submit.prevent>
      <div class="input-container">
        <input
            type="text"
            v-model.trim="user.username"
            placeholder="Введите логин"
            required
        />
        <input
            type="password"
            v-model.trim="user.password"
            placeholder="Введите пароль"
            required
        />
      </div>
      <div class="button-container">
        <LogIn :login="handleLogin"/>
        <Register :registration="handleRegistration" />
      </div>
    </form>
  </div>
</template>

<script>
import LogIn from "@/components/buttons/LogIn.vue";
import Register from "@/components/buttons/Register.vue";
import {defineComponent} from "vue";
import {logIn, registration} from "@/api/userService.js";
import {getId} from "@/api/apiClient.js";
import router from "@/router/index.js";

export default defineComponent({
  components: {Register, LogIn},

  data() {
    return {
      user: {
        username: '',
        password: '',
      },
    }
  },

  methods: {
    async handleLogin() {
      return this.sendUserData(logIn);
    },

    async handleRegistration() {
      return this.sendUserData(registration);
    },

    async sendUserData(action) {
      if (this.user.username.trim() === '' && this.user.password.trim() === '' )
        throw new Error('заполните все поля ввода')

      const response = await action(this.user);
      const userId = getId(response);
      console.log('переходим!');
      await router.push({path: `/main/${this.user.username}/${userId}`});
    }
  }
})
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  width: 400px;
  margin: 10px;
}

.input-container {
  align-items: center;
}

input {
  margin: 10px;
  padding: 5px;
  border-radius: 15px;
  border: none;
  width: 90%;
  font-family: monospace;
  font-size: 30px;
  text-align: center;
}

.button-container {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

button {
  padding: 8px;
  font-size: 16pt;
  cursor: pointer;
  margin: 10px;
  width: auto;
  height: 5vw;
}
</style>
<template>
  <div>
    <form>
      <input type="text" v-model="user.username" placeholder="Введите логин" required />
      <input type="password" v-model="user.password" placeholder="Введите пароль" required />
      <div>
        <LogIn :login="loginUser"/>
        <Register :registration="registrationUser"/>
      </div>
    </form>
  </div>
</template>

<script>
import LogIn from "@/components/buttons/LogIn.vue";
import Register from "@/components/buttons/Register.vue";
import {defineComponent} from "vue";
import {logIn, registration} from "@/api/userService.js";
import {setToken} from "@/api/apiClient.js";
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
    async loginUser() {
      await this.sendUserData(logIn)
    },

    async registrationUser() {
      await this.sendUserData(registration)
    },

    async sendUserData(action) {
      const response = await action(this.user);
      const data = response.data

      if(data.success) {
        setToken(data.token)
        // await router.push('/main')
      }
    }
  }
})
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  width: 200px;
}

input {
  padding: 5px;
  border-radius: 15px;
  border: none;
  width: 30%;
  font-family: monospace;
  font-size: 30px;
  text-align: center;
}

button {
  padding: 8px;
  font-size: 16pt;
  cursor: pointer;
}
</style>

<style scoped>

</style>
<template>
  <Header :header-text="headerText" :header-title="headerTitle">
    <LogOut v-if="isUserPage" :logout="logout"/>
  </Header>
  <Error class="error-message"/>
  <Main class="main-content">
    <router-view/>
  </Main>
</template>

<script>
import Error from "@/components/errors/Error.vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";
import LogOut from "@/components/buttons/LogOut.vue";
import { logOut } from "@/api/userService.js";
import router from "@/router/index.js";

export default {
  components: {
    Error,
    Header,
    Main,
    LogOut
  },

  data() {
    return {
      headerTitle: '',
      headerText: ''
    };
  },

  computed: {
    isUserPage() {
      return this.$route.path.startsWith('/main/');
    }
  },

  watch: {
    '$route': {
      immediate: true,
      handler(newRoute) {
        if (this.isUserPage) {
          this.headerTitle = 'Имя пользователя: ' + newRoute.params.userName;
          this.headerText = 'ID пользователя: ' + newRoute.params.userId;
        } else if (newRoute.path === '/') {
          this.headerTitle = 'Лабораторная работа №4';
          this.headerText = 'Выполнил: Иевлев Ринат Андреевич';
        }
      }
    }
  },

  methods: {
    logout() {
      console.log('Logging out...');
      logOut();
      console.log('Token removed, redirecting to home...');
      router.push(`/`);
    }
  }
};
</script>

<style>
html, body {
  height: 100%;
  margin: 0;
}

html {
  font-size: 16pt;
  color: #ffffff;
  font-family: monospace;
  background-image: url("assets/img/pic3.jpg");
  background-size: cover;
  background-attachment: fixed;
  display: flex;
  flex-direction: column;
  margin: 0;
}

body {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>

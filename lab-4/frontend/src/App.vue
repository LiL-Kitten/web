<template>
  <Header :header-text="headerText" :header-title="headerTitle">
    <LogOut v-if="isUserPage" :logout="logout"/>
  </Header>
  <Main>
    <router-view :key="$route.path" ref="view"/>
  </Main>
  <Error/>
</template>

<script>
import { ref, watch, computed } from 'vue';
import { useRoute } from 'vue-router';
import Error from "@/components/errors/Error.vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";
import LogOut from "@/components/buttons/LogOut.vue";
import {logOut} from "@/api/userService.js";
import router from "@/router/index.js";

export default {
  components: {
    Error,
    Header,
    Main,
    LogOut
  },
  setup() {
    const route = useRoute();

    const headerTitle = ref('');
    const headerText = ref('');

    const isUserPage = computed(() => {
      return route.path.startsWith('/main/');
    });

    watch(route, (newRoute) => {
      if (isUserPage.value) {
        headerTitle.value = 'Имя пользователя: ' + newRoute.params.userName;
        headerText.value = 'ID пользователя: ' + newRoute.params.userId;
      } else if (newRoute.path === '/') {
        headerTitle.value = 'Лабораторная работа №4';
        headerText.value = 'Выполнил: Иевлев Ринат Андреевич';
      }
    }, { immediate: true });

    return {
      headerTitle,
      headerText,
      isUserPage
    };
  },

  methods: {
    logout() {
      console.log('Logging out...');
      logOut()
      console.log('Token removed, redirecting to home...');
      router.push(`/`);
    },
  }
};
</script>

<style>
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
  margin: 0;
}
</style>

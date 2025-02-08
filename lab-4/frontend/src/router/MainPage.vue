<script>
import LogOut from "@/components/buttons/LogOut.vue";
import FutureForm from "@/components/submission/FutureForm.vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";
import Graph from "@/components/submission/Graph.vue";
import Table from "@/components/submission/Table.vue";
import router from "@/router/index.js";
import {checkAuth, removeToken} from "@/api/apiClient.js";
import {getPoint} from "@/api/pointService.js";

export default {
  components: {
    Table,
    Graph,
    Main,
    Header,
    LogOut,
    FutureForm
  },

  data() {
    return {
      points: [],
      headerTitle: 'Имя пользователя: ',
      headerText: 'ID пользователя: '
    }
  },

  created() {
    this.headerTitle += this.$route.params.userName;
    this.headerText += this.$route.params.userId;
  },

  methods: {
    logout() {
      console.log('Logging out...');
      removeToken();
      checkAuth();
      console.log('Token removed, redirecting to home...');
      router.push(`/`);
    },

    async getPoints() {
      try {
        const response = await getPoint()

        console.log(response)

        if (response.data && response.data.success) {
          this.points = response.data.data
          console.log(response.data.data)
        } else {
          console.error('Ошибка при получении точек:', response.data.message);
        }
      } catch (e) {
        console.error('Ошибка:', e);
      }
    }
  },

  mounted() {
    this.getPoints()
  }
}
</script>
<template>
  <Header :header-title="headerTitle" :header-text="headerText">
    <LogOut :logout="logout"/>
  </Header>
  <Main>
      <FutureForm @points-update="getPoints"/>
      <Graph/>
      <Table :points="points"/>
  </Main>
</template>

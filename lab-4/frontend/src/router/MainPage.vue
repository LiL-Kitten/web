<script>
import LogOut from "@/components/buttons/LogOut.vue";
import FutureForm from "@/components/submission /FutureForm.vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";
import Graph from "@/components/submission /Graph.vue";
import Table from "@/components/submission /Table.vue";

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
      headerTitle: 'Лабораторная работа №4',
      headerText: `Выполнил: Иевлев Ринат Андреевич Вариант: 539991 `
    }
  }

}


</script>

<template>
  <Header :header-title="headerTitle" :header-text="headerText">
    <LogOut/>
  </Header>
  <Main>
    <FutureForm/>
    <Graph/>
    <Table :points="points"/>
  </Main>

</template>

<style scoped>

</style>
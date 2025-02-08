<script>

import {defineComponent} from "vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";
import LoginForm from "@/components/registration/LoginForm.vue";

export default defineComponent({
  components: {LoginForm, Main, Header},

  data() {
    return{
      headerTitle: 'Лабораторная работа №4',
      headerText: 'Выполнил: Иевлев Ринат Андреевич'
    }
  }
})
</script>

<template>
  <Header :header-text="headerText" :header-title="headerTitle"/>
  <Main>
    <LoginForm/>
  </Main>
</template>

<style scoped>

</style>
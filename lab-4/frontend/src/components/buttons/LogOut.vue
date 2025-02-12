<template>
  <MyButton id="log-out" :on-click="logout" :title="logoutTitle"/>
</template>

<script>
import {defineComponent} from "vue";
import MyButton from "@/components/buttons/MyButton.vue";

export default defineComponent({
  components: {MyButton},
  props: {logout: Function},
  data() {
    return{
      logoutTitle: "Выйти"
    }
  }
})
</script>

<style scoped>
#log-out {
  background-image: url(../../assets/img/buttons/logout.svg)
}

button {
  width: 70px;
  height: 70px;
}
</style>
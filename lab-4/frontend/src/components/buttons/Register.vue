<template>
  <MyButton :title="text" :on-click="registration" :text="text"/>
</template>

<script>

import {defineComponent} from "vue";
import MyButton from "@/components/buttons/MyButton.vue";

export default defineComponent({
  components: {MyButton},
  props: {registration: Function},
  data() {
    return {
      text: 'Зарегистрироваться'
    }
  }
})
</script>


<style scoped>

</style>
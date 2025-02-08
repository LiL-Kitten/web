<template>
  <MyButton :title="text" :on-click="login" :text="text"/>
</template>

<script>

import {defineComponent} from "vue";
import MyButton from "@/components/buttons/MyButton.vue";

export default defineComponent({
  components: {MyButton},
  props: {login: Function},
  data() {
    return {
      text: 'Войти'
    }
  }
})
</script>

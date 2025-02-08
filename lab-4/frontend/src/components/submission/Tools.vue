<template>
  <div class="tools">
    <MyButton id="send" :title="sendTitle" :on-click="send"/>
    <MyButton id="clear" :title="clearTitle" :on-click="clear"/>
    <MyButton id="delete" :title="deleteTitle" :on-click="deletePoints"/>
    <MyButton id="random" :title="randomTitle" :on-click="random"/>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import MyButton from "@/components/buttons/MyButton.vue";

export default defineComponent({
  components: {MyButton},

  props: {
    send: Function,
    clear: Function,
    deletePoints: Function,
    random: Function
  },

  data() {
    return {
      sendTitle: 'Отправить точку',
      clearTitle: 'Очистить форму',
      deleteTitle: 'Удалить точки из таблицы',
      randomTitle: 'Случайное заполнение формы'
    }
  }
})
</script>

<style scoped>
.tools > * {
  margin-right: 2%;
  margin-bottom: 2%;
  flex: 0 1 calc(50% - 2%);
}

#clear {
  background-image: url("../../assets/img/buttons/clear.svg");
}

#delete {
  background-image: url("../../assets/img/buttons/delete.svg");
}

#random {
  background-image: url(../../assets/img/buttons/random.svg)
}

#send {
  background-image: url(../../assets/img/buttons/send.svg);
}
</style>
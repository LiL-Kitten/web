<template>
  <div :style="{ opacity: error ? 1 : 0 }" class="error-overlay">
    <div class="error-message" v-if="error">
      {{ error }}
    </div>
    <MyButton class="close-button" title="close-error" :on-click="closeError"/>
  </div>
</template>

<script>
import {mapState} from 'vuex';
import MyButton from "@/components/buttons/MyButton.vue";

export default {
  components: {MyButton},

  computed: {
    ...mapState(['error']),
  },

  methods: {
    closeError() {
      this.$store.commit('clearError')
    },
  },

  watch: {
    error(newError) {
      if (newError) {
        window.scrollTo({top: 0, behavior: 'smooth'})
      }
    },
  },
}
</script>

<style>
.error-overlay {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 2%;
  padding: 10px;
  height: 50px;
}

.error-message {
  background-color: white;
  color: black;
  border-radius: 15px;
  position: relative;
  overflow: hidden;
  align-items: center;
}

.close-button {
  background-image: url(../../assets/img/buttons/close.svg);
}
</style>

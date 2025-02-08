<!-- ErrorDisplay.vue -->
<template>
  <div v-if="error" class="error-message">
    {{ error }}
  </div>
</template>

<script>
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
  setup() {
    const store = useStore();
    const error = computed(() => store.state.error);

    return { error };
  },
};
</script>

<style>
.error-message {
  color: red;
}
</style>

<template>
  <div class="main-container">
    <slot/>
  </div>
</template>

<script setup>
</script>

<style scoped>
.main-container {
  border-radius: 15px;
  background-color: rgba(134, 11, 187, 0.9);
  box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.5);
  margin: 2%;
  display: flex;
  padding-left: 20px;
  align-items: center;
}

.main-container > * {
  flex: 1;
}
</style>

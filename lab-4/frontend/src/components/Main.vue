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
  transition: all 0.3s ease;
}

.main-container {
  flex-direction: row;
}

@media (min-width: 770px) and (max-width: 1152px) {
  .main-container {
    flex-direction: row;
    padding: 15px;
    background-color: rgba(134, 11, 187, 0.8);
  }

  .main-container > * {
    margin: 10px;
  }
}

@media (max-width: 769px) {
  .main-container {
    flex-direction: column;
    padding: 10px;
    background-color: rgba(134, 11, 187, 0.7);
    align-items: center;
  }

  .main-container > * {
    margin: 5px 0;
  }
}

@media (min-width: 1153px) {
  .main-container {
    flex-direction: row;
    background-color: rgba(134, 11, 187, 0.9);
  }

  .main-container > * {
    margin: 15px;
  }
}

.main-container > * {
  flex: 1;
}
</style>

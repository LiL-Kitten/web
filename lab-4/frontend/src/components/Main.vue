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
  align-items: flex-start;
  transition: all 0.3s ease;
  flex-direction: row;
}

@media (min-width: 770px) and (max-width: 1152px) {
  .main-container {
    display: flex;
    flex-wrap: wrap;
    background-color: rgba(134, 11, 187, 0.9);
  }

  .main-container > * {
    margin: 15px;
    flex: 1 1 30%;
    box-sizing: border-box;
  }

  .main-container > :nth-child(3) {
    flex-basis: 100%;
  }
}

@media (max-width: 769px) {
  .main-container {
    flex-direction: column;
    padding: 10px;
    background-color: rgba(134, 11, 187, 0.7);
    align-items: center;
    height: 1000px;
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

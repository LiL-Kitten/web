<template>
  <div class="header">
    <div class="header-content">
      <div class="title">
        <p>{{ headerTitle }}</p>
        <p>{{ headerText }}</p>
      </div>
      <slot/>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    headerTitle: {
      type: String,
      required: true
    },
    headerText: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>
.header {
  background-color: rgb(134, 11, 187);
}

.title > * {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin: 0;
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: end;
  font-size: 35px;
  color: #ffffff;
  margin-left: 2%;
  margin-right: 2%;
  padding-left: 20px;
  padding-top: 20px;
  padding-bottom: 20px;
}

</style>
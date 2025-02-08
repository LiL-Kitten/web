<script>
export default {
  props: {
    title: {
      type: String,
      required: true
    },
    onClick: {
      type: Function,
      required: true
    },
    text: {
      type: String,
      default: ''
    }
  }
}
</script>

<template>
  <button type="button" @click="onClick" :title="title">
    {{ text }}
  </button>
</template>

<style scoped>
button {
  display: inline-block;
  width: 8vw;
  height: 8vw;
  background-color: white;
  color: black;
  border-radius: 15px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s, filter 0.3s;
  text-align: center;
  position: relative;
  background-size: cover;
  aspect-ratio: 1 / 1;
}

button:hover {
  filter: invert(1);
}

button::after {
  content: attr(title);
  position: absolute;
  bottom: -20px;
  left: 50%;
  transform: translateX(-50%);
  color: black;
  opacity: 0;
  transition: opacity 0.3s;
}
</style>

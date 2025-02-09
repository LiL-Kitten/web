<template>
  <svg :width="400" :height="400" xmlns="http://www.w3.org/2000/svg">
    <path
        d="
        M 200,200
        L 200,167
        L 134,200
        L 134,236
        L 200,236
        Q 233,233 233,200
        Z"
        fill="lightblue"
        stroke="blue"
        :transform="`translate(200, 200) scale(${scale*2.5}) translate(-200, -200)`"
    />
  </svg>
</template>

<script>
export default {
  props: {
    scale: {
      type: Number,
      required: true
    }
  }
};
</script>

<template>
  <svg :width="400" :height="400" xmlns="http://www.w3.org/2000/svg">
    <path
        d="
         M 200,167
            Q 233,167 233,200
            L 167,200
            L 200,266
            L 200,233
            L 266,233
            L 266,200
            L 200,200
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

<template>
  <div>
    <svg @click="handleClick" class="mySvg" width="400" height="400">
      <image href="../../assets/img/graph.svg" />
      <Shape :scale="scale" />
      <circle v-for="(point, index) in transformedPoints" :key="index"
              :cx="point.x" :cy="point.y" r="3"
              :fill="point.isHit ? 'green' : 'red'" />
    </svg>
  </div>
</template>

<script>
import Shape from "@/components/submission/Shape.vue";

export default {
  components: {
    Shape
  },

  props: {
    points: {
      type: Array,
      required: true
    },
    valueR: {
      type: [Number, String],
      required: true
    },
    function: {
      type: Function,
      required: true
    }
  },

  computed: {
    transformedPoints() {
      if (!this.points || this.points.length === 0) return []


      return this.points.map(point => {
        let valueX = (point.x * 33) + 200
        let valueY = 200 - (point.y * 33)

        const isHit = point.condition

        return {
          ...point,
          x: valueX,
          y: valueY,
          isHit
        }
      })
    },

    scale() {
      if(this.valueR > 5) return
      return this.valueR / 5
    }
  },

  methods: {
    async handleClick(event) {
      const svg = event.currentTarget
      const svgRect = svg.getBoundingClientRect()
      const svgX = event.clientX - svgRect.left
      const svgY = event.clientY - svgRect.top

      let valueX = (svgX - 200) / 33
      let valueY = (200 - svgY) / 33

      valueX = valueX.toFixed(2)
      valueY = valueY.toFixed(2)

      let point = {
        x: valueX,
        y: valueY,
        r: this.valueR
      }

      console.log(point)
      await this.function(point)
    }
  },
}
</script>

<style scoped>
.mySvg {
  cursor: pointer;
  border-radius: 15px;
}
</style>

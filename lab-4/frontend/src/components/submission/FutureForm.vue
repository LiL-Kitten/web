<template>
  <form>
    <h1>Введите значения</h1>
    <div>
      <label for="x">X : </label>
      <input type="text" id="x" v-model="x"/>
    </div>
    <div>
      <label for="y">Y : </label>
      <input type="text" id="y" v-model="y"/>
    </div>
    <div>
      <label for="r">R : </label>
      <input type="text" id="r" v-model="r"/>
    </div>

    <Tools
        :send="sendPoint"
        :clear="clearForm"
        :delete-points="deletePointsInTable"
        :random="randomValues"/>
  </form>
</template>


<script>
import {defineComponent} from "vue";
import Tools from "@/components/submission/Tools.vue";
import {addPoint, deletePoints} from "@/api/pointService.js";

export default defineComponent({
  data() {
    return {
      x: '',
      y: '',
      r: ''
    }
  },

  components: {
    Tools
  },

  methods: {
    convertToNumber(value) {
      return parseFloat(value)
    },

    validate(value, {min, max, errorText}) {
      let num = this.convertToNumber(value)

      if (num < min || num > max) throw new Error(errorText)
    },

    validateX(value) {
      this.validate(value, {min: 0, max: 5, errorText: 'X должон быть между 0 и 5'})
    },

    validateY(value) {
      this.validate(value, {min: 0, max: 5, errorText: 'Y должон быть между 0 и 5'})
    },

    validateR(value) {
      this.validate(value, {min: 0, max: 5, errorText: 'R должон быть между 0 и 5'})
    },

    async sendPoint() {
      try {
        const point = JSON.stringify({
          x: parseFloat(this.x),
          y: parseFloat(this.y),
          r: parseFloat(this.r)
        })

        const response = addPoint(point)

        const data = (await response).data

        if (data.success) {
          console.log('nice job!')
          console.log(data)

          this.$emit('points-update')
        } else {
          console.log(data.error)
        }
      } catch (error) {
        console.error('Error:', error)
      }
    },

    clearForm() {
      this.x = ''
      this.y = ''
      this.r = ''
    },

    //накидать всяких проверок и связать с таблицей
    deletePointsInTable() {
      this.points = []

      deletePoints()
    },

    randomValues() {
      const minX = -10,
          maxX = 10,
          minY = -10,
          maxY = 10,
          minR = 1,
          maxR = 10;

      this.r = (Math.random() * (maxR - minR) + minR).toFixed(2);
      this.x = (Math.random() * (maxX - minX) + minX).toFixed(2);
      this.y = (Math.random() * (maxY - minY) + minY).toFixed(2);
    }

  }
})
</script>

<style scoped>
input {
  padding: 5px;
  border-radius: 15px;
  border: none;
  width: 40%;
  font-family: monospace;
  font-size: 30px;
  text-align: center;
}

div {
  margin: 2%;
}

form {
  height: 40%;
}

label {
  font-size: 2vw;
}
</style>
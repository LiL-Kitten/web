<template>
  <form @submit.prevent="sendPoint">
    <h1>Введите значения</h1>
    <div>
      <label for="x">x : </label>
      <input type="text" id="x" v-model="x" @blur="validateX(x)"/>
    </div>
    <div>
      <label for="y">y : </label>
      <input type="text" id="y" v-model="y" @blur="validateY(y)"/>
    </div>
    <div>
      <label for="r">r : </label>
      <input type="text" id="r" v-model="r" @blur="validateR(r)"/>
    </div>
    <Tools
        :send="sendPoint"
        :clear="clearForm"
        :delete-points="deletePointsInTable"
        :random="randomValues"
        :is-disabled="!isFormValid"/>
  </form>
  <Graph :value-r="r" :points="points" :function="sendClick"/>
  <Table :points="points"/>
</template>

<script>
import {defineComponent} from "vue";
import Tools from "@/components/submission/Tools.vue";
import {addPoint, deletePoints, getPoint} from "@/api/pointService.js";
import Graph from "@/components/submission/Graph.vue";
import Table from "@/components/submission/Table.vue";

export default defineComponent({
  data() {
    return {
      points: [],
      x: '',
      y: '',
      r: ''
    }
  },

  components: {
    Table,
    Graph,
    Tools
  },

  watch: {
    x(newVal) {
      this.x = newVal;
    },
    y(newVal) {
      this.y = newVal;
    },
    r(newVal) {
      this.r = newVal;
    }
  },

  computed: {
    isFormValid() {
      return this.x.trim() !== '' && this.y.trim() !== '' && this.r.trim() !== '';
    }
  },

  methods: {
    convertToNumber(value) {
      return parseFloat(value);
    },

    validate(value, {min, max, errorText}) {
      const num = this.convertToNumber(value);
      if (isNaN(num)) throw new Error('Значение должно быть числом');
      if (num < min || num > max) throw new Error(errorText);
    },

    validateX(value) {
      const trimmed = value.trim();
      if (trimmed === '') throw new Error('x не может быть пустым');
      this.validate(trimmed, {
        min: -3,
        max: 5,
        errorText: 'x должен быть между -3 и 5'
      });
    },

    validateY(value) {
      const trimmed = value.trim();
      if (trimmed === '') throw new Error('y не может быть пустым');
      this.validate(trimmed, {
        min: -5,
        max: 5,
        errorText: 'y должен быть между -5 и 5'
      });
    },

    validateR(value) {
      const trimmed = value.trim();
      if (trimmed === '') throw new Error('r не может быть пустым');
      this.validate(trimmed, {
        min: -3,
        max: 5,
        errorText: 'r должен быть между -3 и 5'
      });
    },

    async sendPoint() {

      this.validateX(this.x);
      this.validateY(this.y);
      this.validateR(this.r);

      const point = JSON.stringify({
        x: parseFloat(this.x.trim()),
        y: parseFloat(this.y.trim()),
        r: parseFloat(this.r.trim())
      });

      try {
        const response = await addPoint(point);


        console.log('nice job!');
        console.log(response);

        // console.log(await getPoint())
        this.points = (await getPoint()).data
      } catch (error) {
        console.error('Error:', error.message);
        throw error;
      }
    },

    async sendClick(point) {
      try {
        const response = await addPoint(point);


        console.log('nice job!');
        console.log(response);

        this.points = (await getPoint()).data
      } catch (error) {
        console.error('Error:', error.message);
        throw error;
      }
    },

    clearForm() {
      this.x = '';
      this.y = '';
      this.r = '';
    },

    deletePointsInTable() {
      this.points = []
      deletePoints();
      // this.$emit('points-update');
    },

    randomValues() {
      const minX = -3,
          maxX = 5,
          minY = -5,
          maxY = 5,
          minR = -3,
          maxR = 5;

      this.r = (Math.random() * (maxR - minR) + minR).toFixed(2);
      this.x = (Math.random() * (maxX - minX) + minX).toFixed(2);
      this.y = (Math.random() * (maxY - minY) + minY).toFixed(2);
    },

  }
});
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
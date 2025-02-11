<template>
  <form @submit.prevent="sendPoint">
    <h1>Введите значения</h1>

    <div v-for="value in values">
      <label :for="value.key">{{value.key}}:</label>
      <input
          type="text"
          :id="value.key"
          v-model="value.content"
          @input="validate(value.content, value.conditions)"
      />
    </div>

    <Tools
        :send="sendPoint"
        :clear="clearForm"
        :delete-points="deletePointsInTable"
        :random="randomValues"
    />
  </form>

  <Graph
      :value-r="values.find(value => value.key === 'r').content"
      :points="points" :function="sendClick"
  />

  <Table :points="points"/>
</template>

<script>
import {defineComponent} from "vue";
import Tools from "@/components/submission/Tools.vue";
import {addPoint, deletePoints, getPoints} from "@/api/pointService.js";
import Graph from "@/components/submission/Graph.vue";
import Table from "@/components/submission/Table.vue";

export default defineComponent({
  components: {
    Table,
    Graph,
    Tools
  },

  data() {
    return {
      points: [],
      values: [
        {
          key: 'x',
          content: '',
          conditions: {
            min: -3,
            max: 5,
            errorText: 'Значение должно быть от -3 до 5'
          }
        },
        {
          key: 'y',
          content: '',
          conditions: {
            min: -5,
            max: 5,
            errorText: 'Значение должно быть от -5 до 5'
          }
        },
        {
          key: 'r',
          content: '',
          conditions: {
            min: 0,
            max: 5,
            errorText: 'Значение должно быть от 0 до 5'
          }
        }
      ]
    }
  },

  async mounted() {
    let newPoints = (await getPoints())
    console.log(newPoints)
    this.points = newPoints
  },

  methods: {
    convertToNumber(value) {
      return parseFloat(value);
    },

    validate(content, conditions) {
      const num = this.convertToNumber(content);
      console.log(num)
      if (isNaN(num)) throw new Error('Значение должно быть числом');
      if (num < conditions.min || num > conditions.max) throw new Error(conditions.errorText);
    },

    async sendPoint() {

      this.values.forEach(value => {
        this.validate(value.content, value.conditions);
      });

      const point = JSON.stringify(
          this.values.reduce((acc, value) => {
            acc[value.key] = parseFloat(value.content.trim());
            return acc;
          }, {})
      );

      try {
        const response = await addPoint(point);

        console.log('nice job!');
        console.log(response);

        this.points = (await getPoints()).data
      } catch (error) {
        console.error('Error:', error.message);
        throw error;
      }
    },

    async sendClick(point) {

        this.validate(point.r, this.values.find(value => value.key === 'r').conditions)

        const response = await addPoint(point);

        console.log('nice job!');
        console.log(response);

        this.points = (await getPoints()).data
    },

    async deletePointsInTable() {
      this.points = []
      await deletePoints();
    },

    clearForm() {
      this.values.forEach(value => {
        value.content = ''
      })
    },


    randomValues() {
      this.values.forEach(value => {
        const randomValue = (Math.random() * (value.conditions.max - value.conditions.min) + value.conditions.min)
            .toFixed(2)
        console.log(randomValue)
        value.content = randomValue
      });
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
<template>
  <form @submit.prevent="sendPoint">
    <h1>Введите значения</h1>

    <div v-for="value in values">
      <label :for="value.key">{{ value.key }}:</label>
      <input
          type="text"
          :id="value.key"
          v-model="value.content"
          @input="validate(value)"
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
      :value-r="convertToNumber(values.find(value => value.key === 'r').content)"
      :points="points"
      :function="sendClick"
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
            errorText: 'Значение x должно быть от -3 до 5'
          }
        },
        {
          key: 'y',
          content: '',
          conditions: {
            min: -5,
            max: 5,
            errorText: 'Значение y должно быть от -5 до 5'
          }
        },
        {
          key: 'r',
          content: '',
          conditions: {
            min: 0,
            max: 5,
            errorText: 'Значение r должно быть от 0 до 5'
          }
        }
      ],
      coordinates: {
        x: null,
        y: null,
        r: null
      }
    }
  },

  async mounted() {
    let newPoints = ((await getPoints()).data)
    console.log(newPoints)
    this.points = newPoints
  },

  methods: {
    convertToNumber(value) {
      const normalizedValue = String(value).replace(',', '.')
      const trimmedValue = normalizedValue.replace(/\.+$/, '')

      const regex = /^-?\d+(\.\d+)?$/
      if (regex.test(trimmedValue)) {
        return parseFloat(trimmedValue).toFixed(2)
      } else {
        return NaN
      }
    },

    validate(value) {
      const num = this.convertToNumber(value.content);
      console.log(num)
      if (isNaN(num)) throw new Error(`Значение ${value.key} должно быть числом`);
      if (num < value.conditions.min || num > value.conditions.max) throw new Error(value.conditions.errorText);
      return true
    },

    async sendPoint() {

      this.values.forEach(value => {
        this.validate(value)

        const convertedValue = this.convertToNumber(value.content)

        if ( this.coordinates.hasOwnProperty(value.key) ) this.coordinates[value.key] = convertedValue
      })

      const point = JSON.stringify(this.coordinates)

      try {
        const response = await addPoint(point)

        console.log('nice job!')
        console.log(response)

        this.points = (await getPoints()).data
      } catch (error) {
        console.error('Error:', error.message)
        throw error
      }
    },

    async sendClick(point) {
      this.validate(this.values.find(value => value.key === 'r'))

      const response = await addPoint(point)

      console.log('nice job!')
      console.log(response)

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
      })
    },

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
  font-size: 30px;
}
</style>
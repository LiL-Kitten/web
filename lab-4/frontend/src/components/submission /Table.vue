<script>
  export default {
    props: {
      points: []
    },

  }
</script>

<template>
  <div class="table-container">
    <table class="points-table">
      <thead>
      <tr>
        <th>x</th>
        <th>y</th>
        <th>r</th>
        <th>condition</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(point, index) in points" :key="index">
        <td>{{ point.x }}</td>
        <td>{{ point.y }}</td>
        <td>{{ point.r }}</td>
        <td :class="{ 'hit': point.condition }">
          {{ point.condition ? 'Hit' : 'Miss' }}
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>


<style scoped>
.table-container {
  max-width: 100%;
  max-height: 400px;
  overflow: auto;
}

.points-table {
  width: 100%;
  border-collapse: collapse;
}

.points-table th, .points-table td {
  border: 1px solid #ddd;
  padding: 8px;
}

.points-table th {
  background-color: #f2f2f2;
}

.hit {
  color: green;
}
</style>

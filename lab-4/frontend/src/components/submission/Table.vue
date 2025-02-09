<template>
  <div class="scroll-table">
    <table class="points-table">
      <thead>
      <tr>
        <th>x</th>
        <th>y</th>
        <th>r</th>
        <th>hit</th>
      </tr>
      </thead>
    </table>
    <div class="scroll-table-body">
      <table class="points-table">
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
  </div>
</template>

<script>
export default {
  props: {
    points: {
      type: Array,
      default: () => []
    }
  }
}
</script>

<style scoped>
.scroll-table {
  border-radius: 15px;
  max-width: 100%;
  display: flex;
  flex-direction: column;
  height: 400px;
}

.scroll-table-body {
  flex: 1;
  overflow-y: auto;
  margin-top: 0;
}

.scroll-table table {
  width: 100%;
  table-layout: fixed;
  border-collapse: collapse;
}

.scroll-table thead th {
  font-weight: bold;
  text-align: left;
  padding: 10px 15px;
  font-size: 14px;
  border: none;
}

.scroll-table tbody td {
  text-align: left;
  border-right: 1px solid #ddd;
  padding: 10px 15px;
  font-size: 14px;
  vertical-align: top;
  background: black;
}

.scroll-table-body::-webkit-scrollbar {
  display: none;
}

.hit {
  color: green;
}
</style>

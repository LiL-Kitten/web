<template>
  <div>
    <router-view :key="$route.path" ref="view"/>
  </div>
</template>

<style>
html {
  font-size: 16pt;
  color: #ffffff;
  font-family: monospace;
  background-image: url("assets/img/pic3.jpg");
  background-size: cover;
  background-attachment: fixed;
  display: flex;
  flex-direction: column;
  margin: 0;
}

body {
  margin: 0;
}
</style>

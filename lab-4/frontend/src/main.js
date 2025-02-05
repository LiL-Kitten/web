import './assets/main.css';
import { createApp } from 'vue';
import App from './App.vue';
import TestApp from "@/TestApp.vue";

// const app = createApp(App);
// app.mount('#app');

const testApp = createApp(TestApp)
testApp.mount('#app')

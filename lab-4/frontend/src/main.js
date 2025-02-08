import { createApp } from 'vue';
import './assets/main.css';
import App from "@/App.vue";
import router from "@/router/index.js";
import store from "@/components/errors/store.js";

const app = createApp(App)

app.config.errorHandler = (err, instance, info) => {
    store.dispatch('triggerError', err.message || 'Произошла ошибка')
};

app.use(store)
app.use(router)
app.mount('#app')

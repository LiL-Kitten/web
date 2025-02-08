import { createRouter, createWebHistory } from 'vue-router';
import StartPage from "@/router/StartPage.vue";
import MainPage from "@/router/MainPage.vue";
import { checkAuth } from "@/api/apiClient.js";

const routes = [
    {
        path: '/',
        component: StartPage
    },
    {
        path: '/main/:userName/:userId',
        component: MainPage
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

router.beforeEach((to, from, next) => {
    const auth = checkAuth();
    console.log(auth);

    if (!auth && to.path !== '/') {
        next('/')
        console.log('Redirecting to StartPage because user is not authenticated.')
        throw new Error('Вы не авторизованы')
    } else {
        next()
        console.log('User is authenticated or accessing StartPage.')
    }
})

export default router;

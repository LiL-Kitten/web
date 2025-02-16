import { createRouter, createWebHistory } from 'vue-router';
import StartPage from "@/router/StartPage.vue";
import { checkAuth } from "@/api/apiClient.js";
import UserPage from "@/router/UserPage.vue";

const routes = [
    {
        path: '/',
        component: StartPage
    },
    {
        path: '/main/:userName/:userId',
        component: UserPage
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from, next) => {
    const auth = checkAuth()
    console.log(auth)

    if (!auth && to.path !== '/') {
        next('/')
        throw new Error('Вы не авторизованы')
    } else {
        next()
    }
})

export default router
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

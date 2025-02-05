import { createRouter, createWebHistory } from 'vue-router'
import StartRouter from "@/router/StartRouter.vue";

const routes = [
    {
        path: '/',
        name: 'home',
        component: StartRouter
    },
    {
        path: '/about',
        name: 'about',
        component: () => import('FinishRouter.vue')
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
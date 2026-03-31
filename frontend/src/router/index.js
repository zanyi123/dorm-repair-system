import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')  // ← 是 ../ 不是 @/
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue')  // ← 是 ../
    },
    {
        path: '/student/home',
        name: 'StudentHome',
        component: () => import('../views/student/Home.vue')  // ← 是 ../
    },
    {
            path: '/student/orders',
            name: 'StudentOrders',
            component: () => import('../views/student/OrderList.vue')
    },
    {
            path: '/student/create-order',  // ⚠️ 新增路由
            name: 'CreateOrder',
            component: () => import('../views/student/CreateOrder.vue')
    },
    {
            path: '/admin/home',
            name: 'AdminHome',
            component: () => import('../views/admin/AdminHome.vue')
    },
    {
        path: '/admin/orders',
        name: 'AdminOrders',
        component: () => import('../views/admin/AdminOrderList.vue')  // ← 是 ../
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
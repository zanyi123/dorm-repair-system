<template>
    <div class="home-container">
        <h2>👋 欢迎，{{ account }}</h2>
        <el-card style="margin: 20px 0;">
            <el-button type="primary" @click="goOrders">查看我的报修单</el-button>
            <el-button type="primary" @click="createOrder">创建新报修单</el-button>
            <el-button @click="logout" style="margin-left: 10px;">退出登录</el-button>
        </el-card>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const account = ref(localStorage.getItem('account'))  // ← 从 localStorage 读取

const goOrders = () => {
    router.push('/student/orders')
}

const createOrder = () => {
    router.push('/student/create-order')
}


const logout = () => {
    // ⚠ 清除所有缓存
    localStorage.removeItem('token')
    localStorage.removeItem('userId')
    localStorage.removeItem('account')
    router.push('/login')
}
</script>

<style scoped>
.home-container {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
}
</style>
<template>
    <div class="admin-home">
        <h2>👋 欢迎， {{ account }}</h2>
        <el-card style="margin: 20px 0;">
            <el-button type="primary" @click="goOrders">报修单管理</el-button>
            <el-button @click="logout" style="margin-left: 10px;" type="danger">退出登录</el-button>
        </el-card>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()
const account = ref(localStorage.getItem('account'))  // ← 从 localStorage 读取

const goOrders = () => {
    router.push('/admin/orders')
}

const logout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('userId')
    localStorage.removeItem('account')
    ElMessage.success('退出成功')
    router.push('/login')
}
</script>

<style scoped>
.admin-home {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
}
</style>
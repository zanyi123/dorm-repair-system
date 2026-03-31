<template>
    <div class="login-container">
        <h2>🏠 宿舍报修系统</h2>

        <el-form :model="form" label-width="80px" style="max-width: 400px; margin: 0 auto;">
            <el-form-item label="账号">
                <el-input v-model="form.account" placeholder="请输入账号" />
            </el-form-item>

            <el-form-item label="密码">
                <el-input v-model="form.password" type="password" placeholder="请输入密码" />
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="handleLogin" style="width: 100%">登录</el-button>
            </el-form-item>

            <el-form-item>
                <span>没有账号？</span>
                <el-link type="primary" @click="goRegister">去注册</el-link>
            </el-form-item>
        </el-form>
    </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()

const form = reactive({
    account: '',
    password: ''
})

const handleLogin = async () => {
    try {
        const res = await axios.post('http://localhost:8080/api/auth/login', form)

        console.log('=== 登录返回 ===')
        console.log('完整返回:', res.data)
        console.log('data 字段:', res.data.data)

        if (res.data.success) {
            // ⚠️ 保存所有信息
            localStorage.setItem('token', res.data.data.token)
            localStorage.setItem('userId', res.data.data.id)
            localStorage.setItem('account', res.data.data.account)
            localStorage.setItem('role', res.data.data.role)  // ⚠️ 记得保存 role

            console.log('用户 ID:', res.data.data.id)
            console.log('账号:', res.data.data.account)
            console.log('角色:', res.data.data.role)
            console.log('Token 已保存:', localStorage.getItem('token'))

            // ⚠️ 根据角色跳转（逻辑清晰版）
            if (res.data.data.role === 1) {
                // 学生
                router.push('/student/home')
            } else if (res.data.data.role === 2) {
                // 管理员
                router.push('/admin/home')
            } else {
                // 未知角色
                ElMessage.error('未知角色')
            }
        } else {
            ElMessage.error(res.data.message)
        }
    } catch (error) {
        ElMessage.error('登录失败：' + error.message)
    }
}

const goRegister = () => {
    router.push('/register')
}
</script>

<style scoped>
.login-container {
    max-width: 400px;
    margin: 100px auto;
    padding: 40px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

h2 {
    text-align: center;
    margin-bottom: 30px;
}
</style>
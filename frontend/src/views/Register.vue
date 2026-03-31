<template>
    <div class="register-container">
        <h2> 用户注册</h2>

        <el-form :model="form" label-width="80px" style="max-width: 400px; margin: 0 auto;">
            <el-form-item label="账号">
                <el-input v-model="form.account" placeholder="学号或工号" />
            </el-form-item>

            <el-form-item label="密码">
                <el-input v-model="form.password" type="password" placeholder="请输入密码" />
            </el-form-item>

            <el-form-item label="角色">
                <el-radio-group v-model="form.role">
                    <el-radio :label="1">学生</el-radio>
                    <el-radio :label="2">管理员</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="handleRegister" style="width: 100%">注册</el-button>
            </el-form-item>

            <el-form-item>
                <el-link @click="goLogin">已有账号？去登录</el-link>
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
    password: '',
    role: 1
})

const handleRegister = async () => {
    try {
        const res = await axios.post('http://localhost:8080/api/auth/register', form)
        if (res.data.code === 200) {
            alert('注册成功！')
            router.push('/login')
        } else {
            alert(res.data.message)
        }
    } catch (error) {
        alert('注册失败：' + error.message)
    }
}

const goLogin = () => {
    router.push('/login')
}
</script>

<style scoped>
.register-container {
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
<template>
    <div class="create-order">
        <h2>📝 创建报修单</h2>

        <el-card style="max-width: 600px; margin: 0 auto;">
            <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
                <!-- 楼栋 - 输入框 -->
                <el-form-item label="楼栋" prop="building">
                    <el-input v-model="form.building" placeholder="如：A 栋" />
                </el-form-item>

                <!-- 房间号 - 输入框 -->
                <el-form-item label="房间号" prop="room">
                    <el-input v-model="form.room" placeholder="如：101" />
                </el-form-item>

                <!-- 设备类型 - 输入框 -->
                <el-form-item label="设备类型" prop="deviceType">
                    <el-input v-model="form.deviceType" placeholder="如：水龙头、电灯、空调" />
                </el-form-item>

                <!-- 问题描述 - 输入框 -->
                <el-form-item label="问题描述" prop="description">
                    <el-input
                        v-model="form.description"
                        type="textarea"
                        :rows="4"
                        placeholder="请详细描述问题"
                    />
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm" :loading="loading" style="width: 100%">
                        提交报修
                    </el-button>
                    <el-button @click="goBack" style="width: 100%; margin-top: 10px;">
                        取消
                    </el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const router = useRouter()
const formRef = ref(null)
const loading = ref(false)

const form = reactive({
    building: '',
    room: '',
    deviceType: '',
    description: ''
})

const rules = {
    building: [{ required: true, message: '请输入楼栋', trigger: 'blur' }],
    room: [{ required: true, message: '请输入房间号', trigger: 'blur' }],
    deviceType: [{ required: true, message: '请输入设备类型', trigger: 'blur' }],
    description: [{ required: true, message: '请输入问题描述', trigger: 'blur' }]
}

const submitForm = async () => {
    await formRef.value.validate(async (valid) => {
        if (!valid) return

        loading.value = true

        try {
            const token = localStorage.getItem('token')
            const userId = localStorage.getItem('userId')

            const formData = {
                ...form,
                studentId: parseInt(userId)
            }

            const res = await axios.post('http://localhost:8080/api/student/orders', formData, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })

            if (res.data.success) {
                ElMessage.success('报修单提交成功！')
                router.push('/student/orders')
            } else {
                ElMessage.error(res.data.message || '提交失败')
            }
        } catch (error) {
            ElMessage.error('提交失败：' + error.message)
        } finally {
            loading.value = false
        }
    })
}

const goBack = () => {
    router.push('/student/home')
}
</script>

<style scoped>
.create-order {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
}

h2 {
    text-align: center;
    margin-bottom: 30px;
}
</style>
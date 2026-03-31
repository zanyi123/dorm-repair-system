<template>
    <div class="order-list">
        <h2>📋 报修单管理</h2>

        <el-button @click="logout" style="margin-bottom: 20px;">退出登录</el-button>

        <el-table :data="orders" border style="width: 100%">
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="building" label="楼栋" />
            <el-table-column prop="room" label="房间" />
            <el-table-column prop="deviceType" label="设备" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="status" label="状态">
                <template #default="{ row }">
                    <el-tag :type="row.status === 1 ? 'warning' : 'success'">
                        {{ row.status === 1 ? '待处理' : '已完成' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" />
            <el-table-column label="操作" width="200">
                <template #default="{ row }">
                    <el-button size="small" @click="updateStatus(row.id, 2)">完成</el-button>
                    <el-button size="small" type="danger" @click="deleteOrder(row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const orders = ref([])

onMounted(async () => {
    await loadOrders()
})

const loadOrders = async () => {
    try {
        const token = localStorage.getItem('token')
        const res = await axios.get('http://localhost:8080/api/admin/orders', {

            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        if (res.data.code === 200) {
            orders.value = res.data.data
        }
    } catch (error) {
        alert('加载失败：' + error.message)
    }
}

const updateStatus = async (id, status) => {
    try {
        const token = localStorage.getItem('token')
        await axios.put(`http://localhost:8080/api/admin/orders/${id}?status=${status}`, null, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        alert('更新成功')
        await loadOrders()
    } catch (error) {
        alert('更新失败：' + error.message)
    }
}

const deleteOrder = async (id) => {
    try {
        const token = localStorage.getItem('token')
        await axios.delete(`http://localhost:8080/api/admin/orders/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        alert('删除成功')
        await loadOrders()
    } catch (error) {
        alert('删除失败：' + error.message)
    }
}

const logout = () => {
    localStorage.removeItem('token')
    router.push('/login')
}
</script>

<style scoped>
.order-list {
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;
}
</style>
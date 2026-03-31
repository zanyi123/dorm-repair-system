<template>
    <div class="order-list">
        <h2>📋 我的报修单</h2>

        <el-button type="primary" @click="goHome" style="margin-bottom: 20px;">返回首页</el-button>

        <el-table :data="orders" border style="width: 100%">
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="building" label="楼栋" />
            <el-table-column prop="room" label="房间" />
            <el-table-column prop="deviceType" label="设备类型" />
            <el-table-column prop="description" label="描述" />
            <el-table-column prop="status" label="状态">
                <template #default="{ row }">
                    <el-tag :type="getStatusType(row.status)">
                        {{ getStatusText(row.status) }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" />
            <el-table-column label="操作" width="120">
                <template #default="{ row }">
                    <el-button
                        v-if="row.status === 1"
                        type="danger"
                        size="small"
                        @click="cancelOrder(row)"
                    >
                        取消
                    </el-button>
                    <span v-else style="color: #999;">不可取消</span>
                </template>
            </el-table-column>
        </el-table>

        <div v-if="orders.length === 0" style="text-align: center; padding: 20px; color: #999;">
            暂无报修单
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { ElMessage, ElMessageBox } from 'element-plus'

const router = useRouter()
const orders = ref([])

onMounted(async () => {
    console.log('=== 开始加载报修单 ===')

    try {
        const token = localStorage.getItem('token')
        const userId = localStorage.getItem('userId')

        console.log('用户 ID:', userId)

        if (!userId) {
            ElMessage.error('未登录，请先登录')
            router.push('/login')
            return
        }

        const res = await axios.get(`http://localhost:8080/api/student/orders?studentId=${userId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })

        console.log('后端返回:', res.data)

        if (res.data.success) {
            orders.value = res.data.data || []
            console.log('报修单数量:', orders.value.length)
        } else {
            ElMessage.error(res.data.message || '加载失败')
        }
    } catch (error) {
        console.error('加载错误:', error)
        ElMessage.error('加载失败：' + error.message)
    }
})

// 取消报修单
const cancelOrder = async (row) => {
    try {
        await ElMessageBox.confirm('确定要取消这个报修单吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        })

        const token = localStorage.getItem('token')
        const userId = localStorage.getItem('userId')

        const res = await axios.put(
            `http://localhost:8080/api/student/orders/${row.id}/cancel?studentId=${userId}`,
            {},
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            }
        )

        if (res.data.success) {
            ElMessage.success('取消成功！')
            await loadOrders()
        } else {
            ElMessage.error(res.data.message || '取消失败')
        }
    } catch (error) {
        if (error !== 'cancel') {
            ElMessage.error('取消失败：' + error.message)
        }
    }
}

// 加载报修单
const loadOrders = async () => {
    const token = localStorage.getItem('token')
    const userId = localStorage.getItem('userId')

    const res = await axios.get(`http://localhost:8080/api/student/orders?studentId=${userId}`, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })

    if (res.data.success) {
        orders.value = res.data.data || []
    }
}

// 状态文字
const getStatusText = (status) => {
    const map = { 1: '待处理', 2: '已完成', 3: '已取消' }
    return map[status] || '未知'
}

// 状态颜色
const getStatusType = (status) => {
    const map = { 1: 'warning', 2: 'success', 3: 'info' }
    return map[status] || ''
}

const goHome = () => {
    router.push('/student/home')
}
</script>

<style scoped>
.order-list {
    padding: 20px;
    max-width: 1000px;
    margin: 0 auto;
}
</style>
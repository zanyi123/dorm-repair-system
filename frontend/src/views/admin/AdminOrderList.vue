<template>
    <div class="admin-order-list">
        <h2>📋 报修单管理</h2>

        <el-button type="primary" @click="goHome" style="margin-bottom: 20px;">返回首页</el-button>

        <el-card style="margin-bottom: 20px;">
            <el-form :inline="true">
                <el-form-item label="状态筛选">
                    <el-select v-model="filterStatus" placeholder="全部状态" @change="loadOrders">
                        <el-option label="全部" value="" />
                        <el-option label="待处理" :value="1" />
                        <el-option label="已完成" :value="2" />
                        <el-option label="已取消" :value="3" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="loadOrders">查询</el-button>
                </el-form-item>
            </el-form>
        </el-card>

        <el-table :data="orders" border style="width: 100%">
            <el-table-column prop="id" label="ID" width="80" />
            <el-table-column prop="studentAccount" label="学生账号" width="120" />
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
            <el-table-column label="操作" width="200">
                <template #default="{ row }">
                    <el-button
                        v-if="row.status === 1"
                        type="success"
                        size="small"
                        @click="updateStatus(row, 2)"
                    >
                        完成
                    </el-button>
                    <el-button
                        type="danger"
                        size="small"
                        @click="deleteOrder(row)"
                    >
                        删除
                    </el-button>
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
const filterStatus = ref('')

onMounted(() => {
    loadOrders()
})

const loadOrders = async () => {
    const token = localStorage.getItem('token')

    console.log('=== 开始加载报修单 ===')
    console.log('Token:', token)

    let url = 'http://localhost:8080/api/admin/orders'
    if (filterStatus.value) {
        url = url + '?status=' + filterStatus.value
    }

    console.log('请求 URL:', url)

    try {
        const res = await axios.get(url, {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })

        console.log('后端返回:', res.data)
        console.log('data 字段:', res.data.data)
        console.log('data 类型:', typeof res.data.data)
        console.log('data 长度:', res.data.data ? res.data.data.length : 'null')

        if (res.data.success) {
            orders.value = res.data.data || []
            console.log('报修单数量:', orders.value.length)
            console.log('第一条数据:', orders.value[0])
        } else {
            ElMessage.error(res.data.message || '加载失败')
        }
    } catch (error) {
        console.error('加载错误:', error)
        ElMessage.error('加载失败：' + error.message)
    }
}

const updateStatus = async (row, newStatus) => {
    try {
        await ElMessageBox.confirm('确定要标记为已完成吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        })

        const token = localStorage.getItem('token')
        const res = await axios.put(
            'http://localhost:8080/api/admin/orders/' + row.id + '/status?status=' + newStatus,
            {},
            {
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            }
        )

        if (res.data.success) {
            ElMessage.success('状态更新成功！')
            loadOrders()
        } else {
            ElMessage.error(res.data.message || '更新失败')
        }
    } catch (error) {
        if (error !== 'cancel') {
            ElMessage.error('更新失败：' + error.message)
        }
    }
}

const deleteOrder = async (row) => {
    try {
        await ElMessageBox.confirm('确定要删除这个报修单吗？', '警告', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        })

        const token = localStorage.getItem('token')
        const res = await axios.delete(
            'http://localhost:8080/api/admin/orders/' + row.id,
            {
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            }
        )

        if (res.data.success) {
            ElMessage.success('删除成功！')
            loadOrders()
        } else {
            ElMessage.error(res.data.message || '删除失败')
        }
    } catch (error) {
        if (error !== 'cancel') {
            ElMessage.error('删除失败：' + error.message)
        }
    }
}

const getStatusText = (status) => {
    const map = { 1: '待处理', 2: '已完成', 3: '已取消' }
    return map[status] || '未知'
}

const getStatusType = (status) => {
    const map = { 1: 'warning', 2: 'success', 3: 'info' }
    return map[status] || ''
}

const goHome = () => {
    router.push('/admin/home')
}
</script>

<style scoped>
.admin-order-list {
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;
}
</style>
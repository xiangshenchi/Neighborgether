<template>
    <div class="web-contain" style="display:flex;width:100%;height:100%;flex-direction: column;">
        <!-- 搜索框 -->
        <div class="search-bar" style="margin-bottom: 10px; text-align: center;">
            <el-input v-model="searchQuery" placeholder="输入报修内容搜索" clearable style="width: 300px;" />
        </div>

        <!-- 时间选择器 -->
        <div class="date-picker">
            <div class="block">
                <el-date-picker v-model="dateRange" type="daterange" unlink-panels range-separator="至"
                    start-placeholder="起始时间" end-placeholder="截至时间" :shortcuts="shortcuts" :size="size"
                    @change="handleDateChange" />
            </div>
        </div>

        <!-- 数据表 -->
        <div style="flex:1;max-width: 100%;width: 100%;overflow: auto;">
            <el-table :data="currentTableData" :row-style="rowStyle" :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="RepairID" label="序号" />
                <el-table-column prop="RepairContent" label="报修内容" />
                <el-table-column prop="RepairDate" label="报修日期" />
                <el-table-column prop="RepairStatus" label="当前状态">
                    <template #default="scope">
                        <div class="ellipsis">
                            {{ scope.row.RepairStatus }}
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <!-- 分页器 -->
        <div class="pagination-block">
            <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 20, 50]"
                :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
                :total="filteredData.length" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
        </div>
    </div>
</template>

<script lang="ts" setup>
// 1.分页器
import { ref, computed, getCurrentInstance } from 'vue'
import type { ComponentSize } from 'element-plus'

const { proxy } = getCurrentInstance()!;
let currentPage = ref(1)
let pageSize = ref(10)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)
const dateRange = ref<Date[]>([])
const searchQuery = ref('') // 搜索关键字

interface Announcement {
    RepairID: number;
    RepairContent: string;
    RepairDate: string;
    RepairStatus: string;
}
const tableData = ref<Announcement[]>([])

fetchData()
async function fetchData() {
    try {
        const res = await (proxy as any).$axios.get('/报修/list')
        tableData.value = res.data
    } catch (error) {
        console.error('Error fetching data:', error)
    }
}

// 根据报修内容和时间范围筛选
const filteredData = computed(() => {
    return tableData.value.filter(item => {
        const isInDateRange = dateRange.value.length === 0 || (new Date(item.RepairDate) >= dateRange.value[0] && new Date(item.RepairDate) <= dateRange.value[1])
        const matchesSearch = !searchQuery.value || item.RepairContent.toLowerCase().includes(searchQuery.value.toLowerCase())
        return isInDateRange && matchesSearch
    })
})

const currentTableData = computed(() => {
    const start = (currentPage.value - 1) * pageSize.value
    const end = start + pageSize.value
    return filteredData.value.slice(start, end)
})

const handleSizeChange = (val: number) => {
    pageSize.value = val
    currentPage.value = 1
}
const handleCurrentChange = (val: number) => {
    currentPage.value = val
}
const handleDateChange = () => {
    currentPage.value = 1
}

// 设置表格行高
const rowStyle = () => {
    return {
        height: '50px',
    };
}

// 日期快捷选项
const shortcuts = [
    {
        text: '过去一周',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            return [start, end]
        },
    },
    {
        text: '过去一月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            return [start, end]
        },
    },
    {
        text: '过去三月',
        value: () => {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            return [start, end]
        },
    },
]
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    gap: 20px;
    width: 100%;
}

.search-bar {
    display: flex;
    justify-content: center;
}

.date-picker {
    /* display: flex;
    width: 100%;
    padding: 0;
    flex: 0;
    align-items: center;
    justify-content: center; */
    width: 80%;
    margin-left: 20px;
    margin-bottom: 10px;
}

.pagination-block {
    display: flex;
    flex: 0;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
    width: 100%;
    bottom: 10px;
}

.ellipsis {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}
</style>

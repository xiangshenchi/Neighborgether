<template>
  <div class="web-contain"
    style="display:flex;width:100%;height:100%;flex-direction: column;justify-content: space-between;">
    <!-- 时间选择器 -->
    <el-card style="margin:10px;padding:0px;">
      <div class="date-picker">
        <div class="block" style="display: flex;align-items: center;">
          <h4 style="margin-right: 15px">日期选择</h4>
          <el-date-picker v-model="dateRange" type="daterange" unlink-panels range-separator="至"
            start-placeholder="起始时间" end-placeholder="截至时间" :shortcuts="shortcuts" :size="size"
            @change="handleDateChange" />
        </div>
        <el-button type="primary" @click="handleDateDelete">取消</el-button>
      </div>
    </el-card>
    <!-- 数据表 -->
    <el-card style="margin:10px;overflow-y: auto;flex:1">
      <div style="flex:1;max-width: 100%;width: 100%;overflow: auto;padding: 10px;">
        <el-table :data="currentTableData" :row-style="rowStyle" :header-cell-style="{ 'text-align': 'center' }"
          :cell-style="{ 'text-align': 'center' }">
          <el-table-column label="序号">
            <template #default="scope">
              {{ (currentPage - 1) * pageSize + scope.$index + 1 }}
            </template>
          </el-table-column>
          <el-table-column label="发布时间">
            <template #default="scope">
              {{ formatDate(scope.row.publishdate) }}
            </template>
          </el-table-column>
          <el-table-column prop="title" label="公告标题" />
          <el-table-column prop="content" label="公告内容">
            <template #default="scope">
              <div class="ellipsis">
                {{ scope.row.content }}
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="userid" label="发布人" />
          <el-table-column fixed="right" label="公告详情">
            <template #default="scope">
              <el-button link type="primary" @click="handleClick(scope.row)">
                详情
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
    <el-card style="margin: 10px;">
      <!-- 分页器 -->
      <div class="pagination-block">
        <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 20, 50]"
          :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper"
          :total="filteredData.length" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </div>
    </el-card>
    <el-dialog v-model="dialogVisible" title="公告详情">
      <p><strong>公告标题：</strong>{{ dialogData.title }}</p>
      <p><strong>发布时间：</strong>{{ dialogData.publishdate }}</p>
      <p><strong>发布人：</strong>{{ dialogData.userid }}</p>
      <p><strong>公告内容：</strong>{{ dialogData.content }}</p>
      <template #footer>
        <el-button @click="dialogVisible = false">关闭</el-button>
      </template>
    </el-dialog>
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
const dialogVisible = ref(false)
const dateRange = ref<Date[]>([])
const dialogData = ref<Announcement>({
  announcementid: 0,
  publishdate: '',
  title: '',
  content: '',
  userid: ''
})
interface Announcement {
  announcementid: number;
  publishdate: string;
  title: string;
  content: string;
  userid: string;
}
const tableData = ref<Announcement[]>([])
function formatDate(date: string | number | Date): string {
  // 格式化日期时间，显示精确到小时和分钟
  const options: Intl.DateTimeFormatOptions = {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit'
  };
  return new Date(date).toLocaleString('zh-CN', options);
}
const handleClick = (row: Announcement) => {
  console.log(row)
  dialogData.value = row
  dialogVisible.value = true
}
fetchData()
async function fetchData() {
  try {
    const res = await (proxy as any).$axios.get('/announcements/list')
    console.log(res.data.list)
    tableData.value = res.data
    console.log(tableData.value)
  } catch (error) {
    console.error('Error fetching data:', error)
  }
}
const filteredData = computed(() => {
  let data = tableData.value

  if (dateRange.value.length > 0) {
    const [start, end] = dateRange.value
    data = data.filter(item => {
      const date = new Date(item.publishdate)
      return date >= start && date <= end
    })
  }
  return data.sort((a, b) => {
    return new Date(b.publishdate).getTime() - new Date(a.publishdate).getTime()
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
const handleDateDelete = () => {
  dateRange.value = []
  currentPage.value = 1
}

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
:deep(.el-card .el-card__body) {
  padding: 0px;
}

.container {
  display: flex;
  flex-direction: column;
  gap: 20px;
  width: 100%;
}

.date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex: 0;
  align-items: center;
  justify-content: center;
}

.date-picker .block {
  text-align: center;
}

.date-picker .block:last-child {
  border-right: none;
}

.date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}

.pagination-block {
  display: flex;
  flex: 0;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.ellipsis {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
</style>

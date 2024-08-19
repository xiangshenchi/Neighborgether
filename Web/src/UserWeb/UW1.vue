<template>
  <div style="display: flex; flex-direction: column; height: 100vh;align-items: center;">
    <!-- 时间选择器 -->
    <div class="date-picker">
      <div class="block">
        <el-date-picker v-model="value2" type="daterange" unlink-panels range-separator="至" start-placeholder="起始时间"
          end-placeholder="截至时间" :shortcuts="shortcuts" :size="size" />
      </div>
      <br>
      <!-- 表格 -->
      <div style="width: 100%; position: relative;">
        <el-table :data="tableData" style="width: 100%; height: 100%;" size="100%" :row-style="rowStyle">
          <el-table-column fixed prop="num" label="序号" width="60" />
          <el-table-column prop="date" label="发布时间" width="160" />
          <el-table-column prop="title" label="公告标题" width="160" />
          <el-table-column prop="content" label="公告内容" width="250" />
          <el-table-column prop="person" label="发布人" width="120" />
          <el-table-column fixed="right" label="公告详情" min-width="100">
            <template #default>
              <!-- 详情按钮 -->
              <el-button link type="primary" size="small" @click="handleClick">
                详情
              </el-button>
              <!-- 编辑按钮 -->
              <!-- <el-button link type="primary" size="small">
            编辑按钮
          </el-button> -->
            </template>
          </el-table-column>
        </el-table>
      </div>

      <!-- 分页器 -->
      <div class="pagination-block">
        <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize" :page-sizes="[10, 20, 50]"
          :disabled="disabled" :background="background" layout="total, sizes, prev, pager, next, jumper" :total="100"
          @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>

// 1.分页器
import { ref } from 'vue'
import type { ComponentSize } from 'element-plus'
const currentPage = ref(1)
const pageSize = ref(10)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)

const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}


// 2.数据表
const handleClick = () => {
  console.log('click')
}
const tableData = [
  {
    num: 1,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 2,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 3,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 4,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 5,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 6,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 7,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 8,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 9,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
  {
    num: 10,
    date: '2016-05-02 18:50:21',
    title: 'California',
    content: 'Los Angeles',
    person: 'XXX',
  },
]
const rowStyle = () => {
  return {
    height: '30px', // 你希望的行高
  };
};

// 3.日期选择器

// const size = ref<'default' | 'large' | 'small'>('default')
const value2 = ref('')
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

// export default {
//   setup() {
//     const dateRange = ref([])
//     const currentPage = ref(1)
//     const pageSize = ref(10)
//     const total = ref(0)
//     const tableData = ref([])
//
//     // 模拟数据获取方法
//     const fetchData = (page, size, range) => {
//       // 这里可以调用后端API获取数据
//       // 假设返回的数据结构如下
//       const data = [
//         { date: '2020-10-01', name: '王小明', address: '上海市普陀区金沙江路 1518 弄' },
//         { date: '2020-10-02', name: '张三', address: '上海市普陀区金沙江路 1517 弄' },
//         // ...
//       ]
//       tableData.value = data.slice((page - 1) * size, page * size)
//       total.value = data.length
//     }
//
//     const handleDateChange = (value) => {
//       // 当时间选择器发生变化时，重新获取数据
//       fetchData(currentPage.value, pageSize.value, value)
//     }
//
//     const handleCurrentChange = (val) => {
//       currentPage.value = val
//       fetchData(val, pageSize.value, dateRange.value)
//     }
//
//     onMounted(() => {
//       fetchData(currentPage.value, pageSize.value, dateRange.value)
//     })
//
//     return {
//       dateRange,
//       currentPage,
//       pageSize,
//       total,
//       tableData,
//       handleDateChange,
//       handleCurrentChange,
//     }
//   },
// }
</script>
<style scoped>
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
  flex-wrap: wrap;
}

.date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
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
  flex-direction: column;
  /* 设置为纵向布局 */
  align-items: center;
  /* 居中对齐 */
  margin-top: 20px;
  width: 100%;
  /* 占据整个宽度 */
  position: absolute;
  bottom: 10px;
}
</style>

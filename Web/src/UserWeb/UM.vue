<template>
    <el-container class="layout-container-demo">
      <el-header style="border-bottom:3px solid #E2E2E2;padding: 0;">
        <div style="width:18vw;height:100%;float:left;display: flex;border-right:3px solid #E2E2E2;overflow: hidden;">
          <div style="height:100%; aspect-ratio: 1 / 1"><img src="./logo.jpg" height="100%" width=height
              alt="logo" /></div>
          <div
            style="width:auto;height:100%;display: flex; justify-content: center; align-items: center;flex-direction: column;">
            <p class="zsdbt">邻聚·智慧物业</p>
            <p class="zsdbt">Neighborgether</p>
          </div>
        </div>
        <div style="width:auto;height:100%;display: flex;align-items: center;">
          <div>
            <span>{{ dateFormat(newDate) }}</span>
          </div>
          <el-avatar size="large" src="/public/logo.jpg" style="float:right;margin-left: auto;" />
          <div>
            <el-dropdown :hide-on-click="false" @command="handleCommand">
              <span class="el-dropdown-link ipt"
                style="width:7vw;height:auto; display: flex;justify-content: center;align-items: center;margin-top: 0;padding: 0.1vw;margin-right: 1vw;color:black ">
                业主<el-icon class="el-icon--right"><arrow-down style="margin-left: auto;" /></el-icon>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item @click="logout">退出账号</el-dropdown-item>
                  <el-dropdown-item @click="gotoWelcome">回到欢迎界面</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </el-header>
      <el-container>
        <el-aside style="width:18vw;height:100%">
          <el-scrollbar>
            <el-menu :default-openeds="['1', '7']" @select="handleMenuSelect">
              <el-menu-item index="/UM/UW1">
                <span slot="title"><el-icon><ChatLineSquare /></el-icon>社区公告</span>
              </el-menu-item>
              <el-sub-menu index="2">
                <template #title>
                  <el-icon><User /></el-icon>个人中心
                </template>
                <el-menu-item index="/UM/UW2-1">账号信息</el-menu-item>
                <el-menu-item index="/UM/UW2-2">房产信息</el-menu-item>
                <el-menu-item index="/UM/UW2-3">车辆信息</el-menu-item>
              </el-sub-menu>
              <el-sub-menu index="3">
                <template #title>
                  <el-icon><Money /></el-icon>生活缴费
                </template>
                <el-menu-item index="/UM/UW3-1">我的水费</el-menu-item>
                <el-menu-item index="/UM/UW3-2">我的电费</el-menu-item>
                <el-menu-item index="/UM/UW3-3">我的物业费</el-menu-item>
              </el-sub-menu>
              <el-sub-menu index="4">
                <template #title>
                  <el-icon><Clock /></el-icon>报修登记
                </template>
                <el-menu-item index="/UM/UW4-1">我要报修</el-menu-item>
                <el-menu-item index="/UM/UW4-2">报修记录</el-menu-item>
              </el-sub-menu>
              <el-sub-menu index="5">
                <template #title>
                  <el-icon><MessageBox /></el-icon>建议&投诉
                </template>
                <el-menu-item index="/UM/UW5-1">我要投诉</el-menu-item>
                <el-menu-item index="/UM/UW5-2">我要建议</el-menu-item>
              </el-sub-menu>
              <el-sub-menu index="6">
                <template #title>
                  <el-icon><Delete /></el-icon>社区绿化
                </template>
                <el-menu-item index="/UM/UW6-1">回收点分布图</el-menu-item>
                <el-menu-item index="/UM/UW6-2">绿色科普</el-menu-item>
              </el-sub-menu>
              <el-menu-item index="/UM/UW7">
                <el-icon><Service /></el-icon>
                <span slot="title">联系方式</span>
              </el-menu-item>
              <el-menu-item index="/UM/UW8">
                <el-icon><More /></el-icon>
                <span slot="title">关于</span>
              </el-menu-item>
            </el-menu>
          </el-scrollbar>
        </el-aside>
        <el-container style="border-left:3px solid #E2E2E2">
          <router-view />
        </el-container>
      </el-container>
    </el-container>
  </template>
  
  <script lang="ts" setup>
  import { useStore } from 'vuex'
  const store = useStore()
  import { ref, onMounted, onBeforeUnmount } from 'vue'
  import { ElMessage } from 'element-plus'
  import { Menu as IconMenu, Message, Setting, ArrowDown } from '@element-plus/icons-vue'
import router from '@/router/router';
  const selectedMenu = ref('')
  const newDate = ref(new Date())
  // 时间格式化函数
  
  const handleCommand = (command: string | number | object) => {
    ElMessage(`click on item ${command}`)
  }
  function handleMenuSelect(index: string) {
  router.push(index);
}
  function dateFormat(date) {
    const year = date.getFullYear()
    const month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
    const day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    const hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    const minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    const seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    const weekArr = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
    const week = date.getDay()
  
    return `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds} ${weekArr[week]}`
  }
  
  // 设置定时器更新时间
  let timer = null
  
  onMounted(() => {
    timer = setInterval(() => {
      newDate.value = new Date()
    }, 1000)
  })
  
  onBeforeUnmount(() => {
    if (timer) {
      clearInterval(timer)
    }
  })
  function logout() {
    store.dispatch('logout');
    window.location.href = '/login';
  }
  function gotoWelcome() {
    window.location.href = '/welcome';
  }
  const item = {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  }
  const tableData = ref(Array.from({ length: 20 }).fill(item))
  </script>
  
  <style scoped>
  .layout-container-demo {
    position: relative;
    width: 100vw;
    height: 97vh;
    overflow: hidden;
    max-width: 100%;
  }
  
  .layout-container-demo .el-header {
    position: relative;
    background-color: #fff;
    height: 10vh;
    color: var(--el-text-color-primary);
  }
  
  .layout-container-demo .el-aside {
    color: var(--el-text-color-primary);
    background: #fff;
  }
  
  .layout-container-demo .el-menu {
    border-right: none;
  }
  
  .layout-container-demo .el-main {
    padding: 0;
  }
  
  .layout-container-demo .toolbar {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    right: 20px;
  }
  ::v-deep .el-sub-menu .el-menu-item {
    background-color: #f0f8ff; /* 你想要的背景颜色 */
  }
  .zsdbt {
    margin-top: 0px;
    margin-bottom: 0px;
    text-align: center;
    color: #22A637;
    font-size: 2.2vh;
    font-family: 'PingFang SC';
    font-weight: 450;
    text-shadow: 7px 7px 7px #BDE5C3;
  }
  
  .example-showcase .el-dropdown+.el-dropdown {
    margin-left: 15px;
  }
  
  .example-showcase .el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
  }
  </style>
  
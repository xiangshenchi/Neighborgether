<template>
  <el-container class="layout-container-demo">
    <el-header style="border-bottom:3px solid #E2E2E2;padding: 0;display:flex;display:0,0,auto;">
      <div style="width:18vw;height:100%;float:left;display: flex;border-right:3px solid #E2E2E2;overflow: hidden;">
        <div style="height:100%; aspect-ratio: 1 / 1"><img src="../../public/logo.jpg" height="100%" width=height
            alt="logo" /></div>
        <div
          style="width:auto;height:100%;display: flex; justify-content: center; align-items: center;flex-direction: column;">
          <p class="zsdbt">邻聚·智慧物业</p>
          <p class="zsdbt">Neighborgether</p>
        </div>
      </div>
      <div style="width:auto;height:100%;display: flex;align-items: center;">
        <div style="font-size: large; font-weight: bold; margin-left: 20px;">
          <span>{{ dateFormat(newDate) }}</span>
        </div>
        <el-avatar size="large" src="/public/logo.jpg" style="float:right;margin-left: auto;" />
        <div>
          <el-dropdown :hide-on-click="false" @command="handleCommand">
            <span class="el-dropdown-link ipt"
              style="width:7vw;height:auto; display: flex;justify-content: center;align-items: center;margin-top: 0;padding: 0.1vw;margin-right: 1vw;color:black ">
              管理员<el-icon class="el-icon--right"><arrow-down style="margin-left: auto;" /></el-icon>
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
    <el-container style="width:100vw;display:flex;display:1;overflow: hidden;">
      <el-aside style="width:18vw;height:100%">
        <!-- <el-scrollbar> -->
        <el-menu :default-openeds="['1', '7']" @select="handleMenuSelect">
          <el-menu-item index="/AM/AW1">
            <span slot="title"><el-icon>
                <ChatLineSquare />
              </el-icon>控制台</span>
          </el-menu-item>
          <el-menu-item index="/AM/AW2-1">
            <span slot="title"><el-icon>
                <User />
              </el-icon>用户管理</span>
          </el-menu-item>
          <!-- <el-sub-menu index="2">
            <template #title>
              <el-icon>
                <User />
              </el-icon>用户管理
            </template>
            <el-menu-item index="/AM/AW2-1">业主</el-menu-item>
            <el-menu-item index="/AM/AW2-2">管理员</el-menu-item>
          </el-sub-menu> -->
          <el-sub-menu index="3">
            <template #title>
              <el-icon>
                <Money />
              </el-icon>小区管理
            </template>
            <el-menu-item index="/AM/AW3-1">公告管理</el-menu-item>
            <el-menu-item index="/AM/AW3-2">车辆管理</el-menu-item>
            <el-menu-item index="/AM/AW3-3">房产管理</el-menu-item>
            <!-- <el-menu-item index="/AM/AW3-4">楼栋管理</el-menu-item> -->
            <el-menu-item index="/AM/AW3-4">访客管理</el-menu-item>
            <!-- <el-menu-item index="/AM/AW3-6">工作分配</el-menu-item> -->
          </el-sub-menu>

          <el-menu-item index="/AM/AW4">
            <span slot="title"><el-icon>
                <Clock />
              </el-icon>缴费管理</span>
          </el-menu-item>

          <!-- <el-sub-menu index="4">
            <template #title>
              <el-icon>
                <Clock />
              </el-icon>缴费管理
            </template>
            <el-menu-item index="/AM/AW4-1">水费</el-menu-item>
            <el-menu-item index="/AM/AW4-2">物业费</el-menu-item>
          </el-sub-menu> -->

          <el-menu-item index="/AM/AW5">
            <el-icon>
              <Service />
            </el-icon>
            <span slot="title">报修管理</span>
          </el-menu-item>
          <el-menu-item index="/AM/AW6">
            <el-icon>
              <More />
            </el-icon>
            <span slot="title">诉求管理</span>
          </el-menu-item>
        </el-menu>
        <!-- </el-scrollbar> -->
      </el-aside>
      <div style="border-left:3px solid #E2E2E2;height:100%;flex:1;box-sizing: border-box;overflow: auto;">
        <router-view />
      </div>
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

  return `${year}年${month}月${day}日-${weekArr[week]}-${hours}:${minutes}:${seconds}`
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
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.layout-container-demo .el-header {
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
  background-color: #f0f8ff;
  /* 你想要的背景颜色 */
}

.zsdbt {
  margin-top: 0px;
  margin-bottom: 0px;
  text-align: center;
  color: #22A637;
  font-size: 2.2vh;
  font-family: 'PingFang SC';
  font-weight: 550;
  text-shadow: 4px 2px 5px #85b38ca1;
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
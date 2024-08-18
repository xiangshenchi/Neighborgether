<template>
  <div class="app-container">
    <Particles
      id="tsparticles"
      :particlesInit="particlesInit"
      :particlesLoaded="particlesLoaded"
      :options="options"
    />
    <div class="Loginbg">
      <div class="lc1">
      <el-card class="c1" shadow="always">
      <img src="/logo.jpg" height="width" width="150" alt="logo">
      <br>
      <h1 class="biaoTi">欢迎使用·邻聚·智慧物业</h1>
      <br>
      <input v-model="account" type="text" placeholder="请输入账号(手机号)" class="ipt">
      <br>
      <input v-model="password" type="password" placeholder="请输入密码" class="ipt" style="margin-bottom: 10px;">
      <br>
      <button type="submit" class="btn":class="button1Class" @mouseenter="() => onMouseEnter('button1')"
        @mouseleave="() => onMouseLeave('button1')"
        @mousedown="() => onMouseDown('button1')"
        @mouseup="() => onMouseUp('button1')" @click="login()">登录</button>
      <br>
      <button type="submit" class="btn":class="button2Class" @mouseenter="() => onMouseEnter('button2')"
        @mouseleave="() => onMouseLeave('button2')"
        @mousedown="() => onMouseDown('button2')"
        @mouseup="() => onMouseUp('button2')" @click="jilu()">访客登记</button>
      <br>
    </el-card>
  </div>
      <div class="yxj">没有账号? <router-link to="/register" style="color: #4095E5">点击注册</router-link></div>
      <br>
      <div class="yxj">忘记密码? <router-link to="/forgetPW" style="color: #4095E5">点击找回</router-link></div>
      <br>
      <div class="yxj">遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5">联系工作人员</a></div>
    </div>
  </div>
</template>
 
<script lang="ts">
import { useStore } from 'vuex'
const store = useStore()
import { defineComponent, reactive } from 'vue'
import { loadFull } from 'tsparticles'
import { useParticles } from './re'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
import './style.css'
import axios from 'axios'
axios.defaults.baseURL = '/api'
const errorAlert = (text) => {
  ElMessageBox.alert(text, '错误', {
    confirmButtonText: 'OK'
  })
}
const contact = (text) => {
  ElMessageBox.alert("联系方式:xxxxxxxxxxxxxxxx", '联系工作人员', {
    confirmButtonText: text
  })
}
// getList()
// async function getList(){
//   const res = await axios({
//     url:'http://192.168.217.139:8090/users/list',
//     methor: 'GET',
//   })
//   console.log(res)
// }
export default defineComponent({
  // 定义组件的数据
  data() {
    return {
      account: "",
      password: "",
      buttons: {
        button1: {
          isHovered: false,
          isPressed: false,
        },
        button2: {
          isHovered: false,
          isPressed: false,
        },
        account: "",
        password: "",
      },
    };
  },
  // 计算属性，用于动态设置按钮的类名
  computed: {
    button1Class() {
      return this.getButtonClass('button1');
    },
    button2Class() {
      return this.getButtonClass('button2');
    },
  },
  // 定义组件的方法
  methods: {
    // 根据按钮状态返回对应的类名
    getButtonClass(button: string): string {
      if (this.buttons[button].isPressed) {
        return `${button}-pressed`;
      } else if (this.buttons[button].isHovered) {
        return `${button}-hovered`;
      } else {
        return `${button}-default`;
      }
    },
    // 鼠标进入按钮区域时的处理
    onMouseEnter(button: string) {
      this.buttons[button].isHovered = true;
    },
    // 鼠标离开按钮区域时的处理
    onMouseLeave(button: string) {
      this.buttons[button].isHovered = false;
      this.buttons[button].isPressed = false;
    },
    // 鼠标按下按钮时的处理
    onMouseDown(button: string) {
      this.buttons[button].isPressed = true;
    },
    // 鼠标释放按钮时的处理
    onMouseUp(button: string) {
      this.buttons[button].isPressed = false;
    },
    // 登录按钮点击处理
    login() {
      if (!this.account && !this.password) {
        this.$message.error('账号和密码不能为空！');
        errorAlert('账号和密码不能为空！');
      } else if (!this.password) {
        errorAlert('密码不能为空！');
      } else if (!this.account) {
        errorAlert('账号不能为空！');
      } else {
        console.log('login');
        const userInfo = { name: 'John Doe', email: 'john@example.com' }
        this.$store.dispatch('login', userInfo)
        window.location.href = "/UM";
      }
    },
    // 访客登记按钮点击处理
    jilu() {
      console.log('jilu');
      window.location.href = "/visitor";
    }
  },
  setup(){
    const contact = (text) => {
      ElMessageBox.alert("联系方式:xxxxxxxxxxxxxxxx", '联系工作人员', {
        confirmButtonText: text
      });
    };
    const { particlesInit, particlesLoaded, options } = useParticles()
    return {
      particlesInit,
      particlesLoaded,
      options,
      contact
    }
  }
})
</script>
 
<style>

</style>


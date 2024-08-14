<template>
  <div class="web-container">
    <Particles id="tsparticles" :particlesInit="particlesInit" :particlesLoaded="particlesLoaded" :options="options" />
    <div class="ys-container">
      <div class="header">
        <img src="/logo.jpg" class="logo" alt="logo">
        <p class="title">邻聚·账号注册</p>
        <button class="btn btn-back" @click="returnToLogin">返回</button>
      </div>
      <div id="form-container1">
        <el-form :model="form" ref="form" label-width="0">
          <el-form-item prop="account" :rules="[{ required: true, message: '请输入手机号', trigger: 'blur' }]">
            <el-input v-model="form.account" placeholder="请输入手机号" />
          </el-form-item>
          <el-form-item prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
            <el-input v-model="form.password" placeholder="请输入密码" show-password />
          </el-form-item>
          <el-form-item prop="confirmPassword" :rules="[{ required: true, message: '请再次输入密码', trigger: 'blur' }]">
            <el-input v-model="form.confirmPassword" placeholder="请再次输入密码" show-password />
          </el-form-item>
          <div class="button-container">
            <el-button type="primary" @click="onSubmit" size="30%">注册</el-button>
          </div>
        </el-form>
      </div>
      <div class="yxj">遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5">联系工作人员</a></div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { useParticles } from './re'
import { ElMessageBox } from 'element-plus'
import { c } from 'vite/dist/node/types.d-aGj9QkWt';

export default defineComponent({
  data() {
    return {
      form: {
        account: '',
        password: '',
        confirmPassword: '',
      },
      btnStates: {
        back: { hovered: false, pressed: false },
      },
    }
  },
  computed: {
    btnBackClass() {
      return this.getBtnClass('back');
    },
  },
  methods: {
    onSubmit() {
      this.$refs.form.validate(valid => {
        ElMessageBox.alert(valid ? '注册成功' : '请完成表单', valid ? 'Success' : 'Error', { confirmButtonText: 'OK' });
      });
    },
    returnToLogin() {
      window.location.href = "/";
    },
    getBtnClass(btn: string): string {
      return this.btnStates[btn].pressed ? `${btn}-pressed` : this.btnStates[btn].hovered ? `${btn}-hovered` : `${btn}-default`;
    },
    hoverBtn(btn: string, state: boolean) {
      this.btnStates[btn].hovered = state;
    },
    pressBtn(btn: string, state: boolean) {
      this.btnStates[btn].pressed = state;
    },
  },
  setup() {
    const contact = (text) => {
      ElMessageBox.alert("联系方式:xxxxxxxxxxxxxxxx", '联系工作人员', {
        confirmButtonText: text
      });
    };
    const { particlesInit, particlesLoaded, options } = useParticles();
    return { particlesInit, particlesLoaded, options, contact };
  }
})
</script>

<style>
.web-container {
  position: relative;
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  max-width: 100%;
}

.ys-container {
  position: absolute;
  width: 100vw;
  height: 35vh;
  max-width: 100%;
}

.header {
  padding: 0 7.5vw;
  width: 85vw;
  height: 13vw;
  top: 5vh;
  z-index: 9999;
  position: relative;
}

.logo {
  float: left;
  height: 100%;
}

.title {
  top: 35%;
  position: relative;
  margin: 0;
  height: 30%;
  float: left;
  font-size: 2.7vw;
  font-family: 'PingFang SC';
  font-weight: 600;
  text-shadow: 10px 10px 10px #BDE5C3;
  color: #22A637;
}


.btn.btn-back {
  background-color: #FFF;
  color: #54BB64;
  float: right;
  top: 35%;
  position: relative;
  margin: 0;
}

.btn-register {
  background-color: #54BB64;
  color: #FFF;
}

#form-container1 {
  margin-top: 25vh;
  height:40vh;
  padding: 20px;
  width: 300px;
  margin-left: auto;
  margin-right: auto;
  display: flex;
  justify-content: center;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.el-input__wrapper {
  border: 2px solid #AADDB2 !important;
  border-radius: 1vw 0 !important;
}

.el-input__wrapper.is-focus {
  border-color: inherit !important;
  box-shadow: none !important;
}
</style>

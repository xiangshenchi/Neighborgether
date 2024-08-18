<template>
    <div class="web-container">
      <Particles id="tsparticles" :particlesInit="particlesInit" :particlesLoaded="particlesLoaded" :options="options" />
      <div class="ys-container">
        <div class="header">
          <img src="/logo.jpg" class="logo" alt="logo">
          <p class="title">邻聚·账号找回</p>
          <button class="btn btn-back" @click="returnToLogin">返回</button>
        </div>
      </div>
      
      <div class="lc2">
        <el-card class="c2" shadow="always">
        <div class="form-container"> 
          <el-form :model="form" ref="form" label-width="0">
            <el-form-item prop="account" :rules="[{ required: true, message: '请输入手机号', trigger: 'blur' }]">
              <el-input v-model="form.account" placeholder="请输入手机号" style="width: auto; " />
            </el-form-item>
            <el-form-item prop="password" :rules="[{ required: true, message: '请输入修改后的密码', trigger: 'blur' }]">
              <el-input v-model="form.password" placeholder="请输入修改后的密码" show-password />
            </el-form-item>
            <el-form-item prop="confirmPassword" :rules="[{ required: true, message: '请再次输入密码', trigger: 'blur' }]">
              <el-input v-model="form.confirmPassword" placeholder="请再次输入密码" show-password />
            </el-form-item>
            <div class="button-container">
              <el-button type="primary" @click="onSubmit" size="large" color="#1EB71E" >修改密码</el-button>
            </div>
          </el-form>
        </div>
      </el-card>
      </div>
        <div class="yxj" style="z-index:100">遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5">联系工作人员</a></div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, reactive } from 'vue'
  import { useParticles } from './re'
  import { ElMessageBox } from 'element-plus'
  import './style.css'

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
    methods: {
      onSubmit() {
        this.$refs.form.validate(valid => {
          ElMessageBox.alert(valid ? '修改待管理员同意后即修改成功' : '请先填写手机号和密码', valid ? '成功' : '错误', { confirmButtonText: 'OK' });
        });
      },
      returnToLogin() {
        window.location.href = "/login";
      },
    },
    setup() {
      const contact = (text) => {
      ElMessageBox.alert("联系方式:xxxxxxxxxxxxxxxx", '联系工作人员', {
        confirmButtonText: text
      });
    };
      const { particlesInit, particlesLoaded, options } = useParticles();
      return { particlesInit, particlesLoaded, options , contact };
    }
  })
  </script>

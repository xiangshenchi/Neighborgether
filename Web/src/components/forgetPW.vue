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
              <el-input class="srk" v-model="form.account" placeholder="请输入手机号" style="width: auto; " />
            </el-form-item>
            <el-form-item prop="password" :rules="[{ required: true, message: '请输入修改后的密码', trigger: 'blur' }]">
              <el-input class="srk" v-model="form.password" placeholder="请输入修改后的密码" show-password />
            </el-form-item>
            <el-form-item prop="confirmPassword" :rules="[{ required: true, message: '请再次输入密码', trigger: 'blur' }]">
              <el-input class="srk" v-model="form.confirmPassword" placeholder="请再次输入密码" show-password />
            </el-form-item>
            <div class="button-container">
              <el-button type="primary" @click="onSubmit" size="large" color="#1EB71E">修改密码</el-button>
            </div>
          </el-form>
        </div>
      </el-card>
    </div>
    <div style="position: absolute; bottom: 0; width: 100%;">
      <div class="yxj">
        遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5" size="large">联系工作人员</a>
      </div>
    </div>
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
        if (!valid) {
          ElMessageBox.alert("请先填写手机号和密码", '错误', { confirmButtonText: '确定' });
          return;
        }
        this.$axios.post('/changepassword/updatepassword', {
          changephone: this.form.account,
          newpassword: this.form.password
        }).then(res => {
          if (res.data.status === 0) {
            ElMessageBox.alert(res.data.message, '错误', {
              confirmButtonText: '确定'
            })
          }
          else
            ElMessageBox.alert(valid ? '修改待管理员同意后即修改成功' : '请先填写手机号和密码', valid ? '成功' : '错误', { confirmButtonText: 'OK' });
        }).catch(err => {
          ElMessageBox.alert('未知错误,提交失败,请联系工作人员！', '失败', {
            confirmButtonText: '确定'
          })
        })
      });
    },
    returnToLogin() {
      window.location.href = "/login";
    },
  },
  setup() {
    const contact = (text) => {
      ElMessageBox.alert("联系方式:10086", '联系工作人员', {
        confirmButtonText: text
      });
    };
    const { particlesInit, particlesLoaded, options } = useParticles();
    return { particlesInit, particlesLoaded, options, contact };
  }
})
</script>

<style>
.srk .el-input__wrapper {
  border: 2px solid #AADDB2 !important;
  border-radius: 1vw 0 !important;
}
.forget-pw-page .el-input__wrapper.is-focus {
  border-color: inherit !important;
  box-shadow: none !important;
}
</style>

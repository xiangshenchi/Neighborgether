<template>
  <div class="web-container">
    <Particles id="tsparticles" :particlesInit="particlesInit" :particlesLoaded="particlesLoaded" :options="options" />
    <div class="ys-container">
      <div class="header">
        <img src="/logo.jpg" class="logo" alt="logo">
        <p class="title">邻聚·账号注册</p>
        <button class="btn btn-back" @click="returnToLogin">返回</button>
      </div>

    </div>
    <div class="lc3">
      <el-card class="c3" shadow="always">
        <div id="form-container1">
          <el-form :model="form" ref="form" label-width="0">
            <el-form-item prop="account" :rules="[{ required: true, message: '请输入手机号', trigger: 'blur' }]">
              <el-input class="srk" v-model="form.account" placeholder="请输入手机号" />
            </el-form-item>
            <el-form-item prop="password" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
              <el-input class="srk" v-model="form.password" placeholder="请输入密码" show-password />
            </el-form-item>
            <el-form-item prop="confirmPassword" :rules="[{ required: true, message: '请再次输入密码', trigger: 'blur' }]">
              <el-input class="srk" v-model="form.confirmPassword" placeholder="请再次输入密码" show-password />
            </el-form-item>
            <div class="button-container">
              <el-button type="primary" color="#1EB71E" @click="onSubmit" size="30%">注册</el-button>
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
  computed: {
    btnBackClass() {
      return this.getBtnClass('back');
    },
  },
  methods: {
    onSubmit() {
      if (this.form.account === '' || this.form.password === '' || this.form.confirmPassword === '') {
        ElMessageBox.alert('请填写完整信息', '提示', {
          confirmButtonText: '确定'
        });
        return;
      }
      else if (this.form.password !== this.form.confirmPassword) {
        ElMessageBox.alert('两次输入的密码不一致', '提示', {
          confirmButtonText: '确定'
        });
        return;
      }
      else {
        this.$axios.post('/users/save', {
          phonenumber: this.form.account,
          password: this.form.password
        }).then(res => {
          if (res.data.status === 0) {
            ElMessageBox.alert(res.data.message, '错误', {
              confirmButtonText: '确定'
            })
          }
          else
            ElMessageBox.alert("注册成功!", '提示', {
              confirmButtonText: '确定'
            })
        }).catch(err => {
          ElMessageBox.alert('未知错误,提交失败,请联系工作人员！', '失败', {
            confirmButtonText: '确定'
          })
        })
      }
    },
    returnToLogin() {
      window.location.href = "/login";
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

<style scoped>
.yxj>div {
  position: absolute;
  bottom: 10px;
  /* 距离容器底部的距离 */
  right: 10px;
  /* 距离容器右边的距离 */
  text-align: right;
}
</style>

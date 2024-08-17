<template>
    <div class="web-container">
        <Particles id="tsparticles" :particlesInit="particlesInit" :particlesLoaded="particlesLoaded"
            :options="options" />
        <div class="ys-container">
            <div class="header">
                <img src="/logo.jpg" class="logo" alt="logo">
                <p class="title">邻聚·访客登记</p>
                <button class="btn btn-back" @click="returnToLogin">返回</button>
            </div>
            <div class="lc4">
            <el-card class="c4" shadow="always">
            <div id="form-container2">
                <el-form :model="form" label-width="80px">
                    <el-form-item label="姓名">
                        <el-input v-model="form.name" placeholder="请输入您的姓名"></el-input>
                    </el-form-item>

                    <el-form-item label="性别">
                        <el-radio-group v-model="form.gender">
                            <el-radio label="男">男</el-radio>
                            <el-radio label="女">女</el-radio>
                            <el-radio label="其他">其他</el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item label="联系方式">
                        <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
                    </el-form-item>

                    <el-form-item label="来访原因">
                        <el-input type="textarea" v-model="form.reason" placeholder="请输入来访原因"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="onSubmit" color="#1EB71E" size="large">提交</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
        </div>
            <div class="yxj">遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5">联系工作人员</a></div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { useParticles } from './re'
import { ElMessageBox } from 'element-plus'
import { ref } from 'vue'
import './style.css'

export default defineComponent({
    data() {
        return {
        }
    },
    methods: {
        onSubmit() {
            this.$refs.form.validate(valid => {

            });
        },
        returnToLogin() {
            window.location.href = "/login";
        },
    },
    setup() {
        const form = ref({
            name: '',
            gender: '男',  // 默认选中“男”
            phone: '',
            reason: ''
        })
        const onSubmit = () => {
            console.log('表单数据:', form.value)
        }
        const contact = (text) => {
      ElMessageBox.alert("联系方式:xxxxxxxxxxxxxxxx", '联系工作人员', {
        confirmButtonText: text
      });
    };
        const { particlesInit, particlesLoaded, options } = useParticles();
        return { particlesInit, particlesLoaded, options,form, onSubmit,contact };
    }
})
</script>

<style>

</style>
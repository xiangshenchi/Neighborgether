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
        </div>
        <div class="lc4">
            <el-card class="c4" shadow="always">
                <div id="form-container2">
                    <el-form :model="form" label-width="40%">
                        <el-form-item label="姓名">
                            <el-input v-model="form.visitname" placeholder="请输入您的姓名"></el-input>
                        </el-form-item>

                        <el-form-item label="性别">
                            <el-radio-group v-model="form.sex">
                                <el-radio label="男">男</el-radio>
                                <el-radio label="女">女</el-radio>
                                <el-radio label="其他">其他</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="联系方式">
                            <el-input v-model="form.visitphone" placeholder="请输入手机号" </el-input>
                        </el-form-item>

                        <el-form-item label="来访原因">
                            <el-input type="textarea" v-model="form.visitreason" placeholder="请输入来访原因"></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="onSubmit" color="#1EB71E" size="large">提交</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </div>
        <div class="yxj">遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5">联系工作人员</a>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive } from 'vue'
import { useParticles } from './re'
import { ElMessageBox } from 'element-plus'
import { ref } from 'vue'
import './style.css'
import axios from 'axios'
import { errorMessages } from 'vue/compiler-sfc'
export default defineComponent({
    data() {
        return {
        }
    },
    methods: {
        returnToLogin() {
            window.location.href = "/login";
        },
    },
    setup() {
        const form = ref({
            visitname: '',
            sex: '男',  // 默认选中“男”
            visitphone: '',
            visitreason: ''
        })
        const onSubmit = () => {
            axios.post('http://192.168.217.70:8090/visitor', form.value).then(res => {
                if (res.data.status === 1) {
                    ElMessageBox.alert('提交成功！', '成功', {
                        confirmButtonText: '确定'
                    })
                }
                else {
                    ElMessageBox.alert('提交失败,同一天只能提交一次申请！', '失败', {
                        confirmButtonText: '确定'
                    })
                }
            }).catch(err => { 
                ElMessageBox.alert('未知错误,提交失败,请联系工作人员！', '失败', {
                    confirmButtonText: '确定'
                })
            })
        }
        const contact = (text) => {
            ElMessageBox.alert("联系方式:xxxxxxxxxxxxxxxx", '联系工作人员', {
                confirmButtonText: text
            });
        };
        const { particlesInit, particlesLoaded, options } = useParticles();
        return { particlesInit, particlesLoaded, options, form, onSubmit, contact };
    }
})
</script>

<style></style>
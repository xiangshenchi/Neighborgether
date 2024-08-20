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
        <div style="position: absolute; bottom: 0; width: 100%;">
            <div class="yxj">
                遇到困难? <a href="javascript:void(0)" @click="contact('OK')" style="color: #4095E5" size="large">联系工作人员</a>
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { defineComponent, getCurrentInstance, ref } from 'vue';
import { useParticles } from './re'
import { ElMessageBox } from 'element-plus'
import './style.css'
function returnToLogin() {
    window.location.href = "/login";
}

const form = ref({
    visitname: '',
    sex: '男',  // 默认选中“男”
    visitphone: '',
    visitreason: ''
})
const { proxy } = getCurrentInstance()!;
const onSubmit = () => {
    if (form.value.visitreason === '') {
        ElMessageBox.alert('请输入来访原因！', '失败', {
            confirmButtonText: '确定'
        })
        return
    }
    (proxy as any).$axios.post('/visitors/save', form.value).then(res => {
        if (res.data.status === 1) {
            ElMessageBox.alert('提交成功！', '成功', {
                confirmButtonText: '确定'
            })
        }
        else {
            ElMessageBox.alert(res.data.message, '失败', {
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

</script>

<style></style>
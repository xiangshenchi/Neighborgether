<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>账号管理</h2>
        </el-card>

        <!-- 基本车辆信息 -->
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="基本车辆信息" :column="3" :size="size">
                <el-descriptions-item label="车主">{{ form.owner }}</el-descriptions-item>
                <el-descriptions-item label="车牌号">{{ form.plateNumber }}</el-descriptions-item>
                <el-descriptions-item label="车辆类型">{{ form.vehicleType }}</el-descriptions-item>
                <el-descriptions-item label="登记时间">{{ form.registrationDate }}</el-descriptions-item>
            </el-descriptions>
        </el-card>

        <!-- 账号信息更新 -->
        <el-card style="margin: 10px;">
            <h4 style="margin: 5px;">修改信息</h4>
            <el-form-item label="车主" :label-position="itemLabelPosition">
                <el-input v-model="formSub.owner" />
            </el-form-item>
            <el-form-item label="车牌号" :label-position="itemLabelPosition">
                <el-input v-model="formSub.building" />
            </el-form-item>
            <el-form-item label="车辆类型" :label-position="itemLabelPosition">
                <el-input v-model="formSub.vehicleType" />
            </el-form-item>
            <el-button type="primary" @click="confirm()" color="#1EB71E">提交</el-button>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            size: '',
            form: {
                owner: '', 
                plateNumber: '', 
                vehicleType: '', 
                registrationDate: ''
            },
            formSub: {
                owner: '', 
                plateNumber: '', 
                vehicleType: '', 
            },
        };
    },
    methods: {
        confirm() {
            this.$confirm('确认修改信息吗？').then(() => {
                this.onSubmit();
            }).catch(() => {
                this.$message.info('已取消');
            });
        },
        onSubmit() {
            this.$axios.post('/vehicle/update', {
                owner: this.formSub.owner,
                plateNumber: this.formSub.plateNumber,
                vehicleType: this.formSub.vehicleType,
            }).then(res => {
                this.$message.success('修改成功,将于1s后刷新页面');
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            }).catch(err => {
                this.$message.error('修改失败');
            });
        }
    }
};
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    gap: 10px;
    width: 100%;
}
</style>

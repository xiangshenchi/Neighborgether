<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>账号管理</h2>
        </el-card>

        <!-- 基本车辆信息 -->
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="基本车辆信息" :column="3" :size="size">
                <el-descriptions-item label="车主">{{ formData.owner }}</el-descriptions-item>
                <el-descriptions-item label="车牌号">{{ formData.plateNumber }}</el-descriptions-item>
                <el-descriptions-item label="车辆类型">{{ formData.vehicleType }}</el-descriptions-item>
                <el-descriptions-item label="登记时间">{{ formData.registrationDate }}</el-descriptions-item>
            </el-descriptions>
        </el-card>

        <!-- 账号信息更新 -->
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="车辆信息更新" :column="3" :size="size">
                <template #extra>
                    <el-button type="primary" size="small" @click="openDialog">修改</el-button>
                </template>
                <el-descriptions-item label="车主">{{ formData.owner }}</el-descriptions-item>
                <el-descriptions-item label="车牌号">{{ formData.plateNumber }}</el-descriptions-item>
                <el-descriptions-item label="车辆类型">{{ formData.vehicleType }}</el-descriptions-item>
                <el-descriptions-item label="登记时间">{{ formData.registrationDate }}</el-descriptions-item>
            </el-descriptions>
        </el-card>

        <!-- 修改车辆信息的对话框 -->
        <el-dialog title="修改车辆信息" :visible.sync="dialogVisible">
            <el-form :model="formData">
                <el-form-item label="车主">
                    <el-input v-model="formData.owner"></el-input>
                </el-form-item>
                <el-form-item label="车牌号">
                    <el-input v-model="formData.plateNumber"></el-input>
                </el-form-item>
                <el-form-item label="车辆类型">
                    <el-input v-model="formData.vehicleType"></el-input>
                </el-form-item>
                <el-form-item label="登记时间">
                    <el-date-picker v-model="formData.registrationDate" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="submitUpdate">保存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            size: '',
            dialogVisible: false,  // 控制对话框的显示和隐藏
            formData: {
                owner: 'kooriookami',           // 车主
                plateNumber: '18100000000',     // 车牌号
                vehicleType: 'SUV',             // 车辆类型
                registrationDate: '2024-08-22'  // 登记时间
            }
        };
    },
    methods: {
        openDialog() {
            // 打开修改对话框
            this.dialogVisible = true;
        },
        submitUpdate() {
            // 提交修改后的车辆信息到后端
            axios.post('http://localhost:8090/api/updateVehicle', this.formData)
                .then(response => {
                    this.$message.success('车辆信息更新成功');
                    this.dialogVisible = false;  // 关闭对话框
                })
                .catch(error => {
                    this.$message.error('更新失败，请稍后重试');
                    console.error(error);
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

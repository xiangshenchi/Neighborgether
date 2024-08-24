<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>缴纳电费</h2>
        </el-card>

        <el-card style="margin: 10px;">
            <div style="margin-top: 10px;">
                <el-form-item label="金额">
                    <el-input v-model="electricityFee.amount" placeholder="请输入电费金额"></el-input>
                </el-form-item>
            </div>
            <div style="margin: 10px;">
                <el-button type="primary" size="small" @click="submitElectricityFee">提交电费</el-button>
            </div>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            userId: 1,  // 当前用户ID，从实际登录信息中获取
            electricityFee: {
                amount: '',  // 电费金额
                paymentDate: new Date(),  // 缴费日期
                status: '未缴'  // 状态
            }
        };
    },
    methods: {
        submitElectricityFee() {
            if (!this.electricityFee.amount) {
                this.$message.error('请输入电费金额');
                return;
            }
            const formData = {
                paymentType: '电费',
                amount: this.electricityFee.amount,
                userId: this.userId,
                paymentDate: this.electricityFee.paymentDate,
                status: this.electricityFee.status
            };
            axios.post('http://localhost:8090/api/payment/submitElectricityFee', formData)
                .then(() => {
                    this.$message.success('电费提交成功');
                    this.electricityFee.amount = '';
                })
                .catch(error => {
                    this.$message.error('提交失败，请稍后重试');
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
    gap: 20px;
    width: 100%;
}
</style>

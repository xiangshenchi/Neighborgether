<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>缴纳物业费</h2>
        </el-card>

        <el-card style="margin: 10px;">
            <div style="margin-top: 10px;">
                <el-form-item label="金额">
                    <el-input v-model="propertyFee.amount" placeholder="请输入物业费金额"></el-input>
                </el-form-item>
            </div>
            <div style="margin: 10px;">
                <el-button type="primary" size="small" @click="submitPropertyFee">提交物业费</el-button>
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
            propertyFee: {
                amount: '',  // 物业费金额
                paymentDate: new Date(),  // 缴费日期
                status: '未缴'  // 状态
            }
        };
    },
    methods: {
        submitPropertyFee() {
            if (!this.propertyFee.amount) {
                this.$message.error('请输入物业费金额');
                return;
            }
            const formData = {
                paymentType: '物业费',
                amount: this.propertyFee.amount,
                userId: this.userId,
                paymentDate: this.propertyFee.paymentDate,
                status: this.propertyFee.status
            };
            axios.post('http://localhost:8090/api/payment/submitPropertyFee', formData)
                .then(() => {
                    this.$message.success('物业费提交成功');
                    this.propertyFee.amount = '';
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

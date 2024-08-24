<template>
    <el-card style="margin:10px">
        <div>
            <!-- 搜索框 -->
            <!-- <div style="width: 20%; margin-left: 10px; display: inline-block;">
            <el-input v-model="search" placeholder="输入缴费类型搜索" size="mini" class="search" />
        </div> -->

            <!-- 缴费类型筛选 -->
            <div style="width: 12%; margin-left: 20px; margin-top: 10px; display: inline-block;">
                <el-select v-model="selectedPaymentType" placeholder="选择缴费类型" size="mini" clearable>
                    <el-option label="所有缴费类型" value=""></el-option>
                    <el-option label="水费" value="水费"></el-option>
                    <el-option label="电费" value="电费"></el-option>
                    <el-option label="物业费" value="物业费"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                </el-select>
            </div>

            <!-- 缴费状态筛选 -->
            <div style="width: 12%; margin-left: 20px; display: inline-block;">
                <el-select v-model="selectedPaymentStatus" placeholder="选择缴费状态" size="mini" clearable>
                    <el-option label="所有状态" value=""></el-option>
                    <el-option label="已缴" value="已缴"></el-option>
                    <el-option label="未缴" value="未缴"></el-option>
                </el-select>
            </div>

            <!-- 用户缴费表格 -->
            <el-table :data="filteredData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="paymentid" label="缴费ID"></el-table-column>
                <el-table-column prop="paymenttype" label="缴费类型"></el-table-column>
                <el-table-column prop="amount" label="金额" sortable></el-table-column>
                <el-table-column prop="paymentdate" label="缴费日期" sortable>
                    <template #default="scope">
                        {{ formatDate(scope.row.paymentdate) }}
                    </template>
                </el-table-column>
                <el-table-column prop="status" label="状态" width="100px">
                    <template #default="scope">
                        <span v-if="scope.row.status === '已缴'">已缴</span>
                        <span v-else>未缴</span>
                    </template>
                </el-table-column>

                <el-table-column label="操作">
                    <template #default="scope">
                        <el-button v-if="scope.row.status === '未缴'" size="mini" type="primary"
                            @click="handlePay(scope.row)">缴费</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 缴费弹出框 -->
            <el-dialog title="确认缴费" v-model="payDialogVisible">
                <p>您的缴费金额为: {{ selectedPayment.amount }} 元</p>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="payDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="confirmPay">确认缴费</el-button>
                </span>
            </el-dialog>
        </div>
    </el-card>
</template>

<script>
export default {
    data() {
        return {
            search: '', // 搜索关键字
            selectedPaymentType: '', // 选择的缴费类型
            selectedPaymentStatus: '', // 选择的缴费状态
            tableData: [
            ],
            payDialogVisible: false, // 控制缴费弹出框的显示
            selectedPayment: {}, // 当前选中的缴费记录
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据缴费类型、缴费状态和搜索关键字进行筛选
                const matchesSearch = !this.search || data.paymenttype.toLowerCase().includes(this.search.toLowerCase());
                const matchesPaymentType = !this.selectedPaymentType || data.paymenttype === this.selectedPaymentType;
                const matchesPaymentStatus = !this.selectedPaymentStatus || data.status === this.selectedPaymentStatus;
                return matchesSearch && matchesPaymentType && matchesPaymentStatus;
            });
        }
    },
    mounted() {
        this.$axios.get('/livingpayment/listF', {
            params: {
                phonenumber: this.$store.getters.userInfo.phonenumber
            }
        }).then(res => {
            this.tableData = res.data;
        });
    },
    methods: {
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        handlePay(row) {
            // 打开缴费弹出框并记录选中的缴费记录
            this.selectedPayment = row;
            this.payDialogVisible = true;
        },
        confirmPay() {
            // 模拟缴费操作
            this.$axios.post('/livingpayment/update', {
                phonenumber: this.$store.getters.userInfo.phonenumber,
                livingpayment: {
                    paymentid: this.selectedPayment.paymentid,
                }
            }).then(res => {
                if (res.data.status === "1") {
                    this.selectedPayment.status = '已缴';
                    this.payDialogVisible = false;
                    this.$message.success("缴费成功！");
                }
                else {
                    this.$message.error("缴费失败！");
                }
            }).catch(err => {
                console.log(err);
                this.$message.error("未知错误，请稍后重试或联系管理员！");
            });
        },
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            // 保存编辑后的数据
            const index = this.tableData.findIndex(item => item.paymentid === this.editForm.paymentid);
            if (index !== -1) {
                this.tableData.splice(index, 1, { ...this.editForm });
            }
            this.editDialogVisible = false;
        },
        handleDelete(row) {
            // 打开删除确认框
            this.deleteRow = row;
            this.deleteDialogVisible = true;
        },
        confirmDelete() {
            // 确认删除并从数据列表中移除
            const index = this.tableData.findIndex(item => item.paymentid === this.deleteRow.paymentid);
            if (index !== -1) {
                this.tableData.splice(index, 1);
            }
            this.deleteDialogVisible = false;
        }
    }
};
</script>

<style scoped>
.search {
    margin-bottom: 10px;
    margin-top: 10px;
}
</style>

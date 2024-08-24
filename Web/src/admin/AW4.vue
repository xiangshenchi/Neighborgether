<template>
    <el-card style="margin:10px">
        <div>
            <div style="display: flex; align-items: center;">
                <!-- 缴费类型筛选 -->
                <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                    <el-select v-model="selectedpaymenttype" placeholder="选择缴费类型" size="mini" clearable>
                        <el-option label="所有缴费类型" value=""></el-option>
                        <el-option label="水费" value="水费"></el-option>
                        <el-option label="电费" value="电费"></el-option>
                        <el-option label="物业费" value="物业费"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </div>

                <!-- 缴费状态筛选 -->
                <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                    <el-select v-model="selectedPaymentstatus" placeholder="选择缴费状态" size="mini" clearable>
                        <el-option label="所有状态" value=""></el-option>
                        <el-option label="已缴" value="已缴"></el-option>
                        <el-option label="未缴" value="未缴"></el-option>
                    </el-select>
                </div>

                <!-- 添加缴费按钮 -->
                <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                    <el-button type="primary" @click="showAddDialog">添加缴费</el-button>
                </div>
            </div>

            <!-- 用户缴费表格 -->
            <el-table :data="paginatedData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="paymentid" label="缴费ID"></el-table-column>
                <!-- <el-table-column prop="UserName" label="用户名" width="120px"></el-table-column> -->
                <el-table-column prop="paymenttype" label="缴费类型"></el-table-column>
                <el-table-column prop="amount" label="金额"></el-table-column>
                <el-table-column label="缴费日期">
                    <template #default="scope">
                        {{ formatDate(scope.row.paymentdate) }}
                    </template>
                </el-table-column>
                <el-table-column prop="status" label="状态">
                    <template #default="scope">
                        <span v-if="scope.row.status === '已缴'">已缴</span>
                        <span v-else>未缴</span>
                    </template>
                </el-table-column>

                <el-table-column label="操作" align="right">
                    <template #default="scope">
                        <!-- <el-button v-if="scope.row.status === '未缴'" size="mini" type="primary"
                            @click="handlePay(scope.row)">缴费</el-button> -->
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页器 -->
            <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
                @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
                style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

            <!-- 添加缴费弹出框 -->
            <el-dialog title="添加缴费信息" v-model="addDialogVisible">
                <el-form :model="addForm">
                    <el-form-item label="缴费类型">
                        <el-select v-model="addForm.paymenttype" placeholder="选择缴费类型">
                            <el-option label="水费" value="水费"></el-option>
                            <el-option label="电费" value="电费"></el-option>
                            <el-option label="物业费" value="物业费"></el-option>
                            <el-option label="其他" value="其他"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="金额">
                        <el-input v-model="addForm.amount" placeholder="输入金额"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="addDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveAdd">保存</el-button>
                </span>
            </el-dialog>

            <!-- 缴费编辑框 -->
            <el-dialog title="编辑缴费信息" v-model="editDialogVisible">
                <el-form :model="editForm">
                    <el-form-item label="金额">
                        <el-input v-model="editForm.amount"></el-input>
                    </el-form-item>
                    <el-form-item label="类型">
                        <el-select v-model="editForm.paymenttype">
                            <el-option label="水费" value="水费"></el-option>
                            <el-option label="电费" value="电费"></el-option>
                            <el-option label="物业费" value="物业费"></el-option>
                            <el-option label="其他" value="其他"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveEdit">保存</el-button>
                </span>
            </el-dialog>
            <!-- 删除确认框 -->
            <el-dialog title="确认删除" v-model="deleteDialogVisible">
                <span>确定要删除该条缴费记录吗？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="deleteDialogVisible = false">取消</el-button>
                    <el-button type="danger" @click="confirmDelete">删除</el-button>
                </span>
            </el-dialog>
        </div>
    </el-card>
</template>

<script>
export default {
    data() {
        return {
            selectedpaymenttype: '', // 选择的缴费类型
            selectedPaymentstatus: '', // 选择的缴费状态
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            tableData: [],
            addDialogVisible: false, // 控制新增缴费弹出框的显示
            addForm: { // 新增缴费表单
                paymenttype: '',
                amount: '',
                paymentdate: '',
                status: ''  //默认未缴费
            },
            payDialogVisible: false, // 控制缴费弹出框的显示
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            selectedPayment: {}, // 当前选中的缴费记录
            editForm: {}, // 编辑表单
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据缴费类型、缴费状态进行筛选
                const matchespaymenttype = !this.selectedpaymenttype || data.paymenttype === this.selectedpaymenttype;
                const matchesPaymentstatus = !this.selectedPaymentstatus || data.status === this.selectedPaymentstatus;
                return matchespaymenttype && matchesPaymentstatus;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    mounted() {
        this.$axios.get('/livingpayment/list')
            .then(response => {
                console.log(this.tabledata);
                this.tableData = response.data;
                console.log(this.tabledata);
            })
            .catch(error => {
                console.log(error);
                this.$message.error("获取缴费列表失败！");
            });
    },
    methods: {
        showAddDialog() {
            this.addDialogVisible = true;
            // 打开弹窗时重置表单
            this.addForm = {
                paymenttype: '',
                amount: '',
                paymentdate: '',
                status: ''//默认未缴费
            };
        },
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        saveAdd() {
            this.$axios.post('/livingpayment/add', {
                paymenttype: this.addForm.paymenttype,
                amount: this.addForm.amount
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("添加缴费成功！");
                    this.addDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("添加缴费失败！");
                }
            }).catch(error => {
                console.log(error);
                this.$message.error("未知错误！");
            });
            this.addDialogVisible = false; // 关闭弹窗
        },
        handlePay(row) {
            // 打开缴费弹出框并记录选中的缴费记录
            this.selectedPayment = row;
            this.payDialogVisible = true;
        },
        confirmPay() {
            // 模拟缴费操作
            this.selectedPayment.status = '已缴';
            this.payDialogVisible = false;
            this.$message.success("缴费成功！");
        },
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            this.$axios.post('/livingpayment/edit', {
                paymentid: this.editForm.paymentid,
                paymenttype: this.editForm.paymenttype,
                amount: this.editForm.amount
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("编辑缴费成功！");
                    this.editDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("编辑缴费失败！");
                }
            }).catch(error => {
                console.log(error);
                this.$message.error("未知错误！");
            });
            this.editDialogVisible = false;
        },
        handleDelete(row) {
            // 打开删除确认框
            this.deleteRow = row;
            this.deleteDialogVisible = true;
        },
        confirmDelete() {
            this.$axios.delete('/livingpayment/delete', {
                params: { paymentid: this.deleteRow.paymentid }
            }).then(response => {
                if (response.data===true) {
                    this.$message.success("删除缴费成功！");
                    this.deleteDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("删除缴费失败！");
                }
            }).catch(error => {
                console.log(error);
                this.$message.error("未知错误！");
            });
            this.deleteDialogVisible = false;
        },
        handlePageChange(page) {
            this.currentPage = page;
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

<template>
    <el-card style="margin:10px">
        <div>
            <div style="display: flex; align-items: center;">
                <!-- 缴费类型筛选 -->
                <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                    <el-select v-model="selectedPaymentType" placeholder="选择缴费类型" size="mini" clearable>
                        <el-option label="所有缴费类型" value=""></el-option>
                        <el-option label="水费" value="水费"></el-option>
                        <el-option label="电费" value="电费"></el-option>
                        <el-option label="物业费" value="物业费"></el-option>
                        <el-option label="其他" value="其他"></el-option>
                    </el-select>
                </div>

                <!-- 缴费状态筛选 -->
                <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                    <el-select v-model="selectedPaymentStatus" placeholder="选择缴费状态" size="mini" clearable>
                        <el-option label="所有状态" value=""></el-option>
                        <el-option label="已缴" value="已缴"></el-option>
                        <el-option label="未缴" value="未缴"></el-option>
                    </el-select>
                </div>

                <!-- 添加公告按钮 -->
                <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                    <el-button type="primary" @click="showAddDialog">添加缴费</el-button>
                </div>
            </div>

            <!-- 用户缴费表格 -->
            <el-table :data="paginatedData" style="width: 100%">
                <el-table-column prop="PaymentID" label="缴费ID" width="80px"></el-table-column>
                <!-- <el-table-column prop="UserName" label="用户名" width="120px"></el-table-column> -->
                <el-table-column prop="PaymentType" label="缴费类型" width="100px"></el-table-column>
                <el-table-column prop="Amount" label="金额" width="100px"></el-table-column>
                <el-table-column prop="PaymentDate" label="缴费日期" width="250px"></el-table-column>
                <el-table-column prop="Status" label="状态" width="100px">
                    <template #default="scope">
                        <span v-if="scope.row.Status === '已缴'">已缴</span>
                        <span v-else>未缴</span>
                    </template>
                </el-table-column>

                <el-table-column label="操作" align="right">
                    <template #default="scope">
                        <!-- <el-button v-if="scope.row.Status === '未缴'" size="mini" type="primary"
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
            <el-dialog title="添加缴费信息" :visible.sync="addDialogVisible">
                <el-form :model="addForm">
                    <el-form-item label="缴费类型">
                        <el-select v-model="addForm.PaymentType" placeholder="选择缴费类型">
                            <el-option label="水费" value="水费"></el-option>
                            <el-option label="电费" value="电费"></el-option>
                            <el-option label="物业费" value="物业费"></el-option>
                            <el-option label="其他" value="其他"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="金额">
                        <el-input v-model="addForm.Amount" placeholder="输入金额"></el-input>
                    </el-form-item>
                    <el-form-item label="缴费日期">
                        <el-date-picker v-model="addForm.PaymentDate" type="datetime"
                            placeholder="选择日期"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="addForm.Status" placeholder="选择缴费状态">
                            <el-option label="已缴" value="已缴"></el-option>
                            <el-option label="未缴" value="未缴"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="addDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveAdd">保存</el-button>
                </span>
            </el-dialog>


            <!-- 删除确认框 -->
            <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
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
            selectedPaymentType: '', // 选择的缴费类型
            selectedPaymentStatus: '', // 选择的缴费状态
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            tableData: [
                { UserName: "张三", PaymentID: 1, PaymentType: "水费", Amount: 50.75, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "李四", PaymentID: 2, PaymentType: "电费", Amount: 120.00, PaymentDate: "2024-08-14", Status: "未缴" },
                { UserName: "王五", PaymentID: 3, PaymentType: "物业费", Amount: 300.00, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "赵六", PaymentID: 4, PaymentType: "其他", Amount: 200.00, PaymentDate: "2024-08-15", Status: "未缴" }, { UserName: "张三", PaymentID: 1, PaymentType: "水费", Amount: 50.75, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "李四", PaymentID: 2, PaymentType: "电费", Amount: 120.00, PaymentDate: "2024-08-14", Status: "未缴" },
                { UserName: "王五", PaymentID: 3, PaymentType: "物业费", Amount: 300.00, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "赵六", PaymentID: 4, PaymentType: "其他", Amount: 200.00, PaymentDate: "2024-08-15", Status: "未缴" }, { UserName: "张三", PaymentID: 1, PaymentType: "水费", Amount: 50.75, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "李四", PaymentID: 2, PaymentType: "电费", Amount: 120.00, PaymentDate: "2024-08-14", Status: "未缴" },
                { UserName: "王五", PaymentID: 3, PaymentType: "物业费", Amount: 300.00, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "赵六", PaymentID: 4, PaymentType: "其他", Amount: 200.00, PaymentDate: "2024-08-15", Status: "未缴" }, { UserName: "张三", PaymentID: 1, PaymentType: "水费", Amount: 50.75, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "李四", PaymentID: 2, PaymentType: "电费", Amount: 120.00, PaymentDate: "2024-08-14", Status: "未缴" },
                { UserName: "王五", PaymentID: 3, PaymentType: "物业费", Amount: 300.00, PaymentDate: "2024-08-14", Status: "已缴" },
                { UserName: "赵六", PaymentID: 4, PaymentType: "其他", Amount: 200.00, PaymentDate: "2024-08-15", Status: "未缴" },
                // 你可以继续添加更多数据
            ],
            addDialogVisible: false, // 控制新增缴费弹出框的显示
            addForm: { // 新增缴费表单
                PaymentType: '',
                Amount: '',
                PaymentDate: '',
                Status: ''  //默认未缴费
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
                const matchesPaymentType = !this.selectedPaymentType || data.PaymentType === this.selectedPaymentType;
                const matchesPaymentStatus = !this.selectedPaymentStatus || data.Status === this.selectedPaymentStatus;
                return matchesPaymentType && matchesPaymentStatus;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    methods: {
        showAddDialog() {
            this.addDialogVisible = true;
            // 打开弹窗时重置表单
            this.addForm = {
                PaymentType: '',
                Amount: '',
                PaymentDate: '',
                Status: ''//默认未缴费
            };
        },
        saveAdd() {
            // 为新增缴费生成唯一的缴费ID（简单递增示例）
            const newPaymentID = this.tableData.length ? this.tableData[this.tableData.length - 1].PaymentID + 1 : 1;
            const newPayment = {
                PaymentID: newPaymentID,
                UserName: "新用户", // 你可以根据需要替换为实际的用户选择逻辑
                PaymentType: this.addForm.PaymentType,
                Amount: this.addForm.Amount,
                PaymentDate: this.addForm.PaymentDate,
                Status: this.addForm.Status
            };
            this.tableData.push(newPayment);
            this.addDialogVisible = false; // 关闭弹窗
            this.$message.success("新增缴费成功！");
        },
        handlePay(row) {
            // 打开缴费弹出框并记录选中的缴费记录
            this.selectedPayment = row;
            this.payDialogVisible = true;
        },
        confirmPay() {
            // 模拟缴费操作
            this.selectedPayment.Status = '已缴';
            this.payDialogVisible = false;
            this.$message.success("缴费成功！");
        },
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            // 保存编辑后的数据
            const index = this.tableData.findIndex(item => item.PaymentID === this.editForm.PaymentID);
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
            const index = this.tableData.findIndex(item => item.PaymentID === this.deleteRow.PaymentID);
            if (index !== -1) {
                this.tableData.splice(index, 1);
            }
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

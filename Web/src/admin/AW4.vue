<template>
    <div>
        <!-- 搜索框和费用类型筛选框 -->
        <div style="width: 20%; margin-left: 10px; display: inline-block;">
            <el-input v-model="search" placeholder="输入费用类型搜索" size="mini" class="search" />
        </div>
        <div style="width: 12%; margin-left: 20px; display: inline-block;">
            <el-select v-model="selectedStatus" placeholder="选择缴费状态" size="mini" clearable>
                <el-option label="所有状态" value=""></el-option>
                <el-option label="已缴" value="已缴"></el-option>
                <el-option label="未缴" value="未缴"></el-option>
            </el-select>
        </div>

        <!-- 费用表格 -->
        <el-table :data="filteredData" style="width: 100%">
            <el-table-column prop="paymentid" label="记录ID" width="80px"></el-table-column>
            <el-table-column prop="userid" label="用户ID" width="80px"></el-table-column>
            <el-table-column prop="paymenttype" label="费用类型" width="100px"></el-table-column>
            <el-table-column prop="amount" label="金额"></el-table-column>
            <el-table-column prop="paymentdate" label="缴费日期"></el-table-column>
            <el-table-column prop="status" label="状态"></el-table-column>

            <el-table-column label="操作" align="right">
                <template #default="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 编辑费用信息弹出框 -->
        <el-dialog title="编辑费用信息" :visible.sync="editDialogVisible">
            <el-form :model="editForm">
                <el-form-item label="费用类型">
                    <el-select v-model="editForm.paymenttype">
                        <el-option label="水费" value="水费"></el-option>
                        <el-option label="电费" value="电费"></el-option>
                        <el-option label="物业费" value="物业费"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="金额">
                    <el-input v-model="editForm.amount"></el-input>
                </el-form-item>
                <el-form-item label="缴费日期">
                    <el-date-picker v-model="editForm.paymentdate" type="datetime"></el-date-picker>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="editForm.status">
                        <el-option label="已缴" value="已缴"></el-option>
                        <el-option label="未缴" value="未缴"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="saveEdit">保存</el-button>
            </span>
        </el-dialog>

        <!-- 删除确认框 -->
        <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
            <span>确定要删除该记录吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="deleteDialogVisible = false">取消</el-button>
                <el-button type="danger" @click="confirmDelete">删除</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    data() {
        return {
            search: '', // 搜索关键字
            selectedStatus: '', // 缴费状态筛选
            tableData: [
                { paymentid: 1, userid: 1, paymenttype: "水费", amount: 50.75, paymentdate: "2024-08-14 15:30:35", status: "已缴" },
                { paymentid: 2, userid: 1, paymenttype: "电费", amount: 120.00, paymentdate: "2024-08-14 15:30:35", status: "未缴" },
                { paymentid: 3, userid: 2, paymenttype: "物业费", amount: 300.00, paymentdate: "2024-08-14 15:30:35", status: "已缴" },
                // 更多示例数据
            ],
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑费用的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据费用类型和缴费状态进行筛选
                const matchesSearch = !this.search || data.paymenttype.includes(this.search);
                const matchesStatus = !this.selectedStatus || data.status === this.selectedStatus;
                return matchesSearch && matchesStatus;
            });
        }
    },
    methods: {
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

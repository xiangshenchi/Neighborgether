<template>
    <div>
        <!-- 搜索框和状态筛选框 -->
        <div style="width: 20%; margin-left: 10px; display: inline-block;">
            <el-input v-model="search" placeholder="输入诉求内容搜索" size="mini" class="search" />
        </div>
        <div style="width: 12%; margin-left: 20px; display: inline-block;">
            <el-select v-model="selectedStatus" placeholder="选择处理状态" size="mini" clearable>
                <el-option label="所有状态" value=""></el-option>
                <el-option label="处理中" value="处理中"></el-option>
                <el-option label="待处理" value="待处理"></el-option>
            </el-select>
        </div>

        <!-- 诉求表格 -->
        <el-table :data="paginatedData" style="width: 100%">
            <el-table-column prop="repairid" label="诉求ID" width="80px"></el-table-column>
            <el-table-column prop="repaircontent" label="诉求内容" width="180px"></el-table-column>
            <el-table-column prop="repairdate" label="诉求日期" width="150px"></el-table-column>
            <el-table-column prop="repairstatus" label="处理状态" width="100px"></el-table-column>

            <el-table-column label="操作" align="right">
                <template #default="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页器 -->
        <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
            @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
            style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

        <!-- 编辑诉求信息弹出框 -->
        <el-dialog title="编辑诉求信息" :visible.sync="editDialogVisible">
            <el-form :model="editForm">
                <el-form-item label="诉求内容">
                    <el-input v-model="editForm.repaircontent"></el-input>
                </el-form-item>
                <el-form-item label="诉求日期">
                    <el-date-picker v-model="editForm.repairdate" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="处理状态">
                    <el-select v-model="editForm.repairstatus">
                        <el-option label="处理中" value="处理中"></el-option>
                        <el-option label="待处理" value="待处理"></el-option>
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
            <span>确定要删除该诉求吗？</span>
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
            selectedStatus: '', // 处理状态筛选
            tableData: [
                { repairid: 1, repaircontent: "水管破裂", repairdate: "2024-08-10", repairstatus: "处理中" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 2, repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },

                // 更多示例数据
            ],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑诉求的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据诉求内容和处理状态进行筛选
                const matchesSearch = !this.search || data.repaircontent.includes(this.search);
                const matchesStatus = !this.selectedStatus || data.repairstatus === this.selectedStatus;
                return matchesSearch && matchesStatus;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
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
            const index = this.tableData.findIndex(item => item.repairid === this.editForm.repairid);
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
            const index = this.tableData.findIndex(item => item.repairid === this.deleteRow.repairid);
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

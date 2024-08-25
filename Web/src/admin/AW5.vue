<template>
    <el-card style="margin:10px">
        <div>
            <!-- 维修状态筛选 -->
            <div style="width: 12%; margin-left: 10px; margin-bottom: 10px; display: inline-block;">
                <el-select v-model="selectedrepairstatus" placeholder="选择维修状态" size="mini" clearable>
                    <el-option label="所有状态" value=""></el-option>
                    <el-option label="待处理" value="待处理"></el-option>
                    <el-option label="处理中" value="处理中"></el-option>
                    <el-option label="已完成" value="已完成"></el-option>
                </el-select>
            </div>

            <!-- 维修管理表格 -->
            <el-table :data="paginatedData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="repairid" label="维修ID"></el-table-column>
                <el-table-column prop="repairphone" label="联系电话"></el-table-column>
                <el-table-column prop="repaircontent" label="维修内容">
                    <template #default="scope">
                        <div style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                            {{ scope.row.repaircontent }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="维修日期">
                    <template #default="scope">
                        {{ formatDate(scope.row.repairdate) }}
                    </template>
                </el-table-column>
                <el-table-column prop="repairstatus" label="状态">
                    <template #default="scope">
                        <span v-if="scope.row.repairstatus === '处理中'">处理中</span>
                        <span v-else-if="scope.row.repairstatus === '已完成'">已完成</span>
                        <span v-else>待处理</span>
                    </template>
                </el-table-column>

                <el-table-column label="操作" align="right">
                    <template #default="scope">
                        <!-- <el-button v-if="scope.row.repairstatus === '待处理'" size="mini" type="primary"
                            @click="handleProcess(scope.row)">处理</el-button>
                        <el-button v-if="scope.row.repairstatus === '处理中'" size="mini" type="success"
                            @click="handleComplete(scope.row)">完成</el-button> -->
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <!-- <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button> -->
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页器 -->
            <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
                @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
                style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

            <!-- 编辑维修信息对话框 -->
            <el-dialog title="编辑维修信息" v-model="editDialogVisible">
                <el-form :model="editForm">
                    <el-form-item label="状态">
                        <el-select v-model="editForm.repairstatus">
                            <el-option label="待处理" value="待处理"></el-option>
                            <el-option label="处理中" value="处理中"></el-option>
                            <el-option label="已完成" value="已完成"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveEdit">保存</el-button>
                </span>
            </el-dialog>

            <!-- 删除确认对话框 -->
            <!-- <el-dialog title="确认删除" v-model="deleteDialogVisible">
                <span>确定要删除该条维修记录吗？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="deleteDialogVisible = false">取消</el-button>
                    <el-button type="danger" @click="confirmDelete">删除</el-button>
                </span>
            </el-dialog> -->
        </div>
    </el-card>
</template>

<script>
export default {
    data() {
        return {
            selectedrepairstatus: '', // 选择的维修状态筛选
            tableData: [ ],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            processDialogVisible: false, // 控制处理确认对话框
            completeDialogVisible: false, // 控制完成确认对话框
            editDialogVisible: false, // 控制编辑对话框
            deleteDialogVisible: false, // 控制删除确认对话框
            selectedRepair: {}, // 当前选择的维修记录
            editForm: {}, // 编辑维修信息的表单
            deleteRow: null, // 要删除的记录
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 按维修状态筛选
                const matchesStatus = !this.selectedrepairstatus || data.repairstatus === this.selectedrepairstatus;
                return matchesStatus;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    mounted() {
        this.$axios.get('/repairmanagement/list')
            .then(response => {
                console.log(this.tabledata);
                this.tableData = response.data;
                console.log(this.tabledata);
            })
            .catch(error => {
                console.log(error);
                this.$message.error("获取维修列表失败！");
            });
    },
    methods: {
        handleProcess(row) {
            // 打开处理确认对话框，并记录选中的维修
            this.selectedRepair = row;
            this.processDialogVisible = true;
        },
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        confirmProcess() {
            // 模拟处理维修
            this.selectedRepair.repairstatus = '处理中';
            this.processDialogVisible = false;
            this.$message.success("处理成功！");
        },
        handleComplete(row) {
            // 打开完成确认对话框，并记录选中的维修
            this.selectedRepair = row;
            this.completeDialogVisible = true;
        },
        confirmComplete() {
            // 模拟完成维修
            this.selectedRepair.repairstatus = '已完成';
            this.completeDialogVisible = false;
            this.$message.success("维修完成！");
        },
        handleEdit(row) {
            // 打开编辑对话框，并设置表单内容
            this.editForm = Object.assign({}, row);
            this.editDialogVisible = true;
        },
        saveEdit() {
            this.$axios.post('/repairmanagement/edit', {
                repairid: this.editForm.repairid,
                repairstatus: this.editForm.repairstatus
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("编辑维修成功！");
                    this.editDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("编辑维修失败！");
                }
            }).catch(error => {
                console.log(error);
                this.$message.error("未知错误！");
            });
            this.editDialogVisible = false;
        },
        // handleDelete(row) {
        //     // 打开删除确认对话框
        //     this.deleteRow = row;
        //     this.deleteDialogVisible = true;
        // },
        // confirmDelete() {
        //     // 确认删除，并从表格中移除数据
        //     const index = this.tableData.findIndex(item => item.repairid === this.deleteRow.repairid);
        //     if (index !== -1) {
        //         this.tableData.splice(index, 1);
        //     }
        //     this.deleteDialogVisible = false;
        //     this.$message.success("删除成功！");
        // },
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

<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;">
        <el-card style="margin:10px">
            <!-- 维修状态筛选 -->
            <div style="width: 12%; margin-left: 20px; margin-top: 10px; display: inline-block;">
                <el-select v-model="selectedRepairStatus" placeholder="选择维修状态" size="mini" clearable>
                    <el-option label="所有状态" value=""></el-option>
                    <el-option label="待处理" value="待处理"></el-option>
                    <el-option label="处理中" value="处理中"></el-option>
                    <el-option label="已完成" value="已完成"></el-option>
                </el-select>
            </div>

            <!-- 维修管理表格 -->
            <el-table :data="paginatedData" style="width: 100%; margin-left: 20px"
                :header-cell-style="{ 'text-align': 'center' }" :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="repairid" label="维修ID"></el-table-column>
                <el-table-column prop="repaircontent" label="维修内容">
                    <template #default="scope">
                        <div class="ellipsis">
                            {{ scope.row.repaircontent }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="repairdate" label="维修日期"></el-table-column>
                <el-table-column prop="repairstatus" label="状态">
                    <template #default="scope">
                        <span v-if="scope.row.repairstatus === '处理中'">处理中</span>
                        <span v-else-if="scope.row.repairstatus === '已完成'">已完成</span>
                        <span v-else>待处理</span>
                    </template>
                </el-table-column>
            </el-table>
            <!-- 分页器 -->
            <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
                @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
                style="margin-top: 20px; text-align: right; margin-left: 20px;"></el-pagination>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            selectedRepairStatus: '', // 选择的维修状态筛选
            tableData: [
                { repairid: 1, RepairPhone: "789", repaircontent: "水管破裂", repairdate: "2024-08-10", repairstatus: "处理中" },
                { repairid: 2, RepairPhone: "123", repaircontent: "电梯故障", repairdate: "2024-08-12", repairstatus: "待处理" },
                { repairid: 3, RepairPhone: "456", repaircontent: "空调故障", repairdate: "2024-08-15", repairstatus: "处理中" },
                { repairid: 4, RepairPhone: "987", repaircontent: "屋顶漏水", repairdate: "2024-08-09", repairstatus: "已完成" },
                { repairid: 5, RepairPhone: "654", repaircontent: "电力系统故障", repairdate: "2024-08-13", repairstatus: "待处理" },
                { repairid: 6, RepairPhone: "321", repaircontent: "排水管堵塞", repairdate: "2024-08-11", repairstatus: "处理中" },
                { repairid: 7, RepairPhone: "741", repaircontent: "墙壁开裂", repairdate: "2024-08-16", repairstatus: "待处理" },
                { repairid: 8, RepairPhone: "852", repaircontent: "电梯按钮失灵", repairdate: "2024-08-18", repairstatus: "处理中" },
                { repairid: 9, RepairPhone: "963", repaircontent: "停车场门禁系统故障", repairdate: "2024-08-14", repairstatus: "已完成" },
                { repairid: 10, RepairPhone: "159", repaircontent: "监控摄像头故障", repairdate: "2024-08-17", repairstatus: "待处理" },
            ],
            currentPage: 1, // 当前页
            pageSize: 5, // 每页显示的数据条数
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
                const matchesStatus = !this.selectedRepairStatus || data.repairstatus === this.selectedRepairStatus;
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
        this.$axios.get('/repairmanagement/listR', {
            params: {
                phonenumber: this.$store.getters.userInfo.phonenumber
            }
        }).then(res => {
            this.tableData = res.data;
        });
    },
    methods: {
        handleProcess(row) {
            // 打开处理确认对话框，并记录选中的维修
            this.selectedRepair = row;
            this.processDialogVisible = true;
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
            // 保存编辑的维修信息
            const index = this.tableData.findIndex(item => item.repairid === this.editForm.repairid);
            if (index !== -1) {
                this.tableData.splice(index, 1, this.editForm);
            }
            this.$message.success("编辑成功！");
            this.editDialogVisible = false;
        },
        handleDelete(row) {
            // 打开删除确认对话框
            this.deleteRow = row;
            this.deleteDialogVisible = true;
        },
        confirmDelete() {
            // 确认删除，并从表格中移除数据
            const index = this.tableData.findIndex(item => item.repairid === this.deleteRow.repairid);
            if (index !== -1) {
                this.tableData.splice(index, 1);
            }
            this.deleteDialogVisible = false;
            this.$message.success("删除成功！");
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

.ellipsis {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
}
</style>

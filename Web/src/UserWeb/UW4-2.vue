<template>
    <div>
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
        <el-table :data="paginatedData" style="width: 100%">
            <el-table-column prop="RepairID" label="维修ID" width="80px"></el-table-column>
            <el-table-column prop="RepairPhone" label="联系电话" width="120px"></el-table-column>
            <el-table-column prop="RepairContent" label="维修内容" width="150px"></el-table-column>
            <el-table-column prop="RepairDate" label="维修日期"></el-table-column>
            <el-table-column prop="RepairStatus" label="状态" width="100px">
                <template #default="scope">
                    <span v-if="scope.row.RepairStatus === '处理中'">处理中</span>
                    <span v-else-if="scope.row.RepairStatus === '已完成'">已完成</span>
                    <span v-else>待处理</span>
                </template>
            </el-table-column>

            <el-table-column label="操作" align="right">
                <template #default="scope">
                    <el-button v-if="scope.row.RepairStatus === '待处理'" size="mini" type="primary"
                        @click="handleProcess(scope.row)">处理</el-button>
                    <el-button v-if="scope.row.RepairStatus === '处理中'" size="mini" type="success"
                        @click="handleComplete(scope.row)">完成</el-button>
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页器 -->
        <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
            @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
            style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

        <!-- 处理维修对话框 -->
        <el-dialog title="确认处理" :visible.sync="processDialogVisible">
            <p>确认处理用户 {{ selectedRepair.RepairPhone }} 的维修请求: {{ selectedRepair.RepairContent }}?</p>
            <span slot="footer" class="dialog-footer">
                <el-button @click="processDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="confirmProcess">确认处理</el-button>
            </span>
        </el-dialog>

        <!-- 完成维修对话框 -->
        <el-dialog title="确认完成" :visible.sync="completeDialogVisible">
            <p>确认已完成用户 {{ selectedRepair.RepairPhone }} 的维修请求: {{ selectedRepair.RepairContent }}?</p>
            <span slot="footer" class="dialog-footer">
                <el-button @click="completeDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="confirmComplete">确认完成</el-button>
            </span>
        </el-dialog>

        <!-- 编辑维修信息对话框 -->
        <el-dialog title="编辑维修信息" :visible.sync="editDialogVisible">
            <el-form :model="editForm">
                <el-form-item label="维修内容">
                    <el-input v-model="editForm.RepairContent"></el-input>
                </el-form-item>
                <el-form-item label="联系电话">
                    <el-input v-model="editForm.RepairPhone"></el-input>
                </el-form-item>
                <el-form-item label="维修日期">
                    <el-date-picker v-model="editForm.RepairDate" type="date"></el-date-picker>
                </el-form-item>
                <el-form-item label="状态">
                    <el-select v-model="editForm.RepairStatus">
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
        <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
            <span>确定要删除该条维修记录吗？</span>
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
            selectedRepairStatus: '', // 选择的维修状态筛选
            tableData: [
                { RepairID: 1, RepairPhone: "789", RepairContent: "水管破裂", RepairDate: "2024-08-10", RepairStatus: "处理中" },
                { RepairID: 2, RepairPhone: "123", RepairContent: "电梯故障", RepairDate: "2024-08-12", RepairStatus: "待处理" },
                { RepairID: 3, RepairPhone: "456", RepairContent: "空调故障", RepairDate: "2024-08-15", RepairStatus: "处理中" },
                { RepairID: 4, RepairPhone: "987", RepairContent: "屋顶漏水", RepairDate: "2024-08-09", RepairStatus: "已完成" },
                { RepairID: 5, RepairPhone: "654", RepairContent: "电力系统故障", RepairDate: "2024-08-13", RepairStatus: "待处理" },
                { RepairID: 6, RepairPhone: "321", RepairContent: "排水管堵塞", RepairDate: "2024-08-11", RepairStatus: "处理中" },
                { RepairID: 7, RepairPhone: "741", RepairContent: "墙壁开裂", RepairDate: "2024-08-16", RepairStatus: "待处理" },
                { RepairID: 8, RepairPhone: "852", RepairContent: "电梯按钮失灵", RepairDate: "2024-08-18", RepairStatus: "处理中" },
                { RepairID: 9, RepairPhone: "963", RepairContent: "停车场门禁系统故障", RepairDate: "2024-08-14", RepairStatus: "已完成" },
                { RepairID: 10, RepairPhone: "159", RepairContent: "监控摄像头故障", RepairDate: "2024-08-17", RepairStatus: "待处理" },
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
                const matchesStatus = !this.selectedRepairStatus || data.RepairStatus === this.selectedRepairStatus;
                return matchesStatus;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    methods: {
        handleProcess(row) {
            // 打开处理确认对话框，并记录选中的维修
            this.selectedRepair = row;
            this.processDialogVisible = true;
        },
        confirmProcess() {
            // 模拟处理维修
            this.selectedRepair.RepairStatus = '处理中';
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
            this.selectedRepair.RepairStatus = '已完成';
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
            const index = this.tableData.findIndex(item => item.RepairID === this.editForm.RepairID);
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
            const index = this.tableData.findIndex(item => item.RepairID === this.deleteRow.RepairID);
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
</style>

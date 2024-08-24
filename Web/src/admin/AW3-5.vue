<template>
        <div>
                <!-- 搜索框和过滤器 -->
                <div style="width: 20%; margin-left: 10px; display: inline-block;">
                        <el-input v-model="search" placeholder="输入访客姓名搜索" size="mini" class="search" />
                </div>
                <div style="width: 25%; margin-left: 20px; display: inline-block;">
                        <el-date-picker v-model="selectedDateRange" type="daterange" range-separator="至"
                                start-placeholder="开始日期" end-placeholder="结束日期" size="mini" align="right"
                                :clearable="true" value-format="yyyy-MM-dd" />
                </div>

                <!-- 访客信息表格 -->
                <el-table :data="filteredData" style="width: 100%" class="custom-table">
                        <el-table-column prop="VisitID" label="访客ID" width="80px"></el-table-column>
                        <el-table-column prop="VisitName" label="访客姓名" width="100px"></el-table-column>
                        <el-table-column prop="VisitPhone" label="访客电话" width="150px"></el-table-column>
                        <el-table-column prop="VisitDate" label="访问日期" width="180px"></el-table-column>
                        <el-table-column prop="VisitReason" label="访问原因" width="160px"></el-table-column>

                        <el-table-column label="操作" align="right">
                                <template #default="scope">
                                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                                        <el-button size="mini" type="danger"
                                                @click="handleDelete(scope.row)">删除</el-button>
                                </template>
                        </el-table-column>
                </el-table>

                <!-- 编辑访客信息弹出框 -->
                <el-dialog title="编辑访客信息" :visible.sync="editDialogVisible">
                        <el-form :model="editForm">
                                <el-form-item label="访客姓名">
                                        <el-input v-model="editForm.VisitName"></el-input>
                                </el-form-item>
                                <el-form-item label="访客电话">
                                        <el-input v-model="editForm.VisitPhone"></el-input>
                                </el-form-item>
                                <el-form-item label="访问日期">
                                        <el-date-picker v-model="editForm.VisitDate" type="date" placeholder="选择日期"
                                                value-format="yyyy-MM-dd" />
                                </el-form-item>
                                <el-form-item label="访问原因">
                                        <el-input v-model="editForm.VisitReason"></el-input>
                                </el-form-item>
                        </el-form>
                        <span slot="footer" class="dialog-footer">
                                <el-button @click="editDialogVisible = false">取消</el-button>
                                <el-button type="primary" @click="saveEdit">保存</el-button>
                        </span>
                </el-dialog>

                <!-- 删除确认框 -->
                <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
                        <span>确定要删除该访客信息吗？</span>
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
                        selectedDateRange: '', // 日期范围筛选
                        tableData: [
                                {
                                        VisitID: 1,
                                        VisitName: "Alice Brown",
                                        VisitPhone: "1230987654",
                                        VisitDate: "2024-08-14",
                                        VisitReason: "探望家人"
                                },
                                {
                                        VisitID: 2,
                                        VisitName: "Bob White",
                                        VisitPhone: "0981234567",
                                        VisitDate: "2024-08-14",
                                        VisitReason: "送快递"
                                }
                        ],
                        editDialogVisible: false, // 控制编辑弹出框的显示
                        deleteDialogVisible: false, // 控制删除确认框的显示
                        editForm: {}, // 编辑访客的信息
                        deleteRow: null, // 待删除的行
                };
        },
        computed: {
                filteredData() {
                        return this.tableData.filter(data => {
                                const matchesSearch = !this.search || data.VisitName.includes(this.search);
                                const matchesDate =
                                        !this.selectedDateRange ||
                                        (new Date(data.VisitDate) >= new Date(this.selectedDateRange[0]) &&
                                                new Date(data.VisitDate) <= new Date(this.selectedDateRange[1]));
                                return matchesSearch && matchesDate;
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
                        const index = this.tableData.findIndex(item => item.VisitID === this.editForm.VisitID);
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
                        const index = this.tableData.findIndex(item => item.VisitID === this.deleteRow.VisitID);
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

/* 自定义表格边框样式 */
.custom-table .el-table__body-wrapper table {
        border-collapse: collapse;
        /* 合并单元格边框 */
}

.custom-table .el-table__body-wrapper table tr {
        border: 1px solid #ddd;
        /* 为每一行添加边框 */
}

.custom-table .el-table__header-wrapper table th,
.custom-table .el-table__body-wrapper table td {
        border: 1px solid #ddd;
        /* 为每一列添加边框 */
}

.custom-table .el-table__header-wrapper table th {
        background-color: #f5f5f5;
        /* 表头背景色 */
}
</style>
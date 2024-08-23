<template>
    <div>
        <!-- 搜索框 -->
        <div style="width: 20%; margin-left: 10px; display: inline-block;">
            <el-input v-model="search" placeholder="输入车牌号搜索" size="mini" class="search" />
        </div>

        <!-- 车辆表格 -->
        <el-table :data="filteredData" style="width: 100%">
            <el-table-column prop="vehicleid" label="车辆ID" width="80px"></el-table-column>
            <el-table-column prop="userid" label="用户ID" width="80px"></el-table-column>
            <el-table-column prop="licenseplate" label="车牌号" width="100px"></el-table-column>
            <el-table-column prop="vehicletype" label="车辆类型" width="100px"></el-table-column>
            <el-table-column prop="registrationdate" label="登记日期" width="160px"></el-table-column>

            <el-table-column label="操作" align="right">
                <template #default="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 编辑车辆信息弹出框 -->
        <el-dialog title="编辑车辆信息" :visible.sync="editDialogVisible">
            <el-form :model="editForm">
                <el-form-item label="用户ID">
                    <el-input v-model="editForm.userid"></el-input>
                </el-form-item>
                <el-form-item label="车牌号">
                    <el-input v-model="editForm.licenseplate"></el-input>
                </el-form-item>
                <el-form-item label="车辆类型">
                    <el-input v-model="editForm.vehicletype"></el-input>
                </el-form-item>
                <el-form-item label="注册日期">
                    <el-input v-model="editForm.registrationdate"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="saveEdit">保存</el-button>
            </span>
        </el-dialog>

        <!-- 删除确认框 -->
        <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
            <span>确定要删除该车辆吗？</span>
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
            tableData: [
                {
                    vehicleid: 1,
                    userid: 1,
                    licenseplate: "ABC123",
                    vehicletype: "Sedan",
                    registrationdate: "2024-08-14 15:30:35"
                },
                {
                    vehicleid: 2,
                    userid: 2,
                    licenseplate: "XYZ789",
                    vehicletype: "SUV",
                    registrationdate: "2024-08-14 15:30:35"
                }
            ],
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑车辆的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据车牌号进行筛选
                return !this.search || data.licenseplate.toLowerCase().includes(this.search.toLowerCase());
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
            const index = this.tableData.findIndex(item => item.vehicleid === this.editForm.vehicleid);
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
            const index = this.tableData.findIndex(item => item.vehicleid === this.deleteRow.vehicleid);
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

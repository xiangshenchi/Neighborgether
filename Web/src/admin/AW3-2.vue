<template>
    <el-card style="margin:10px">
        <div>
            <!-- 搜索框 -->
            <div style="width: 20%; margin-left: 10px; display: inline-block;">
                <el-input v-model="search" placeholder="输入车牌号搜索" size="mini" class="search" />
            </div>

            <!-- 车辆表格 -->
            <el-table :data="paginatedData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="vehicleid" label="车辆ID"></el-table-column>
                <el-table-column prop="userid" label="用户ID"></el-table-column>
                <el-table-column prop="licenseplate" label="车牌号"></el-table-column>
                <el-table-column prop="vehicletype" label="车辆类型" ></el-table-column>
                <el-table-column prop="registrationdate" label="登记日期" ></el-table-column>

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

            <!-- 编辑车辆信息弹出框 -->
            <el-dialog title="编辑车辆信息" v-model="editDialogVisible">
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
            <el-dialog title="确认删除" v-model="deleteDialogVisible">
                <span>确定要删除该车辆吗？</span>
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
                },
                {
                    vehicleid: 2,
                    userid: 2,
                    licenseplate: "XYZ789",
                    vehicletype: "SUV",
                    registrationdate: "2024-08-14 15:30:35"
                },
                {
                    vehicleid: 2,
                    userid: 2,
                    licenseplate: "XYZ789",
                    vehicletype: "SUV",
                    registrationdate: "2024-08-14 15:30:35"
                },
                {
                    vehicleid: 2,
                    userid: 2,
                    licenseplate: "XYZ789",
                    vehicletype: "SUV",
                    registrationdate: "2024-08-14 15:30:35"
                },
                {
                    vehicleid: 2,
                    userid: 2,
                    licenseplate: "XYZ789",
                    vehicletype: "SUV",
                    registrationdate: "2024-08-14 15:30:35"
                },
            ],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
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
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    mounted() {
        this.$axios.get('/vehicles/list')
            .then(response => {
                console.log(this.tabledata);
                this.tableData = response.data;
                console.log(this.tabledata);
            })
            .catch(error => {
                console.log(error);
                this.$message.error("获取车辆列表失败！");
            });
    },
    methods: {
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            // 保存编辑后的数据
            this.$axios.post('/vehicles/edit', {

                vehicleid: this.editForm.vehicleid,
                licenseplate: this.editForm.licenseplate,
                vehicletype: this.editForm.vehicletype,
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("编辑车辆成功！");
                    this.editDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("编辑车辆失败！");
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
            this.$axios.delete('/vehicles/delete', {
                params: { vehicleid: this.deleteRow.vehicleid }
            }).then(response => {
                if (response.data===true) {
                    this.$message.success("删除车辆成功！");
                    this.deleteDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("删除车辆失败！");
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

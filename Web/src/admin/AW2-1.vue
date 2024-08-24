<template>
    <el-card style="margin:10px">
        <div>
            <!-- 搜索框和用户类型筛选框 -->
            <div style="width: 20%; margin-left: 10px; display: inline-block;">
                <el-input v-model="search" placeholder="输入用户姓名搜索" size="mini" class="search" />
            </div>
            <div style="width: 12%; margin-left: 20px; display: inline-block;">
                <el-select v-model="selectedRole" placeholder="选择用户类型" size="mini" clearable>
                    <el-option label="所有用户" value=""></el-option>
                    <el-option label="业主" value="Owner"></el-option>
                    <el-option label="工作人员" value="Staff"></el-option>
                    <el-option label="管理员" value="Admin"></el-option>
                </el-select>
            </div>

            <!-- 用户表格 -->
            <el-table :data="paginatedData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="userid" label="用户ID" width="80px"></el-table-column>
                <el-table-column prop="username" label="用户名" width="100px"></el-table-column>
                <el-table-column prop="password" label="密码"></el-table-column>
                <el-table-column prop="phonenumber" label="电话"></el-table-column>
                <el-table-column prop="email" label="邮箱"></el-table-column>
                <el-table-column prop="address" label="住址"></el-table-column>

                <!-- 不同用户类别背景颜色不同，style块中定义了颜色 -->
                <el-table-column prop="role" label="用户类别">
                    <!-- 目前先注释，用示例数据，后期用后端数据 -->
                    <!-- <template #default="scope">
                    <span :class="getRoleClass(scope.row.role)">
                        {{ scope.row.role }}
                    </span>
                </template> -->
                </el-table-column>
                <el-table-column label="创建时间">
                    <template #default="scope">
                        {{ formatDate(scope.row.createdat) }}
                    </template>
                </el-table-column>

                <el-table-column label="操作" align="right">
                    <template #default="scope">
                        <el-button size="mini" @click="handleEdit(scope.row)"
                            :disabled="scope.row.role === 'Admin'">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)"
                            :disabled="scope.row.role === 'Admin'">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页器 -->
            <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
                @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
                style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

            <!-- 编辑用户信息弹出框 -->
            <el-dialog title="编辑用户信息" v-model="editDialogVisible">
                <el-form :model="editForm">
                    <el-form-item label="用户类别">
                        <el-select v-model="editForm.role">
                            <el-option label="Admin" value="Admin"></el-option>
                            <el-option label="Staff" value="Staff"></el-option>
                            <el-option label="Owner" value="Owner"></el-option>
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
                <span>确定要删除该用户吗？</span>
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
            selectedRole: '', // 用户类型筛选
            tableData: [],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑用户的信息
            deleteRow: null, // 待删除的行
        };
    },
    mounted() {
        this.$axios.get('/users/list')
            .then(response => {
                console.log(this.tableData);
                this.tableData = response.data;
                console.log(this.tableData);
            })
            .catch(error => {
                console.log(error);
                this.$message.error("获取公告列表失败！");
            });
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据用户名和用户类型进行筛选
                const matchesSearch = !this.search || (data.username && data.username.toLowerCase().includes(this.search.toLowerCase()));
                const matchesRole = !this.selectedRole || data.role === this.selectedRole;
                return matchesSearch && matchesRole;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        },
    },
    methods: {
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        saveEdit() {
            this.$axios.post('/users/adupdate', {
                phonenumber: this.editForm.phonenumber,
                role: this.editForm.role
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("编辑用户信息成功！");
                    this.addDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("编辑用户信息失败！");
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
            this.$axios.post('/users/addelete', {
                phonenumber: this.editForm.phonenumber,
                role: this.editForm.role
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("删除用户信息成功！");
                    this.addDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("删除用户信息失败！");
                }
            }).catch(error => {
                console.log(error);
                this.$message.error("未知错误！");
            });
            this.editDialogVisible = false;
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

.role-admin {
    background-color: #ffcccc;
    padding: 3px 5px;
    border-radius: 3px;
}

.role-staff {
    background-color: #ccffcc;
    padding: 3px 5px;
    border-radius: 3px;
}

.role-owner {
    background-color: #ccccff;
    padding: 3px 5px;
    border-radius: 3px;
}
</style>

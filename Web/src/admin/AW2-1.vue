<template>
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
        <el-table :data="filteredData" style="width: 100%">
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
            <el-table-column prop="createdat" label="创建时间"></el-table-column>

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

        <!-- 编辑用户信息弹出框 -->
        <el-dialog title="编辑用户信息" :visible.sync="editDialogVisible">
            <el-form :model="editForm">
                <el-form-item label="用户姓名">
                    <el-input v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item label="用户密码">
                    <el-input v-model="editForm.password"></el-input>
                </el-form-item>
                <el-form-item label="用户电话">
                    <el-input v-model="editForm.phonenumber"></el-input>
                </el-form-item>
                <el-form-item label="用户邮箱">
                    <el-input v-model="editForm.email"></el-input>
                </el-form-item>
                <el-form-item label="用户住址">
                    <el-input v-model="editForm.address"></el-input>
                </el-form-item>
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
        <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
            <span>确定要删除该用户吗？</span>
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
            selectedRole: '', // 用户类型筛选
            tableData: [
                {
                    userid: 1,
                    username: "",
                    password: "",
                    phonenumber: "16581616",
                    email: "",
                    address: "",
                    role: "Owner",
                    createdat: "2024-08-14 07:28:28"
                },
                {
                    userid: 2,
                    password: "password456",
                    phonenumber: "0987654321",
                    email: "jane.smith@example.com",
                    username: "Jane Smith",
                    address: "456 Elm St",
                    role: "Staff",
                    createdat: "2024-08-14 07:28:28"
                },
                {
                    userid: 3,
                    username: "Admin User",
                    password: "adminpass",
                    phonenumber: "1122334455",
                    email: "admin@example.com",
                    address: "Admin Office",
                    role: "Admin",
                    createdat: "2024-08-14 07:28:28"
                },
                {
                    userid: 10,
                    username: "123",
                    password: "123456",
                    phonenumber: "13545769103",
                    email: "789",
                    address: "456",
                    role: "Owner",
                    createdat: "2024-08-14 07:28:28"
                }
            ],
            currentPage: 1, // 当前页
            pageSize: 5, // 每页显示的数据条数
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑用户的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据用户名和用户类型进行筛选
                const matchesSearch = !this.search || data.username.toLowerCase().includes(this.search.toLowerCase());
                const matchesRole = !this.selectedRole || data.role === this.selectedRole;
                return matchesSearch && matchesRole;
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
            const index = this.tableData.findIndex(item => item.userid === this.editForm.userid);
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
            const index = this.tableData.findIndex(item => item.userid === this.deleteRow.userid);
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

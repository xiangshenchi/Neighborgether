<template>
    <el-card style="margin:10px">
        <div>
            <!-- 搜索框和日期选择器 -->
            <div style="width: 20%; margin-left: 10px; display: inline-block;">
                <el-input v-model="search" placeholder="输入访客名搜索" size="mini" class="search" />
            </div>
            <div style="width: 20%; margin-left: 20px; display: inline-block;">
                <el-date-picker v-model="selectedDate" type="daterange" range-separator="至" start-placeholder="开始日期"
                    end-placeholder="结束日期" size="mini" value-format="yyyy-MM-dd" @change="filterByDate" />
            </div>

            <!-- 公告表格 -->
            <el-table :data="filteredData" style="width: 100%">
                <el-table-column prop="visitid" label="访客ID" width="80px"></el-table-column>
                <el-table-column prop="visitname" label="访客名字" width="120px"></el-table-column>
                <!-- <el-table-column prop="sex" label="性别" width="80px"></el-table-column> -->
                <el-table-column prop="visitphone" label="电话号码" width="150px"></el-table-column>
                <el-table-column prop="visitreason" label="访问理由" width="200px"></el-table-column>
                <el-table-column prop="visitdate" label="访问时间" width="180px">
                    <template #default="scope">
                        {{ formatDate(scope.row.visitdate) }}
                    </template>
                </el-table-column>

                <!-- <el-table-column label="操作" align="right">
                    <template #default="scope">
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column> -->
            </el-table>

            <!-- 分页器 -->
            <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
                @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
                style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

            <!-- 编辑公告信息弹出框 -->
            <el-dialog title="编辑访客信息" :visible.sync="editDialogVisible">
                <el-form :model="editForm">
                    <el-form-item label="访客名字">
                        <el-input v-model="editForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="访问理由">
                        <el-input type="textarea" v-model="editForm.content"></el-input>
                    </el-form-item>
                    <el-form-item label="访问日期">
                        <el-date-picker v-model="editForm.publishDate" type="date" value-format="yyyy-MM-dd" />
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveEdit">保存</el-button>
                </span>
            </el-dialog>

            <!-- 删除确认框 -->
            <!-- <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
                <span>确定要删除该访客信息吗？</span>
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
            search: '', // 搜索关键字
            selectedDate: null, // 选中的日期范围
            tableData: [
                {
                    id: 1,
                    visitid: 'V001',
                    visitname: '张三',
                    visitphone: '13812345678',
                    visitreason: '拜访朋友',
                    visitdate: '2024-08-01 10:30:00'
                },
                {
                    id: 2,
                    visitid: 'V002',
                    visitname: '李四',
                    visitphone: '13987654321',
                    visitreason: '送快递',
                    visitdate: '2024-08-05 14:15:00'
                },
                {
                    id: 3,
                    visitid: 'V003',
                    visitname: '王五',
                    visitphone: '13798765432',
                    visitreason: '物业维修',
                    visitdate: '2024-08-07 09:45:00'
                },
                {
                    id: 4,
                    visitid: 'V004',
                    visitname: '赵六',
                    visitphone: '13612349876',
                    visitreason: '朋友聚会',
                    visitdate: '2024-08-09 16:00:00'
                },
                {
                    id: 5,
                    visitid: 'V005',
                    visitname: '钱七',
                    visitphone: '13565432123',
                    visitreason: '家政服务',
                    visitdate: '2024-08-12 11:20:00'
                },
            ],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑访客的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                // 根据标题和日期进行筛选
                const matchesSearch = !this.search || data.title.toLowerCase().includes(this.search.toLowerCase());
                const publishDate = this.formatDateToYMD(data.publishDate); // 截取日期部分
                const matchesDate = !this.selectedDate ||
                    (publishDate >= this.formatDateToYMD(this.selectedDate[0]) &&
                        publishDate <= this.formatDateToYMD(this.selectedDate[1]));
                return matchesSearch && matchesDate;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    methods: {
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        formatDateToYMD(date) {
            if (!date) return ''; // 如果日期无效，返回空字符串
            const parsedDate = new Date(date);
            if (isNaN(parsedDate)) return ''; // 如果日期格式不正确，返回空字符串
            return parsedDate.toISOString().split('T')[0]; // 正确的日期格式
        },
        filterByDate(dates) {
            this.selectedDate = dates;
        },
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            // 保存编辑后的数据
            const index = this.tableData.findIndex(item => item.id === this.editForm.id);
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
            const index = this.tableData.findIndex(item => item.id === this.deleteRow.id);
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

<template>
    <el-card style="margin:10px">
        <div>
            <div style="display: flex; align-items: center;">
                <!-- 搜索框和日期选择器 -->
                <div style="margin-left: 10px;">
                    <el-input v-model="search" placeholder="输入公告标题搜索" class="search" />
                </div>
                <div style="margin-left: 20px;">
                    <el-date-picker v-model="selectedDate" type="daterange" unlink-panels range-separator="至"
                        start-placeholder="起始时间" end-placeholder="截至时间" @change="filterByDate"
                        @clear="selectedDate = []" />
                </div>
                <!-- 添加公告按钮 -->
                <div style="margin-left: 20px;">
                    <el-button type="primary" @click="showAddDialog()">添加公告</el-button>
                </div>
            </div>

            <!-- 公告表格 -->
            <el-table :data="paginatedData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="announcementid" label="公告ID"></el-table-column>
                <el-table-column prop="title" label="公告标题"></el-table-column>
                <el-table-column prop="content" label="公告内容">
                    <template #default="scope">
                        <div style="overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
                            {{ scope.row.content }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="发布日期">
                    <template #default="scope">
                        {{ formatDate(scope.row.publishdate) }}
                    </template>
                </el-table-column>

                <el-table-column label="操作" align="right">
                    <template #default="scope">
                        <el-button @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页器 -->
            <el-pagination :current-page="currentPage" :page-size="pageSize" :total="filteredData.length"
                @current-change="handlePageChange" layout="total, prev, pager, next, jumper"
                style="margin-top: 20px; text-align: right; margin-left: 10px;"></el-pagination>

            <!-- 添加公告信息弹出框 -->
            <el-dialog title="添加公告信息" v-model="addDialogVisible">
                <el-form :model="addForm">
                    <el-form-item label="公告标题">
                        <el-input v-model="addForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="公告内容">
                        <el-input type="textarea" v-model="addForm.content"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="addDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="addAnnouncement">添加</el-button>
                </span>
            </el-dialog>

            <!-- 编辑公告信息弹出框 -->
            <el-dialog title="编辑公告信息" v-model="editDialogVisible">
                <el-form :model="editForm">
                    <el-form-item label="公告标题">
                        <el-input v-model="editForm.title"></el-input>
                    </el-form-item>
                    <el-form-item label="公告内容">
                        <el-input type="textarea" v-model="editForm.content"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveEdit">保存</el-button>
                </span>
            </el-dialog>

            <!-- 删除确认框 -->
            <el-dialog title="确认删除" v-model="deleteDialogVisible">
                <span>确定要删除该公告吗？</span>
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
            selectedDate: [], // 选中的日期范围
            tabledata: [],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            addDialogVisible: false, // 控制添加弹出框的显示
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            addForm: { // 添加公告的信息
                title: '',
                content: '',
            },
            editForm: {}, // 编辑公告的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tabledata
                .filter(data => {
                    const matchesSearch = !this.search || data.title.toLowerCase().includes(this.search.toLowerCase());
                    const publishDate = new Date(data.publishdate);
                    console.log(publishDate);
                    const matchesDate = !this.selectedDate.length ||
                        (publishDate >= this.selectedDate[0] &&
                            publishDate <= this.selectedDate[1]);
                    return matchesSearch && matchesDate;
                })
                .sort((a, b) => { return new Date(b.publishdate).getTime() - new Date(a.publishdate).getTime() }) // 按发布日期降序排序
        },
        paginatedData() {
            console.log(this.tabledata);
            console.log("selectedDate: ", this.selectedDate);
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    mounted() {
        this.$axios.get('/announcements/list')
            .then(response => {
                console.log(this.tabledata);
                this.tabledata = response.data;
                console.log(this.tabledata);
            })
            .catch(error => {
                console.log(error);
                this.$message.error("获取公告列表失败！");
            });
    },
    methods: {
        formatDateToYMD(date) {
            if (!date) return ''; // 如果日期无效，返回空字符串
            const parsedDate = new Date(date);
            if (isNaN(parsedDate)) return ''; // 如果日期格式不正确，返回空字符串
            return parsedDate.toISOString().split('T')[0]; // 正确的日期格式
        },
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        filterByDate() {
            this.currentPage = 1;
        },
        showAddDialog() {
            // 打开添加弹出框
            console.log(this.addDialogVisible);
            this.addForm = { title: '', content: '', publishdate: '' };
            this.addDialogVisible = true;
            console.log(this.addDialogVisible);
        },
        addAnnouncement() {
            this.$axios.post('/announcements/add', {
                title: this.addForm.title,
                content: this.addForm.content
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("添加公告成功！");
                    this.addDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("添加公告失败！");
                }
            }).catch(error => {
                console.log(error);
                this.$message.error("未知错误！");
            });
            this.addDialogVisible = false;
        },
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            // 保存编辑后的数据
            this.$axios.post('/announcements/edit', {
                announcementid: this.editForm.announcementid,
                title: this.editForm.title,
                content: this.editForm.content,
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("编辑公告成功！");
                    this.editDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("编辑公告失败！");
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
            this.$axios.delete('/announcements/delete', {
                params: { announcementid: this.deleteRow.announcementid }
            }).then(response => {
                if (response.data === true) {
                    this.$message.success("删除公告成功！");
                    this.deleteDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("删除公告失败！");
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

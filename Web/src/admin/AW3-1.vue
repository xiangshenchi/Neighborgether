<template>
    <div>
        <!-- 搜索框和日期选择器 -->
        <div style="width: 20%; margin-left: 10px; display: inline-block;">
            <el-input v-model="search" placeholder="输入公告标题搜索" size="mini" class="search" />
        </div>
        <div style="width: 20%; margin-left: 20px; display: inline-block;">
            <el-date-picker v-model="selectedDate" type="daterange" range-separator="至" start-placeholder="开始日期"
                end-placeholder="结束日期" size="mini" value-format="yyyy-MM-dd" @change="filterByDate" />
        </div>

        <!-- 公告表格 -->
        <el-table :data="filteredData" style="width: 100%">
            <el-table-column prop="id" label="公告ID" width="80px"></el-table-column>
            <el-table-column prop="title" label="公告标题" width="200px"></el-table-column>
            <el-table-column prop="content" label="公告内容"></el-table-column>
            <el-table-column prop="publishDate" label="发布日期" width="180px">
                <template #default="scope">
                    {{ formatDate(scope.row.publishDate) }}
                </template>
            </el-table-column>

            <el-table-column label="操作" align="right">
                <template #default="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 编辑公告信息弹出框 -->
        <el-dialog title="编辑公告信息" :visible.sync="editDialogVisible">
            <el-form :model="editForm">
                <el-form-item label="公告标题">
                    <el-input v-model="editForm.title"></el-input>
                </el-form-item>
                <el-form-item label="公告内容">
                    <el-input type="textarea" v-model="editForm.content"></el-input>
                </el-form-item>
                <el-form-item label="发布日期">
                    <el-date-picker v-model="editForm.publishDate" type="date" value-format="yyyy-MM-dd" />
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="saveEdit">保存</el-button>
            </span>
        </el-dialog>

        <!-- 删除确认框 -->
        <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
            <span>确定要删除该公告吗？</span>
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
            selectedDate: null, // 选中的日期范围
            tableData: [
                {
                    id: 1,
                    title: "系统升级公告",
                    content: "系统将在2024年8月20日进行升级，预计持续2小时。",
                    publishDate: "2024-08-18 10:30:00"
                },
                {
                    id: 2,
                    title: "水电维护通知",
                    content: "小区将于2024年8月22日进行水电检修，请提前做好准备。",
                    publishDate: "2024-08-15 14:45:00"
                },
                {
                    id: 3,
                    title: "社区活动通知",
                    content: "2024年8月25日将举行社区活动，欢迎大家参加。",
                    publishDate: "2024-08-10 08:00:00"
                }
            ],
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑公告的信息
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
        }
    },
    methods: {
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        formatDateToYMD(date) {
            // 仅返回日期部分，精确到天
            return new Date(date).toISOString().split('T')[0];
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

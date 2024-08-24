<template>
    <el-card style="margin:10px">
        <div>
            <!-- 搜索框和过滤器 -->
            <div style="width: 20%; margin-left: 10px; display: inline-block;">
                <el-input v-model="search" placeholder="输入房间号搜索" size="mini" class="search" />
            </div>
            <div style="width: 12%; margin-left: 20px; display: inline-block;">
                <el-select v-model="selectedBuilding" placeholder="选择楼栋" size="mini" clearable>
                    <el-option label="所有楼栋" value=""></el-option>
                    <el-option label="A" value="A"></el-option>
                    <el-option label="B" value="B"></el-option>
                    <el-option label="C" value="C"></el-option>
                </el-select>
            </div>
            <div style="width: 12%; margin-left: 20px; display: inline-block;">
                <el-select v-model="selectedUnit" placeholder="选择单元" size="mini" clearable>
                    <el-option label="所有单元" value=""></el-option>
                    <el-option label="1" value="1"></el-option>
                    <el-option label="2" value="2"></el-option>
                    <el-option label="3" value="3"></el-option>
                    <el-option label="4" value="4"></el-option>
                </el-select>
            </div>

            <!-- 房源信息表格 -->
            <el-table :data="paginatedData" style="width: 100%" :header-cell-style="{ 'text-align': 'center' }"
            :cell-style="{ 'text-align': 'center' }">
                <el-table-column prop="propertyid" label="房源ID" width="80px"></el-table-column>
                <el-table-column prop="userid" label="用户ID" width="80px"></el-table-column>
                <el-table-column prop="roomnumber" label="房间号" width="100px"></el-table-column>
                <el-table-column prop="buildingnumber" label="楼栋" width="100px"></el-table-column>
                <el-table-column prop="unitnumber" label="单元" width="100px"></el-table-column>
                <el-table-column prop="area" label="面积(㎡)" width="160px"></el-table-column>

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

            <!-- 编辑房源信息弹出框 -->
            <el-dialog title="编辑房源信息" v-model="editDialogVisible">
                <el-form :model="editForm">
                    <el-form-item label="用户ID">
                        <el-input v-model="editForm.userid"></el-input>
                    </el-form-item>
                    <el-form-item label="房间号">
                        <el-input v-model="editForm.roomnumber"></el-input>
                    </el-form-item>
                    <el-form-item label="楼栋">
                        <el-input v-model="editForm.buildingnumber"></el-input>
                    </el-form-item>
                    <el-form-item label="单元">
                        <el-input v-model="editForm.unitnumber"></el-input>
                    </el-form-item>
                    <el-form-item label="面积(㎡)">
                        <el-input v-model="editForm.area"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editDialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveEdit">保存</el-button>
                </span>
            </el-dialog>

            <!-- 删除确认框 -->
            <el-dialog title="确认删除" v-model="deleteDialogVisible">
                <span>确定要删除该房源信息吗？</span>
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
            selectedBuilding: '', // 楼栋筛选
            selectedUnit: '', // 单元筛选
            tableData: [],
            currentPage: 1, // 当前页
            pageSize: 10, // 每页显示的数据条数
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑房源的信息
            deleteRow: null, // 待删除的行
        };
    },
    computed: {
        filteredData() {
            return this.tableData.filter(data => {
                const matchesSearch = !this.search || data.roomnumber.includes(this.search);
                const matchesBuilding = !this.selectedBuilding || data.buildingnumber === this.selectedBuilding;
                const matchesUnit = !this.selectedUnit || data.unitnumber === this.selectedUnit;
                return matchesSearch && matchesBuilding && matchesUnit;
            });
        },
        paginatedData() {
            const start = (this.currentPage - 1) * this.pageSize;
            const end = start + this.pageSize;
            return this.filteredData.slice(start, end);
        }
    },
    mounted() {
        this.$axios.get('/propertyinfo/list')
            .then(response => {
                this.tableData = response.data;
            })
            .catch(error => {
                console.log(error);
                this.$message.error("获取房源列表失败！");
            });
    },
    methods: {
        handleEdit(row) {
            // 打开编辑弹出框并将选中的行数据赋值给编辑表单
            this.editForm = { ...row };
            this.editDialogVisible = true;
        },
        saveEdit() {
            this.$axios.post('/propertyinfo/edit', {
                propertyid: this.editForm.propertyid,
                roomnumber: this.editForm.roomnumber,
                buildingnumber: this.editForm.buildingnumber,
                unitnumber: this.editForm.unitnumber,
                area: this.editForm.area
            }).then(response => {
                if (response.data.status === '1') {
                    this.$message.success("编辑房源成功！");
                    this.editDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("编辑房源失败！");
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
            this.$axios.delete('/propertyinfo/delete', {
                params: { propertyid: this.deleteRow.propertyid } 
            }).then(response => {
                if (response.data===true) {
                    this.$message.success("删除房源成功！");
                    this.deleteDialogVisible = false;
                    window.location.reload();
                }
                else {
                    this.$message.error("删除房源失败！");
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

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
                    <el-option label="1" value="1"></el-option>
                    <el-option label="2" value="2"></el-option>
                    <el-option label="3" value="3"></el-option>
                    <el-option label="4" value="4"></el-option>
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

            <!-- 物业信息表格 -->
            <el-table :data="filteredData" style="width: 100%">
                <el-table-column prop="propertyid" label="物业ID" width="80px"></el-table-column>
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

            <!-- 编辑物业信息弹出框 -->
            <el-dialog title="编辑物业信息" :visible.sync="editDialogVisible">
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
            <el-dialog title="确认删除" :visible.sync="deleteDialogVisible">
                <span>确定要删除该物业信息吗？</span>
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
            tableData: [
                {
                    propertyid: 1,
                    userid: 1,
                    roomnumber: "101",
                    buildingnumber: "A",
                    unitnumber: "1",
                    area: 120.5
                },
                {
                    propertyid: 2,
                    userid: 2,
                    roomnumber: "202",
                    buildingnumber: "B",
                    unitnumber: "2",
                    area: 98.3
                }
            ],
            editDialogVisible: false, // 控制编辑弹出框的显示
            deleteDialogVisible: false, // 控制删除确认框的显示
            editForm: {}, // 编辑物业的信息
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
            const index = this.tableData.findIndex(item => item.propertyid === this.editForm.propertyid);
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
            const index = this.tableData.findIndex(item => item.propertyid === this.deleteRow.propertyid);
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

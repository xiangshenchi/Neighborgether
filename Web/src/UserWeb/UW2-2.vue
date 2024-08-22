<template>
    <div class="container">
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h2>房产信息</h2>
            </el-card>
        </div>
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <el-descriptions class="margin-top" title="基本房产信息" :column="3" :size="size">
                    <el-descriptions-item label="业主">{{ formData.owner }}</el-descriptions-item>
                    <el-descriptions-item label="单元">{{ formData.unit }}</el-descriptions-item>
                    <el-descriptions-item label="楼栋">{{ formData.building }}</el-descriptions-item>
                    <el-descriptions-item label="房间号">{{ formData.room }}</el-descriptions-item>
                    <el-descriptions-item label="面积">{{ formData.area }} m²</el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>

        <div style="margin-top: 70px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <el-descriptions class="margin-top" title="房产信息更新" :column="3" :size="size">
                    <template #extra>
                        <el-button type="primary" size="small" @click="openDialog">修改</el-button>
                    </template>
                    <el-descriptions-item label="业主">{{ formData.owner }}</el-descriptions-item>
                    <el-descriptions-item label="单元">{{ formData.unit }}</el-descriptions-item>
                    <el-descriptions-item label="楼栋">{{ formData.building }}</el-descriptions-item>
                    <el-descriptions-item label="房间号">{{ formData.room }}</el-descriptions-item>
                    <el-descriptions-item label="面积">{{ formData.area }} m²</el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>

        <!-- 修改房产信息的对话框 -->
        <el-dialog title="修改房产信息" :visible.sync="dialogVisible">
            <el-form :model="formData">
                <el-form-item label="业主">
                    <el-input v-model="formData.owner"></el-input>
                </el-form-item>
                <el-form-item label="单元">
                    <el-input v-model="formData.unit"></el-input>
                </el-form-item>
                <el-form-item label="楼栋">
                    <el-input v-model="formData.building"></el-input>
                </el-form-item>
                <el-form-item label="房间号">
                    <el-input v-model="formData.room"></el-input>
                </el-form-item>
                <el-form-item label="面积">
                    <el-input v-model="formData.area"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="submitUpdate">保存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            size: '',
            dialogVisible: false, // 控制对话框的显示和隐藏
            formData: {
                owner: 'kooriookami',   // 业主
                unit: '18100000000',    // 单元
                building: 'xxx@qq.com',  // 楼栋
                room: 'admin',          // 房间号
                area: 74                // 面积
            }
        };
    },
    methods: {
        openDialog() {
            // 打开修改对话框
            this.dialogVisible = true;
        },
        submitUpdate() {
            // 1. 提交修改后的房产信息到后端
            axios.post('http://localhost:8090/api/updateProperty', this.formData)
                .then(response => {
                    this.$message.success('房产信息更新成功');
                    this.dialogVisible = false;  // 关闭对话框
                })
                .catch(error => {
                    this.$message.error('更新失败，请稍后重试');
                    console.error(error);
                });
        }
    }
};
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    gap: 10px;
    width: 100%;
}
</style>

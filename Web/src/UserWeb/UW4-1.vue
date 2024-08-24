<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>我要报修</h2>
        </el-card>
        <el-card style="margin: 10px;">
            <div style=" margin-top: 10px;">
                <div class="block">
                    <el-date-picker v-model="date" type="date" placeholder="选择报修日期">
                    </el-date-picker>
                </div>
            </div>
            <div style="margin-top: 10px;">
                <el-input type="textarea" :rows="4" placeholder="请输入报修内容，包括地点、设备名称等信息。" v-model="textarea">
                </el-input>
            </div>
            <div style="margin-top: 10px;">
                <el-button v-slot="extra" type="primary" size="small" @click="submit()">提 交</el-button>
            </div>
        </el-card>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            textarea: '', // 报修内容
            date: '',     // 选择的日期
        };
    },
    methods: {
        submit() {
            // 1. 检查是否填写完整
            if (!this.textarea || !this.date) {
                this.$message.error('请填写报修内容和选择日期');
                return;
            }

            // 2. 准备请求数据
            const formData = {
                repairContent: this.textarea,   // 报修内容
                repairDate: this.date,          // 报修日期
                userId: 1,                      // 假设这里有当前用户 ID，可以从 store 获取用户信息
            };

            // 3. 提交到后端接口
            axios.post('http://localhost:8090/demand/add', formData)  //API接口需修改
                .then(response => {
                    // 4. 提交成功后提示
                    this.$message.success('报修提交成功');
                    // 清空输入内容
                    this.textarea = '';
                    this.date = '';
                })
                .catch(error => {
                    this.$message.error('提交失败，请稍后重试');
                    console.error(error);
                });
        }
    }
};
</script>
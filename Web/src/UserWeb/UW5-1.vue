<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;">
        <el-card style="margin: 10px;">
            <h2>我要投诉</h2>
        </el-card>
        <el-card style="margin: 10px;">
            <div style="margin-top: 10px;">
                <el-input type="textarea" :rows="4" placeholder="请输入投诉内容" v-model="textarea">
                </el-input>
            </div>
            <div style="margin-top: 10px;">
                <el-button v-slot="extra" type="primary" @click="submit()">提交投诉</el-button>
            </div>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            textarea: '', // 报修内容
        };
    },
    methods: {
        submit() {
            // 1. 检查是否填写完整
            if (this.textarea.trim() == '') {
                this.$message.error('请填写投诉内容');
                return;
            }
            // 2. 提交到后端接口
            this.$axios.post('/demand/addC', {
                phonenumber: this.$store.getters.userInfo.phonenumber,
                demand: {
                    demandmsg: this.textarea
                }
            }).then(res => {
                if (res.data.status == 1) {
                    this.$message.success('投诉提交成功');
                    this.textarea = '';
                }
                else{
                    this.$message.error('提交失败，请稍后重试');
                }
            }).catch(error => {
                this.$message.error('出错了，请稍后重试或联系管理员');
                console.error(error);
            });
        }
    }
};
</script>
<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>账号管理</h2>
        </el-card>
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="基本账号信息" :column="3" :size="size">
                <el-descriptions-item label="用户名">{{ form.username }}</el-descriptions-item>
                <el-descriptions-item label="手机号">{{ form.phonenumber }}</el-descriptions-item>
                <el-descriptions-item label="邮箱">{{ form.email }}</el-descriptions-item>
                <el-descriptions-item label="地址">{{ form.address }}</el-descriptions-item>
                <el-descriptions-item label="创建时间">{{ this.formatDate(form.createdat) }}</el-descriptions-item>
                <el-descriptions-item label="角色">
                    <el-tag size="small">{{ form.role }}</el-tag>
                </el-descriptions-item>
            </el-descriptions>
        </el-card>
        <el-card style="margin: 10px;">
            <h4 style="margin: 5px;">修改信息</h4>
            <el-form-item label="地址" :label-position="itemLabelPosition">
                <el-input v-model="formSub.address" />
            </el-form-item>
            <el-form-item label="邮箱" :label-position="itemLabelPosition">
                <el-input v-model="formSub.email" />
            </el-form-item>
            <el-form-item label="用户名" :label-position="itemLabelPosition">
                <el-input v-model="formSub.username" />
            </el-form-item>
            <el-button type="primary" @click="confirm()" color="#1EB71E">提交</el-button>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            size: '',
            form: {
                username: '',
                phonenumber: '',
                email: '',
                role: '',
                createdat: '',
                address: '',
            },
            formSub: {
                address: '',
                username: '',
                email: '',
                phonenumber: this.$store.getters.userInfo.phonenumber,
            }
        };
    },
    mounted() {
        this.$axios.get('/users/listU', {
            params: {
                phonenumber: this.$store.getters.userInfo.phonenumber
            }
        }).then(res => {
            this.form = res.data[0];
            this.formSub.address = this.form.address;
            this.formSub.username = this.form.username;
            this.formSub.email = this.form.email;
        }).catch(err => {
            this.$message.error('获取用户信息失败,请稍后再试或联系管理员');
        });
    },
    methods: {
        confirm() {
            this.$confirm('确认修改信息吗？').then(() => {
                this.onSubmit();
            }).catch(() => {
                this.$message.info('已取消');
            });
        },
        formatDate(date) {
            // 格式化日期时间，显示精确到小时和分钟
            const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
            return new Date(date).toLocaleString('zh-CN', options);
        },
        onSubmit() {
            this.$axios.post('/users/update', {
                phonenumber: this.formSub.phonenumber,
                address: this.formSub.address,
                email: this.formSub.email,
                username: this.formSub.username,
            }).then(res => {
                this.$message.success('修改成功,将于1s后刷新页面');
                setTimeout(() => {
                    window.location.reload();
                }, 1000);
            }).catch(err => {
                this.$message.error('修改失败');
            });
        }
    }

}
</script>

<style scoped></style>
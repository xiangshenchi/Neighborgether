<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>账号管理</h2>
        </el-card >
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="基本账号信息" :column="3" :size="size">
                <el-descriptions-item label="用户名">{{ form.username }}</el-descriptions-item>
                <el-descriptions-item label="手机号">{{ form.phonenumber }}</el-descriptions-item>
                <el-descriptions-item label="邮箱">{{ form.email }}</el-descriptions-item>
                <!-- <el-descriptions-item label="角色">admin</el-descriptions-item> -->
                <el-descriptions-item label="创建时间">{{ form.createdat }}</el-descriptions-item>
                <el-descriptions-item label="角色">
                    <el-tag size="small">{{ form.role }}</el-tag>
                </el-descriptions-item>
            </el-descriptions>
        </el-card>
        <el-card style="margin: 10px;">
            
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
            console.log(this.form);
            console.log(res.data[0]);
            console.log(this.form.role);
        });
    }
}
</script>

<style scoped></style>
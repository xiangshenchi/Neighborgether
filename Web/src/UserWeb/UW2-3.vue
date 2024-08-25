<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>车辆管理</h2>
        </el-card>

        <!-- 基本车辆信息 -->
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="基本车辆信息" :column="1" :size="size">
                <el-descriptions-item label="车牌号">{{ form.licenseplate }}</el-descriptions-item>
                <el-descriptions-item label="车辆类型">{{ form.vehicletype }}</el-descriptions-item>
                <el-descriptions-item label="登记时间">{{ this.formatDate(form.registrationdate) }}</el-descriptions-item>
            </el-descriptions>
        </el-card>

        <!-- 账号信息更新 -->
        <el-card style="margin: 10px;">
            <h4 style="margin: 5px;">修改信息</h4>
            <el-form-item label="车牌号" :label-position="itemLabelPosition">
                <el-input v-model="formSub.licenseplate" />
            </el-form-item>
            <el-form-item label="车辆类型" :label-position="itemLabelPosition">
                <el-input v-model="formSub.vehicletype" />
            </el-form-item>
            <el-button type="primary" @click="confirm()" color="#1EB71E" :disabled="butisdisabled">提交</el-button>
        </el-card>
    </div>
</template>

<script>

export default {
    data() {
        return {
            size: '',
            form: {
                owner: '',
                licenseplate: '',
                vehicletype: '',
                registrationdate: ''
            },
            butisdisabled: false,
            formSub: {
                owner: '',
                licenseplate: '',
                vehicletype: '',
            },
        };
    },
    mounted() {
        console.log(this.$store.getters.userInfo.phonenumber);
        this.$axios.get('/vehicles/listC', {
            params: {
                phonenumber: this.$store.getters.userInfo.phonenumber
            }
        }).then(res => {
            if (res.data.length == 0) {
                this.$message.error('暂无车辆信息');
                return;
            }
            this.form = res.data[0];
            this.formSub.licenseplate = this.form.licenseplate;
            this.formSub.vehicletype = this.form.vehicletype;
        }).catch(err => {
            this.$message.error('获取车辆信息失败,请稍后再试或者联系管理员');
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
            this.$axios.post('/vehicles/update', {
                phonenumber: this.$store.getters.userInfo.phonenumber,
                vehicles: {
                    licenseplate: this.formSub.licenseplate,
                    vehicletype: this.formSub.vehicletype
                }
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

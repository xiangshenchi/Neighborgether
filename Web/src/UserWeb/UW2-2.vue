<template>
    <div class="web-container" style="display:flex;width:100%;height:100%;flex-direction: column;flex:0,0,1">
        <el-card style="margin: 10px;">
            <h2>房产信息</h2>
        </el-card>
        <el-card style="margin: 10px;">
            <el-descriptions class="margin-top" title="基本房产信息" :column="3" :size="size">
                <el-descriptions-item label="单元">{{ form.unit }}</el-descriptions-item>
                <el-descriptions-item label="楼栋">{{ form.building }}</el-descriptions-item>
                <el-descriptions-item label="房间号">{{ form.room }}</el-descriptions-item>
                <el-descriptions-item label="面积">{{ form.area }} m²</el-descriptions-item>
            </el-descriptions>
        </el-card>
        <el-card style="margin: 10px;">
            <h4 style="margin: 5px;">修改信息</h4>
            <el-form-item label="单元" :label-position="itemLabelPosition">
                <el-input v-model="formSub.unit" />
            </el-form-item>
            <el-form-item label="楼栋" :label-position="itemLabelPosition">
                <el-input v-model="formSub.building" />
            </el-form-item>
            <el-form-item label="楼房间号" :label-position="itemLabelPosition">
                <el-input v-model="formSub.room" />
            </el-form-item>
            <el-form-item label="面积" :label-position="itemLabelPosition">
                <el-input v-model="formSub.area" />
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
            dialogVisible: false,
            form: {
                unit: '',
                building: '',
                room: '',
                area: ''
            },
            formSub: {
                unit: '',
                building: '',
                room: '',
                area: ''
            }
        };
    },
    methods: {
        mounted() {
            this.$axios.get('/users/listU', {
                params: {
                    phonenumber: this.$store.getters.userInfo.phonenumber
                }
            }).then(res => {
                this.form = res.data[0];
                this.formSub = this.form;
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
            onSubmit() {
                this.$axios.post('/propertyinfo/update', {
                    phonenumber: this.formSub.phonenumber,
                    unit: this.formSub.unit,
                    building: this.formSub.building,
                    room: this.formSub.room,
                    area: this.formSub.area
                }).then(res => {
                    this.$message.success('修改成功');
                    javascript: location.reload(true);
                }).catch(err => {
                    this.$message.error('修改失败');
                });
            }
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

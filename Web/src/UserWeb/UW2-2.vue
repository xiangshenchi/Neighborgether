<template>
    <div class="container">
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h2>房产信息</h2>
            </el-card>
        </div>
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h3>基本信息</h3>
                <span>户型：{{ housetype }}</span>
                <span>面积：{{ area }}</span>
                <span>单元：{{ unit }}</span>
                <span>楼栋：{{building }}</span>
                <span>房号：{{ roomnumber }}</span>
                <span>户主：{{ househost }}</span>
                <span>入住时间：{{ checkintime }}</span>
            </el-card>
        </div>
        <div style="margin-top: 100px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <span><img src="" alt="户型图"></span>
            </el-card>
            
        </div>
    </div>
</template>
<script>
</script>
<style scoped>
.container {
    display: flex;
    flex-direction: column;
    gap: 10px;
    width: 100%;
}
</style>
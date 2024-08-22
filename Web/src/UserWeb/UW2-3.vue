<template>
    <div class="container">
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h2>车辆信息</h2>
            </el-card>
        </div>
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h3>基本信息</h3>
                <span>车主：{{ carhost }}</span>
                <span>车牌号：{{ carnumber }}</span>
                <span>车辆类型：{{ cartype }}</span>
                <span>车辆品牌：{{carbrand }}</span>
                <span>登记时间：{{ regtime }}</span>
                <span>户主：{{ househost }}</span>
            </el-card>
        </div>
        <div style="margin-top: 100px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <span><img src="" alt="小区停车位分布图"></span>
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
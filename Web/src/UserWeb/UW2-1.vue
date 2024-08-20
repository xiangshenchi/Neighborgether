<template>
    <div class="container">
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h2>账号管理</h2>
            </el-card>
        </div>
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h3>基本信息</h3>
                <span>姓名：{{ name }}</span>
                <span>性别：{{ gender }}</span>
                <span>年龄：{{ age }}</span>
                <span>邮箱：{{ email }}</span>
               
            </el-card>
        </div>
        <div style="margin-top: 100px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <span><img src="" alt="用户图片"></span>
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
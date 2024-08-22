<template>
    <div class="container">
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <h2>账号管理</h2>
            </el-card>
        </div>
        <div style="margin-top: 10px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <el-descriptions class="margin-top" title="基本账号信息" :column="3" :size="size">
                    <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
                    <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                    <el-descriptions-item label="邮箱">xxx@qq.com</el-descriptions-item>
                    <!-- <el-descriptions-item label="角色">admin</el-descriptions-item> -->
                    <el-descriptions-item label="创建时间">2024-08-22 00:00:00</el-descriptions-item>
                    <el-descriptions-item label="角色">
                        <el-tag size="small">admin</el-tag>
                    </el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>

        <div style="margin-top: 70px;margin-left: 10px;margin-right: 10px; width: 100%;height: 80px;">
            <el-card>
                <el-descriptions class="margin-top" title="账号信息更新" :column="3" :size="size">
                    <template #extra>
                        <el-button type="primary" size="small">修改</el-button>
                    </template>
                    <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
                    <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                    <el-descriptions-item label="邮箱">xxx@qq.com</el-descriptions-item>
                    <!-- <el-descriptions-item label="角色">admin</el-descriptions-item> -->
                    <el-descriptions-item label="创建时间">2024-08-22 00:00:00</el-descriptions-item>
                    <el-descriptions-item label="角色">
                        <el-tag size="small">admin</el-tag>
                    </el-descriptions-item>
                </el-descriptions>
            </el-card>
        </div>
    </div>

</template>

<script>
export default {
    data() {
        return {
            size: ''
        };
    }
}
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    gap: 10px;
    width: 100%;
}
</style>
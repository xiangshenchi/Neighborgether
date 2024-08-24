<template>
    <div style="display: flex; flex-direction: column; height: 100vh; width: 100vw;">
        <div
            style="height: 10%; background-color: #f5f5f5; display: flex; align-items: center; justify-content:space-between;">
            <img src="../../public/logo.jpg" alt="logo" style="height:100%; width:height;margin-left: 20px;">
            <h1 class="biaoTi" style="font-size: 30px;">Welcome to Neighborgether</h1>
            <el-button type="primary" color="#1EB71E" @click="$router.push('/login')">登录</el-button>
        </div>
        <div style="flex: 1;">
            <el-carousel :interval="5000" height="100%">
                <el-carousel-item v-for="item in 3" :key="item">
                    <img :src="getImageUrl(item)" alt="糟糕！图片被小猫吃掉了！:<" class="carousel-img">
                </el-carousel-item>
            </el-carousel>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        getImageUrl(item) {
            // 根据项目的构建工具，确保路径正确
            return String(`/src/components/pic/${item}.jpg`);
        }
    }
};
</script>

<style scoped>
.el-carousel {
    height: 100%;
}

.el-carousel__item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}

.el-carousel__item h3 {
    color: #475669;
    opacity: 0.75;
    margin: 0;
    text-align: center;
    font-size: 2em;
}

.carousel-img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    /* 或者使用 cover */
}
</style>

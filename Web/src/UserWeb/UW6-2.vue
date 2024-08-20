<style scoped>
.doc {
    overflow-y: auto;
    /* 确保页面可以垂直滚动 */
}

.icon {
    display: flex;
    justify-content: center;
    align-items: center;
}

.content {
    margin-left: 50px;
    /* 或者使用 margin-left */
    margin-right: 50px;
}

.footer {
    position: relative;
    /* height: 100%; */
    bottom: 0%;
    width: 100%;
    text-align: center;
    /* background-color: white; */
    /* 确保背景色 */
    padding: 10px 0;
    /* 添加一些内边距 */
    box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
    /* 添加阴影以区分内容和 footer */
}
</style>

<template>
    <div class="doc">
        <div class="icon">
            <img src="/logo.jpg" width="250" alt="糟糕！图片被偷走了:<">
        </div>

        <div class="content">
            <h2 style="text-align: center;">
                邻聚 · 智慧物业 · 绿色科普</h2>
            <hr>
            <div style="text-align: center;">
                <img src="/public/GreenEco.png" width="400" alt="糟糕！图片被偷走了:<"></div>
            
            <p>绿色科普站点：<br>
            <ul>
                <li>绿色和平网站点：<a href="https://www.greenpeace.org.cn/biodiversity/">绿色和平 GREENPEACE</a></li>
                <li>地球科普网站点：<a href="https://www.china-shj.org.cn/">地球科普网</a></li>
                <li>环境生态网站点：<a href="http://www.eedu.org.cn/Index.shtml">环境生态网</a></li>

            </ul>

            </p>
        </div>

        <div class="footer">
            <p>copyright© 2024 · 邻聚 · Neighborgether</p>
        </div>

    </div>
</template>
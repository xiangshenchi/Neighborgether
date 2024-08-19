<style>
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
    bottom: 6%;
    width: 100%;
    text-align: center;
}
</style>

<template>
    <div class="doc">
        <div class="icon">
            <img src="/logo.jpg" width="250" alt="糟糕！图片被偷走了:<">
        </div>

        <div class="content">
            <h2 style="text-align: center;">
                邻聚 · 智慧物业 · 联系方式</h2>
            <hr>

            <h2>联系我们</h2>
            <p>Github仓库：<a href="https://github.com/xiangshenchi/Neighborgether">邻聚Neighborgether</a></p>
        </div>

        <div class="footer">
            <p>copyright© 2024 · 邻聚 · Neighborgether</p>
        </div>

    </div>
</template>
<template>
    <div style=" width: 90%; margin-top: 20px; margin-left: 20px;">
        <!-- 上方两张图：访客和用户对比 & 缴费状态 -->
        <div style="display: flex; justify-content: space-between;">
            <!-- 访客和用户对比图 -->
            <div ref="visitorUserChart" style="width: 48%; height: 400px;"></div>
            <!-- 缴费状态图 -->
            <div ref="paymentStatusChart" style="width: 48%; height: 400px;"></div>
        </div>

        <!-- 下方图：保修处理状态 -->
        <div ref="warrantyStatusChart" style="width: 100%; height: 400px; margin-top: 20px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';

export default {
    name: 'ThreePieCharts',
    mounted() {
        this.fetchData();
    },
    methods: {
        initVisitorUserChart(data) {
            const chart = echarts.init(this.$refs.visitorUserChart);
            const option = {
                title: {
                    text: '访客数量和用户数量对比',
                    left: 'center',
                },
                tooltip: {
                    trigger: 'item',
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                color: ['#5470C6', '#91CC75'], // 自定义颜色
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '50%',
                        data: data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)',
                            },
                        },
                    },
                ],
            };
            chart.setOption(option);
            window.addEventListener('resize', chart.resize);
        },
        initPaymentStatusChart(data) {
            const chart = echarts.init(this.$refs.paymentStatusChart);
            const option = {
                title: {
                    text: '缴费状态',
                    left: 'center',
                },
                tooltip: {
                    trigger: 'item',
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                series: [
                    {
                        name: '缴费状态',
                        type: 'pie',
                        radius: ['40%', '70%'], // 环状图
                        data: data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)',
                            },
                        },
                    },
                ],
            };
            chart.setOption(option);
            window.addEventListener('resize', chart.resize);
        },
        initWarrantyStatusChart(data) {
            const chart = echarts.init(this.$refs.warrantyStatusChart);
            const option = {
                title: {
                    text: '报修处理状态',
                    left: 'center',
                },
                tooltip: {
                    trigger: 'item',
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                },
                color: ['#EE6666', '#73A373'], // 不同的颜色
                series: [
                    {
                        name: '报修状态',
                        type: 'pie',
                        radius: '50%',
                        data: data,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)',
                            },
                        },
                    },
                ],
            };
            chart.setOption(option);
            window.addEventListener('resize', chart.resize);
        },
        fetchData() {
            // 初始数据
            const visitorUserData = [
                { value: 100, name: '访客数量' },
                { value: 80, name: '用户数量' },
            ];
            const paymentStatusData = [
                { value: 50, name: '已缴费' },
                { value: 30, name: '未缴费' },
            ];
            const warrantyStatusData = [
                { value: 20, name: '已处理' },
                { value: 10, name: '未处理' },
            ];

            // 初始化图表
            this.initVisitorUserChart(visitorUserData);
            this.initPaymentStatusChart(paymentStatusData);
            this.initWarrantyStatusChart(warrantyStatusData);

            // 这里可以通过 axios 请求后端数据并更新图表
        },
    },
    beforeUnmount() {
        window.removeEventListener('resize', this.initVisitorUserChart);
        window.removeEventListener('resize', this.initPaymentStatusChart);
        window.removeEventListener('resize', this.initWarrantyStatusChart);
    },
};
</script>

<style scoped>
/* 让图表容器有一定的大小 */
</style>
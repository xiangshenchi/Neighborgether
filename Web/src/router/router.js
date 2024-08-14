

import { createRouter, createWebHistory } from 'vue-router';
import register from '../components/register.vue'
import main from '../components/main.vue'
import forgetPW from '@/components/forgetPW.vue';
import visitor from '../components/visitor.vue';

const routes=[
    {path: '/', name: 'main', component: main},
    {path: '/register', name:'register', component: register},
    {path: '/forgetPW', name: 'forgetPW', component: forgetPW},
    {path: '/visitor' , name: 'visitor', component: visitor }
]

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
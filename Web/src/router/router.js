

import { createRouter, createWebHistory } from 'vue-router';
import register from '../components/register.vue'
import login from '../components/login.vue'
import forgetPW from '@/components/forgetPW.vue';
import visitor from '../components/visitor.vue';
import UM from '../UserWeb/UM.vue';
import UW1 from '../UserWeb/UW1.vue';
import UW2_1 from '../UserWeb/UW2-1.vue';
import UW2_2 from '../UserWeb/UW2-2.vue';
import UW2_3 from '../UserWeb/UW2-3.vue';
import UW3_1 from '../UserWeb/UW3-1.vue';
import UW3_2 from '../UserWeb/UW3-2.vue';
import UW3_3 from '../UserWeb/UW3-3.vue';
import UW4_1 from '../UserWeb/UW4-1.vue';
import UW4_2 from '../UserWeb/UW4-2.vue';
import UW5_1 from '../UserWeb/UW5-1.vue';
import UW5_2 from '../UserWeb/UW5-2.vue';
import UW6_1 from '../UserWeb/UW6-1.vue';
import UW6_2 from '../UserWeb/UW6-2.vue';
import UW7 from '../UserWeb/UW7.vue';
import UW8 from '../UserWeb/UW8.vue';
import store from '../store'
import welcome from '../components/welcome.vue'

const routes = [
  { path: '/', redirect: '/welcome' },
  { path: '/:pathMatch(.*)*', redirect: '/welcome' },
  { path: '/login', name: 'login', component: login, meta: { requiresAuth: false } },
  { path: '/register', name: 'register', component: register },
  { path: '/forgetPW', name: 'forgetPW', component: forgetPW },
  { path: '/visitor', name: 'visitor', component: visitor },
  { path: '/welcome', name: 'welcome', component: welcome },
  {
    path: '/UM', name: 'UM', component: UM, meta: { requiresAuth: true },
    children: [
      { path: 'UW1', name: 'UW1', component: UW1 },
      { path: 'UW2-1', name: 'UW2-1', component: UW2_1 },
      { path: 'UW2-2', name: 'UW2-2', component: UW2_2 },
      { path: 'UW2-3', name: 'UW2-3', component: UW2_3 },
      { path: 'UW3-1', name: 'UW3-1', component: UW3_1 },
      { path: 'UW3-2', name: 'UW3-2', component: UW3_2 },
      { path: 'UW3-3', name: 'UW3-3', component: UW3_3 },
      { path: 'UW4-1', name: 'UW4-1', component: UW4_1 },
      { path: 'UW4-2', name: 'UW4-2', component: UW4_2 },
      { path: 'UW5-1', name: 'UW5-1', component: UW5_1 },
      { path: 'UW5-2', name: 'UW5-2', component: UW5_2 },
      { path: 'UW6-1', name: 'UW6-1', component: UW6_1 },
      { path: 'UW6-2', name: 'UW6-2', component: UW6_2 },
      { path: 'UW7', name: 'UW7', component: UW7 },
      { path: 'UW8', name: 'UW8', component: UW8 }
    ]
  }
]
const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  const isLoggedIn = store.getters.isLoggedIn
  if (to.path === '/login' && isLoggedIn) {
    next('/UM') // 如果已经登录，访问 /login 时重定向到 /UM
  } else if (to.matched.some(record => record.meta.requiresAuth) && !isLoggedIn) {
    next('/login') // 如果需要授权访问但用户未登录，重定向到 /login
  } else {
    next() // 继续导航
  }
})

export default router;
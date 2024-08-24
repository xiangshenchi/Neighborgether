

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
// import UW3_2 from '../UserWeb/UW3-2.vue';
// import UW3_3 from '../UserWeb/UW3-3.vue';
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
import AM from '../admin/AM.vue';
import AW1 from '../admin/AW1.vue';
import AW2_1 from '../admin/AW2-1.vue';
import AW2_2 from '../admin/AW2-2.vue';
import AW2_3 from '../admin/AW2-3.vue';
import AW3_1 from '../admin/AW3-1.vue';
import AW3_2 from '../admin/AW3-2.vue';
import AW3_3 from '../admin/AW3-3.vue';
// import AW3_4 from '../admin/AW3-4.vue';
import AW3_5 from '../admin/AW3-5.vue';
// import AW3_6 from '../admin/AW3-6.vue';
import AW4 from '../admin/AW4.vue';
import AW5 from '../admin/AW5.vue';
import AW6 from '../admin/AW6.vue';


const routes = [
  { path: '/', redirect: '/welcome' },
  { path: '/:pathWatch(.*)*', redirect: '/welcome' },
  { path: '/login', name: 'login', component: login, meta: { requiresAuth: false } },
  { path: '/register', name: 'register', component: register },
  { path: '/forgetPW', name: 'forgetPW', component: forgetPW },
  { path: '/visitor', name: 'visitor', component: visitor },
  { path: '/welcome', name: 'welcome', component: welcome },
  {
    path: '/UM', name: 'UM', component: UM, meta: { requiresAuth: true, requiredIdentity: 'Owner' },
    children: [
      { path: 'UW1', name: 'UW1', component: UW1 },
      { path: 'UW2-1', name: 'UW2-1', component: UW2_1 },
      { path: 'UW2-2', name: 'UW2-2', component: UW2_2 },
      { path: 'UW2-3', name: 'UW2-3', component: UW2_3 },
      { path: 'UW3-1', name: 'UW3-1', component: UW3_1 },
      // { path: 'UW3-2', name: 'UW3-2', component: UW3_2 },
      // { path: 'UW3-3', name: 'UW3-3', component: UW3_3 },
      { path: 'UW4-1', name: 'UW4-1', component: UW4_1 },
      { path: 'UW4-2', name: 'UW4-2', component: UW4_2 },
      { path: 'UW5-1', name: 'UW5-1', component: UW5_1 },
      { path: 'UW5-2', name: 'UW5-2', component: UW5_2 },
      { path: 'UW6-1', name: 'UW6-1', component: UW6_1 },
      { path: 'UW6-2', name: 'UW6-2', component: UW6_2 },
      { path: 'UW7', name: 'UW7', component: UW7 },
      { path: 'UW8', name: 'UW8', component: UW8 }
    ]
  },
  {
    path: '/AM', name: 'AM', component: AM, meta: { requiresAuth: true, requiredIdentity: 'Admin' },
    children: [
      { path: 'AW1', name: 'AW1', component: AW1 },
      { path: 'AW2-1', name: 'AW2-1', component: AW2_1 },
      { path: 'AW2-2', name: 'AW2-2', component: AW2_2 },
      { path: 'AW2-3', name: 'AW2-3', component: AW2_3 },
      { path: 'AW3-1', name: 'AW3-1', component: AW3_1 },
      { path: 'AW3-2', name: 'AW3-2', component: AW3_2 },
      { path: 'AW3-3', name: 'AW3-3', component: AW3_3 },
      // { path: 'AW3-4', name: 'AW3-4', component: AW3_4 },
      { path: 'AW3-5', name: 'AW3-5', component: AW3_5 },
      // { path: 'AW3-6', name: 'AW3-6', component: AW3_6 },
      { path: 'AW4', name: 'AW4', component: AW4 },
      { path: 'AW5', name: 'AW5', component: AW5 },
      { path: 'AW6', name: 'AW6', component: AW6 }
    ]
  }
]
const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  const isLoggedIn = store.getters.isLoggedIn
  const UserInfo = store.getters.userInfo
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!isLoggedIn) {
      next({ path: '/login' }); // 如果未登录，跳转到登录页面
    } else {
      const userIdentity = UserInfo.identity;
      if (to.meta.requiredIdentity === 'Owner' && userIdentity === 'Admin') {
        next({ path: '/AM' }); // Admin访问UM时跳转到AM
      } else if (to.meta.requiredIdentity === 'Admin' && userIdentity === 'Owner') {
        next({ path: '/UM' }); // Owner访问AM时跳转到UM
      } else {
        next(); // 允许访问
      }
    }
  } else {
    next(); // 不需要身份验证，直接访问
  }
})

export default router;
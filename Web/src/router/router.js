

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
      { path: '/UM/UW1', name: 'UW1', component: UW1 },
      { path: '/UM/UW2-1', name: 'UW2-1', component: UW2_1 },
      { path: '/UM/UW2-2', name: 'UW2-2', component: UW2_2 },
      { path: '/UM/UW2-3', name: 'UW2-3', component: UW2_3 }
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
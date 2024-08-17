

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

const routes = [
  { path: '/login', name: 'login', component: login, meta: { requiresAuth: false } },
  { path: '/register', name: 'register', component: register },
  { path: '/forgetPW', name: 'forgetPW', component: forgetPW },
  { path: '/visitor', name: 'visitor', component: visitor },
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
  if (to.matched.some(record => record.meta.requiresAuth) && !isLoggedIn) {
    next('/login')  // 如果没有登录，则重定向到登录页面
  } else {
    next()
  }
})

export default router;
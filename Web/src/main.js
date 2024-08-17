import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Particles from 'vue3-particles'
import router from './router/router.js'
import store from'./store'
const app = createApp(App)
app.use(store)
app.use(router)
app.use(ElementPlus)
app.use(Particles).mount('#app')
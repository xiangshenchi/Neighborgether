import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Particles from 'vue3-particles'
import router from './router/router.js'
const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.use(Particles).mount('#app')
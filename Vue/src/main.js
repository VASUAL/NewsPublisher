import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'//国际化
import 'element-plus/dist/index.css'//全局样式表
import './assets/css/global/global.css'

createApp(App).use(store).use(router).use(ElementPlus,{locale:zhCn,}).mount('#app')

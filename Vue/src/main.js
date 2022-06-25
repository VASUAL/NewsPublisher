import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'//国际化
import 'element-plus/dist/index.css'//全局样式表
import './assets/css/global/global.css'

//引入富文本编辑器
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

createApp(App).use(store).use(router).use(VueQuillEditor).use(ElementPlus,{locale:zhCn,}).mount('#app')

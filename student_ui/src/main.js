// main.js
import { createApp } from 'vue'
import App from './App.vue'
// 导入 Element Plus 组件库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'  // 导入 Element Plus 样式
import zhCn from 'element-plus/es/locale/lang/zh-cn';
import dayjs from 'dayjs';
import 'dayjs/locale/zh-cn';  // 引入中文
import router from './router'; // 引入路由配置
// 设置全局语言为中文
dayjs.locale('zh-cn');

const app = createApp(App)

// 使用 Element Plus 组件库
app.use(ElementPlus, { locale: zhCn })
app.use(router) // 使用 Vue Router

app.mount('#app')


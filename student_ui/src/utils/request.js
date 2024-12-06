import axios from 'axios';
import router from '@/router';  // 引入 Vue Router，用于重定向到登录页

const requests = axios.create({
    baseURL: 'http://localhost:8080/api', // 后端接口地址
    timeout: 5000,
});

// 请求拦截器，添加 JWT token 到请求头
requests.interceptors.request.use(config => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 响应拦截器，处理 token 过期和未授权错误
requests.interceptors.response.use(response => {
    return response; // 如果响应成功，直接返回
}, error => {
    // 判断错误的类型
    const { response } = error;
    if (response) {
        // 处理 token 过期的情况，假设后端返回 401 未授权
        if (response.status === 401) {
            // 清除无效的 token
            localStorage.removeItem('jwt_token');

            // 提示用户登录状态失效
            alert('您的登录信息已过期，请重新登录');

            // 跳转到登录页面
            router.push('/login'); // 这里的 /login 需要与路由配置一致
        } else {
            // 其他错误直接抛出
            return Promise.reject(error);
        }
    } else {
        // 请求没有响应，可能是网络错误
        alert('网络错误，请稍后重试');
        return Promise.reject(error);
    }
});

export default requests;

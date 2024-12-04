import axios from 'axios';

const requests = axios.create({
    baseURL: 'http://localhost:8080/api', // 后端接口地址
    timeout: 5000
});

requests.interceptors.request.use(config => {
    const token = localStorage.getItem('jwt_token');
    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

export default requests;

import { createStore } from 'vuex';

export default createStore({
    state: {
        token: localStorage.getItem('jwt_token') || '',
        user: JSON.parse(localStorage.getItem('user')) || null
    },
    mutations: {
        setToken(state, token) {
            state.token = token;
            localStorage.setItem('jwt_token', token);
        },
        setUser(state, user) {
            state.user = user;
            localStorage.setItem('user', JSON.stringify(user));
        },
        logout(state) {
            state.token = '';
            state.user = null;
            localStorage.removeItem('jwt_token');
            localStorage.removeItem('user');
        }
    },
    actions: {
        login({ commit }, credentials) {
            // 通过调用后端API进行认证
        },
        logout({ commit }) {
            commit('logout');
        }
    }
});

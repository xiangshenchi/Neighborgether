import { createStore } from 'vuex'
const store = createStore({
    state: {
        isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',  // 从 localStorage 获取登录状态userInfo: JSON.parse(localStorage.getItem('userInfo')) || null,  // 从 localStorage 获取用户信息
    },
    mutations: {
        login(state, userInfo) {
            state.isLoggedIn = true
            state.userInfo = userInfo
            localStorage.setItem('isLoggedIn', 'true')  // 将登录状态存储到 localStoragelocalStorage.setItem('userInfo', JSON.stringify(userInfo))  // 将用户信息存储到 localStorage
        },
        logout(state) {
            state.isLoggedIn = false
            state.userInfo = nulllocalStorage.removeItem('isLoggedIn')  // 清除 localStorage 中的登录状态localStorage.removeItem('userInfo')  // 清除 localStorage 中的用户信息
        }
    },
    actions: {
        login({ commit }, userInfo) {
            commit('login', userInfo)
        },
        logout({ commit }) {
            commit('logout')
        }
    },
    getters: {
        isLoggedIn: state => state.isLoggedIn,
        userInfo: state => state.userInfo
    }
})
export default store

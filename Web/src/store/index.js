import { createStore } from'vuex'
const store = createStore({
    state: {
      isLoggedIn: localStorage.getItem('isLoggedIn') === 'true',
      userInfo: JSON.parse(localStorage.getItem('userInfo')) || null,
    },
    mutations: {
      login(state, userInfo) {
        state.isLoggedIn = true
        state.userInfo = userInfo
        localStorage.setItem('isLoggedIn', 'true')
        localStorage.setItem('userInfo', JSON.stringify(userInfo))
      },
      logout(state) {
        state.isLoggedIn = false
        state.userInfo = nulllocalStorage.removeItem('isLoggedIn')
        localStorage.removeItem('userInfo')
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
  
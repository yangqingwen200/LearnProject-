import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    totalPrice: 0,
    tabIndex: 0,
  },
  getters: {
    getTotalPrice: function (state) {
      return state.totalPrice;
    },
  },
  mutations: { //必须是同步操作, 无法调用后台接口
    increment (state, price) {
      console.info("mutations increment: " + price);
      state.totalPrice += price; //在vuex_1.vue中调用increment
    },
    changeTabIndex (state, index) {
      state.tabIndex = index; //改变底部导航选中状态
    },
  },
  actions: { //可以为异步操作(如调用后台接口), 再调用mutations中对应的方法
    increments: function (context, price, commit) {
      console.info("actions increments: " + price);
      context.commit('increment', price);  //在action中去调用mutations中的increment方法, 在vuex_2.vue中调用increments
    },
  }
})

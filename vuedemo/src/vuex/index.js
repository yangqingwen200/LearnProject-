import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    totalPrice: 0,
    uniPrice: 1
  },
  getters: {
    getTotalPrice: function (state) {
      return state.totalPrice;
    },
    getTodoById: function (state) {
      return state.totalPrice * state.uniPrice;
    },
    getUniPrice: function (state) {
      return state.uniPrice;
    }
  },
  mutations: { //必须是同步操作, 无法调用后台接口
    increment (state, price) {
      console.info("mutations increment: " + price);
      state.totalPrice += price; //在vuex_1.vue中调用increment
    },
    decrement: function (state, price) {
      console.info("mutations decrement: " + price);
      state.totalPrice -= price;
    },
    setUniPrice: function (state, param) {
      state.uniPrice = param;
    }
  },
  actions: { //可以为异步操作(如调用后台接口), 再调用mutations中对应的方法
    increments: function (context, price, commit) {
      console.info("actions increments: " + price);
      context.commit('increment', price);  //在action中去调用mutations中的increment方法, 在vuex_2.vue中调用increments
    },
    decrements: function (context, price) {
      console.info("actions decrements: " + price);
      context.commit('decrement', price);
    }
  }
})

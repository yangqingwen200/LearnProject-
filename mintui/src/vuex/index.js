import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { //使用方式: this.$store.state.xxx
    totalPrice: 0,
    tabIndex: 'home',
    allLoaded: false //分页是否全部加载完毕
  },
  getters: {  //使用方式: this.$store.getters.xxx
    getTotalPrice: function (state) {
      return state.totalPrice;
    },
    getTabIndex: function (state) {
      return state.tabIndex;
    }
  },
  mutations: { //必须是同步操作, 无法调用后台接口, 使用方式:  this.$store.commit("xxx", param);
    modAllLoaded(state, flag) {
      state.allLoaded = flag;
    },
    increment (state, price) {
      console.info("mutations increment: " + price);
      state.totalPrice += price; //在vuex_1.vue中调用increment
    },
    modTabIndex (state, index) {
      state.tabIndex = index; //改变底部导航选中状态
    },
  },
  actions: { //可以为异步操作(如调用后台接口), 再调用mutations中对应的方法, 使用方式: this.$store.dispatch("xxx", param);
    increments: function (context, price, commit) {
      console.info("actions increments: " + price);
      context.commit('increment', price);  //在action中去调用mutations中的increment方法, 在vuex_2.vue中调用increments
    },
  }
})

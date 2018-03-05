import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { //使用方式: this.$store.state.xxx
    totalPrice: 0,
    tabIndex: 'home',
    allLoaded: false,//分页是否全部加载完毕
    pullOrDrop: false, //上拉或下拉数据时, 服务器响应情况
  },
  getters: {  //使用方式: this.$store.getters.xxx
    getTotalPrice: function (state) {
      return state.totalPrice;
    },
    getAllLoaded: function (state) {
      return state.allLoaded;
    },
    getPullOrDrop: function (state) {
      return state.pullOrDrop;
    }
  },
  mutations: { //必须是同步操作, 无法调用后台接口, 使用方式:  this.$store.commit("xxx", param);
    modAllLoaded(state, flag) {
      state.allLoaded = flag;
    },
    modPullOrDrop(state, flag) {
      state.pullOrDrop = flag;
    },

  },
  actions: { //可以为异步操作(如调用后台接口), 再调用mutations中对应的方法, 使用方式: this.$store.dispatch("xxx", param);
    increments: function (context, price, commit) {
      console.info("actions increments: " + price);
      context.commit('increment', price);  //在action中去调用mutations中的increment方法
    },
  }
})

import Vue from 'vue'
import Vuex from 'vuex'
import around from './modules/around'
import commons from './modules/commons'
import mine from './modules/mine'
import more from './modules/more'
import index from './modules/index'

Vue.use(Vuex)

export default new Vuex.Store({
  state: { //使用方式: this.$store.state.xxx
  },
  getters: {  //使用方式: this.$store.getters.xxx
  },
  mutations: { //必须是同步操作, 无法调用后台接口, 使用方式:  this.$store.commit("xxx", param);
  },
  actions: { //可以为异步操作(如调用后台接口), 再调用mutations中对应的方法, 使用方式: this.$store.dispatch("xxx", param);
  },
  modules: {
    around,
    commons,
    mine,
    more,
    index
  }
})

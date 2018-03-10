import store from '@/vuex/index'

/* 使用插件的方式, 扩展Vue默认的方法或者组件或者属性 */
/* 使用方法: 在main.js中导入后, 使用Vue.use(xx), 然后在vue对象中, 使用this.clearJumpParam()即可 */
const clearParam = {
  install: function (Vue) { //有install后, 才能使用use方法
    Vue.prototype.clearJumpParam = function (){
      store.commit('modBeforeJumpPram', {});
      store.commit('modLeaveList', []);
      store.commit('modIsLeaveList', false);
      store.commit('modPosition', 0);
    };
  }
};
export default clearParam;

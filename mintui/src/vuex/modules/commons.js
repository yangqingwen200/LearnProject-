const commons = {
  state: { //使用方式: this.$store.state.xxx
    allLoaded: false,//分页是否全部加载完毕
    beforeJumpPram: {}, //跳转前的参数
    isLeaveList: false, //是否离开过列表页面
    leaveList: [], //离开列表页面时的数据
    position: 0, //离开列表页面时滚动条Y轴的位置
    noMoreData: false
  },
  getters: {  //使用方式: this.$store.getters.xxx
    getAllLoaded: function (state) {
      return state.allLoaded;
    },
    getBeforeJumpPram: function (state) {
      return state.beforeJumpPram;
    },
    getLeaveList: function (state) {
      return state.leaveList;
    },
    getIsLeaveList: function (state) {
      return state.isLeaveList;
    },
    getPosition: function (state) {
      return state.position;
    },
    getNoMoreData: function (state) {
      return state.noMoreData;
    },
  },
  mutations: { //必须是同步操作, 无法调用后台接口, 使用方式:  this.$store.commit("xxx", param);
    modAllLoaded(state, flag) {
      state.allLoaded = flag;
    },
    modBeforeJumpPram(state, param) {
      state.beforeJumpPram = param;
    },
    modLeaveList(state, param) {
      state.leaveList = param;
    },
    modIsLeaveList(state, param) {
      state.isLeaveList = param;
    },
    modPosition(state, param) {
      state.position = param;
    },
    modNoMoreData(state, param) {
      state.noMoreData = param;
    },
  },
  actions: { //可以为异步操作(如调用后台接口), 再调用mutations中对应的方法, 使用方式: this.$store.dispatch("xxx", param);
    increments: function (context, price, commit) {
      console.info("actions increments: " + price);
      context.commit('increment', price);  //在action中去调用mutations中的increment方法
    },
  }
};

export default commons;

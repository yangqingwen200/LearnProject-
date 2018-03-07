const commons = {
  state: { //使用方式: this.$store.state.xxx
    allLoaded: false,//分页是否全部加载完毕
    pullOrDrop: false, //上拉或下拉数据时, 服务器响应情况, 响应成功, 关闭加载时动画
    beforeJumpPram: {}, //跳转前的参数
    leaveList: false, //是否离开过列表页面
    list: [], //离开列表页面时的数据
    position: 0, //离开列表页面时滚动条Y轴的位置
    noDataShow: true //当前第一页, 且list为0时, 提示"暂时没数据..."样式top 30%
  },
  getters: {  //使用方式: this.$store.getters.xxx
    getAllLoaded: function (state) {
      return state.allLoaded;
    },
    getPullOrDrop: function (state) {
      return state.pullOrDrop;
    },
    getBeforeJumpPram: function (state) {
      return state.beforeJumpPram;
    },
    getList: function (state) {
      return state.list;
    },
    getLeaveList: function (state) {
      return state.leaveList;
    },
    getPosition: function (state) {
      return state.position;
    },
    getNoDataShow: function (state) {
      return state.noDataShow;
    }
  },
  mutations: { //必须是同步操作, 无法调用后台接口, 使用方式:  this.$store.commit("xxx", param);
    modAllLoaded(state, flag) {
      state.allLoaded = flag;
    },
    modPullOrDrop(state, flag) {
      state.pullOrDrop = flag;
    },
    modBeforeJumpPram(state, param) {
      state.beforeJumpPram = param;
    },
    modList(state, param) {
      state.list = param;
    },
    modLeaveList(state, param) {
      state.leaveList = param;
    },
    modPosition(state, param) {
      state.position = param;
    },
    modNoDataShow(state, param) {
      state.noDataShow = param;
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

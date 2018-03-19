const commons = {
  state: {
    lastClickModule: 'travel',
    historyId: new Set(),
    topNavBarPost: 0,
    topNavBarModules: [  //模块列表, 应当从服务器获取
      {name: '推荐', moduleId: 'travel'},
      {name: '游戏', moduleId: 'game'},
      {name: '居家', moduleId: 'home'},
      {name: '国际', moduleId: 'international'},
      {name: '财经', moduleId: 'finance'},
      {name: '科技', moduleId: 'society'},
      {name: '健康', moduleId: 'health'},
      {name: '汽车', moduleId: 'car'},
      {name: '历史', moduleId: 'history'},
      {name: '数码', moduleId: 'digital'},
      {name: '时尚', moduleId: 'fashion'},
    ]
  },
  getters: {
    getLastClickModule: function (state) {
      return state.lastClickModule;
    },
    getHistoryId: function (state) {
      return state.historyId;
    },
    getTopNavBarPost: function (state) {
      return state.topNavBarPost;
    },
    getTopNavBarModules: function (state) {
      return state.topNavBarModules;
    }
  },
  mutations: {
    modLastClickModule(state, flag) {
      state.lastClickModule = flag;
    },
    modHistoryId(state, flag) {
      state.historyId.add(flag);
    },
    modTopNavBarPost(state, flag) {
      state.topNavBarPost = flag;
    },
  }
};

export default commons;

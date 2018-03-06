const commons = {
  state: {
    lastClickModule: 'game',
    historyId: new Set(),
    topNavBarPost: 0
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

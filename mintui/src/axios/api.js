import fetch from "./fetch";

export default {
  getHomeData(params) {
    return fetch('/app/index_appUser.do', params)
  },
  getHomeFriend(params) {
    return fetch('/app/aroundFriend_appUser.do', params)
  },
}

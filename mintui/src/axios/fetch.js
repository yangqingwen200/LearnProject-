import axios from "axios/index";
import Cookies from 'js-cookie';

export default function fetch(url, params, isLogin) {
  if(isLogin) {
    let token = Cookies.get("token");
    let userId = Cookies.get("userId");
    if(token && userId) {
      params.token = token;
      params.userId = userId;
    } else {
      return Promise.reject("你还未登录");
    }
  }
  return new Promise((resolve, reject) => {
    axios.post(url, params)
      .then(response => {
        resolve(response.data);
      }, err => {
        reject(err);
      })
      .catch((error) => {
        reject(error)
      })
  })
}

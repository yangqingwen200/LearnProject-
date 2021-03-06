import Vue from 'vue'
import axios from 'axios'
import qs from 'qs'

Vue.prototype.$http = axios;

axios.defaults.timeout = 10000; //响应时间
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';  //配置请求头
axios.defaults.baseURL = 'http://192.168.1.181:8080';   //配置接口地址

//添加请求拦截器(在发送请求之前做某件事)
axios.interceptors.request.use((config) => {
  if(config.method  === 'post'){
    config.data = qs.stringify(config.data); //POST传参序列化
  }
  return config;
},(error) =>{
  return Promise.reject(error);
});

//添加响应拦截器
axios.interceptors.response.use((res) =>{
  /*if(!res.data.success){  //返回状态判断
    return Promise.reject(res);
  }*/
  return res;
}, (error) => {
  return Promise.reject(error);
});

//返回一个Promise(发送post请求)
export function fetch(url, params) {
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
};
/*

export default {
  /!**
   * 用户登录
   *!/
  Login(params) {
    return fetch('/users/api/userLogin', params)
  },
  /!**
   * 用户注册
   *!/
  Regist(params) {
    return fetch('/users/api/userRegist', params)
  },
  /!**
   * 发送注册验证码
   *!/
  RegistVerifiCode(tellphone) {
    return fetch('/users/api/registVerifiCode', {tellphone: tellphone})
  }
}
*/


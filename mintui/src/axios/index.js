import Vue from 'vue'
import axios from 'axios'
import qs from 'qs'
import {Toast} from 'mint-ui'

Vue.prototype.$http = axios;

axios.defaults.timeout = 10000; //响应时间
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';  //配置请求头
axios.defaults.baseURL = 'http://192.168.1.247:8080';   //配置接口地址

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
axios.interceptors.response.use((response) =>{
  if(response.data.code != 1000) { //返回状态判断
    Toast({
      message: response.data.msg,
      position: 'middle',
      duration: 2000
    });
  } else {
    if(response.data.hasOwnProperty('pageNow') && response.data.hasOwnProperty('pageCount')) {
      //说明是分页调用, 友好显示当前页和总页数
      Toast({
        message: response.data.pageNow + " / " + response.data.pageCount,
        position: 'bottom',
        duration: 1000
      });
    }
  }
  return response;
}, (error) => {
  Toast({
    message: '网络错误...',
    position: 'middle',
    duration: 2000
  });
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

export default {
  /**
   * 用户登录
   */
  Login(params) {
    return fetch('/app/login_appUser.do', params)
  },
  /**
   * 用户注册
   */
  Regist(params) {
    return fetch('/users/api/userRegist', params)
  },
  /**
   * 发送注册验证码
   */
  RegistVerifiCode(tellphone) {
    return fetch('/users/api/registVerifiCode', {tellphone: tellphone})
  }
}


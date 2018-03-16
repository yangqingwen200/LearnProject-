import Vue from 'vue'
import axios from 'axios'
import qs from 'qs'
import store from '../vuex'
import {Toast} from 'mint-ui'

Vue.prototype.$http = axios;

axios.defaults.timeout = 20000; //响应时间
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';  //配置请求头
axios.defaults.baseURL = 'http://192.168.1.247:8082';   //配置接口地址

//添加请求拦截器(在发送请求之前做某件事)
axios.interceptors.request.use((config) => {
  if(config.method  === 'post'){
    let data = config.data;
    for(let key in data) {
      if(data[key] === '') {
        delete data[key]; //剃掉值为空的key
      }
    }
    config.data = qs.stringify(data); //POST传参序列化
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
      let pageNow = response.data.pageNow;
      let pageCount = response.data.pageCount;
      if(pageNow === 1 && response.data.list.length === 0) {

      } else {
        Toast({ //说明是分页调用, 友好显示当前页和总页数 (当前页为第一页且有数据才显示)
          message: pageNow + " / " + pageCount,
          position: 'bottom',
          duration: 1000
        });
      }
      store.commit('modAllLoaded', pageNow === pageCount); //改变是否还能上拉加载, 为true则不能继续上拉
      store.commit('modNoMoreData', pageNow === pageCount);  //显示没有更多数据
    }
  }

  return response;
}, (error) => {
  Toast({
    message: '网络有小问题...',
    position: 'middle',
    duration: 2000
  });
  return Promise.reject(error);
});

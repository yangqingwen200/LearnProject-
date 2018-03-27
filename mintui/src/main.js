// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import {router} from './router'
import store from './vuex'
import 'mint-ui/lib/style.css'
import './assets/css/my-mintui.css'
import './assets/css/commons.css'
import './assets/font/style.css' //样式地址: https://icomoon.io/app/#/select
import './assets/aliIconFont/iconfont.css' //阿里icon字体样式: http://iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=4491
import './axios'
import Mint from 'mint-ui'
import clearParam from './assets/js/method/clearParam'

Vue.use(Mint);
Vue.use(clearParam);

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})

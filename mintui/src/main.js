// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import {router} from './router'
import store from './vuex'
import 'mint-ui/lib/style.css'
import './assets/css/my-mintui.css'
import './assets/css/commons.css'
import './assets/js/iconfont'
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

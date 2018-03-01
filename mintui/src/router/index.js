import Vue from 'vue'
import Router from 'vue-router'
import home from '../components/index/home'
import login from '../components/mine/login'
import register from '../components/mine/register'
import personal from '../components/mine/personal'
import around from '../components/around/around'
import schoolDetail from '../components/index/schoolDetail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '*',
      redirect: home
    },
    {
      path: '/',
      name: 'home',
      component: home
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/register',
      name: 'register',
      component: register
    },
    {
      path: '/personal',
      name: 'personal',
      component: personal
    },
    {
      path: '/school/detail/:id',
      name: 'detail',
      //component: resolve => { require(['../components/index/schoolDetail.vue'], resolve); }
      component: schoolDetail
    },
    {
      path: '/around',
      name: 'around',
      component: around,
      children: [
      ]
    }
  ]
})

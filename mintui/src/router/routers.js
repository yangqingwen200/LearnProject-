import home from '../components/index/home'
import login from '../components/mine/login'
import register from '../components/mine/register'
import personal from '../components/mine/personal'
import around from '../components/around/around'
import more from '../components/more/more'
import friendDetail from '../components/more/friendDetail'
import schoolDetail from '../components/index/schoolDetail'

export default [
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
    component: schoolDetail
  },
  {
    path: '/more/friendDetail/:id',
    name: 'friendDetail',
    component: resolve => {
      require(['../components/more/friendDetail'], resolve);
    }
  },
  {
    path: '/articleDetail/:id',
    name: 'articleDetail',
    component: resolve => {
      require(['../components/index/articleDetail'], resolve);
    }
  },
  {
    path: '/video',
    name: 'video',
    component: resolve => {
      require(['../components/video/video'], resolve);
    }
  },
  {
    path: '/around',
    name: 'around',
    component: around,
    children: []
  },
  {
    path: '/more',
    name: 'more',
    component: more
  }
]

import Vue from 'vue'
import Router from 'vue-router'
import routers from './routers'

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: routers
});

router.beforeEach((to, from, next) => {
  console.info(to);
  console.info(from);
  next();
});

router.afterEach((to) => {
  console.info(to);
});

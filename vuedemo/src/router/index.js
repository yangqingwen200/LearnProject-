import Vue from 'vue'
import Router from 'vue-router' // 自动补全的扩展名 extensions: ['.js', '.vue', '.json']
import HelloWorld from '@/components/HelloWorld' //@等价于 /src 这个目录
import Apple from '@/components/Apple'
import Banana from '@/components/Banana'
import BananaChild from '@/components/BananaChild'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/apple/:color',
      component: Apple
    },
    {
      path: '/banana/',
      component: Banana,
      children: [
        {
          path: 'bananaChild',
          component: BananaChild

        }
      ]
    },
    {
      path: '/hello',
      name: 'Hello',
      component: HelloWorld
    },
    {
      path: '/',
      name: 'home',
      redirect: '/hello' /*当浏览器输入/时 会重定向到/hello这个路由*/
    }
  ]
})

<template>
  <div id="app">
    <img src="./assets/logo.png">
    <div class="cen">

      <!-- name为router/index.js文件中routes对象中使用name定义过 -->
      <router-link :to="{name: 'home'}">Home</router-link>


      <router-link to="/apple/Red">Red Apple</router-link>

      <router-link to="/apple/Green">Green Apple</router-link>

      <!-- banana为router/index.js文件中routes对象中使用path定义过(好像不要带/) -->
      <router-link :to="{path: '/banana'}">Banana</router-link>

      <!-- router-link正常渲染之后, 页面呈现为a标签, 使用tag属性, 使之渲染成其他html标签元素: span/p... -->
      <router-link :to="{path: '/banana'}" tag="span">BananaSpan</router-link>

      <!-- 模板放置的地方, 当点击/apple/Red的时候, 对应的component就会放在这里  -->
      <!-- 有几个router-view标签, 就会放置几份对应的component就会放在这里  -->
      <!-- 当浏览器输入http://localhost:8080/, 就会找router对应path为/的路由信息(在router/index.js中)放置这里  -->
      <router-view/>
    </div>

    <div style="margin-top: 50px">
      以下是测试Vuex相关知识, 点击按钮的执行顺序, 请看控制台输出<br>
      {{total}}
      <Vuex_1></Vuex_1>
      <Vuex_2></Vuex_2>

    </div>
  </div>
</template>

<script>
import Vuex_1 from './components/Vuex_1.vue';
import Vuex_2 from './components/Vuex_2.vue';
export default {
  name: 'app',


  /*测试Vuex相关知识才引入的组件*/
  components: {Vuex_1, Vuex_2}, /*添加两个组件*/
  computed: {
      total: function () { //监听值变化
        console.info("computed total");
        console.info("");
        //return this.$store.state.totalPrice // 第一种方式: 从$store的state中直接取值,
        return this.$store.getters.getTotalPrice // 第二种方式: 调用$store getters中的对应方法
      }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
  .cen {
    text-align: center;
  }
</style>

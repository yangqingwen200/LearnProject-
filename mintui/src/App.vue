<template>
  <div id="app">
    <div :style="{marginBottom: tabShow ? '3.75rem' : 0}">
      <router-view/>
    </div>
    <mt-tabbar v-model="selected" fixed v-if="tabShow">
      <mt-tab-item id="home" @click.native="home">
        <img slot="icon" src="./assets/logo.png">
        首页
      </mt-tab-item>
      <mt-tab-item id="around" @click.native="around">
        <img slot="icon" src="./assets/logo.png">
        视频
      </mt-tab-item>
      <mt-tab-item id="more" @click.native="more">
        <img slot="icon" src="./assets/logo.png">
        微头条
      </mt-tab-item>
      <mt-tab-item id="mine" @click.native="mine">
        <img slot="icon" src="./assets/logo.png">
        我的
      </mt-tab-item>
    </mt-tabbar>
  </div>
</template>

<script>
  import Cookies from 'js-cookie';
  import tabShowPath from './assets/js/tabShowPath'

  export default {
    name: 'App',
    data() {
      return {
        selected: 'home', //设置selected默认值为第一个mt-tab-item的id
        tabShow: true
      }
    },
    methods: {
      home: function () {
        this.clearJumpParam();
        this.$router.push({name: 'home'});
      },
      around: function () {
        this.clearJumpParam();
        this.$router.push({name: 'around'});
      },
      more: function () {
        this.clearJumpParam();
        this.$router.push({name: 'more'});
      },
      mine: function () {
        this.clearJumpParam();
        let token = Cookies.get("token");
        if (token) {
          this.$router.push({name: 'personal'});
        } else {
          this.$router.push({name: 'login'});
        }
      }
    },
    watch: {
      $route() {
        let curPath = this.$route.path;
        for( var index = 0; index < tabShowPath.length; index ++){
          if(curPath.indexOf(tabShowPath[index]) != -1) {
            this.tabShow = false;
            break;
          } else {
            this.tabShow = true;
          }
        }
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    margin-top: 0.5rem;
  }
</style>

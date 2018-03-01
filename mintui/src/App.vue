<template>
  <div id="app">
    <div :style="{marginBottom: tabShow ? '60px' : 0}">
      <router-view/>
    </div>
    <mt-tabbar v-model="selected" fixed v-if="tabShow">
      <mt-tab-item id="home" @click.native="home">
        <img slot="icon" src="./assets/logo.png">
        首页
      </mt-tab-item>
      <mt-tab-item id="around" @click.native="around">
        <img slot="icon" src="./assets/logo.png">
        发现
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
  import tabShowPath from './assets/tabShowPath'

  export default {
    name: 'App',
    data() {
      return {
        selected: true,
        tabShow: true
      }
    },
    computed: {
      tabIndex() {
        return this.$store.state.tabIndex
      }
    },
    methods: {
      home: function () {
        this.$router.push({name: 'home'});
      },
      around: function () {
        this.$router.push({name: 'around'});
      },
      mine: function () {
        let status = Cookies.get("token");
        if (status) {
          this.$router.push({name: 'personal'});
        } else {
          this.$router.push({name: 'login'});
        }
      }
    },
    watch: {
      $route() {
        for( var index = 0; index < tabShowPath.length; index ++){
          if(this.$route.path.indexOf(tabShowPath[index]) != -1) {
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
    margin-top: 20px;
  }
</style>

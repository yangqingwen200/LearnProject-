<template>
  <div class="over-layer">  <!--@touchmove.prevent: 可阻止滑动-->
    <div class="over-layer-children">
      <span style="font-weight: bold;font-size: 1.1rem">你还未登录</span>
      <div style="margin-top: 0.3rem;padding: 0 0.5rem">
        <mt-field placeholder="请输入手机号..." type="number" v-model="phone"></mt-field>
        <mt-field placeholder="请输入密码..." type="password" v-model="password"></mt-field>
      </div>
      <div style="margin-top: 1.0rem;">
        <mt-button type="default" size="small" @click="close">暂不登录</mt-button>
        <mt-button type="primary" size="small" @click="login" style="margin-left: 0.9rem">立即登录</mt-button>
      </div>
    </div>
  </div>
</template>

<script>
  import md5 from 'js-md5'
  import Cookies from 'js-cookie'
  import {Indicator} from 'mint-ui'
  import {Toast} from 'mint-ui'

  export default {
    name: "login-alert",
    data() {
      return {
        phone: '',
        password: ''
      }
    },
    props: {

    },
    methods: {
      close() {
        this.$emit('close-layer', false);
      },
      login() {
        let phone = this.phone;
        if (phone.trim().length == 0) {
          Toast({
            message: '手机号码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        let password = this.password;
        if (password.trim().length == 0) {
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        Indicator.open({
          text: '登录中...',
          spinnerType: 'fading-circle'
        });
        let data = {password: md5(this.password), phone: this.phone};
        this.$http.post('/app/login_appUser.do', data).then(function (response) {
          Indicator.close();
          if (response.data.code === 1000) {
            Cookies.set('token', response.data.token);
            Cookies.set('userId', response.data.userId);
            Cookies.set('phone', this.phone);

            Toast({
              message: '登录成功.',
              position: 'middle',
              duration: 1000
            });
            this.$emit('close-layer', false);
          }
        }.bind(this)).catch((error) => {
          Indicator.close();
        });
      }
    }
  }
</script>

<style scoped>
  .over-layer {
    position: fixed;
    background: rgba(0, 0, 0, 0.5);
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    z-index: 10;
    filter: alpha(opacity=40);
    text-align: -webkit-center;
  }

  .over-layer-children {
    text-align: center;
    top: 30%;
    position: relative;
    background-color: #fafafa;
    border-radius: 0.3rem;
    width: 90%;
    padding: 1%
  }

</style>

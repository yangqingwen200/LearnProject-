<template>
  <div>
    <div>
      <mt-field label="手机号" placeholder="请输入手机号" type="number" v-model="phone" :style="[inputStyle]"></mt-field>
      <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password" :style="[inputStyle]"></mt-field>
    </div>
    <div style="margin-top: 2rem">
      <mt-button type="primary" size="large" @click="login">登录</mt-button>
      <br>
      <mt-button type="default" size="large" @click="register">立即注册</mt-button>
    </div>
  </div>
</template>

<script>
  import md5 from 'js-md5';
  import {Indicator} from 'mint-ui';
  import {Toast} from 'mint-ui';
  import Cookies from 'js-cookie';

  export default {
    name: "login",
    data() {
      return {
        phone: '18617102944',
        password: '123456',
        inputStyle: {
          fontSize: '12rem',
          fontWeight: 'bold'
        },
      }
    },
    methods: {
      register() {
        this.$router.push({name: 'register'});
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
          if (response.data.code !== 1000) {
            Toast({
              message: response.data.msg,
              position: 'middle',
              duration: 2000
            });
          } else {
            Cookies.set('token', response.data.token);
            Cookies.set('userId', response.data.userId);
            Cookies.set('phone', this.phone);
            this.$router.push({name: 'personal'});
          }
        }.bind(this)).catch((error) => {
          Indicator.close();
        });
      }
    }
  }
</script>

<style scoped>

</style>

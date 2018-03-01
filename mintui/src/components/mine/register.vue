<template>
  <div>
    <div>
      <mt-field label="手机号" placeholder="请输入手机号" type="number" v-model="phone" :style="[inputStyle]"></mt-field>

      <mt-field label="密码" placeholder="请输入密码" type="password" v-model="password" :style="[inputStyle]"></mt-field>

      <mt-field label="验证码" placeholder="请输入验证码" type="number" v-model="code" :style="[inputStyle]"></mt-field>
    </div>
    <div style="margin-top: 30px">
      <br>
      <mt-button type="primary" size="large" @click="register">立即注册</mt-button><br>
      <mt-button type="default" size="large" @click="backLogin">返回登录</mt-button>
    </div>
  </div>
</template>

<script>
  import md5 from 'js-md5';
  import {Indicator} from 'mint-ui';
  import {Toast} from 'mint-ui';
  import Cookies from 'js-cookie';

  export default {
    name: "register",
    data() {
      return {
        phone: '',
        password: '',
        code: '',
        inputStyle: {
          fontSize: '12rem',
          fontWeight: 'bold'
        },
      }
    },
    methods: {
      backLogin() {
        this.$router.push({name: 'login'});
      },
      register() {
        if (this.phone.trim().length == 0) {
          Toast({
            message: '手机号码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if (this.password.trim().length == 0) {
          Toast({
            message: '密码不能为空',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        if (this.code.trim().length == 0) {
          Toast({
            message: '验证码',
            position: 'middle',
            duration: 1000
          });
          return;
        }
        Indicator.open({
          text: '提交中...',
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
          Toast({
            message: '网络错误...',
            position: 'middle',
            duration: 2000
          });
        });
      }
    }
  }
</script>

<style scoped>

</style>

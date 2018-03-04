<template>
  <div>
    <div style="text-align: center">
      <img :src="logo" style="width: 50%"/>
      <mt-field label="姓名" v-model="name" readonly :style="[inputStyle]"></mt-field>
      <mt-field label="手机号" v-model="telephone" readonly :style="[inputStyle]"></mt-field>
      <mt-field label="生日" v-model="bir" readonly :style="[inputStyle]"></mt-field>
    </div>
    <div style="margin-top: 10%">
      <mt-button type="primary" size="large" @click="exitLogin">退出登录</mt-button>
      <br>
    </div>
  </div>
</template>

<script>
  import Cookies from 'js-cookie';
  import {MessageBox} from 'mint-ui';

  export default {
    name: "personal",
    data() {
      return {
        logo: '',
        name: '',
        bir: '',
        telephone: '',
        inputStyle: {
          fontSize: '12rem',
          fontWeight: 'bold'
        },
      }
    },
    methods: {
      exitLogin() {
        MessageBox.confirm('确定退出登录吗?').then(action => {
          if(action === 'confirm') {
            Cookies.set("token", "");
            this.$router.push({name: 'login'});
          }
        }).catch(()=> {
        });
      },
      getUserInfo() {
        let userId = Cookies.get("userId");
        let token = Cookies.get("token");
        let data = {
          userId: userId,
          token: token,
          version: 1.1,
        };
        this.$http.post('/app/getAppUserInfo_appUser.do', data).then(function (response) {
          if (response.data.code === 1000) {
            this.logo = response.data.userInfo.logourl;
            this.name = response.data.userInfo.name;
            this.telephone = response.data.userInfo.telephone;
            this.bir = response.data.userInfo.bir;
          } else {
            this.$router.push({name: 'login'});
          }
        }.bind(this));
      }
    },
    mounted() {
      this.getUserInfo();
    }
  }
</script>

<style scoped>

</style>

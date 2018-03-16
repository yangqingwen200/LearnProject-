<template>
  <div style="text-align: center;">
    <img src="../../assets/images/logo.png" style="width: 45%"/>
    <mt-field readonly :style="[inputStyle]" label="姓名" v-model="detail.acName"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="手机号码" v-model="detail.mobile"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="状态" v-model="detail.state == 'WBK' ? '未认证' : '已认证'"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="证件号码" v-model="detail.idNo"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="银行账号" v-model="detail.acNo"></mt-field>
    <mt-button :type="detail.state == 'WBK' ? 'danger' : 'primary'" size="large" @click="joinUs"
               style="margin-top: 5%">加为好友
    </mt-button>
    <mt-button v-show="detail.state == 'WBK'" type="primary" size="large" @click="report" style="margin-top: 3%">我要举报
    </mt-button>
  </div>
</template>

<script>
  import Cookies from 'js-cookie'
  import {Toast} from 'mint-ui'
  import {MessageBox} from 'mint-ui'

  export default {
    name: "friend-detail",
    data() {
      return {
        detail: {},
        inputStyle: {
          fontWeight: 'bold'
        }
      }
    },
    methods: {
      init() {
        let id = this.$route.params.id;
        let data = {
          friendId: id,
          userId: Cookies.get("userId"),
          token: Cookies.get("token")
        }
        this.$http.post('/app/friendDetail_appUser.do', data).then(function (response) {
          if (response.data.code === 1000) {
            this.detail = response.data.detail;
          }
        }.bind(this));
      },
      joinUs() {
        if (this.detail.state === 'WBK') {
          MessageBox({
            title: '提示',
            message: '该好友未经过认证, 你确定要加为好友?',
            showCancelButton: true,
            confirmButtonText: '心意已决',
            cancelButtonText: '再考虑下'
          }).then(action => {
            if (action === 'confirm') {
              this.joinSuccessTip();
            }
          });
        } else {
          this.joinSuccessTip();
        }
      },
      report() {
        //console.info(this.$route.params.id);
        MessageBox.prompt(' ', '举报好友, 给我们个理由', {
          //inputPlaceholder: '填个举报的理由',
          confirmButtonText: '狠心举报',
          cancelButtonText: '放过好友',
          inputErrorMessage: '至少写6个字...',
          inputValidator: (val) => {
            if (val === null) {
              return false;
            }
            return !(val.length < 6)
          }
        }).then(({value, action}) => {
          if (action === 'confirm') {
            console.log(value)
          }
        }).catch(error => {

        });
      },
      joinSuccessTip() {
        Toast({
          message: '添加成功',
          position: 'middle',
          duration: 2000
        });
      }
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>

</style>

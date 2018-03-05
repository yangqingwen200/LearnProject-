<template>
  <div>
    <mt-navbar v-model="selected" fixed style="background-color: #eee">
      <mt-tab-item id="all">全部</mt-tab-item>
      <mt-tab-item id="YBK">已认证</mt-tab-item>
      <mt-tab-item id="WBK">未认证</mt-tab-item>
    </mt-navbar>

    <div style="margin-top: 13%">
      <load-more ref="children" @get-server-data="getServerData" :req-param-add="reqParamAdd">
        <div slot="viewTemplate">
          <ul style="margin: 0;padding: 0">
            <li v-for="item in list" @click="friendDetail(item.userId)">
              <table border="0" style="width: 100%">
                <tr>
                  <td rowspan="3" style="width: 15%;text-align: center">
                    <img src="../../assets/logo.png" style="height: 18%;"/>
                  </td>
                </tr>
                <tr>
                  <td>
                    姓名: <span v-text="item.acName"></span>
                    <span style="font-size: 0.8rem">
                      ({{item.mobile  | dealChar}})
                    </span>
                  </td>
                  <td style="text-align: right">
                    <span>
                    No: <span v-text="item.userId"></span>
                  </span>
                  </td>
                </tr>
                <tr>
                  <td>身份证: {{item.idNo | dealChar}}</td>
                  <td style="text-align: right">
                    状态:
                    <mt-badge size="small" :type="item.state === 'YBK' ? 'primary' : 'error'">{{item.state === 'YBK' ?
                      '已认证' : '未认证'}}
                    </mt-badge>
                  </td>
                </tr>
                <tr>
                  <td colspan="5">
                    <hr style="border:0.07rem dotted #036"/>
                  </td>
                </tr>
              </table>
            </li>
          </ul>
          <div v-if="list.length === 0" style="color: grey;text-align: center; margin-top: 40%">
            暂无数据...
          </div>
        </div>
      </load-more>
    </div>

    <div v-show="overLayer">
      <login-alert @close-layer="closeLayer">
      </login-alert>
    </div>
  </div>
</template>

<script>
  import loadMore from '../commons/loadMore'
  import loginAlert from '../commons/loginAlert'
  import Cookies from 'js-cookie'

  export default {
    name: "more",
    data() {
      return {
        selected: 'all',
        overLayer: false,
        phone: '',
        password: '',
        list: [],
        reqParamAdd: {
          pageSize: 10,
          state: ''
        },
      }
    },
    components: {
      loadMore,
      loginAlert
    },
    filters: {
      dealChar: function (value) {
        let length = value.length / 2;
        let re = value.substring(length / 2, length + length / 2);
        return value.replace(re, '****');
      }
    },
    watch: {
      selected: function (val, oldVal) { //监听点击navbar点击变化
        this.reqParamAdd.state = val; //改变请求参数值, loadMore会自动重新发起请求, 因为在loadMore组件中watch了reqParamAdd对象的变化
      }
    },
    methods: {
      getServerData(reqParam) {
        this.$http.post('/app/aroundFriend_appUser.do', reqParam).then(function (response) {
          if (response.data.code === 1000) {
            if (reqParam.pageNow === 1) {
              this.list = response.data.list;
            } else {
              this.list = this.list.concat(response.data.list);
            }
          }
        }.bind(this));
      },
      friendDetail(userId) {
        let token = Cookies.get("token");
        if (!token) {
          this.overLayer = true;
        } else {
          this.$router.push({
            name: 'friendDetail',
            params: {
              id: userId
            }
          });
        }
      },
      closeLayer(param) {
        this.overLayer = param;
      }
    }
  }
</script>

<style scoped>

</style>

<template>
  <div>
    <load-more ref="children" @get-server-data="getServerData" :req-param-add="reqParamAdd">
      <div slot="viewSearchBar" style="margin-bottom: 10px;">
        筛选: <input type="text" style="border: 2px solid #e2e2e4;border-radius:7px;line-height: 2.0;" v-model="reqParamAdd.name" placeholder="请输入姓名..."/>
      </div>
      <div slot="viewTemplate">
        <ul style="margin: 0;padding: 0">
          <li v-for="item in list">
            <table border="0" style="width: 100%">
              <tr>
                <td rowspan="5" style="width: 35%;padding-right: 20px;padding-left: 15px"><img
                  src="../../assets/logo.png" style="width: 100%"/></td>
              </tr>
              <tr>
                <td>姓名: <span v-text="item.acName"></span>
                  <span style="font-size: 0.8rem">
                    ({{item.mobile | dealChar}})
                  </span>
                </td>
              </tr>
              <tr>
                <td>状态: <span v-text="item.state === 'YBK' ? '已认证' : '未认证'"></span>
                  <span style="font-size: 0.8rem">
                    (No: <span v-text="item.userId"></span>)
                  </span>
                </td>
              </tr>
              <tr>
                <td colspan="2">银行卡: {{item.acNo | dealChar}}</td>
              </tr>
              <tr>
                <td colspan="2">身份证: {{item.idNo | dealChar}}</td>
              </tr>
              <tr>
                <td colspan="2">
                  <hr style="border:1px dotted #036"/>
                </td>
              </tr>
            </table>
          </li>
        </ul>
        <div v-if="list.length === 0" style="color: grey;text-align: center; margin-top: 50%">
          暂无数据...
        </div>
      </div>
    </load-more>
  </div>
</template>

<script type="text/babel">
  import loadMore from '../commons/loadmore'
  import {Toast} from 'mint-ui'

  export default {
    data() {
      return {
        name: '',
        list: [],
        reqParamAdd: {
          pageSize: 10,
          name: ''
        },
      };
    },
    components: {
      loadMore
    },
    filters: {
      dealChar: function (value) {
        let length = value.length / 2;
        let re = value.substring(length / 2, length + length / 2);
        return value.replace(re, '****');
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
            if (response.data.pageNow === response.data.pageCount) {
              this.$refs.children.changeAllLoaded();
            }
          }
        }.bind(this));
      },
    },
    mounted() {
    },
  };
</script>

<style scoped>
</style>

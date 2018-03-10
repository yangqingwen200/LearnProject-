<template>
  <div>
    <load-more ref="children" @get-server-data="getServerData" :req-param-add="reqParamAdd">
      <div slot="viewSearchBar" class="searchBarSlot">
        <img style="display: inline-block; vertical-align: middle;" src="../../assets/search.png"/>
        <input type="text" class="searchBarSlotInput" v-model="reqParamAdd.name" placeholder="请输入姓名..."/>
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
        <div v-if="list.length === 0" style="color: grey;text-align: center; margin-top: 40%">
          暂无数据...
        </div>
      </div>
    </load-more>
  </div>
</template>

<script type="text/babel">
  import loadMore from '../commons/loadMore'

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
              this.list = response.data.leaveList;
            } else {
              this.list = this.list.concat(response.data.leaveList);
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
  .searchBarSlot {
    padding: 0.31rem 0 0.31rem 0.63rem;
    margin-bottom: 0.63rem;
    background-color: #e0e0e0;
    border-radius: 1rem;
    width: 95%;
    vertical-align: middle;
  }

  .searchBarSlotInput {
    border: none;
    height: 1rem;
    font-size: 1rem;
    background-color: transparent;
    width: 80%;
    margin-left: 0.18rem;
  }
</style>

<template>
  <!-- 加载组件url: https://www.cnblogs.com/yuri2016/p/7045709.html -->
  <div class="page-loadmore">
    <div class="page-loadmore-wrapper" ref="wrapper" :style="{ height: wrapperHeight + 'px' }">
      <mt-loadmore :top-method="loadTop" @translate-change="translateChange" @top-status-change="handleTopChange"
                   :bottom-method="loadBottom" @bottom-status-change="handleBottomChange" :bottom-all-loaded="allLoaded"
                   ref="loadmore" :top-drop-text="topDropText" :bottom-pull-text="bottomPullText" :bottom-drop-text="bottomDropText" :auto-fill=false>
        <ul style="margin: 0;padding: 0">
          <li v-for="item in list">
            <table border="0" style="width: 100%">
              <tr>
                <td rowspan="5" style="width: 35%;padding-right: 20px;padding-left: 15px"><img src="../../assets/logo.png" style="width: 100%"/></td>
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
                <!--<td colspan="2">银行卡: <span v-text="item.acNo | dealChar"></span></td>-->
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
      </mt-loadmore>
    </div>
  </div>
</template>

<script type="text/babel">
  import {Toast} from 'mint-ui';
  export default {
    data() {
      return {
        pageNow: 1,
        topDropText: '释放刷新',
        bottomPullText: '上拉加载',
        bottomDropText: '释放加载',
        list: [],
        allLoaded: false,
        bottomStatus: '',
        wrapperHeight: 0,
        topStatus: '',
        //wrapperHeight: 0,
        translate: 0,
        moveTranslate: 0,

      };
    },
    filters: {
      dealChar: function (value) {
        let length = value.length / 2;
        let re = value.substring(length / 2, length + length / 2);
        return value.replace(re, '****');
      }
    },
    methods: {
      handleBottomChange(status) {
        this.bottomStatus = status;
      },
      handleTopChange(status) {
        this.moveTranslate = 1;
        this.topStatus = status;
      },
      translateChange(translate) {
        const translateNum = +translate;
        this.translate = translateNum.toFixed(2);
        this.moveTranslate = (1 + translateNum / 70).toFixed(2);
      },
      loadTop() {
        this.pageNow = 1;
        this.allLoaded = false;
        this.getServerData("clear");
      },
      loadBottom() {
        this.pageNow++;
        this.getServerData("add");
      },
      getServerData(flag) {
        this.$http.post('/app/aroundFriend_appUser.do', {pageNow: this.pageNow}).then(function (response) {
          if (response.data.code === 1000) {

            if(flag === "clear") {
              this.list = response.data.list;
              this.$refs.loadmore.onTopLoaded();
            } else if(flag === "add") {
              this.list = this.list.concat(response.data.list);
              this.$refs.loadmore.onBottomLoaded();
            }
          }
          if(this.pageNow === response.data.pageCount) {
            this.allLoaded = true;
          }
          Toast({
            message: this.pageNow + " / " + response.data.pageCount,
            position: 'bottom',
            duration: 1000
          });
        }.bind(this)).catch((error) => {
        });
      }
    },
    created() {
      this.getServerData("clear");
    },
    mounted() {
      this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top;
    }
  };
</script>

<style scoped>
  .loading-background, .mint-loadmore-top span {
    -webkit-transition: .2s linear;
    transition: .2s linear
  }

  .mint-loadmore-top span {
    display: inline-block;
    vertical-align: middle
  }

  .mint-loadmore-top span.is-rotate {
    -webkit-transform: rotate(180deg);
    transform: rotate(180deg)
  }

  .page-loadmore .mint-spinner {
    display: inline-block;
    vertical-align: middle
  }

  .page-loadmore-desc {
    text-align: center;
    color: #666;
    padding-bottom: 5px
  }

  .page-loadmore-desc:last-of-type,
  .page-loadmore-listitem {
    border-bottom: 1px solid #eee
  }

  .page-loadmore-listitem {
    height: 50px;
    line-height: 50px;
    text-align: center
  }

  .page-loadmore-listitem:first-child {
    border-top: 1px solid #eee
  }

  .page-loadmore-wrapper {
    overflow: scroll
  }

  .mint-loadmore-bottom span {
    display: inline-block;
    -webkit-transition: .2s linear;
    transition: .2s linear;
    vertical-align: middle
  }

  .mint-loadmore-bottom span.is-rotate {
    -webkit-transform: rotate(180deg);
    transform: rotate(180deg)
  }

</style>

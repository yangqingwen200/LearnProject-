<template>
  <!-- 加载组件url: https://www.cnblogs.com/yuri2016/p/7045709.html -->
  <div>
    <!--<transition name="fade">
      <div v-show="showSearchBar">
        <slot name="viewSearchBar">
        </slot>
      </div>
    </transition>
-->
    <div id="loadMoreWrapper" class="page-loadmore-wrapper" ref="wrapper" :style="{ height: wrapperHeight + 'px' }">
      <mt-loadmore :top-method="loadTop" @translate-change="translateChange" @top-status-change="handleTopChange"
                   :bottom-method="loadBottom" @bottom-status-change="handleBottomChange"
                   :bottom-all-loaded="this.$store.getters.getAllLoaded"
                   ref="loadmore" :top-drop-text="topDropText" :bottom-pull-text="bottomPullText"
                   :top-pull-text="topPullText"
                   :bottom-drop-text="bottomDropText" :auto-fill=false>
        <!-- 使用slot, 父组件传递什么, 子组件就显示什么, 达到公用 -->
        <slot name="viewTemplate">
        </slot>
      </mt-loadmore>
      <div v-show="noMoreData" :class="{'no-data-show': zeroRowData}"
           style="color: grey;text-align: center;">
        <span>暂时没数据...</span>
      </div>
    </div>
  </div>
</template>

<script type="text/babel">
  import {Indicator} from 'mint-ui';
  import fetch from '../../axios/fetch';

  export default {
    props: {
      topDropText: {
        type: String,
        default: '↑ 释放刷新..'
      },
      topPullText: {
        type: String,
        default: '↓ 下拉刷新.'
      },
      bottomPullText: { //这样可以 在methods中, this.topDropText可以拿到值
        type: String,
        default: '↑ 上拉加载.'
      },
      bottomDropText: {
        type: String,
        default: '↓ 释放加载..'
      },
      reqParamInit: { //分页默认请求服务器的参数
        type: Object,
        default() {
          return {
            pageNow: 1,
            random: Date.now()
          }
        }
      },
      reqParamAdd: { // 请求服务器额外的参数
        type: Object,
        default() {
          return {}
        }
      },
      reqUrl: {
        type: String,
        default: ''
      }
    },
    data() {
      return {
        bottomStatus: '',
        wrapperHeight: 0,
        topStatus: '',
        translate: 0,
        moveTranslate: 0,
        scroll: 0,
        showSearchBar: true,
        'no-data-show': 'no-data-show',
        list: [],
        noMoreData: this.$store.getters.getNoMoreData,
        zeroRowData: false,
      }
    },
    methods: {
      scrollEve() {
        let after = document.getElementById('loadMoreWrapper').scrollTop;
        if (typeof this.$slots.viewSearchBar !== 'undefined') {
          if (this.scroll - after > 0) {
            this.showSearchBar = true; //向上滑动, 隐藏搜索栏
          } else {
            this.showSearchBar = false; //向下滑动, 显示搜索栏
          }
        } //有搜索栏才添加滚动事件, 通过是否有viewSearchBar插槽判断

        this.scroll = after;
        this.$store.commit('modPosition', after);
      },
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
        this.init(false, 'down');
      },
      loadBottom() {
        this.init(false, 'up');
      },

      init(flag, type) {
        let isLeave = this.$store.getters.getIsLeaveList; //是否离开过列表
        if (isLeave) {
          this.list = this.$store.getters.getLeaveList;
          this.$emit('init', this.list);
          this.$nextTick(function () {
            document.getElementById('loadMoreWrapper').scrollTop = this.$store.getters.getPosition;
          });
          this.reqParamInit.pageNow = this.$store.getters.getBeforeJumpPram.pageNow;
        } else {
          if (flag) {
            Indicator.open({ //弹出加载中遮罩
              spinnerType: 'fading-circle'
            });
          }
          if (type === 'down') { //下拉刷新
            this.reqParamInit.pageNow = 1;
          } else if (type === 'up') { //上拉加载
            this.reqParamInit.pageNow++;
          }
          this.$store.commit('modIsLeaveList', false);

          this.noMoreData = false;
          this.zeroRowData = false;
          let _this = this;
          let param = Object.assign(this.reqParamInit, this.reqParamAdd);

          fetch(this.reqUrl, param).then(function (response) {
            if (type === 'down') {
              _this.list = response.list;
              _this.$refs.loadmore.onTopLoaded(); //关闭下拉刷新中动画
            } else if (type === 'up') {
              _this.list = _this.list.concat(response.list);
              _this.$refs.loadmore.onBottomLoaded(); //关闭上拉加载中动画
            }
            _this.$emit('init', _this.list); //调用父组件init属性对应的方法, 更新list

            if (response.pageNow === response.pageCount) {
              _this.noMoreData = true; //没有下一页, 禁止上拉加载
            }
            if (_this.list.length === 0) {
              _this.zeroRowData = true; //0条数据, 提示没数据 距离顶部50%
            }

            if (flag) {
              Indicator.close(); //关闭加载中遮罩
            }
            _this.$store.commit('modBeforeJumpPram', param); //保留请求的参数
            _this.$store.commit('modLeaveList', _this.list); //保留返回的数据
          });
        }
      },
    },
    mounted() {
      this.init(true, 'down');
      this.$store.commit('modIsLeaveList', false);
      this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top - 60;
      document.getElementById('loadMoreWrapper').addEventListener('scroll', this.scrollEve); //添加监听滑动事件
    },
    watch: {
      'reqParamAdd': { //监听请求服务器额外的参数(一般搜索栏), 变化后重新发起请求
        handler(newValue, oldValue) {
          this.init(true, 'down');
        },
        deep: true
      }
    }
  };
</script>

<style scoped>
  .fade-enter-active {
    transition: opacity 1s;
  }

  .fade-leave-active {
    transition: opacity .5s;
  }

  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */
  {
    opacity: 0;
  }

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

  .no-data-show {
    margin-top: 50%;
  }

</style>

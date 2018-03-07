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
      <div v-show="this.$store.getters.getAllLoaded" :class="{'no-data-show': this.$store.getters.getNoDataShow}"
           style="color: grey;text-align: center;">
        <span>暂时没数据...</span>
      </div>
    </div>
  </div>
</template>

<script type="text/babel">
  import { Indicator } from 'mint-ui';
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
        'no-data-show': 'no-data-show'
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
        this.$store.commit('modLeaveList', false);

        this.init(false);
        this.$store.commit('modPullOrDrop', false);
        let _this = this;
        var interval = setInterval(function () {
          let pullOrDrop = _this.$store.getters.getPullOrDrop;
          if (pullOrDrop) {
            _this.$refs.loadmore.onTopLoaded(); //目前采用定时访问的方式 查看服务器是否响应成功, 最好是采用回调的方式
            clearInterval(interval);
          }
        }, 200);
      },
      loadBottom() {
        if (this.$store.getters.getLeaveList) {
          this.reqParamInit.pageNow = this.$store.getters.getBeforeJumpPram.pageNow + 1;
        } else {
          this.reqParamInit.pageNow++;
        }
        this.$store.commit('modLeaveList', false);

        this.$emit('get-server-data', Object.assign(this.reqParamInit, this.reqParamAdd)); //Object.assign() 合并对象

        this.$store.commit('modPullOrDrop', false);
        let _this = this;
        var interval = setInterval(function () {
          let pullOrDrop = _this.$store.getters.getPullOrDrop;
          if (pullOrDrop) {
            _this.$refs.loadmore.onBottomLoaded();
            clearInterval(interval);
          }
        }, 200);
      },

      init(flag) {
        if(flag && !this.$store.getters.getLeaveList) {
          Indicator.open({
            spinnerType: 'fading-circle'
          });
        }
        this.reqParamInit.pageNow = 1;
        this.$emit('get-server-data', Object.assign(this.reqParamInit, this.reqParamAdd));
        this.$nextTick(function () {
          document.getElementById('loadMoreWrapper').scrollTop = this.$store.getters.getPosition;
        });

        if(flag && !this.$store.getters.getLeaveList) { //清除加载时动画
          let _this = this;
          var interval = setInterval(function () {
            let pullOrDrop = _this.$store.getters.getPullOrDrop;
            if (pullOrDrop) {
              Indicator.close();
              _this.$store.commit('modPullOrDrop', false);
              clearInterval(interval);
            }
          }, 100);
        }
      },

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
    },
    mounted() {
      this.init(true);
      this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top - 60;
      document.getElementById('loadMoreWrapper').addEventListener('scroll', this.scrollEve); //添加监听滑动事件
    },
    watch: {
      'reqParamAdd': { //监听请求服务器额外的参数(一般搜索栏), 变化后重新发起请求
        handler(newValue, oldValue) {
          this.init(true);
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
    margin-top: 30%;
  }

</style>

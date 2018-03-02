<template>
  <!-- 加载组件url: https://www.cnblogs.com/yuri2016/p/7045709.html -->
  <div class="page-loadmore">
    <slot name="viewSearchBar">
    </slot>
    <div id="loadMoreWrapper" class="page-loadmore-wrapper" ref="wrapper" :style="{ height: wrapperHeight + 'px' }">
      <mt-loadmore :top-method="loadTop" @translate-change="translateChange" @top-status-change="handleTopChange"
                   :bottom-method="loadBottom" @bottom-status-change="handleBottomChange" :bottom-all-loaded="allLoaded"
                   ref="loadmore" :top-drop-text="topDropText" :bottom-pull-text="bottomPullText"
                   :bottom-drop-text="bottomDropText" :auto-fill=false>
        <!-- 使用slot, 父组件传递什么, 子组件就显示什么, 达到公用 -->
        <slot name="viewTemplate">
        </slot>
      </mt-loadmore>
    </div>
  </div>
</template>

<script type="text/babel">
  export default {
    props: {
      topDropText: '释放刷新', //写成这样, 在methods中, this.topDropText是拿不到值
      bottomPullText: { //这样可以 在methods中, this.topDropText可以拿到值
        type: String,
        default: '上拉加载'
      },
      bottomDropText: '释放加载',
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
          return {

          }
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
        allLoaded: false,
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
        this.init();
        let _this = this;
        setTimeout(function () {
          _this.$refs.loadmore.onTopLoaded();
        }, 1000);
      },
      loadBottom() {
        this.reqParamInit.pageNow++;//Object.assign() 合并对象
        this.$emit('get-server-data', Object.assign(this.reqParamInit, this.reqParamAdd));
        let _this = this;
        setTimeout(function () {
          _this.$refs.loadmore.onBottomLoaded();
        }, 1000);
      },
      init() {
        document.getElementById('loadMoreWrapper').scrollTop = 0;
        this.allLoaded = false;
        this.reqParamInit.pageNow = 1;
        this.$emit('get-server-data', Object.assign(this.reqParamInit, this.reqParamAdd));
      },
      changeAllLoaded() {
        this.allLoaded = true;
      }
    },
    mounted() {
      this.init();
      this.wrapperHeight = document.documentElement.clientHeight - this.$refs.wrapper.getBoundingClientRect().top;
    },
    watch: {
      'reqParamAdd': { //监听请求服务器额外的参数(一般搜索栏), 变化后重新发起请求
        handler(newValue, oldValue) {
          this.init();
        },
        deep: true
      }
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

<template>
  <div id="slideBar" :style="{opacity: opacity}">
    <slot name="slideBar"></slot>
  </div>
</template>

<script>
  export default {
    name: "slide",
    data() {
      return {
        clientWidth: document.documentElement.clientWidth,
        moveBegin: 0,
        opacity: 1,
        startX: 0,
        startY: 0,
        moveEndX: 0,
        moveEndY: 0,
        X: 0,
        Y: 0,
        showFlash: true,
        index: 0
      };
    },
    props: {
      topNavBarModules: {
        type: Array,
        default: [],
      },
      lastClickModule: {
        type: String,
        default: '',
        require: true
      }
    },
    mounted() {
      let _this = this;
      let elementById = document.getElementById("slideBar");
      elementById.addEventListener('touchstart', function (e) {
        _this.moveBegin = e.changedTouches[0].pageX;
        _this.startX = e.changedTouches[0].pageX;
        _this.startY = e.changedTouches[0].pageY;
      });

      elementById.addEventListener('touchmove', function (e) {
        _this.moveEndX = e.changedTouches[0].pageX;
        _this.moveEndY = e.changedTouches[0].pageY;
        _this.X = _this.moveEndX - _this.startX;
        _this.Y = _this.moveEndY - _this.startY;

        //判断第一个动作, 如果是上下滑, 不能左右滑动
        if (_this.index == 0) {
          if (Math.abs(_this.X) > Math.abs(_this.Y) && _this.X > 0) {
            console.info("向右");
            _this.showFlash = true;
          } else if (Math.abs(_this.X) > Math.abs(_this.Y) && _this.X < 0) {
            console.info("向左");
            _this.showFlash = true;
          } else if (Math.abs(_this.Y) > Math.abs(_this.X) && _this.Y > 0) {
            console.info("向下");
            _this.showFlash = false;

          } else if (Math.abs(_this.Y) > Math.abs(_this.X) && _this.Y < 0) {
            console.info("向上");
            _this.showFlash = false;
          } else {
            console.info("没滑动");
          }
        }
        _this.index++;
        if (_this.showFlash) {
          e.preventDefault();
          if (_this.opacity > 0.3) {
            _this.opacity -= 0.05;
          }
        }
      });

      elementById.addEventListener('touchend', function (e) {
        let page = e.changedTouches[0].pageX;
        if (Math.abs(page - _this.moveBegin) > _this.clientWidth / 3) {
          let length = _this.topNavBarModules.length;
          for (let i = 0; i < length; i++) {
            let topNavBarModule = _this.topNavBarModules[i];
            if (topNavBarModule.hasOwnProperty('moduleId') && topNavBarModule.moduleId === _this.lastClickModule) {
              if (page - _this.moveBegin < 0) { //右滑
                if (i !== length - 1) {
                  _this.$emit('slide-method', _this.topNavBarModules[i + 1].moduleId);
                  if (_this.clientWidth < 46 * (i + 3)) {
                    document.getElementById('topNavBar').scrollLeft = 46 * (i + 1);
                  }
                }
              } else { //左滑
                if (i !== 0) {
                  _this.$emit('slide-method', _this.topNavBarModules[i - 1].moduleId);
                  //document.getElementById('topNavBar').scrollLeft = document.getElementById('topNavBar').scrollLeft - 20;
                  document.getElementById('topNavBar').scrollLeft = (_this.clientWidth > 46 * i) ? 0 : 46 * (i - 1) - 20;
                }
              }
              break;
            }
          }
        }
        _this.index = 0;
        _this.moveBegin = 0;
        _this.opacity = 1;
      });
    }
  }
</script>

<style scoped>

</style>

<template>
  <div>
    <div v-for="item in videoSource" class="video" :style="{height: height}">
      <video :id="'video' + item.id" :height="height" :width="width"
             x5-playsinline="" playsinline="" webkit-playsinline="" poster="" preload="auto"
             @ended="playEnd(item.id)" @click="click(item.id)">
        <source :src="item.url"/>
      </video>
      <div :id="'canvas' + item.id">
        <div class="canvas-video bg-cover" :style="{backgroundImage: item.bgCover}">
        </div>
        <div class="title">
          <h4>{{item.title}}</h4>
          <small>{{item.playCount}}次播放</small>
        </div>
        <div class="play" @click="play(item.id)">
          <i class="icon-play3" style="font-size: 1.5rem;padding-left: 0.3rem;"></i>
        </div>
        <time v-html="item.duration"></time>
        <div class="avatar bg-cover-all" :style="{backgroundImage: item.header}"></div>
      </div>

      <div :id="'canvasOver' + item.id" style="display: none">
        <div class="canvas-video bg-cover" style="background: rgba(0, 0, 0, 0.5);">
          <div class="video-div">
            <i class="icon-grin"></i> 好看
            <i class="icon-angry" style="margin-left: 1rem"></i> 吐槽
          </div>
          <span class="video-span" @click="replay(item.id)">
            <i class="icon-spinner11"></i> 重播
          </span>
        </div>
        <div class="title">
          <h4>{{item.title}}</h4>
        </div>
      </div>

      <div :id="'canvasPause' + item.id" style="display: none">
        <div class="canvas-video bg-cover" style="background: rgba(0, 0, 0, 0.5);">
          <div class="play" @click="continuePlay(item.id)">
            <i class="icon-play3" style="font-size: 1.5rem;padding-left: 0.3rem;"></i>
          </div>
        </div>
        <div class="title">
          <h4>{{item.title}}</h4>
        </div>
      </div>

      <div class="comment">
        <span class="comment-source" v-html="item.source"></span>
        <span class="comment-right">
          <i class="icon-plus" style="font-size: 0.7rem"></i> 关注
          <i class="icon-bubbles4"></i> {{item.commentCount}}
            <i class="icon-share2"></i> 分享
        </span>
      </div>
    </div>
    <hr class="my-hr"/>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        height: document.documentElement.clientHeight / 3 + 'px',
        width: document.documentElement.clientWidth - 16,
        videoSource: [
          {
            id: 3,
            url: 'http://192.168.1.247:8082/app/video/mov_bbb.mp4',
            bgCover: 'url(' + require('../../assets/images/background.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount: 180
          },
          {
            id: 4,
            url: 'http://192.168.1.247:8082/app/video/mov_bbb.mp4',
            bgCover: 'url(' + require('../../assets/images/background.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount: 180
          },
          {
            id: 5,
            url: 'http://192.168.1.247:8082/app/video/mov_bbb.mp4',
            bgCover: 'url(' + require('../../assets/images/background.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount: 180
          },
          {
            id: 6,
            url: 'http://192.168.1.247:8082/app/video/mov_bbb.mp4',
            bgCover: 'url(' + require('../../assets/images/background.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount: 180
          }
        ]
      }
    },
    components: {},
    mounted() {
    },
    computed: {},
    methods: {
      click(index) {
        document.getElementById('canvasPause' + index).style.display = "";
        document.getElementById('video' + index).pause();
      },
      play(index) {
        let videoArr = document.querySelectorAll("video[id^='video']");
        videoArr.forEach(function (value) {
          if ('video' + index === value.id) {
            document.getElementById('canvas' + index).style.display = "none";
            value.play();
          } else {
            value.pause(); //其他视频暂停播放
          }
        });
      },
      replay(index) {
        document.getElementById('canvasOver' + index).style.display = "none";
        this.play(index);
      },
      continuePlay(index) {
        document.getElementById('canvasPause' + index).style.display = "none";
        this.play(index);
      },
      playEnd(index) {
        document.getElementById('canvasOver' + index).style.display = "";
      },
    }
  }
</script>

<style lang="less" scoped>
/*
  .video::-webkit-media-controls-start-playback-button {
    display: none !important;
  }

  .video::-webkit-media-controls {
    display: none !important;
  }
*/

  .comment {
    font-size: 0.8rem;
    margin-top: 0.5rem;
    .comment-source {
      margin-left: 0.8rem;
    }
    .comment-right {
      float: right;
      margin-right: 0.8rem;
      i {
        margin-left: 0.8rem;
      }
    }
  }

  .video-span {
    position: absolute;
    bottom: 0.5rem;
    left: 0.8rem;
    color: white;
    font-size: 0.8rem;
  }

  .video-div {
    position: absolute;
    top: 50%;
    left: 30%;
    font-size: 1rem;
    color: white;
  }

  .video {
    width: 100%;
    height: 2rem;
    position: relative;
    margin-bottom: 2.5rem;
    video {
      width: 100%;
      height: 100%;
      z-index: -1;
    }
    .canvas-video {
      width: 100%;
      height: 100%;
      position: absolute;
      left: 0;
      top: 0;
    }

    .play {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      background-color: rgba(0, 0, 0, .3);
      border-radius: 50%;
      color: white;
      width: 2.5rem;
      height: 2.5rem;
      text-align: center;
      line-height: 3.1rem;
    }
    .title {
      position: absolute;
      left: 0;
      top: 0;
      line-height: 0.2rem;
      width: 100%;
      color: white;
      background: linear-gradient(top, rgba(0, 0, 0, .45), rgba(0, 0, 0, 0) 100%);
      background: -webkit-linear-gradient(top, rgba(0, 0, 0, .45), rgba(0, 0, 0, 0) 100%);
      small {
        font-size: 0.7rem;
        padding-left: 0.8rem;
      }
      H4 {
        padding-left: 0.8rem;
        font-size: 0.9rem;
      }
    }
    time {
      position: absolute;
      right: 0.8rem;
      bottom: 0.5rem;
      font-size: 0.7rem;
      padding: 0.1rem 0.25rem;
      border-radius: 1rem;
      background-color: rgba(0, 0, 0, 0.5);
      color: #f4f4f4;
    }
    .avatar {
      width: 2.5rem;
      height: 2.5rem;
      border-radius: 50%;
      position: absolute;
      bottom: -0.4rem;
      left: 0.8rem;
    }
  }

  .bg-cover {
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center center;
  }

  .bg-cover-all {
    background-size: 100% 100%;
    background-repeat: no-repeat;
    background-position: center center;
  }
</style>

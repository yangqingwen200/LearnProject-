<template>
  <div>
    <div v-for="item in videoSource" class="video" :style="{height: height}">
      <video :id="'video' + item.id" :height="height" :width="width" :src="item.url"
             x5-playsinline="" playsinline="" webkit-playsinline="" poster="" preload="auto"
             @ended="playEnd(item.id)" @pause="pause"></video>
      <div :id="'canvas' + item.id">
        <div class="canvas-video bg-cover" :style="{backgroundImage: item.bgCover}">
        </div>
        <div class="title">
          <h4>{{item.title}}</h4>
          <small>{{item.playCount}}次播放</small>
        </div>
        <div class="play" @click="play(item.id)">
          <icon name="play"></icon>
        </div>
        <time v-html="item.duration"></time>
        <div class="avatar bg-cover-all" :style="{backgroundImage: item.header}"></div>
      </div>

      <div class="comment">
        <span class="comment-source" v-html="item.source"></span>
        <span class="comment-right">
          <span>
            <img src="../../assets/images/f196.png" style="width: 1.1rem;"/>
            <span style="vertical-align: super;">关注</span>
          </span>
          <span style="margin-left: 1.5rem">
            <img src="../../assets/images/ali_message.png" style="width: 1.1rem;"/>
            <span style="vertical-align: super;" v-html="item.commentCount"></span>
          </span>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
  import icon from '../commons/iconSvg'

  export default {
    data() {
      return {
        height: document.documentElement.clientHeight / 3 + 'px',
        width: document.documentElement.clientWidth - 16,
        videoSource: [
          {
            id: 1,
            url: 'http://192.168.1.247:8082/app/video/mov_bbb.mp4',
            bgCover: 'url(' + require('../../assets/images/background.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount : 180
          },
          {
            id: 2,
            url: 'http://192.168.1.247:8082/app/video/oceans.mp4',
            bgCover: 'url(' + require('../../assets/images/background1.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount : 180
          },
          {
            id: 3,
            url: 'http://192.168.1.247:8082/app/video/oceans.mp4',
            bgCover: 'url(' + require('../../assets/images/background.jpg') + ')',
            header: 'url(' + require('../../assets/images/header.jpeg') + ')',
            title: '习大大发布视频, 建议大家都看',
            duration: '19:20:20',
            source: '悟空问答',
            playCount: 20,
            commentCount : 180
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
            commentCount : 180
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
            commentCount : 180
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
            commentCount : 180
          }
        ]
      }
    },
    components: {
      icon
    },
    mounted() {
    },
    computed: {},
    methods: {
      play(index) {
        document.getElementById('video' + index).style.display = "";
        document.getElementById('canvas' + index).style.display = "none";
        document.getElementById('video' + index).play();
      },
      playEnd(index) {
        document.getElementById('canvas' + index).style.display = "";
        document.getElementById('video' + index).style.display = "none";
      },
      pause() {
      }
    }
  }
</script>

<style lang="less" scoped>
  .video::-webkit-media-controls-start-playback-button {
    display: none !important;
  }

  .video::-webkit-media-controls {
    display: none !important;
  }

  .comment {
    font-size: 0.8rem;
    margin-top: 0.5rem;
    margin-bottom: 4rem;
    .comment-source {
      margin-left: 0.8rem;
      vertical-align: -webkit-baseline-middle
    }
    .comment-right {
      float: right;
      margin-right: 0.8rem;
    }
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
      svg {
        color: #fff;
        font-size: 0.4rem;
      }
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

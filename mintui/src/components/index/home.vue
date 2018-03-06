<template>
  <div>
    <div>
      <div class="lists">
        <div @click="changeModules('focus')" :class="{red: module === 'focus'}">关注</div>
        <div @click="changeModules('travel')" :class="{red: module === 'travel'}">推荐</div>
        <div @click="changeModules('game')" :class="{red: module === 'game'}">游戏</div>
        <div @click="changeModules('finance')" :class="{red: module === 'finance'}">财经</div>
        <div @click="changeModules('car')" :class="{red: module === 'car'}">汽车</div>
        <div @click="changeModules('home')" :class="{red: module === 'home'}">居家</div>
        <div @click="changeModules('society')" :class="{red: module === 'society'}">科技</div>
        <div @click="changeModules('video')" :class="{red: module === 'video'}">视频</div>
        <div @click="changeModules('qa')" :class="{red: module === 'qa'}">问答</div>
        <div @click="changeModules('military')" :class="{red: module === 'military'}">军事</div>
        <div @click="changeModules('sport')" :class="{red: module === 'sport'}">体育</div>
        <div @click="changeModules('international')" :class="{red: module === 'international'}">国际</div>
        <div @click="changeModules('health')" :class="{red: module === 'health'}">健康</div>
        <div @click="changeModules('house')" :class="{red: module === 'house'}">房产</div>
        <div @click="changeModules('fashion')" :class="{red: module === 'fashion'}">时尚</div>
        <div @click="changeModules('school')" :class="{red: module === 'school'}">高校</div>
        <div @click="changeModules('fiction')" :class="{red: module === 'fiction'}">小说</div>
        <div @click="changeModules('history')" :class="{red: module === 'history'}">历史</div>
        <div @click="changeModules('hots')" :class="{red: module === 'hots'}">热点</div>
        <div @click="changeModules('digital')" :class="{red: module === 'digital'}">数码</div>
      </div>
      <div>
        <hr style="margin-top: 6px;background-color: #ddd;height: 1px;border: none">
      </div>
    </div>

    <div>
      <load-more ref="children" @get-server-data="getServerData" :req-param-add="reqParamAdd">
        <div slot="viewTemplate">
          <ul style="margin: 0;padding: 0;">
            <li v-for="item in list" @click="articleDetail(item.userId)" style="margin-top: -10px;">
              <table border="0" style="width: 100%">
                <tr>
                  <td><span v-text="item.title"></span></td>
                  <td rowspan="2" style="width: 30%">
                    <img :src="item.middle_image" style="width: 100%;border-radius: 3px;"/>
                  </td>
                </tr>
                <tr>
                  <td style="font-size: 0.6rem;color: #888">
                    <span v-text="item.source"></span>
                    <span v-text="item.comments_count" style="margin-left: 3px"></span>评论
                    <span v-text="item.publish" style="margin-left: 3px"></span>
                  </td>
                </tr>
                <tr>
                  <td colspan="3">
                    <hr style="border:0.07rem dotted #ccc;padding: 0"/>
                  </td>
                </tr>
              </table>
            </li>
          </ul>
         <!-- <div v-if="list.length === 0" style="color: grey;text-align: center; margin-top: 40%">
            暂无数据...
          </div>-->
        </div>
      </load-more>
    </div>
  </div>
</template>

<script>
  import loadMore from '../commons/loadMore'

  export default {
    name: "more",
    data() {
      return {
        module: 'game',
        red: 'red',
        selected: 'all',
        phone: '',
        password: '',
        list: [],
        reqParamAdd: {
          pageSize: 10,
          category: ''
        },
      }
    },
    components: {
      loadMore,
    },
    methods: {
      getServerData(reqParam) {
        if (this.$store.getters.getLeaveList) {
          this.list = this.$store.getters.getList;
        } else {
          this.$http.post('/app/index_appUser.do', reqParam).then(function (response) {
            if (response.data.code === 1000) {
              if (reqParam.pageNow === 1) {
                this.list = response.data.list;
              } else {
                this.list = this.list.concat(response.data.list);
              }
            }
            this.$store.commit('modBeforeJumpPram', reqParam);
            this.$store.commit('modList', this.list);
          }.bind(this));
        }
      },
      articleDetail(articleId) {
        console.info(articleId);
        this.$store.commit('modLeaveList', true);
      },
      changeModules(modules) {
        this.module = modules;
        this.reqParamAdd.category = modules;
        console.info(modules);
      }
    }
  }
</script>

<style scoped>
  .lists {
    overflow-y: hidden;
    overflow-x: auto;
    white-space: nowrap;
  }

  .lists div {
    display: inline-table;
    vertical-align: top;
    margin-left: 0;
    padding: 2px 7px;
  }

  .red {
    color: #26a2ff !important;
    background-color: #f0f0f0;
    border-radius: 10px;
  }
</style>

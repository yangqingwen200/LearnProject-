<template>
  <div>
    <div>
      <top-nav-bar>
        <div slot="topBar">
          <div v-for="item in topNavBarModules" @click="changeModules(item.moduleId)"
               :class="{'top-nar-bar': module === item.moduleId}">{{item.name}}
          </div>
        </div>
      </top-nav-bar>
    </div>
    <div>
      <load-more ref="children" @get-server-data="getServerData" :req-param-add="reqParamAdd">
        <div slot="viewTemplate">
          <ul style="margin: 0;padding: 0;">
            <li v-for="item in list" @click="articleDetail(item.id)" style="margin-top: -5px;">
              <table border="0" style="width: 100%">
                <tr>
                  <td><span v-text="item.title" :class="{'history-id': historyId.has(item.id)}"></span></td>
                  <td rowspan="2" style="width: 30%">
                    <img :src="item.middle_image" style="width: 100%;border-radius: 3px;"/>
                  </td>
                </tr>
                <tr>
                  <td style="font-size: 0.7rem;color: #a9acb1">
                    <span v-text="item.source"></span>
                    <span v-text="item.comments_count" style="margin-left: 3px"></span>评论
                    <span v-text="item.publish" style="margin-left: 3px"></span>
                  </td>
                </tr>
                <tr>
                  <td colspan="3">
                    <hr style="background-color: #ddd;height: 1px;border: none;">
                  </td>
                </tr>
              </table>
            </li>
          </ul>
        </div>
      </load-more>
    </div>
  </div>
</template>

<script>
  import loadMore from '../commons/loadMore'
  import topNavBar from '../commons/topNavBar'

  export default {
    name: "more",
    data() {
      return {
        topNavBarModules: [  //模块列表, 应当从服务器获取
          {name: '关注', moduleId: 'focus'},
          {name: '推荐', moduleId: 'travel'},
          {name: '游戏', moduleId: 'game'},
          {name: '财经', moduleId: 'finance'},
          {name: '居家', moduleId: 'home'},
          {name: '科技', moduleId: 'society'},
          {name: '国际', moduleId: 'international'},
          {name: '健康', moduleId: 'health'},
          {name: '汽车', moduleId: 'car'},
          {name: '历史', moduleId: 'history'},
          {name: '数码', moduleId: 'digital'},
          {name: '时尚', moduleId: 'fashion'},
        ],
        module: this.$store.getters.getLastClickModule, //进入页面默认选中模块
        historyId: this.$store.getters.getHistoryId,
        'history-id': 'history-id',
        list: [],
        reqParamAdd: {
          pageSize: 10,
          category: this.$store.getters.getLastClickModule
        },
      };
    },
    components: {
      loadMore,
      topNavBar
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
        this.$store.commit('modHistoryId', articleId); //保存被点击过文章的id, 渲染列表显示不同颜色
        this.$store.commit('modLeaveList', true);
        this.$router.push({
          name: 'articleDetail',
          params: {
            id: articleId
          }
        });
      },
      changeModules(modules) {
        if (this.$store.getters.getLastClickModule === modules) {
          return;  //当前处于这个模块, 再次点击, 直接返回.
        }
        this.list = []; //先清空list, 否则模块之间切换会有闪动
        this.$store.commit('modLeaveList', false);
        this.$store.commit('modLastClickModule', modules); //保存最后点击模块
        this.module = modules;
        this.reqParamAdd.category = modules;
      }
    },
  }
</script>

<style scoped>
  .history-id {
    color: #a9acb1;
  }
</style>

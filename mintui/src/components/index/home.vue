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
      <load-more ref="children" @init="init" :req-param-add="reqParamAdd" :req-url="reqUrl">
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
                    <hr class="my-hr">
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
        topNavBarModules: this.$store.getters.getTopNavBarModules,
        module: this.$store.getters.getLastClickModule, //进入页面默认选中模块
        historyId: this.$store.getters.getHistoryId,
        'history-id': 'history-id',
        list: [],
        reqParamAdd: {
          pageSize: 10,
          category: this.$store.getters.getLastClickModule
        },
        reqUrl: '/app/index_appUser.do'
      };
    },
    components: {
      loadMore,
      topNavBar
    },
    methods: {
      init(param) {
        this.list = param;
      },
      articleDetail(articleId) {
        this.$store.commit('modHistoryId', articleId); //保存被点击过文章的id, 渲染列表显示不同颜色
        this.$store.commit('modIsLeaveList', true);
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
        this.$store.commit('modIsLeaveList', false);
        this.$store.commit('modLastClickModule', modules); //保存最后点击模块
        this.module = modules;
        this.reqParamAdd.category = modules;
      }
    },
    mounted() {
    }
  }
</script>

<style scoped>
  .history-id {
    color: #a9acb1;
  }
</style>

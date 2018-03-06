<template>
  <div>
    <div class="page-swipe">
      <mt-swipe :auto="0" :show-indicators="true">
        <mt-swipe-item>
          <img src="../../assets/1.jpg" :class="{imgSize: true}"/>
        </mt-swipe-item>
        <mt-swipe-item>
          <img src="../../assets/2.jpg" :class="{imgSize: true}"/>
        </mt-swipe-item>
        <mt-swipe-item>
          <img src="../../assets/3.jpg" :class="{imgSize: true}"/>
        </mt-swipe-item>
      </mt-swipe>
    </div>
    <div>
      <span style="font-weight: bold;margin-bottom: 0.31rem">精选驾校</span>
      <ul style="margin:0;padding:0">
        <li v-for="item in school" style="list-style-type:none;" @click="schoolDetail(item.id)">
          <table style="padding: 0.125rem;">
            <tr>
              <td rowspan="3" style="width: 25%;padding-right: 1rem"><img src="../../assets/logo.png"
                                                                          style="width: 100%"/></td>
              <td colspan="2">驾校: <span v-text="item.name"></span></td>
            </tr>
            <tr>
              <td colspan="2">地址: <span v-text="item.address"></span></td>
            </tr>
            <tr>
              <td>城市: <span v-text="item.city_name"></span></td>
              <td style="text-align: right;">评分: <span v-text="item.star_num"></span></td>
            </tr>
            <tr>
              <td colspan="3">
                <hr style="border: 0.06rem dotted #036"/>
              </td>
            </tr>
          </table>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: "home",
    data() {
      return {
        pageNow: 1,
        school: []
      }
    },
    methods: {
      getInitSchool() {
        this.$http.post('/app/getSchool_appUser.do', {pageNow: this.pageNow}).then(function (response) {
          if (response.data.code === 1000) {
            this.school = response.data.list;
          }
        }.bind(this)).catch((error) => {
        });
      },
      schoolDetail(id) {
        this.$router.push({
          name: 'detail',
          params: {
            id: id
          }
        });
      }
    },
    mounted() {
      this.getInitSchool();
    }
  }
</script>

<style scoped>
  .page-swipe .mint-swipe {
    height: 12.5rem;
    color: #fff;
    font-size: 1.87rem;
    text-align: center;
    margin-bottom: 1.25rem;
  }

  .page-swipe .mint-swipe-item {
    line-height: 12.5rem;
  }

  .page-swipe-desc {
    text-align: center;
    color: #666;
    margin-bottom: 0.31rem;
  }

  .imgSize {
  }
</style>

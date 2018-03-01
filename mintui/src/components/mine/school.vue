<template>

  <div>
    <div ref="elememt" style="margin-bottom: 60px">
      <ul>
        <li v-for="item in list" style="list-style-type:none;">
          <mt-field label="驾校名称" v-model="item.name" readonly :style="[inputStyle]"></mt-field>
          <mt-field label="地址" v-model="item.address" readonly :style="[inputStyle]"></mt-field>
          <mt-field label="城市" v-model="item.city_name" readonly :style="[inputStyle]"></mt-field>
          <mt-field label="评分" v-model="item.star_num" readonly :style="[inputStyle]"></mt-field>
          <mt-field label="加入时间" v-model="item.create_date" readonly :style="[inputStyle]"></mt-field>
        </li>
      </ul>
    </div>
    <!--<div style="margin-top: 1px;margin-bottom: 50px">
      <mt-button type="default" size="large">查看更多</mt-button>
      <br>
    </div>-->

    <!--
    clientHeight、offsetHeight、scrollTop
    https://zhidao.baidu.com/question/332211315823909605.html?qbl=relate_question_0&word=html%D2%C6%B6%AF%B6%CB%20%C9%CF%C0%AD%CB%A2%D0%C2

    https://www.cnblogs.com/xiaojingyuan/p/6008424.html
     -->
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import {MessageBox} from 'mint-ui';
  export default {
    name: "school",
    data() {
      return {
        inputStyle: {
          fontSize: '12rem',
          fontWeight: 'bold'
        },
        pageNow: 1,
        allLoaded: true,
        list: [
          {
            "city_name": "哈尔滨市",
            "address": "哈尔滨市呼兰区",
            "name": "凯旋驾校",
            "logo": "http://dev.hncarlife.com:2010/drivingschool/logo/20160606231529928891.jpg",
            "star_num": 1,
            "id": 1,
            "create_date": "2016-05-28 19:13:52"
          },
          {
            "city_name": "哈尔滨市",
            "address": "哈尔滨市香坊区香滨路9号11组",
            "name": "龙地驾校",
            "logo": "http://image.jspxpt.com/drivingschool/logo/longdijiaxiao_logo.jpg",
            "star_num": 6,
            "id": 2,
            "create_date": "2016-05-28 19:13:52"
          },
          {
            "city_name": "哈尔滨市",
            "address": "哈尔滨市宾县二龙山国家4A级风景区境内",
            "name": "龙广驾校",
            "logo": "http://image.jspxpt.com/drivingschool/logo/longguangjiaxiao_logo.jpg",
            "star_num": 3.3,
            "id": 3,
            "create_date": "2016-05-28 19:13:52"
          }
        ]
      }
    },
    methods: {
      scrollEve() {
        window.addEventListener('scroll', () => {
         //console.log("window.scrollY: " + window.scrollY);
         //let height= this.$refs.elememt.offsetHeight;
         //let scrollHeight= this.$refs.elememt.scrollHeight;
         let clientHeight= this.$refs.elememt.clientHeight;
         // console.info("height: " + height);
          //console.info("scrollHeight: " + scrollHeight);
          //console.info("clientHeight: " + clientHeight);

          console.info("window.scrollY: " + window.scrollY + ", clientHeight: " + clientHeight + ", precent: " + (clientHeight - window.scrollY)/clientHeight);
          if(((clientHeight - window.scrollY)/clientHeight <= 0.23)) {
           if(this.allLoaded) {
             Toast({
               message: "全部加载完毕...",
               position: 'bottom',
               duration: 1500
             });
           }
            this.allLoaded = false;
          } else {
            this.allLoaded = true;
          }
          console.info(this.allLoaded);
          /*let data = {pageNow: this.pageNow};
           this.$http.post('/app/getSchool_appUser.do', data).then(function (response) {
             if (response.data.code === 1000) {
               this.logo = response.data.userInfo.logourl;
               this.name = response.data.userInfo.name;
               this.telephone = response.data.userInfo.telephone;
               this.bir = response.data.userInfo.bir;
             } else {
               Toast({
                 message: response.data.msg,
                 position: 'middle',
                 duration: 2000
               });
             }
             this.pageNow ++;
           }.bind(this)).catch((error) => {
             Toast({
               message: '网络错误...',
               position: 'middle',
               duration: 2000
             });
           });*/
        });
      }
    },
    mounted() {
      this.scrollEve();
    }
  }
</script>

<style scoped>

</style>

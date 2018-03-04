<template>
  <div style="text-align: center;">
    <img src="../../assets/logo.png" style="width: 45%"/>
    <mt-field readonly :style="[inputStyle]" label="驾校名称" v-model="detail.name"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="地址" v-model="detail.address"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="城市" v-model="detail.city_name"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="联系人" v-model="detail.link_man"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="联系电话" v-model="detail.link_tel"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="评分" v-model="detail.star_num"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="有无考场" v-model="detail.is_examroom == 1 ? '有考场' : '无考场'"></mt-field>
    <mt-field readonly :style="[inputStyle]" type="textarea" label="介绍" rows="4" v-model="detail.introduction"></mt-field>
    <mt-field readonly :style="[inputStyle]" label="建校时间" v-model="detail.create_date"></mt-field>
    <mt-button type="primary" size="large" @click="joinUs">我要报名</mt-button>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui';

  export default {
    name: "schoolDetail",
    data() {
      return {
        detail: {},
        inputStyle: {
          fontSize: '1rem',
          fontWeight: 'bold'
        },
      }
    },
    methods: {
      init() {
        let id = this.$route.params.id;
        this.$http.post('/app/schoolDetail_appUser.do', {id: id}).then(function (response) {
          if (response.data.code === 1000) {
            this.detail = response.data.detail;
          }
        }.bind(this)).catch((error) => {
        });
      },
      joinUs() {
        Toast({
          message: '先交学费再报名...',
          position: 'middle',
          duration: 2000,
          className: 'show'
        });
      }
    },
    mounted() {
      this.init();
    }
  }
</script>

<style scoped>
  .show {
    padding: 3rem;
    top: 80%;
  }
</style>

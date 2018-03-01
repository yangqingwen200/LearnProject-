<template>
  <div>
    <Button type="success" v-on:click="addFive">Add Five</Button>
    <Button type="error" v-on:click="deleteFive">Delete Five</Button>
  </div>
</template>
<script>
export default {
  name: 'Apple',
  data () {
    return {
      price: 5
    }
  },
  methods: {
    addFive: function () {
      console.info("vue_2 add");
      this.$store.dispatch("increments", this.price).then(()=>{console.info("等increments 执行完毕, 才执行then里面代码")}); //调用vuex/index.js文件中actions中increments方法
    },
    deleteFive: function () {
      console.info("vue_2 delete");
      this.$store.dispatch("decrements", this.price);  //调用vuex/index.js文件中actions中decrements方法
    }
  }
}
</script>

<style scoped>
</style>

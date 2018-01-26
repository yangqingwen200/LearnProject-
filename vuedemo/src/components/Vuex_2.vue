<template>
  <div>
    <button v-on:click="addFive">Add Five</button>
    <button v-on:click="deleteFive">Delete Five</button>
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
      this.$store.dispatch("increments", this.price); //调用vuex/index.js文件中actions中increments方法
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

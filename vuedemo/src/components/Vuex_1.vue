<template>
  <div>
    <button v-on:click="addOne">Add One</button>
    <button v-on:click="deleteOne">Delete One</button>
  </div>
</template>
<script>
export default {
  name: 'Apple',
  data () {
    return {
      price: 1
    }
  },
  methods: {
    addOne: function () {
      console.info("vue_1 add");
      this.$store.commit("increment", this.price);  //调用vuex/index.js文件中mutations中increment方法
    },
    deleteOne: function () {
      console.info("vue_1 delete");
      this.$store.commit("decrement", this.price); //调用vuex/index.js文件中mutations中decrement方法
    }
  }
}
</script>

<style scoped>
</style>

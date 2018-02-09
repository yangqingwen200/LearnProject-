<template>
  <div>
    <h1>{{ this.$route.params.color }} Apple...</h1><br/>
    <h1>{{ this.$route.params}}  </h1> 路由路径参数: {"color": "red"}, 指url路径参数
    <h1>{{ this.$route.path}} </h1>  路由路径结果: /apple/Red
    <h1>{{ this.$route.query}} </h1>  路由请求参数: { "id": "123" }, 指跟在问号后面的参数
  </div>
</template>
<script>
export default {
  name: 'Apple',
  data () {
    return {
      msg: 'I`m Apple'
    }
  }
}
</script>

<style scoped>
</style>

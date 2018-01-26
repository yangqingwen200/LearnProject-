<template>
  <div>
    <h1>{{ msg }}</h1>
    <router-link to="/banana/bananaChild">BananaChild</router-link>

    <!-- 有2个router-view标签,所以当点击BananaChild, 就会放置2份对应的component就会放在这里  -->
    <router-view/>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'Banana',
  data () {
    return {
      msg: 'I`m Banana'
    }
  }
}
</script>

<style scoped>
</style>

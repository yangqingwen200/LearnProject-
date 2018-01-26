<template>
  <div>
    <h1>{{ this.$route.params.color }} Apple...</h1><br/>
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

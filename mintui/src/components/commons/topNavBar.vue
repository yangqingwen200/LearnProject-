<template>
  <div>
    <div id="topNavBar" class="lists">
      <slot name="topBar"></slot>
    </div>
    <div>
      <hr style="margin-top: 6px;background-color: #ddd;height: 1px;border: none">
    </div>
  </div>
</template>

<script>
  export default {
    name: "top-nav-bar",
    data() {
      return {
        'top-nar-bar': 'top-nar-bar',
      }
    },
    props: {
    },
    methods: {
    },
    mounted() {
      let _this = this;
      document.getElementById('topNavBar').addEventListener('scroll', function () {
        _this.$store.commit('modTopNavBarPost', this.scrollLeft);
      });
      this.$nextTick(function () {
        document.getElementById('topNavBar').scrollLeft = this.$store.getters.getTopNavBarPost;
      });
    }
  }
</script>

<style scoped>
  .lists {
    overflow-y: hidden;
    overflow-x: auto;
    white-space: nowrap;
  }

  .lists div {
    display: inline-table;
    vertical-align: top;
    margin-left: 0;
    padding: 2px 7px;
  }

  .top-nar-bar {
    color: #26a2ff !important;
    background-color: #f0f0f0;
    border-radius: 10px;
  }
</style>

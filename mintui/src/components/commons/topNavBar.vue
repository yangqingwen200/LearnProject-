<template>
  <div>
    <div id="topNavBar" class="lists">
      <div v-for="item in topNavBarModules" @click="topNavBar(item.moduleId)"
           :class="{'top-nar-bar': lastClickModule === item.moduleId}">{{item.name}}
      </div>
      <slot name="topBar"></slot>
    </div>
    <div>
      <hr class="my-hr" style="margin-top: 6px;">
    </div>
  </div>
</template>

<script>
  export default {
    name: "top-nav-bar",
    data() {
      return {
      }
    },
    props: {
      topNavBarModules: {
        type: Array,
        default: [],
        require: true
      },
      lastClickModule: {
        type: String,
        default: '',
      }
    },
    methods: {
      topNavBar(module) {
        this.$emit('change-module', module);
      }
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

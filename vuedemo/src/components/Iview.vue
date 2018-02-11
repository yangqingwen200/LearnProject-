<template>
  <div>
    <Input v-model="value" placeholder="Enter something..." style="width: 300px"></Input><br/>
    <span v-text="value" @click="showValue"></span>

    <Modal v-model="isShow" title="Common">
      <span v-text="value"></span>
    </Modal>

    <Menu active-name="1-2" :open-names="['1']" @on-select="selectMenu">
      <Submenu name="1">
        <template slot="title">
          <Icon type="ios-analytics"></Icon>
          Navigation One
        </template>
        <MenuGroup title="Item 1">
          <MenuItem name="1-1">Option 1</MenuItem>
          <MenuItem name="1-2">Option 2</MenuItem>
        </MenuGroup>
        <MenuGroup title="Item 2">
          <MenuItem name="1-3">Option 3</MenuItem>
          <MenuItem name="1-4">Option 4</MenuItem>
        </MenuGroup>
      </Submenu>
    </Menu>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        value: '',
        isShow: false
      }
    },
    methods: {
      showValue: function () {
        this.isShow = true
      },
      selectMenu: function (active) {
        alert(active);
      }
    }
  }
</script>

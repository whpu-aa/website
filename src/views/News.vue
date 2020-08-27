<template>
  <div :class="main">
    <el-header>
      <el-row>
        <el-input
          v-model="input"
          placeholder="请输入内容"
          class="search"
        ></el-input>
        <el-button icon="el-icon-search" circle></el-button>
      </el-row>
    </el-header>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchtext: ""
    };
  }
};
</script>
<style scoped>
.main {
  display: flex;
}
.el-header {
  display: flex;
  justify-content: space-between;
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.search{
  min-width: 200px;
}
</style>

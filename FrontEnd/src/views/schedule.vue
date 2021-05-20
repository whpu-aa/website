<template>
  <div class="out">
    <el-row class="top">
      <el-col :span="4" class="topinf">协会日历</el-col>
      <el-col :span="8">
        <el-input v-model="searchData" placeholder="search">
          <el-button icon="el-icon-search" circle></el-button>
        </el-input>
      </el-col>
      <el-col :span="1">
        <el-badge is-dot class="bell">
          <el-button icon="el-icon-bell" circle></el-button>
        </el-badge>
      </el-col>
      <el-col :span="3">
        <el-avatar :size="35" :src="avaterUrl" fit="contain"></el-avatar>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchData: "",
      avaterUrl: require("../assets/avater1.png"),
    };
  },
};
</script>
<style scoped>
.top {
  display: flex;
  align-items: center;
}
.bell >>> .el-button {
  background: none;
  padding: 0;
  border: 0;
}
</style>

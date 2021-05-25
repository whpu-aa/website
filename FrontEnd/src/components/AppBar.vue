<template>
  <div class="nav">
    <img class="logo" src="@/assets/logo.png"/>
    <el-tabs v-model="activeName" @tab-click="tabClick">
      <el-tab-pane
          v-for="(item ,index) in site"
          :name="item"
          :label="item"
          :key="index"
      ></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: "主页",
      site: ['主页', '登录', '注册', '关于我们', '新闻介绍', '部门介绍', '新闻', '上传代码', '展示代码'],
      page:['/','Login','SignUp','About','NewsArticles','GroupIntroduce','News','updateCode','showCode']
    };
  },
  methods: {
    tabClick() {
      for (let i = 0; i < this.site.length; i++) {
        if (this.activeName === this.site[i])
          this.$router.push(this.page[i]);
      }
    },
  },
};
</script>

<style>
.nav {
  position: fixed;
  z-index: 1000;

  top: 0;
  height: 48px; /* 如果你改变这个高度，也需要在App.vue里面改变div#app的margin-top来留出相同的空位 */
  width: 100%;

  padding-left: 20px;

  display: flex;
  align-items: center;

  background-color: white; /* TODO: 设计一个颜色 */
  box-shadow: 0 3px 3px 0 #ccc;
}

.logo {
  width: 40px;
  vertical-align: middle;
  margin: 0 10px;
}

.nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;

  margin: 0 10px;
}

/*.nav a.router-link-exact-active {*/
/*  color: #42b983;*/
/*  text-decoration: none;*/
/*}*/
</style>

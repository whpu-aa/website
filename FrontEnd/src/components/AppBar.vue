<template>
  <!--  <nav class="nav">-->
  <!--    <img class="logo" src="@/assets/logo.png" />-->
  <!--    <router-link to="/">主页</router-link>-->
  <!--    <router-link to="/Login">登录</router-link>-->
  <!--    <router-link to="/SignUp">注册</router-link>-->
  <!--    <router-link to="/about">关于我们</router-link>-->
  <!--    <router-link to="/NewsArticles">新闻文章</router-link>-->
  <!--    <router-link to="/GroupIntroduce">部门介绍</router-link>-->
  <!--    <router-link to="/News">新闻</router-link>-->
  <!--    <router-link to="/updateCode">上传代码</router-link>-->
  <!--    <router-link to="/showcode">展示代码</router-link>-->
  <!--  </nav>-->
  <div class="nav">
    <img class="logo" src="@/assets/logo.png" />
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="主页" name="Home"></el-tab-pane>
      <el-tab-pane label="登录" name="Login"></el-tab-pane>
      <el-tab-pane label="注册" name="SignUp"></el-tab-pane>
      <el-tab-pane label="关于我们" name="About"></el-tab-pane>
      <el-tab-pane label="新闻文章" name="NewsArticles"></el-tab-pane>
      <el-tab-pane label="部门介绍" name="GroupIntroduce"></el-tab-pane>
      <el-tab-pane label="新闻" name="News"></el-tab-pane>
      <el-tab-pane label="上传代码" name="updateCode"></el-tab-pane>
      <el-tab-pane label="展示代码" name="showCode"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: "Home",
    };
  },
  methods: {
    handleClick() {
      console.log(this.activeName);
      console.log(this.$route.name);
      // console.log(this.$route);
      // this.$route =;
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

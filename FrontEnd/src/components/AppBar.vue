<template>
  <nav class="nav">
    <img class="logo" src="@/assets/logo.png" />
    <router-link to="/">主页</router-link>
    <router-link to="/Login">登录</router-link>
    <router-link to="/SignUp">注册</router-link>
    <router-link to="/about">关于我们</router-link>
    <router-link to="/NewsArticles">新闻文章</router-link>
    <router-link to="/GroupIntroduce">部门介绍</router-link>
    <router-link to="/News">新闻</router-link>
    <router-link to="/updateCode">上传代码</router-link>
    <router-link to="/showcode">展示代码</router-link>
  </nav>
</template>

<script>
export default {
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

.nav a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}
</style>

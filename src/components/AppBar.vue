<template>
  <nav class="nav">
    <img class="logo" src="@/assets/logo.png" />
    <router-link to="/">主页</router-link>
    <router-link to="/about">关于我们</router-link>
    <router-link to="/NewsArticles">新闻文章</router-link>
  </nav>
</template>

<style scoped>
.nav {
  position: fixed;
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

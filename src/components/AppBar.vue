<template>
  <nav class="nav">
    <router-link to="/">主页</router-link>|
    <router-link to="/about">关于我们</router-link>
  </nav>
</template>

<style scoped>
.nav {
  position: fixed;
  top: 0;
  height: 48px; /* 如果你改变这个高度，也需要在App.vue里面改变div#app的margin-top来留出相同的空位 */
  width: 100%;
}

.nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
}

.nav a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}
</style>

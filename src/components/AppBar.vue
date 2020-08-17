<template>
  <nav class="nav">
    <router-link to="/">主页</router-link>|
    <router-link to="/about">关于我们</router-link>
  </nav>
</template>

<style scoped>
.nav {
  padding: 30px;
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

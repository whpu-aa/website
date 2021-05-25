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
  <div>
    <div class="nav">
      <img class="logo" src="@/assets/logo.png" />
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane
            v-for="(item ,index) in site"
            :name="item"
            :label="item"
            :key="index"
        ></el-tab-pane>
      </el-tabs>
    </div>
      <div v-if="activeName ==='主页'">切换到Home</div>
      <div v-else-if="activeName ==='登录'">切换到Login</div>
      <div v-else-if="activeName === '注册'">切换到SingUp</div>
      <div v-else-if="activeName === '关于我们'">切换到About</div>
      <div v-else-if="activeName === '新闻介绍'">切换到News</div>
      <div v-else-if="activeName === '部门介绍'">切换到Group</div>
      <div v-else-if="activeName === '新闻'">切换到News</div>
      <div v-else-if="activeName === '上传代码'">切换到updateCode</div>
      <div v-else-if="activeName === '展示代码'">切换到showCode</div>
    <div>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: "Home",
      site:['主页','登录','注册','关于我们','新闻介绍','部门介绍','新闻','上传代码','展示代码'],
    };
  },
  methods: {
    handleClick() {
      console.log(this.activeName);
      console.log(this.$route.name);
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

<template>
  <div :class="main">
    <el-header>
      <el-input
        v-model="input"
        placeholder="请输入内容"
        class="search"
      ></el-input>
      <el-button icon="el-icon-search" circle></el-button>
    </el-header>
    <el-main>
      <more-article-item
        v-for="info in infos"
        :key="info.id"
        :info="info"
      ></more-article-item>
    </el-main>
  </div>
</template>

<script>
import MoreArticleItem from "../components/MoreArticleItem";
export default {
  components: {
    MoreArticleItem
  },
  data() {
    return {
      searchtext: "",
      /*传递给子组件的对象数组 */
      infos: [
        {
          id: 1,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        },
        {
          id: 2,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        }
      ]
    };
  }
};
</script>
<style scoped>
.main {
  display: flex;
}
.el-input {
  width: 300px;
}
.el-button {
  margin-top: 10px;
  margin-left: 10px;
  width: 40px;
  height: 40px;
}
.el-header {
  justify-content: center;
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.search {
  min-width: 200px;
}
</style>

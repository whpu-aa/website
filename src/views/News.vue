<template>
  <div>
    <el-header>
      <el-input
        v-model="inputText"
        placeholder="请输入内容"
        class="search"
      ></el-input>
      <el-button icon="el-icon-search" circle></el-button>
    </el-header>
    <el-container>
      <el-aside>
        <a href="#" @click="handleCheckOut(1)">筛选新闻条件一</a>
        <a href="#" @click="handleCheckOut(2)">筛选新闻条件二</a>
        <a href="#" @click="handleCheckOut(3)">筛选新闻条件三</a>
        <a href="#" @click="handleCheckOut(4)">筛选新闻条件四</a>
        <a href="#" @click="handleCheckOut(5)">筛选新闻条件五</a>
      </el-aside>
      <el-main>
        <transition-group appear name="el-fade-in">
          <more-article-item
            class="more-article"
            v-for="info in infos"
            :key="info.id"
            :info="info"
          ></more-article-item>
        </transition-group>
      </el-main>
    </el-container>
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
      inputText: "",
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
        },
        {
          id: 3,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        },
        {
          id: 4,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        },
        {
          id: 5,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        },
        {
          id: 6,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        }
      ]
    };
  },
  methods: {
    // 处理筛选新闻函数，该函数处理筛选逻辑
    // eslint-disable-next-line no-unused-vars
    handleCheckOut: function(id) {}
  }
};
</script>
<style scoped>
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
.el-main {
  min-width: 500px;
}
.el-aside {
  background-color: #b3c0d1;
  color: #333;
}
.search {
  min-width: 200px;
}
.more-article {
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
a:hover {
  color: #409eff;
}
a {
  display: block;
  text-decoration: none;
  color: black;
  margin: 10px;
}
</style>

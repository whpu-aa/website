<template>
  <div>
    <el-header class="top">
      <div class="title">新闻</div>
      <el-input
        v-model="inputText"
        placeholder="请输入内容"
        class="search"
      ></el-input>
      <el-button icon="el-icon-search" class="searchButton" circle></el-button>
    </el-header>
    <div class="container">
      <div class="aside">
        <el-card class="box-card">
          <div slot="header">筛选条件</div>
          <a href="#" @click="handleCheckOut(1)">筛选新闻条件一</a>
          <a href="#" @click="handleCheckOut(2)">筛选新闻条件二</a>
          <a href="#" @click="handleCheckOut(3)">筛选新闻条件三</a>
          <a href="#" @click="handleCheckOut(4)">筛选新闻条件四</a>
          <a href="#" @click="handleCheckOut(5)">筛选新闻条件五</a>
        </el-card>
        <el-card class="box-card">
          <div v-for="item in 5" :key="item">{{ item }}</div>
        </el-card>
        <el-card class="box-card">
          <div v-for="item in 5" :key="item">{{ item + 5 }}</div>
        </el-card>
      </div>
      <div class="main">
        <more-article-item
          class="more-article"
          v-for="info in infos"
          :key="info.id"
          :info="info"
        ></more-article-item>
      </div>
    </div>
  </div>
</template>

<script>
import MoreArticleItem from "../components/MoreArticleItem";
export default {
  components: {
    MoreArticleItem,
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
          articleContents: "文章内容",
        },
        {
          id: 2,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容",
        },
        {
          id: 3,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容",
        },
        {
          id: 4,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容",
        },
        {
          id: 5,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容",
        },
        {
          id: 6,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容",
        },
      ],
    };
  },
  methods: {
    // 处理筛选新闻函数，该函数处理筛选逻辑
    // eslint-disable-next-line no-unused-vars
    handleCheckOut: function (id) {},
  },
};
</script>
<style scoped>
.container {
  background-color: #f4f5f5;
  display: flex;
}
.box-card {
  margin-bottom: 10px;
}
.aside {
  margin-left: 10%;
  margin-top: 2%;
  width: 250px;
}
.title {
  text-align: center;
  font-weight: bold;
  font-size: 25px;
  margin-right: 26%;
}
.search {
  min-width: 200px;
  max-width: 300px;
}
.searchButton {
  margin-top: 10px;
  margin-left: 10px;
  width: 40px;
  height: 40px;
}
.top {
  display: flex;
  justify-content: flex-end;
  background-color: #ffffff;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.main {
  width: 60%;
  margin: 10px 20px;
}
.more-article {
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  background-color: white;
  margin-left: 40px;
}
a:hover {
  color: #c55a11;
}
a {
  display: block;
  text-decoration: none;
  color: black;
  margin: 10px;
}
</style>

<template>
  <div class="bgbody">
    <!--左边点击的区域-->
    <div class="left">
      <ul class="rocker">
        <li :key="index" v-for="(item, index) in block">
          <a v-bind:href="item.url" target="myframe">{{ item.ename }}</a>
        </li>
      </ul>
    </div>
    <!--中间显示窗口-->
    <div class="cen">
      <iframe
        height="100%"
        width="100%"
        name="myframe"
        scrolling="yes"
        frameborder="0"
      ></iframe>
    </div>
  </div>
</template>
<script>
export default {
  name: "GroupPage",
  data() {
    return {
      block: [
        { ename: "管理组", url: "http://www.baidu.com" },
        { ename: "运行组", url: "http://www.taobao.com" },
        { ename: "宣传组", url: "http://www.taobao.com" },
        { ename: "人员介绍", url: "http://www.taobao.com" },
        { ename: "five", url: "http://www.taobao.com" }
      ]
    };
  }
};
</script>
<style scoped>
.cen {
  /*主页中间最大的div*/
  width: 900px;
  height: 500px;
  margin: auto 20px;
  float: left;
  position: relative;
  opacity: 0.8;
  background: url("../assets/group_pic/snow.png");
  border: solid 10px white;
  border-radius: 10px;
}
.bgbody {
  /*body背景*/
  background: url(../assets/group_pic/bg.png);
  display: flex;
  align-items: center;
  height: 800px;
}
.rocker {
  margin-right: 35px;
}
a {
  font-size: x-large;
  height: 100%;
  display: block;
  color: white;
  text-decoration: none;
}
.left {
  background-color: white;
  border-radius: 25px;
  opacity: 0.7;
  width: 400px;
  height: 500px;
  display: flex;
  align-items: center;
}
.rocker li {
  background-image: url(../assets/group_pic/webmaker/rocker.png);
  border: 1px solid black;
  width: 300px;
  height: 50px;
  list-style: none;
  border-radius: 25px;
  box-shadow: 10px 10px 5px rgb(106, 106, 245);
}
.rocker li:hover {
  background-image: url(../assets/group_pic/webmaker/rocker2.png);
}
</style>

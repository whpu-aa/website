<template>
  <div class="bgbody">
    <!--左边点击的区域-->
    <div class="left" name="left" id="left">
      <ul class="rocker">
        <li :key="index" v-for="(item,index) in block">
          <a v-bind:href="item.url" target="myframe">{{item.ename}}</a>
        </li>
      </ul>
    </div>

    <div class="cen">
      <iframe height="100%" width="100%" name="myframe" scrolling="yes" frameborder="0"></iframe>
    </div>
  </div>
</template>
 
      <script>
export default {
  name: "group_page",
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
  margin: 10% 20px;
  height: 500px;
  float: left;
  position: relative;
  opacity: 0.8;
  background: url("../assets/group_pic/snow.png");
}
.bgbody /*body背景*/
 {
  display: table;
  background: url(../assets/group_pic/bg.png);
}

.rocker {
  margin: 100px 0px;
}
a {
  font-size: x-large;
  height: 100%;
  display: grid;
  color: white;
  list-style: none;
  text-decoration: none;
}

.left {
  background-color: white;
  border-radius: 100px;
  opacity: 0.7;
  margin: 10% -8px;
  width: 400px;
  height: 500px;
  position: relative;
  float: left;
}
dd {
  border: 1px solid black;
  margin: 0;
  opacity: 0;
  background-color: white;
  color: black;
}
.rocker li {
  background-image: url(../assets/group_pic/webmaker/rocker.png);
  border: 1px solid black;
  text-align: center;
  width: 300px;
  height: 50px;
  list-style: none;
  border-radius: 25px;
}
.rocker li:hover {
  background-image: url(../assets/group_pic/webmaker/rocker2.png);
}
</style>   
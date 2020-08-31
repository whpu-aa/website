<template>
  <div class="bgbody">
    <!--左边点击的区域-->
    <div class="left">
      <ul class="rocker">
        <li :key="index" v-for="(item, index) in block">
          <a href="javascript:;" @click="num = index">{{ item.ename }}</a>
        </li>
      </ul>
    </div>
    <!--中间显示窗口-->
    <div class="cen">
      <gd v-bind:index="num"></gd>
    </div>
  </div>
</template>
<script>
import groupDetail from "../components/GroupDetail";
export default {
  name: "GroupPage",
  data() {
    return {
      num: 0,
      block: [
        { ename: "管理组" },
        { ename: "运行组" },
        { ename: "宣传组" },
        { ename: "人员介绍" }
      ]
    };
  },
  components: {
    gd: groupDetail
  }
};
</script>
<style scoped>
.cen {
  /*主页中间最大的div*/
  width: 900px;
  height: 500px;
  margin: auto 20px;
  opacity: 0.8;
  border: solid 10px white;
  border-radius: 10px;
  background-color: blanchedalmond;
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

<template>
  <div class="bgbody">
    <!--左边点击的区域-->
    <div class="left">
      <ul class="rocker">
        <li :key="index" v-for="(item, index) in block">
          <a href="javascript:void(0);" @click="flag = item.ename">{{
          item.ename
          }}</a>
        </li>
      </ul>
    </div>
    <!--中间显示窗口-->
    <div class="cen">
      <gd v-bind:index="flag"></gd>
    </div>
  </div>
</template>

<script>
import GroupDetail from "../components/GroupDetail";
export default {
  name: "GroupPage",
  methods: {
  },
  
  data() {
    return {
      flag: "管理组", //确定显示的页面，默认为管理组
      current: 0,
      block: [
        { ename: "管理组"},
        { ename: "运行组"},
        { ename: "宣传组" },
      ],
    };
  },
  components: {
    gd: GroupDetail
  }
};
</script>

<style scoped>
.right {
  background-color: white;
  height: 500px;
}
.cen {
  /*主页中间最大的div*/
  width: 900px;
  height: 800px;
  margin: auto;
  opacity: 0.8;
  border: solid 10px white;
  border-radius: 10px;
  background-color:white;
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
  margin-top: 150px;
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
  opacity: 0.8;
  width: 400px;
  height: 800px;
  display: flex;
}
.rocker li {
  background-image: url(../assets/group_pic/webmaker/rocker.png);
  border: 1px solid black;
  width: 300px;
  height: 50px;
  list-style: none;
  border-radius: 25px;
  box-shadow: 10px 10px 5px rgb(106, 106, 245);
  margin-top: 10px;
  line-height: 50px;
}
.rocker li:hover {
  background-image: url(../assets/group_pic/webmaker/rocker2.png);
}
</style>

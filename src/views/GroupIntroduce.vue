<template>
  <div class="bgbody">
    <!--左边点击的区域-->
    <div class="left">
      <ul class="rocker">
        <li :key="index" v-on:click="change(index)" v-for="(item, index) in block">
          <a v-bind:href="item.url" target="myframe">{{ item.ename }}</a>
        </li>
      </ul>
    </div>

    <div class="right">
      <table > 
        <!-- v-bind:key="index" v-for="(item, index) in part" -->
        <tr>{{ part[0] + ":" }}</tr>
        <tr>{{ department[current].introduction }}</tr>
        <div>------------------------------------------------------------------------------</div>
        <tr>{{ part[1] + ":" }}</tr>
        <tr>{{ department[current].manager }}</tr>
        <div>------------------------------------------------------------------------------</div>
        <tr>{{ part[2] + ":" }}</tr>
        <tr :key="index" v-for="(name, index) in department[current].member">
          <td>{{ name }}</td>
        </tr>
      </table>
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
    <div class="clear"></div>

    
  </div>
</template>

<script>
export default {
  name: "GroupPage",
  methods: {
    change: function(index){
      this.current = index;
      console.log(this.current);
    }
  },
  
  data() {
    return {
      current: 0,
      block: [
        { ename: "管理组"},
        { ename: "运行组"},
        { ename: "宣传组" },
        { ename: "人员介绍"},
        { ename: "five"}
      ],
      part: ["部门介绍", "负责人介绍", "成员介绍"],

      department: [{
        introduction: "管理组的日常主要任务是......",
        manager: "周云",
        member: ["张翔轩", "袁菲", "邵嘉林", "黄鑫鑫"],
      },{
        introduction: "运行组的日常主要任务是......",
        manager: "魏锦明",
        member: ["张翔轩", "袁菲", "邵嘉林", "黄鑫鑫"],
      },{
        introduction: "宣传组的日常主要任务是......",
        manager: "刘德",
        member: ["张翔轩", "袁菲", "邵嘉林", "黄鑫鑫"],
      }]

    };
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
  margin: 10% 20px;
  height: 500px;
  float: left;
  position: relative;
  opacity: 0.8;
  /* background: url("../assets/group_pic/snow.png"); */
  border: solid 10px white;
  border-radius: 10px;
}
.bgbody {
  /*body背景*/
  background: url(../assets/group_pic/bg.png);
}
.rocker {
  margin: 100px 0px;
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
  margin: 10% -8px;
  width: 400px;
  height: 500px;
  position: relative;
  float: left;
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
.clear {
  clear: both;
}
</style>

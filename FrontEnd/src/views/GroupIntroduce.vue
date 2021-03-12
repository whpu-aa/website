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
      <div v-show="flag == '管理组'">
        <div class="title">管理组</div>
        <div class="first">{{ part[0] }}</div>
        <div class="second">{{ department[0].introduction }}</div>
        <div class="first">{{ part[1] }}</div>
        <div class="second">
          <a
            class="people"
            href=""
            v-bind:key="index"
            v-for="(person, index) in department[0].manager"
          >
            {{ person }}
          </a>
        </div>
        <div class="first">{{ part[2] }}</div>
        <div class="second">
          <a
            class="people"
            href=""
            v-bind:key="index"
            v-for="(person, index) in department[0].member"
          >
            {{ person }}
          </a>
        </div>
      </div>

      <div v-show="flag == '运行组'">
        <div class="title">运行组</div>
        <div class="first">{{ part[0] }}</div>
        <div class="second">{{ department[1].introduction }}</div>
        <div class="first">{{ part[1] }}</div>
        <div class="second">
          <a
            class="people"
            href=""
            v-bind:key="index"
            v-for="(person, index) in department[1].manager"
          >
            {{ person }}
          </a>
        </div>
        <div class="first">{{ part[2] }}</div>
        <div class="second">
          <a
            class="people"
            href=""
            v-bind:key="index"
            v-for="(person, index) in department[1].member"
          >
            {{ person }}
          </a>
        </div>
      </div>

      <div v-show="flag == '宣传组'">
        <div class="title">宣传组</div>
        <div class="first">{{ part[0] }}</div>
        <div class="second">{{ department[2].introduction }}</div>
        <div class="first">{{ part[1] }}</div>
        <div class="second">
          <a
            class="people"
            href=""
            v-bind:key="index"
            v-for="(person, index) in department[2].manager"
          >
            {{ person }}
          </a>
        </div>
        <div class="first">{{ part[2] }}</div>
        <div class="second">
          <a
            class="people"
            href=""
            v-bind:key="index"
            v-for="(person, index) in department[2].member"
          >
            {{ person }}
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "GroupPage",
  data() {
    return {
      flag: "管理组", //确定显示的页面，默认为管理组
      block: [{ ename: "管理组" }, { ename: "运行组" }, { ename: "宣传组" }],
      part: ["部门介绍", "负责人", "成员"],
      department: [
        {
          introduction: "管理组的日常主要任务是......",
          manager: ["周云", "邹研"],
          member: ["张翔轩", "袁菲", "邵嘉林", "黄鑫鑫"]
        },
        {
          introduction: "运行组的日常主要任务是......",
          manager: [
            "杨宇千",
            "魏锦明",
            "柳家望",
            "周佳鹏",
            "朱仲坤",
            "胡奕",
            "左志祥"
          ],
          member: ["余言", "吴浩楠", "李攀峰", "苟书洋", "丰志强"]
        },
        {
          introduction: "宣传组的日常主要任务是......",
          manager: ["刘德"],
          member: ["张翔轩", "袁菲", "邵嘉林", "黄鑫鑫"]
        }
      ]
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
  height: 800px;
  margin: auto;
  opacity: 0.8;
  border: solid 10px white;
  border-radius: 4px;
  background-color: white;
}
.bgbody {
  /*body背景*/
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
  color: black;
  text-decoration: none;
}
.left {
  background-color: white;
  border-radius: 4px;
  opacity: 0.8;
  width: 400px;
  height: 800px;
  display: flex;
}
.rocker li {
  border: 1px solid black;
  width: 300px;
  height: 50px;
  list-style: none;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  margin-top: 10px;
  line-height: 50px;
}
.title {
  font-size: 40px;
}

.people {
  height: 32px;
  line-height: 32px;
  margin: 6px 10px 6px 0;
  display: inline-block;
  width: 92px;
  color: #333;
  cursor: pointer;
  text-align: center;
  border: 1px solid #ddd;
  overflow: hidden;
}
.first {
  border: 1px solid #ebebeb;
  font-size: 18px;
  margin-top: 20px;
  padding-top: 10px;
  padding-bottom: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.second {
  padding-top: 10px;
  padding-bottom: 10px;
  border: 1px solid #ebebeb;
  margin-bottom: 60px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
</style>

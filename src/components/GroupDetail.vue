<template>
  <div>
    <div v-show="index == '管理组'">
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

    <div v-show="index == '运行组'">
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

    <div v-show="index == '宣传组'">
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
</template>

<script>
export default {
  name: "GroupDetail",
  props: ["index"],

  data() {
    return {
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
a {
  text-decoration: none;
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
}
.second {
  padding-top: 10px;
  padding-bottom: 10px;
  border: 1px solid #ebebeb;
  margin-bottom: 60px;
}
.pos {
  margin: auto;
}

.pos table {
  margin: auto;
}
</style>

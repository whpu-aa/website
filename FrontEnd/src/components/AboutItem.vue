<template>
  <el-row>
    <el-col :span="8" v-for="(o, index) in memberName" :key="index">
      <el-card>
        <div slot="header" class="clearfix">
          <span>{{ memberName[index] }}</span>
        </div>
        <img :src="photo" class="image" />
        <div class="content">
          <div class="bottom clearfix">
            {{ introduction[index] }}
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "MemberStyle",
  data() {
    return {
      photo: require("@/assets/member1.jpg"),
      memberName: ["刘德", "杨宇千", "周云"],
      introduction: [
        "软工1804班，算法协会会长，入党积极分子。热爱运动，喜欢打羽毛球和乒乓球。为人比较活跃，喜欢参加各种活动，以此来充实自己的大学生活。希望在数计学院算法协会学习到更多专业技能，希望能为数计学院学风建设做出一点贡献。\n",
        "大类1901班，算法协会副会长。热爱编程，喜欢钻研各种问题，学习最前沿的技术。在GitHub上有着十几万的代码量，拥有一定的开发经验。曾参与过数计学院的网站建设。平时爱好是听音乐，看书。希望能够通过算法协会，为学院的学风和技术氛围的建设做出自己的贡献。\n",
        "软工1801班，算法协会副会长，入党积极分子。兴趣爱好是跑步，打羽毛球，看动漫。专业基础知识掌握牢固，面对困难乐观积极进取，能主动探索自己的知识盲区，拓宽自己的眼界。希望在数计学院算法协会不仅能学习各种优化算法，提升自己的编程能力，更能在与协会成员磨合过程中，提升自己的团队协作能力。\n",
      ],
    };
  },
};
</script>

<style scoped>
.el-card {
  margin: 1rem;
}

.el-card >>> .el-card__header {
  font-size: 2rem;
  font-weight: bold;
}

.content {
  padding: 0.5rem;
}

.image {
  width: 95%;
  margin: auto auto 1em;
  display: block;
  object-fit: contain;
}

.clearfix:after {
  clear: both;
}

.content {
  font-family: fantasy;
  text-indent: 2em;
  text-align: left;
}
/*手机*/
@media screen and (max-width: 767px) {
  .el-row >>> .el-col-8 {
    width: 100%;
  }

  .el-card {
    border: 5px solid #ebeef5;
    margin-bottom: 10%;
  }
}

/*平板*/
@media screen and (min-width: 768px) {
  .el-card {
    width: 95%;
  }
}
</style>

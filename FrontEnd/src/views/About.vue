<template>
  <div class="about">
    <el-header>
      <h1>
        数计学院算法协会
        <el-tabs v-model="itemName" stretch tab-position="top">
          <el-tab-pane
            v-for="(item, index) in site"
            :label="item"
            :key="index"
            :name="item"
          ></el-tab-pane>
        </el-tabs>
      </h1>
    </el-header>

    <div class="textCommonent">
      <div v-if="itemName == '协会介绍'">
        <h3>{{ itemName }}</h3>
        <h4>
          <pre class="content">
            算法协会是一个以计算机软件算法为核心的组织，
            它力求以严格、规范、先进的模式，为广大学生提供一个学习、监督、交流的平台，
            为培养专业、全面、领先的计算机高技术型人才服务。
          </pre>
        </h4>
        <h4>
          <pre class="content">
            制定管理制度：协会的正常运行和发展离不开完善的规章制度。目前协会刚刚起步，针对协会当前运营及工作情况，主要对协会成员日常题目训练，题目讲解以及参加会议情况等进行考察并制定相关的管理制度。
            算法协会管理组将主要负责对协会成员日常活动的考察，并建立完善相关制度。
            目前协会主要分为管理组，运行组和宣传组，实行分组管理，各尽其责。同时针对每一位低年级同学，都有一位高年级同学与之对接，即采用一对一方式进行指导和管理。
            根据目前协会运营管理状况，制定如下管理制度：
            （1）每位成员对于平时的做题训练，题目讲解以及开会等活动，不得无故缺勤。若无故缺勤，事后需向管理组负责人阐明具体情况。
            （2）协会成员因紧急的事情而耽误协会工作时，需向相对应负责人请假，阐述具体原因，再由相关负责人向管理组报备，做好记载。
            （3）协会成员不得多次请假，滞后协会工作和日常学习进度，对于多次请假者，需向管理组相关负责人提交一份说明书，要阐明每次缺勤缘由并将耽误的工作弥补起来。
            （4）每位成员在加入协会之前应写一份保证书，表明自己加入协会的意愿并且将为协会的运营建设出力，不能中途退出，否则需向协会负责人分别提交一份退会申请书。
            （5）针对每位成员的平时表现，对于平时表现优异的同学给予适当的奖励（物质奖励和精神奖励）。
            由于协会仍在不断建设之中，各项制度措施会不断完善，我们会在不断摸索中建立健全各项制度。
          </pre>
        </h4>
      </div>
      <div v-else-if="itemName == '成员风采'">
        <MemberStyle class="main"></MemberStyle>
      </div>
    </div>
  </div>
</template>

<script>
//把成员风采的东西单独抽取了一个组件
import MemberStyle from "@/components/AboutItem";

export default {
  name: "About",
  data() {
    return {
      site: ["协会介绍", "成员风采", "招新专区"],
      itemName: "协会介绍",
    };
  },
  components: {
    MemberStyle,
  },
};
</script>

<style scoped>
el-header {
  flex-direction: row;
}

.el-tabs >>> .el-tabs__item {
  width: 100%;
  font-size: 17px;
}

.textCommonent {
  margin-top: 55px;
}

/*成员风采的响应式布局*/
/* 手机*/
@media screen and (max-width: 767px) {
  .el-header {
    margin-top: 55px;
  }

  .el-tabs {
    padding: 10px 0;
  }
}

/*平板*/
@media screen and (min-width: 768px) {
  .el-header {
    margin-top: 70px;
  }

  .el-tabs {
    padding: 10px 0;
  }
}
</style>

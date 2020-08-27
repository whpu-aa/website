<template>
  <div class="about">
    <div class="title">
      <span>数计学院算法协会</span>
    </div>
    <div class="association">
      <!--顶端索引-->
      <div class="headIndex">
        <router-link to="/">主页</router-link>
        <span>&gt;</span>
        <router-link to="/about">关于我们</router-link>
        <span>&gt;</span>
        <span>{{ currentItem }}</span>
      </div>
      <div class="majorStructure">
        <!--左边导航栏-->
        <div class="leftColumn">
          <h2>协会概况</h2>
          <ul>
            <li
              @click="currentItem = site.item"
              :key="index"
              v-for="(site, index) in sites1"
            >
              <a>{{ site.item }}</a>
            </li>
          </ul>
        </div>
        <!--中间显示对应内容-->
        <div class="middleBlock">
          <DetailComponent :itemName="currentItem"></DetailComponent>
        </div>
        <!--右边导航栏-->
        <div class="rightColumn">
          <h2>协会概况</h2>
          <ul>
            <li
              @click="currentItem = site.item"
              :key="index"
              v-for="(site, index) in sites2"
            >
              <a>{{ site.item }}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import DetailComponent from "../components/DetailComponent.vue";
export default {
  name: "About",
  components: {
    DetailComponent: DetailComponent
  },
  data() {
    return {
      sites1: [
        { item: "协会简介" },
        { item: "协会负责人" },
        { item: "协会机构" },
        { item: "协会章程" },
        { item: "联系我们" }
      ],
      sites2: [
        { item: "成员风采" },
        { item: "招新专区" },
        { item: "技术专栏" },
        { item: "活动纪实" }
      ],
      currentItem: "协会简介"
    };
  }
};
</script>
<style>
.about {
  width: 100%;
  min-height: 600px;
  border: solid 1px thistle;
}
.title {
  width: 100%;
  height: 60px;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  font-size: xx-large;
  background-color: #88b0c5;
  text-align: center inherit;
}
.association {
  width: 85%;
  height: fit-content;
  margin-left: 100px;
}
.headIndex {
  height: 40px;
  width: 100%;
  text-align: left;
  background-color: peachpuff;
}
.majorStructure {
  width: 100%;
  height: fit-content;
}
.leftColumn {
  width: 15%;
  min-height: 470px;
  border-radius: 5px;
  margin-right: 12px;
  border: solid 1px thistle;
  float: left;
}
h2 {
  width: 100%;
  margin-top: 0px;
  text-align: center;
  font-size: 20px;
  background-color: #1295d8;
}
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
li {
  margin-top: 25px;
  height: 60px;
  width: 100%;
  border-radius: 5px;
  font-size: 20px;
}
li:hover {
  background-color: #869bae;
}
.middleBlock {
  width: 67%;
  min-height: 470px;
  margin-right: 12px;
  border: solid 1px thistle;
  float: left;
}
.rightColumn {
  width: 15%;
  min-height: 470px;
  border-radius: 5px;
  border: solid 1px thistle;
  float: left;
}
</style>

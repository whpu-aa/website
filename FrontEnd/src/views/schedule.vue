<template>
  <div class="out">
    <el-row class="top">
      <el-col :span="4" class="topinf"><h3>协会日历</h3></el-col>
      <el-button class="btn-sreach" icon="el-icon-search"></el-button>
      <el-col :span="8">
        <el-input v-model="searchData" placeholder="search"> </el-input>
      </el-col>
      <el-col :span="1" :offset="7">
        <el-badge is-dot class="bell" :hidden="bellBadge">
          <el-button @click="bell" icon="el-icon-bell" circle></el-button>
        </el-badge>
      </el-col>
      <el-col :span="1">
        <div class="avater">
          <img :src="avaterUrl" fit="contain" />
        </div>
      </el-col>
      <el-col :span="3">
        <div class="name">我是大饼,你的超级大饼</div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4" :offset="2">
        <el-date-picker
          v-model="dateValue"
          type="date"
          placeholder="选择日期"
          value-format="yyyy-MM-dd"
        ></el-date-picker>
      </el-col>
    </el-row>
    <div @click="changeDate($event)" class="date-array">
      <div
        :class="[each == day ? 'active' : '', 'day-box']"
        :key="index"
        v-for="(each, index) in dateArray"
      >
        {{ each }}
      </div>
    </div>
    <div class="main">
      <div class="hour-column"></div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dateValue: new Date().toISOString().substring(0, 10),
      searchData: "",
      avaterUrl: require("../assets/avater1.png"),
      bellBadge: false,
      dateArray: Array.from({ length: 30 }, (_, index) => index + 1),
    };
  },
  methods: {
    bell: function () {
      this.bellBadge = !this.bellBadge;
      //跳转信息页面
    },
    changeDate: function (e) {
      let day =
        e.target.innerText < 10 ? "0" + e.target.innerText : e.target.innerText;
      if (day.length > 2) {
        return;
      }
      this.dateValue = this.dateValue.substring(0, 8) + day;
    },
  },
  computed: {
    day: function () {
      if (this.dateValue) {
        return this.dateValue.split("-")[2];
      } else {
        return 0;
      }
    },
  },
};
</script>
<style scoped>
.active {
  color: white;
  background-color: orange;
}
.top {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
}
.bell >>> .el-button {
  background: none;
  padding: 0;
  border: 0;
}
.btn-sreach {
  background-color: rgba(0, 0, 0, 0);
  z-index: 1;
  position: relative;
  left: 2.5rem;
  padding-right: 0.7rem;
  border: none;
  border-radius: 0;
  border-right: 1px solid #dcdfe6;
}
.name {
  min-width: 4rem;
  font-size: 50%;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.avater {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.avater > img {
  border-radius: 50%;
  height: 100%;
  width: 100%;
  max-width: 2em;
}
.el-input >>> input {
  padding-left: 2.5rem;
}
.day-box {
  flex-shrink: 0;
  display: inline-flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #dcdfe6;
  height: 2rem;
  width: 2rem;
  border-radius: 4px;
}
.date-array {
  margin-top: 2rem;
  white-space: nowrap;
  overflow-x: auto;
}
</style>

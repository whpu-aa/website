<template>
  <div class="out">
    <el-row class="top">
      <el-col :span="5" class="topinf"><h3>协会日历</h3></el-col>
      <el-col :span="7">
        <el-date-picker
          v-model="MouthValue"
          type="month"
          placeholder="选择月"
          value-format="yyyy-MM"
        ></el-date-picker>
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
    <div @click="changeDate($event)" class="date-array">
      <div
        :class="[each == day ? 'active' : '', 'day-box']"
        :key="index"
        v-for="(each, index) in dateArray"
      >
        {{ each }}
      </div>
    </div>
    <el-row class="main">
      <el-col :span="2" class="hour-column">
        <div class="hour-column-box" :key="each" v-for="each in hourArr">
          {{ each }}
        </div>
      </el-col>
      <el-col :span="14" class="label-column">
        <div
          v-show="
            timeFliter[0] <= item.startTime &&
            timeFliter[1] >= item.endTime &&
            eventFliter.includes(item.tag)
          "
          class="label-box"
          :key="id"
          v-for="(item, id) in labelObjArray"
          :style="{ top: item.top, height: item.height }"
        >
          <div
            class="label-before"
            :style="{ background: item.beforeColor }"
          ></div>
          <div class="label-content" :style="{ background: item.color }">
            <div class="content-time">
              {{ item.startTime }}~~{{ item.endTime }}
            </div>
            {{ item.main }}
          </div>
        </div>
      </el-col>
      <el-col class="fliter-time" :span="4">
        <div class="fliter-time-title">时间</div>
        <el-time-select
          placeholder="起始时间"
          v-model="fliterStartTime"
          :picker-options="{
            start: '08:00',
            step: '00:30',
            end: '22:00',
          }"
        >
        </el-time-select>
        <el-time-select
          placeholder="结束时间"
          v-model="fliterEndTime"
          :picker-options="{
            start: '08:00',
            step: '00:30',
            end: '22:00',
            minTime: fliterStartTime,
          }"
        >
        </el-time-select>
        <el-button @click="setFliter" icon="el-icon-search" circle></el-button>
        <el-button
          @click="dialog = true"
          icon="el-icon-plus"
          circle
        ></el-button>
      </el-col>
      <el-col :span="4" class="fliter-event"
        ><div>任务</div>
        <el-checkbox-group class="fliter-event-group" v-model="checkList">
          <el-checkbox class="fliter-event-box-0" label="开会讲题母鸡吖别的"
            >全部</el-checkbox
          >
          <el-checkbox class="fliter-event-box-1" label="开会"></el-checkbox>
          <el-checkbox class="fliter-event-box-2" label="讲题"></el-checkbox>
          <el-checkbox class="fliter-event-box-3" label="母鸡吖"></el-checkbox>
          <el-checkbox class="fliter-event-box-4" label="别的"></el-checkbox>
        </el-checkbox-group>
      </el-col>
    </el-row>

    <el-drawer
      title="我嵌套了 Form !"
      :before-close="handleClose"
      :visible.sync="dialog"
      size="40%"
      direction="rtl"
      custom-class="demo-drawer"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <span>请选择标签</span>
        <el-form :model="form">
          <el-form-item label-width="80px">
            <el-checkbox-group
              class="fliter-event-group-aside"
              v-model="form.check"
            >
              <el-checkbox
                class="fliter-event-box-1"
                label="开会"
              ></el-checkbox>
              <el-checkbox
                class="fliter-event-box-2"
                label="讲题"
              ></el-checkbox>
              <el-checkbox
                class="fliter-event-box-3"
                label="母鸡吖"
              ></el-checkbox>
              <el-checkbox
                class="fliter-event-box-4"
                label="别的"
              ></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="位置" label-width="80px" size="100%">
            <el-input
              class="aside-input"
              v-model="form.pos"
              placeholder="请输入位置"
            ></el-input>
          </el-form-item>
        </el-form>
        <div class="demo-drawer__footer">
          <el-button>取 消</el-button>
          <el-button type="primary" @click="$refs.drawer.closeDrawer()">{{
            loading ? "提交中 ..." : "确 定"
          }}</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      day: new Date().toISOString().substring(8, 10), //亮的那个
      MouthValue: new Date().toISOString().substring(0, 7),

      dialog: false,
      form: {
        check: "",
      },

      fliterStartTime: "",
      fliterEndTime: "",
      timeFliter: ["08:00", "22:00"],
      checkList: ["开会讲题母鸡吖别的"],
      eventFliter: "开会讲题母鸡吖别的",
      avaterUrl: require("../assets/avater1.png"),
      bellBadge: false,

      hourArr: Array.from({ length: 15 }, (_, index) => {
        let hour = index + 8;
        return hour < 10 ? "0" + hour + ":00" : hour + ":00";
      }),
      labelObjArray: [
        {
          id: "111", //唯一识别号
          top: "3.15rem",
          height: "9.45rem", //每一个小时就是3.15Rem
          beforeColor: "#4Ec9Fc",
          color: "#EAF9FF",
          startTime: "9:00",
          endTime: "12:00",
          tag: "开会", //留个tag的筛选
          main: "一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长的文字",
        },
        {
          id: "222",
          top: "15.75rem",
          height: "3.15rem", //每一个小时就是3.15Rem
          beforeColor: "#5EA0F6",
          color: "#E2ECFF",
          startTime: "13:00",
          endTime: "14:00",
          tag: "讲题", //留个tag的筛选
          main: "一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长的文字",
        },
        {
          id: "333",
          top: "18.90rem",
          height: "4.725rem", //每一个小时就是3.15Rem
          beforeColor: "#f9e26e",
          color: "#FEFAED",
          startTime: "14:00",
          endTime: "15:30",
          tag: "母鸡吖", //留个tag的筛选
          main: "一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长的文字",
        },
        {
          id: "444",
          top: "25.2rem",
          height: "18.9rem", //每一个小时就是3.15Rem
          beforeColor: "#F985AB",
          color: "#FFF1F6",
          startTime: "16:00",
          endTime: "22:00",
          tag: "别的", //留个tag的筛选
          main: "一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长一段很长很长的文字",
        },
      ],
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
      this.day = day;
      // todo:转换日子,清除tagObjArray,重新获取新的tagObjArray//并自动执行一次setFliter
    },
    setFliter: function () {
      this.timeFliter = [
        this.fliterStartTime || "08:00",
        this.fliterEndTime || "22:00",
      ];
      this.eventFliter = this.checkList.join("");
    },
  },
  computed: {
    dateArray() {
      let yyyy = parseInt(this.MouthValue.split("-")[0], 10);
      let mm = parseInt(this.MouthValue.split("-")[1], 10);
      return Array.from(
        { length: new Date(yyyy, mm, 0).getDate() },
        (_, index) => index + 1
      );
    },
  },
};
</script>
<style scoped>
.el-date-editor {
  width: auto;
}
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
  user-select: none;
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
.main {
  display: flex;
  position: relative;
}
.hour-column {
  display: flex;
  flex-direction: column;
  min-width: 3rem;
}
.hour-column-box {
  margin: 1rem 0;
  color: #65646ea6;
}
.label-column {
  position: relative;
}
.label-before {
  height: 100%;
  width: 10px;
  display: inline-block;
  border-radius: 4px 0 0 4px;
}
.label-box {
  display: flex;
  position: absolute;
  overflow: hidden;
}
.label-content {
  padding: 1rem;
  text-align: initial;
  width: 100%;
  border-radius: 0 4px 4px 0;
  font-weight: bold;
}

.content-time {
  font-size: 80%;
}

.fliter-time {
  padding: 1rem 1rem;
}
.fliter-time >>> .el-date-editor {
  margin: 2rem 0;
}
.el-checkbox-group {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 30%;
  margin: auto;
}
.el-checkbox {
  margin: 0.5rem 0;
}
.fliter-event {
  padding: 1rem 0;
}
.fliter-event-group {
  display: flex;
}
.fliter-event-box-0 >>> .el-checkbox__inner {
  transition: all 0.25s;
  border-color: #fda97a;
  background-color: #fcd2bbc5;
}
.fliter-event-box-0 >>> .is-checked .el-checkbox__inner {
  padding: 10%;
  transition: all 0.25s;
  background-clip: content-box;
  background-color: #fda97a;
}

.fliter-event-box-1 >>> .el-checkbox__inner {
  transition: all 0.25s;
  border-color: #4ec9fc;
  background-color: #eaf9ff;
}
.fliter-event-box-1 >>> .is-checked .el-checkbox__inner {
  padding: 10%;
  transition: all 0.25s;
  background-clip: content-box;
  background-color: #4ec9fc;
}

.fliter-event-box-2 >>> .el-checkbox__inner {
  transition: all 0.25s;
  border-color: #5ea0f6;
  background-color: #e2ecff;
}
.fliter-event-box-2 >>> .is-checked .el-checkbox__inner {
  padding: 10%;
  transition: all 0.25s;
  background-clip: content-box;
  background-color: #5ea0f6;
}

.fliter-event-box-3 >>> .el-checkbox__inner {
  transition: all 0.25s;
  border-color: #f9e26e;
  background-color: #fefaed;
}
.fliter-event-box-3 >>> .is-checked .el-checkbox__inner {
  padding: 10%;
  transition: all 0.25s;
  background-clip: content-box;
  background-color: #f9e26e;
}

.fliter-event-box-4 >>> .el-checkbox__inner {
  transition: all 0.25s;
  border-color: #f985ab;
  background-color: #fff1f6;
}
.fliter-event-box-4 >>> .is-checked .el-checkbox__inner {
  padding: 10%;
  transition: all 0.25s;
  background-clip: content-box;
  background-color: #f985ab;
}

.el-form-item >>> .el-form-item__content {
  margin-left: 0 !important;
}
.fliter-event-group-aside {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}
.aside-input >>> .el-input__inner {
  border: 0;
  padding: 0;
  border-radius: 0;
  margin-left: 2rem;
  border-bottom: 1px solid #dcdfe6;
}
</style>

<template>
  <div>
    <div v-show="index == '管理组'">
      管理组
    </div>
    <div v-show="index == '运行组'">
      运行组
    </div>
    <div v-show="index == '宣传组'">
      宣传组
    </div>
    <div v-show="index == '人员介绍'">
      人员介绍
    </div>
  </div>
</template>
<script>
export default {
  name: "GroupDetail",
  props: {
    index: String
  }
};
</script>

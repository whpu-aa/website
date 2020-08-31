<template>
  <div>
    <div v-show="index == 0">
      管理组
    </div>
    <div v-show="index == 1">
      运行组
    </div>
    <div v-show="index == 2">
      宣传组
    </div>
    <div v-show="index == 3">
      人员介绍
    </div>
  </div>
</template>
<script>
export default {
  name: "groupDetail",
  props: {
    index: Number
  }
};
</script>

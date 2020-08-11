<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/log1.png" />
    <HelloWorld msg="欢迎来到武汉轻工大学算法协会的网站" />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";

export default {
  name: "Home",
  components: {
    HelloWorld
  }
};
</script>

<template>
  <a
    class="toMoreArticles"
    :class="{ over: number == 0 }"
    :href="href"
    target="_block"
    @mouseover="mouseOver"
    @mouseleave="mouseLeave"
  >
    <img class="moreArticlesIcon" :src="require('@/assets/' + src)" />
    <div class="articleContents">{{ articleContents }}</div>
  </a>
</template>

<script>
export default {
  name: "Skip",
  data() {
    return {
      number: null
    };
  },
  props: {
    href: String,
    src: String,
    articleContents: String
  },
  methods: {
    mouseOver() {
      this.number = 0;
    },
    mouseLeave() {
      this.number = null;
    }
  }
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
  margin: 10px;
}
.over {
  color: aqua;
}
.toMoreArticles {
  display: flex;
  justify-content: flex-start;
}
.moreArticlesIcon {
  width: 150px;
  height: 75px; /*图片大小根据具体情况调整 */
  object-fit: cover;
  margin: 10px;
}
.articleContents {
  margin: 10px;
}
</style>

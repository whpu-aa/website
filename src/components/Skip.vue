<template>
  <a
    class="toMoreArticles"
    :class="{ over: number == 0 }"
    :href="targetHref"
    target="_block"
  >
    <img class="moreArticlesIcon" :src="require('@/assets/' + iconSrc)" />
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
    targetHref: String,
    iconSrc: String,
    articleContents: String
  }
};
</script>

<style scoped>
a:hover {
  color: aqua;
}
a {
  text-decoration: none;
  color: black;
  margin: 10px;
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

<template>
  <a class="toMoreArticles" :href="info.targetHref" target="_block">
    <img class="moreArticlesIcon" :src="require('@/assets/' + info.iconSrc)" />
    <div class="articleContents">{{ info.articleContents }}</div>
  </a>
</template>

<script>
export default {
  name: "MoreArticleItem",
  props: {
    /*接收父组件传递的对象，含有targetHref,iconSrc,articleContents,
      targetHerf: String, (需要使用超链跳转的URL)
      iconSrc: String, (图片在assets文件夹中的位置)
      articleContents: String, (文章内容简介)
     */
    info: {
      type: Object,
    },
  },
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}
.toMoreArticles {
  display: flex;
  justify-content: flex-start;
}
.moreArticlesIcon {
  width: 150px;
  height: 75px; /*图片大小根据具体情况在父组件使用深度作用选择器调整 */
  object-fit: cover;
  margin: 10px;
}
.articleContents {
  margin: 10px;
}
</style>

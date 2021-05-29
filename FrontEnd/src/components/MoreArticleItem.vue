<template>
  <div class="MoreArticleItem">
    <a class="toMoreArticles" :href="info.targetHref" target="_block">
      <img
        class="moreArticlesIcon"
        :src="require('@/assets/' + info.iconSrc)"
      />
      <div class="articleContents">{{ info.articleContents }}</div>
    </a>
    <div class="triangle"></div>
  </div>
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
.MoreArticleItem {
  display: flex;
  min-width: 700px;
  transition: all 0.3s ease-in-out;
}
.MoreArticleItem:hover {
  transform: scale(1.03);
}
.triangle {
  width: 0px; /*设置宽高为0，所以div的内容为空，从才能形成三角形尖角*/
  height: 0px;
  border-left: 65px solid #e7e7e7;
  border-top: 65px solid transparent; /*transparent 表示透明*/
  border-bottom: 65px solid transparent;
  margin: 10px 0;
}
a:hover {
  color: #00a8e4;
}
a {
  width: 50%;
  background-color: #e7e7e7;
  text-decoration: none;
  color: black;
  margin: 10px;
  margin-right: 0;
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

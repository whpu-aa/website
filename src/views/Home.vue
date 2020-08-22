<template>
  <div class="home">
    <aside class="left">
      <div id="group-introduction" class="component sample-component">
        小组介绍
      </div>
      <div id="about-us" class="component sample-component">关于我们</div>
    </aside>
    <div class="main">
      <div class="main-top">
        <div id="carousel" class="component sample-component">轮播图</div>
        <div id="daily-problem" class="component sample-component">
          日常题目
        </div>
      </div>
      <div id="news" class="component sample-component">新闻</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home"
};
</script>

<style scoped>
.home {
  display: flex;
  justify-content: center;

  padding: 20px;
}

.left {
  width: 400px;

  margin-right: 20px;

  display: flex;
  align-items: stretch;
  flex-direction: column;
}

.main {
  max-width: 800px;

  flex-grow: 1;

  display: flex;
  flex-direction: column;
}

.main-top {
  height: 350px;

  margin-bottom: 20px;

  display: flex;
}

.component {
  box-sizing: border-box;
  border-radius: 8px;
}

.sample-component {
  /* 仅用来展示，在替换为真正的组件后删除 */
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 4em;
}

#group-introduction {
  height: 500px;
  border-radius: 8px;

  margin-bottom: 20px;

  background-color: cornflowerblue; /* 仅用来展示，在替换为真正的组件后删除 */
}

#about-us {
  height: 200px;

  background-color: greenyellow; /* 仅用来展示，在替换为真正的组件后删除 */
}

#carousel {
  width: 500px;

  margin-right: 20px;

  background-color: indianred; /* 仅用来展示，在替换为真正的组件后删除 */
}

#daily-problem {
  flex-grow: 1;

  background-color: lightseagreen; /* 仅用来展示，在替换为真正的组件后删除 */
}

#news {
  height: 800px;

  background-color: moccasin;
}
</style>

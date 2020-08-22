<template>
  <div class="NewsArticles">
    <div class="article">
      <h3>{{ title }}</h3>
      <pre><p :key="index" v-for="(item, index) in msg_center" class="msgcenter">{{ item }}</p></pre>
      <pre><p :key="index" v-for="(item, index) in msg_left" class="msgleft">{{ item }}</p></pre>
    </div>
    <div class="morearticle">
      更多新闻
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "文章标题",
      msg_center: [
        "原标题：拜登正式接受美国民主党总统候选人提名",
        "拜登 （图源：Getty）拜登 （图源：Getty）"
      ],
      msg_left: [
        "  海外网8月20日电 据美国国家公共广播电台报道，当地时间20日，美国前副总统拜登在民主党全国代表大会上发表演讲，正式接受民主党总统候选人提名。",
        "  据CNN报道，拜登在演讲中表示：“我们团结在一起，将能够越过美国当前的这一充满黑暗的时节。如果你们委任我成为美国总统，我将会发挥出我们最好的一面，而不是最坏的一面。现在是时候让我们团结起来了。",
        "  据美联社报道，为其四天的美国民主党全国代表大会于17日正式开始。19日，来自美国50个州、哥伦比亚特区和海外领地的57个代表团逐一远程唱票。在北卡罗来纳州宣布计票结果后，拜登获得2448张党代表票，超过提名所需2374票，正式成为2020年美国总统选举民主党候选人，参加11月3日的总统大选，与共和党籍、74岁的现任总统特朗普角逐下一任美国总统",
        "  拜登1942年11月20日出生于美国宾夕法尼亚州，律师出身，1970年踏入政界，1972年首次当选联邦参议员，曾任参议院司法委员会和对外关系委员会主席，在奥巴马执政时期任美国副总统。当前是他第三次竞选总统。（海外网 李芳 张霓）"
      ]
    };
  }
};
</script>

<style scoped>
.NewsArticles {
  padding-top: 5%;
  font-size: 100%;
  flex-wrap: wrap;
  justify-content: space-evenly;
  display: flex;
  flex-direction: row;
  align-items: baseline;
}
.article {
  border-radius: 3%;
  height: auto;
  flex-basis: 50%;
  background-color: rgb(211, 211, 211); /* 颜色未定 ,后期统一背板颜色 */
}
.morearticle {
  border-radius: 3%;
  flex-basis: 30%;
  background-color: rgb(211, 211, 211);
}
pre {
  white-space: pre-wrap; /*css-3*/
}
.msgcenter {
  text-align: center;
}
.msgleft {
  text-align: left;
}
</style>

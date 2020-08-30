<template>
  <div class="main" :style="{ fontSize: fontSize + 'vw' }">
    <el-button-group class="FontSizeButton">
      <el-button type="primary" @click="FrontSizeMinus()"
        >Aa<i class="el-icon-minus"></i
      ></el-button>
      <el-button type="primary" @click="FrontSizePlus()"
        >Aa<i class="el-icon-plus"></i
      ></el-button>
    </el-button-group>
    <div class="NewsArticles">
      <div class="article">
        <h3>{{ title }}</h3>
        <pre><p :key="index" v-for="(item, index) in msg_center" class="msgcenter">{{ item }}</p></pre>
        <pre><p :key="index" v-for="(item, index) in msg_left" class="msgleft">{{ item }}</p></pre>
      </div>
      <div class="rightPart">
        <div class="morearticle">
          <p>其他新闻</p>
          <!--跳转至其他文章-->
          <div class="articleList">
            <more-article-item
              v-for="info in infos"
              :key="info.id"
              :info="info"
            ></more-article-item>
          </div>
          <!--跳转到新闻目录-->
          <div class="readMore">
            <router-link to="/News">阅读更多新闻</router-link>
          </div>
        </div>
        <!--跳转至其他页面，例如日常题目，协会介绍等等-->
        <div class="otherRecommended">
          <p>相关推荐</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MoreArticleItem from "../components/MoreArticleItem";
export default {
  components: {
    MoreArticleItem
  },
  data() {
    return {
      fontSize: 1.5,
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
      ],
      skip_icon_src: "logo.png",
      skip_href: "http://www.baidu.com",
      articles_contents: "文章内容",
      /*传递给子组件的对象数组 */
      infos: [
        {
          id: 1,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        },
        {
          id: 2,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        },
        {
          id: 3,
          targetHref: "http://www.baidu.com",
          iconSrc: "logo.png",
          articleContents: "文章内容"
        }
      ]
    };
  },
  methods: {
    FrontSizeMinus: function() {
      var num = this.fontSize * 10;
      num -= 1;
      num /= 10;
      if (num < 1) {
        num = 1;
        this.$alert("已经达到字体最小", "提示", {
          confirmButtonText: "确定"
        });
      }
      this.fontSize = num;
    },
    FrontSizePlus: function() {
      var num = this.fontSize * 10;
      num += 1;
      num /= 10;
      if (num > 2) {
        num = 2;
        this.$alert("已经达到字体最大", "提示", {
          confirmButtonText: "确定"
        });
      }
      this.fontSize = num;
    }
  }
};
</script>

<style scoped>
.main {
  padding-top: 2.5%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.NewsArticles {
  padding-top: 1.5%;
  flex-wrap: wrap;
  justify-content: space-evenly;
  display: flex;
  flex-direction: row;
  align-items: baseline;
}
.article {
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  height: auto;
  min-height: 700px;
  flex-basis: 50%;
  background-color: rgb(211, 211, 211); /* 颜色未定 ,后期统一背板颜色 */
}
.morearticle {
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  flex-basis: 30%;
  height: auto;
  background-color: rgb(211, 211, 211);
}
pre {
  white-space: pre-wrap; /*css-3*/
}
a {
  text-decoration: none;
  color: black;
  margin: 10px;
}
.msgcenter {
  text-align: center;
}
.msgleft {
  text-align: left;
}
.rightPart {
  display: flex;
  height: auto;
  flex-basis: 30%;
  flex-direction: column;
}
.articleList {
  display: flex;
  flex-direction: column;
  height: auto;
  margin-bottom: 10px;
}
.readMore {
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  display: block;
  min-height: 30px;
  height: auto;
  background-color: rgb(180, 180, 180);
  margin: 10px;
}
.otherRecommended {
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  margin-top: 50px;
  background-color: rgb(211, 211, 211);
}
</style>

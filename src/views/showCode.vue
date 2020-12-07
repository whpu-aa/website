<template>
  <div>
    <el-header>
      <h1>武汉轻工大学算法协会日常打卡</h1>
    </el-header>
    <h4 class="subtitle">此代码由 {{ name }} 提交于 {{ time }}</h4>
    <div class="paste">
      <div class="rows" v-html="rows"></div>
      <pre>
          <code :class="language" v-html="strValue">
          </code>
      </pre>
    </div>
  </div>
</template>
<script>
import hljs from "highlight.js";
import "highlight.js/styles/monokai-sublime.css";
export default {
  mounted() {
    let block = document.querySelectorAll("pre code");
    block.forEach(el => {
      hljs.highlightBlock(el);
    });
  },
  data() {
    return {
      name: "柳家望", //此名字到时候由后端提供
      time: "2020年12月6日22:08",
      strValue:
        "import functools\n\
    class Solution:\n\
      def mostCompetitive(self, nums, k: int) :\n\
          min_ = nums[:k]\n\
          def dfs(nums,deep,temp):\n\
              nonlocal min_,k\n\
              if deep==0:\n\
                  for i in range(k):\n\
                      if temp[i]  min_[i]:\n\
                          min_ = temp\n\
                  return\n\
              for i in nums:\n\
                  if min_[k-deep] >= i:\n\
                      dfs(nums[i+1:],deep-1,temp+[i])\n\
      \n\
          dfs(nums,k,[])\n\
          return min_\n\
    Solution().mostCompetitive([3,5,2,6],2)",
      rows: "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18"
    };
  }
};
</script>
<style scoped>
.subtitle {
  color: red; /*颜色仅为暂时填充 */
}
.paste {
  display: flex;
  justify-content: center;
}
pre {
  margin: 0px;
  background: #464741;
}
code {
  background-color: rgba(0, 0, 0, 0);
  position: relative;
  top: -12px;
  text-align: initial;
  margin-left: 0px;
  border-left: 0px;
  min-height: 500px;
  padding: 0px 5px;
  height: 100%;
  resize: none;
  font-size: 18px;
  width: 800px;
  line-height: 1.25 !important;
  overflow: hidden;
}
.rows {
  display: inline-block;
  padding-top: 5px;
  font-size: 18px;
  width: 22px;
  background: #464741;
  color: aliceblue;
  word-break: keep-all;
  line-height: 1.25 !important;
}
</style>

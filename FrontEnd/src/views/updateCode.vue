<template>
  <div>
    <el-header>
      <h1>武汉轻工大学算法协会日常打卡</h1>
    </el-header>
    <el-form class="main" :model="code" :rules="rules" ref="codeForm">
      <div class="paste">
        <el-row>
          <el-col :span="6" class="beforetag"><strong>姓名:</strong></el-col>
          <el-col :span="6">
            <el-form-item prop="name">
              <el-input
                class="name"
                v-model="code.name"
                maxlength="10"
                placeholder="请输入姓名"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6" class="beforetag"><strong>语言:</strong></el-col>

          <el-col :span="6" class="language" justify="start"
            ><el-form-item prop="language">
              <el-select v-model="code.language">
                <el-option
                  v-for="(item, index) in code.languageGroup"
                  :key="index"
                  :label="item"
                  :value="item"
                ></el-option></el-select></el-form-item
          ></el-col>
        </el-row>
        <el-row>
          <el-col :span="6" class="beforetag"><strong>题号:</strong></el-col>
          <el-col :span="6">
            <el-form-item prop="number">
              <el-input
                class="name"
                v-model="code.number"
                placeholder="请输入题号"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6"
            ><el-button type="primary" @click="submitForm()"
              >提交</el-button
            ></el-col
          >
        </el-row>
        <el-row>
          <el-col :span="24"><strong>代码区域</strong></el-col>
        </el-row>
        <el-form-item>
          <div class="highlight">
            <div class="move">
              <div class="placeholder"></div>
              <pre>
                    <code :class="code.language" v-text="code.strValue">
                    </code>
                </pre>
            </div>
            <div class="rows" v-text="code.rows"></div>
            <textarea
              class="input-font"
              v-model="code.strValue"
              @input="creatRows()"
              style="word-wrap: break-word"
            ></textarea>
          </div>
        </el-form-item>
      </div>
      <input hidden type="text" v-text="code.time" />
    </el-form>
  </div>
</template>
<script>
import hljs from "highlight.js";
import "highlight.js/styles/monokai-sublime.css";
export default {
  mounted() {
    this.highlightCode();
  },
  updated() {
    this.highlightCode();
  },
  data() {
    return {
      code: {
        name: "",
        number: "",
        language: "c",
        languageGroup: ["c", "python3", "java", "c++"],
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
        rows: "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18",
        time: new Date(),
      },
      rules: {
        name: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          { min: 2, max: 5, message: "长度在2到5个字符", tigger: "blur" },
        ],
        number: [
          { required: true, message: "请输入题号", trigger: "blur" },
          { min: 1, max: 10, message: "长度在1到10个字符", tigger: "blur" },
        ],
      },
    };
  },
  methods: {
    highlightCode: () => {
      let block = document.querySelectorAll("pre code");
      block.forEach((el) => {
        hljs.highlightBlock(el);
      });
    },
    creatRows: function () {
      let value = this.code.strValue.split("\n");
      let lens = value.length;
      let index = 0;
      while (index < lens) {
        if (value[index].length > 88) {
          let data = value[index].split("");
          data.splice(88, 0, "\n");
          data = data.join("").split("\n");
          value[index] = data[0];
          value.splice(index + 1, 0, data[1]);
          lens++;
        }
        index++;
      }
      this.code.strValue = value.join("\n");
      let rows = this.code.strValue.split("\n").length - 1;
      let recentRow = this.code.rows.split(" ");
      if (rows >= recentRow[recentRow.length - 1]) {
        while (rows >= recentRow[recentRow.length - 1]) {
          recentRow.push(parseInt(recentRow[recentRow.length - 1]) + 1);
        }
        this.code.rows = recentRow.join(" ");
      } else {
        while (rows < parseInt(recentRow[recentRow.length - 1]) - 1) {
          recentRow.pop();
        }
        this.code.rows = recentRow.join(" ");
      }
    },
    submitForm: function () {
      this.$refs["codeForm"].validate((valid) => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("erroe submit!!");
          return false;
        }
      });
    },
  },
};
</script>
<style scoped>
.el-header {
  margin-top: 100px;
}
.paste {
  margin-top: 50px;
}
.beforetag {
  padding: 10px;
}
.language {
  text-align: initial;
}
/* 以下部分内容让一个代码高亮div覆盖到input上面 */
.highlight {
  display: flex;
  position: relative;
  justify-content: center;
}
.move {
  position: absolute;
  display: flex;
  justify-content: center;
  width: 820.2px;
  pointer-events: none;
  min-height: 500px;
}
.placeholder {
  min-width: 22px;
  max-width: 22px;
}
pre {
  margin: 0;
  pointer-events: none;
  padding-top: 0;
  min-height: 500px;
  width: 800px;
  overflow: hidden;
}
code {
  position: relative;
  top: -40px;
  left: -1px;
  background-color: rgba(0, 0, 0, 0);
  font-size: 18px;
  text-align: initial;
  min-height: 500px;
  width: 800px;
  padding: 0;
  pointer-events: none;
  border: none;
  resize: none;
  line-height: 1.25 !important;
  overflow-wrap: break-word;
  white-space: pre-wrap;
  word-break: break-all;
  word-wrap: break-word;
}
.input-font {
  margin-left: 0px;
  border-left: 0px;
  min-height: 500px;
  min-width: 800px;
  background: #464741;
  resize: none;
  font-size: 18px;
  line-height: 1.25 !important;
  padding: 0;
  border: none;
  outline: none;
  white-space: pre-wrap;
  word-break: break-all;
  word-wrap: break-word;
  overflow: hidden;
}
.rows {
  display: inline-block;
  padding: 0;
  font-size: 18px;
  width: 22px;
  background: #464741;
  color: aliceblue;
  word-break: keep-all;
  line-height: 1.25 !important;
}
</style>

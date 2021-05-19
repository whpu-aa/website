<template>
  <div class="main">
    <div class="table">
      <img :src="avatar" alt="头像" class="avatar" />
      <h2 class="title">Login</h2>
      <el-form
        :model="studentData"
        ref="loginForm"
        label-position="left"
        label-width="40px"
        :rules="rules"
      >
        <el-form-item
          ref="idLabel"
          class="row"
          prop="id"
          label="账号"
          label-width="50px"
        >
          <el-input
            @blur="blur('id')"
            @focus="focus('id')"
            class="input"
            v-model="studentData.id"
          ></el-input>
        </el-form-item>

        <el-form-item
          ref="pwLabel"
          class="row"
          prop="password"
          label="密码"
          label-width="50px"
        >
          <el-input
            @blur="blur('pw')"
            @focus="focus('pw')"
            class="input"
            v-model="studentData.password"
            show-password
          ></el-input>
        </el-form-item>

        <el-button
          class="btn-login"
          type="primary"
          plain
          @click="login('studentData')"
          :loading="loading"
          >登录</el-button
        >
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      studentData: {
        id: "",
        password: "",
      },
      loading: false,
      avatar: require("../assets/undraw_male_avatar_323b.png"),
      rules: {
        id: [{ required: true, message: " 请输入姓名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    login: function () {
      this.loading = true;
      // console.log(this.$refs.loginForm);
      this.$refs.loginForm.validate((res, faild) => {
        console.log(res);
        if (res) {
          console.log("success begin"); //此步不会被调用
          setTimeout(() => {
            console.log("success begin");
            this.$message({
              message: this.studentData,
              type: "success",
            });
            this.loading = false;
          }, 3000);
        }
        console.log("clean begin");
        setTimeout(() => {
          console.log("clean run");
          this.$refs.loginForm.clearValidate();
        }, 3000);
        console.log(faild); //错误的内容
      });
      setTimeout(() => {
        this.$message({
          message: this.studentData,
          type: "success",
        });
        this.loading = false;
      }, 2000);
    },
    SignUp: function () {
      this.$router.replace("/SignUp");
    },
    focus: function (data) {
      let dom = "";
      if (data == "id") {
        dom = this.$refs.idLabel.$el;
      } else if (data == "pw") {
        dom = this.$refs.pwLabel.$el;
      }
      dom.classList.value = dom.classList.value.replace("row", "up");
      // this.$refs.idLabel.$children[0].add("row-up");
    },
    blur: function (data) {
      let dom = "";
      if (data == "id") {
        dom = this.$refs.idLabel.$el;
        if (this.studentData.id) {
          return;
        }
      } else if (data == "pw") {
        dom = this.$refs.pwLabel.$el;
        if (this.studentData.password) {
          return;
        }
      }
      dom.classList.value = dom.classList.value.replace("up", "row");
    },
  },
};
</script>

<style scoped>
.row >>> .el-form-item__label {
  pointer-events: none;
  transition: all 0.5s;
  color: rgba(0, 0, 0, 0.5);
  position: relative;
  left: 1.6rem;
}
.up >>> .el-form-item__label {
  transition: all 0.5s;
  transform: translateY(-1.3rem);
  color: rgba(0, 0, 0, 0.5);
  position: relative;
  left: 1.6rem;
}
.input >>> .el-input__inner {
  padding: 0;
  width: 100%;
  transition: all 1s;
  border-radius: 0;
  background-color: rgb(255, 255, 255, -0);
  border: 0;
  border-bottom: 1px solid #3d3b4f;
  position: relative;
  left: -1rem;
}
.btn-login {
  border: 0;
  background: none;
}
.btn-login:hover {
  border: 0;
  background: none;
  color: #409eff;
}
.btn-login::before {
  background-color: rgba(255, 255, 255, 0);
}
.main {
  min-height: 100vh;
  background-image: linear-gradient(to right, #e0c3fc, #8ec5fc);
  display: flex;
  justify-content: center;
  align-items: center;
}
.table {
  border-radius: 4px;
  padding: 30px;
  background: rgba(255, 255, 255, 0.5);
  max-width: 30vw;
  width: 30px;
  min-width: 300px;
  transition: all 1s;
}
.avatar {
  width: 35%;
  border-radius: 50%;
}
@media (max-width: 499px) {
  .table {
    transition: all 1s;
    background: none;
    width: 100vw;
  }
  .input >>> .el-input__inner {
    transition: all 1s;
    border-bottom: 1px solid #dcdfe6;
    position: relative;
    left: -1rem;
  }
}
</style>

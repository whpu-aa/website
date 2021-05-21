<template>
  <div class="main">
    <div class="table">
      <h2 class="title">注册</h2>
      <el-form
        :model="studentData"
        ref="SignUpForm"
        label-position="left"
        label-width="40px"
      >
        <el-form-item label="账号">
          <el-input v-model="studentData.id"></el-input>
        </el-form-item>

        <el-form-item label="密码">
          <el-input
            placeholder="请输入密码"
            v-model="studentData.password"
            show-password
          ></el-input>
        </el-form-item>
        <el-button type="primary" plain @click="login" :loading="loading"
          >注册</el-button
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
    };
  },
  methods: {
    login: function () {
      const that = this;
      that.loading = true;
      that.$message({
        message: that.studentData,
        type: "success",
      });
      setTimeout(function () {
        console.log("run");
        that.loading = false;
      }, 2000);
    },
    SignUp: function () {
      this.$router.replace("/SignUp");
    },
  },
};
</script>

<style scoped>
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
  height: 50vh;
}
.avatar {
  width: 35%;
  border-radius: 50%;
}
@media (max-width: 499px) {
  .table {
    padding-top: 20vh;
    width: 100vw;
    height: 100vh;
  }
}
</style>

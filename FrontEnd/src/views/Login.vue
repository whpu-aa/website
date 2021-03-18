<template>
  <div class="main">
    <div class="table">
      <h2 class="title">Login</h2>
      <el-form
        :model="studentData"
        ref="loginForm"
        label-position="left"
        label-width="80px"
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
  padding: 50px;
  background: rgba(255, 255, 255, 0.5);
  max-width: 500px;
  min-width: 300px;
  height: 30vh;
}
</style>

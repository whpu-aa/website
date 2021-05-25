import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  //开启严格模式禁止直接修改 开发环境避免性能损失则关闭
  strict: process.env.NODE_ENV !== "production",
  state: {
    user: {
      id: "number", // 唯一id
      username: "string", // 用户名
      permission: "string[]", // 拥有的权限。
      name: "string", // 名字
      description: "string", // 描述
      details: "Record<string, string>", // 其他的信息，一个string map。
      avatarUrl: "string", // 头像链接，即 /api/users/:id/avatar},
    },
    token: null,
  },
  getters: {
    user: (state) => state.user,
    token: (state) => state.token,
  },
  mutations: {
    loginSet: (state, token) => {
      state.token = token;
      localStorage.setItem("token", token);
    },
    userSet: (state, user) => {
      state.user = { ...user }; //遵循响应式原则
    },
  },
  actions: {},
  modules: {},
});

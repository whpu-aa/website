import axios from "axios";
import store from "../store";
import router from "../router";
import { Message } from "element-ui";
// import qs from "qs";

const message = (text) => {
  Message({
    message: text,
    type: "error",
    duration: 1000,
  });
};
var instance = axios.create({ timeout: 1000 * 12 });
instance.defaults.headers.post["Content-Type"] =
  "application/x-www-form-urlencoded";

// 请求拦截器
instance.interceptors.request.use(
  (config) => {
    // 每次发送请求之前判断vuex中是否存在token
    // 如果存在，则统一在http请求的header都加上token，这样后台根据token判断你的登录情况
    // 即使本地存在token，也有可能token是过期的，所以在响应拦截器中要对返回状态进行判断
    const token = store.state.getters.getCounter;
    token && (config.headers.Authorization = token);
    return config;
  },
  (error) => {
    return Promise.error(error);
  }
);

instance.interceptors.response.use(
  (response) => {
    if (response.status === 200) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  (error) => {
    if (error.response.status) {
      switch (error.response.status) {
        case 400:
          switch (error.response.status.ErrorCode) {
            case 100000:
              message("格式错误,请重试");
              break;
            case 100101:
              message("账号密码不正确");
              break;

            // 登录过期对用户进行提示
            // 清除本地token和清空vuex中token对象
            // 跳转登录页面
            case 100102:
              message("登录过期，请重新登录");
              // 清除token
              store.commit("tokenSet", null);
              // 跳转登录页面，并将要浏览的页面fullPath传过去，登录成功后跳转需要访问的页面
              setTimeout(() => {
                router.replace({
                  path: "/login",
                  query: {
                    redirect: router.currentRoute.fullPath,
                  },
                });
              }, 1000);
              break;
          }
          break;
        // 401: 未登录
        // 未登录则跳转登录页面，并携带当前页面的路径
        // 在登录成功后返回当前页面，这一步需要在登录页操作。
        case 401:
          router.replace({
            path: "/login",
            query: {
              redirect: router.currentRoute.fullPath,
            },
          });
          break;

        // 404请求不存在
        case 404:
          message("网络请求不存在");
          break;
        // 其他错误，直接抛出错误提示
        default:
          message(error.response.data.message);
      }
      return Promise.reject(error.response);
    }
  }
);
export default instance;

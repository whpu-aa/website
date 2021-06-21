module.exports = {
  lintOnSave: false,
  devServer: {
    port: 3000,
    proxy: {
      "^/api": {
        target: "http://localhost:8080",
        ws: true,
        changeOrigin: true,
      },
    },
  },
};

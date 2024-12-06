const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    client: {
      overlay: false, // 禁用错误和警告覆盖
    },
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // 后端 API 的地址
        changeOrigin: true,  // 是否改变原始主机头为目标URL
      },
    }
  },
});

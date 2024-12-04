<template>
  <div id="app">
    <el-header>
      <el-menu :default-active="activeIndex" mode="horizontal">
        <el-menu-item index="1">
          <router-link to="/">首页</router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <router-link to="/settings">设置</router-link>
        </el-menu-item>
      </el-menu>
    </el-header>

    <el-main>
      <!-- 路由视图 -->
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      activeIndex: '1' // 用于标识当前菜单项
    };
  }
};
</script>


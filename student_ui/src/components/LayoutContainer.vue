<template>
  <div class="layout-container">
    <div class="sidebar">
      <!-- 侧边栏导航，使用router-link来进行页面跳转 -->
      <el-menu :default-active="activeMenu" class="el-menu-vertical-demo" @select="handleMenuSelect">
        <el-menu-item index="1">
          <router-link to="/home">主页</router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <router-link to="/students">学生管理</router-link>
        </el-menu-item>
        <el-menu-item index="3">
          <router-link to="/settings">设置</router-link>
        </el-menu-item>
      </el-menu>
    </div>

    <div class="content">
      <!-- 页面内容，使用router-view动态加载 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LayoutContainer',
  data() {
    return {
      activeMenu: '1', // 默认选中的菜单项
    };
  },
  methods: {
    handleMenuSelect(index) {
      this.activeMenu = index;
      // 根据菜单项跳转路由
      switch (index) {
        case '1':
          this.$router.push('/home');
          break;
        case '2':
          this.$router.push('/students');
          break;
        case '3':
          this.$router.push('/settings');
          break;
        default:
          break;
      }
    },
  },
};
</script>

<style scoped>
.layout-container {
  display: flex;
  height: 100vh;
}

.sidebar {
  width: 250px;
  background-color: #f4f4f4;
}

.content {
  flex: 1;
  padding: 20px;
}
</style>

<template>
  <div class="layout-container">
    <div class="sidebar">
      <!-- 侧边栏导航，使用router-link来进行页面跳转 -->
      <el-menu  class="el-menu-vertical-demo" >
        <el-menu-item index="1">
          <router-link to="/home">主页</router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <router-link to="/management">学生管理</router-link>
        </el-menu-item>
        <el-menu-item index="3">
          <router-link to="/course">课程管理</router-link>
        </el-menu-item>
        <el-menu-item index="4">
          <router-link to="/teacher">教师管理</router-link>
        </el-menu-item>
        <el-menu-item index="5">
          <router-link to="/enroll">选课管理</router-link>
        </el-menu-item>
        <el-menu-item index="6">
          <router-link to="/attendance">考勤管理</router-link>
        </el-menu-item>
        <el-menu-item index="8">
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

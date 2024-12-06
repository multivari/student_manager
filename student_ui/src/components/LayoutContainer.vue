<template>
  <div class="layout-container">
    <div class="sidebar">
      <!-- 侧边栏导航，使用router-link来进行页面跳转 -->
      <el-menu :default-active="activeMenu" class="el-menu-vertical-demo menu">
        <el-menu-item index="1">
          <router-link to="/home" class="menu-item">主页</router-link>
        </el-menu-item>

        <!-- 学生角色只能看到学生管理 -->
        <el-menu-item v-if="hasRole('student')" index="2">
          <router-link to="/management" class="menu-item">学生管理</router-link>
        </el-menu-item>

        <!-- 教师角色只能看到课程管理、教师管理和选课管理 -->
        <el-menu-item v-if="hasRole('instructor')" index="3">
          <router-link to="/course" class="menu-item">课程管理</router-link>
        </el-menu-item>

        <el-menu-item v-if="hasRole('instructor')" index="4">
          <router-link to="/teacher" class="menu-item">教师管理</router-link>
        </el-menu-item>

        <el-menu-item v-if="hasRole('instructor')" index="5">
          <router-link to="/enroll" class="menu-item">选课管理</router-link>
        </el-menu-item>

        <!-- 管理员角色可以看到所有菜单项 -->
        <el-menu-item v-if="hasRole('admin')" index="6">
          <router-link to="/attendance" class="menu-item">考勤管理</router-link>
        </el-menu-item>

        <el-menu-item v-if="hasRole('admin')" index="7">
          <router-link to="/settings" class="menu-item">设置</router-link>
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
  computed: {
    // 获取当前用户的角色
    userRole() {
      // 假设从 Vuex 或 localStorage 中读取用户角色
      return this.$store.state.user.role || 'student';  // 默认角色为学生
    }
  },
  methods: {
    // 根据角色判断是否有权限访问某些菜单项
    hasRole(role) {
      return this.userRole === role || this.userRole === 'admin'; // 管理员可以访问所有角色权限
    }
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
  background: linear-gradient(135deg, #6e7aaf, #4e5a87);
  color: #fff;
  box-shadow: 4px 0 10px rgba(0, 0, 0, 0.1);
  padding-top: 20px;
  transition: width 0.3s ease;
}

.sidebar:hover {
  width: 270px;
}

.el-menu-vertical-demo {
  width: 100%;
  padding-top: 20px;
  background-color: transparent;
}

.menu-item {
  color: #fff;
  font-size: 16px;
  padding: 10px 20px;
  text-align: left;
  transition: background-color 0.3s, color 0.3s;
}

.menu-item:hover {
  color: #3e4b66;
}

.content {
  flex: 1;
  background-color: #f9f9f9;
  padding: 20px;
  overflow-y: auto;
  transition: padding 0.3s ease;
}

.layout-container:hover .content {
  padding: 30px;
}
</style>

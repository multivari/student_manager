<template>
  <div class="settings-page">
    <div class="settings-container">
      <!-- 用户信息 -->
      <el-card class="settings-card" shadow="hover">
        <div class="card-header">
          <h2>个人信息</h2>
        </div>
        <el-form :model="userInfo" label-width="150px" class="settings-form">
          <el-form-item label="姓名">
            <el-input v-model="userInfo.name" placeholder="请输入姓名" />
          </el-form-item>

          <el-form-item label="邮箱">
            <el-input v-model="userInfo.email" placeholder="请输入邮箱" />
          </el-form-item>

          <el-form-item label="电话">
            <el-input v-model="userInfo.phone" placeholder="请输入电话" />
          </el-form-item>

          <el-form-item label="地址">
            <el-input v-model="userInfo.address" placeholder="请输入地址" />
          </el-form-item>

          <el-button type="primary" @click="saveUserInfo">保存信息</el-button>
        </el-form>
      </el-card>

      <!-- 设置主题 -->
      <el-card class="settings-card" shadow="hover">
        <div class="card-header">
          <h2>主题设置</h2>
        </div>
        <el-form :model="themeSettings" label-width="150px">
          <el-form-item label="选择主题">
            <el-radio-group v-model="themeSettings.selectedTheme">
              <el-radio label="light">亮色主题</el-radio>
              <el-radio label="dark">暗色主题</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-button type="primary" @click="saveThemeSettings">保存主题设置</el-button>
        </el-form>
      </el-card>

      <!-- 通知设置 -->
      <el-card class="settings-card" shadow="hover">
        <div class="card-header">
          <h2>通知设置</h2>
        </div>
        <el-form :model="notificationSettings" label-width="150px">
          <el-form-item label="启用通知">
            <el-switch v-model="notificationSettings.notificationsEnabled" active-text="开启" inactive-text="关闭" />
          </el-form-item>
          <el-form-item label="邮件通知">
            <el-switch v-model="notificationSettings.emailNotifications" active-text="开启" inactive-text="关闭" />
          </el-form-item>
          <el-button type="primary" @click="saveNotificationSettings">保存通知设置</el-button>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SettingsPage',
  data() {
    return {
      // 用户信息数据
      userInfo: {
        name: '',
        email: '',
        phone: '',
        address: '',
      },
      // 主题设置
      themeSettings: {
        selectedTheme: 'light', // 默认主题
      },
      // 通知设置
      notificationSettings: {
        notificationsEnabled: true,
        emailNotifications: true,
      },
    };
  },
  methods: {
    saveUserInfo() {
      // 保存用户信息逻辑（可以是API请求）
      this.$message.success('用户信息已保存');
    },
    saveThemeSettings() {
      // 保存主题设置逻辑（比如切换主题样式）
      this.$message.success('主题设置已保存');
    },
    saveNotificationSettings() {
      // 保存通知设置逻辑
      this.$message.success('通知设置已保存');
    },
  },
};
</script>

<style scoped>
.settings-page {
  padding: 30px;
  background-color: #f4f6f9;
}

.settings-container {
  display: flex;
  flex-direction: column;
  gap: 30px;
}

.settings-card {
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.card-header {
  font-size: 22px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}

.settings-form {
  width: 100%;
}

.el-button {
  margin-top: 20px;
}

.el-radio-group {
  margin-top: 10px;
}

.el-switch {
  margin-top: 10px;
}
</style>

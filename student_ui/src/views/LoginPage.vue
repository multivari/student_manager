<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2>登录</h2>
      <el-form :model="form" ref="form" :rules="rules" label-width="80px">
        <el-form-item label="角色" prop="role">
          <el-select v-model="form.role" placeholder="请选择角色">
            <el-option label="学生" value="student"></el-option>
            <el-option label="教师" value="instructor"></el-option>
            <el-option label="管理员" value="admin"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import requests from '@/utils/request';  // 确保这里是你封装的请求工具

export default {
  name: "LoginPage",
  data() {
    return {
      form: {
        role: "", // 用户角色
        username: "", // 用户名
        password: "" // 密码
      },
      rules: {
        role: [{ required: true, message: "请选择角色", trigger: "change" }],
        username: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    async onSubmit() {
      // 校验表单
      this.$refs.form.validate(async (valid) => {
        if (!valid) return; // 校验失败直接返回

        const {role, username, password} = this.form;

        try {
          // 请求登录接口，传递角色、用户名和密码
          const response = await requests.post('/login', {username, password, role});

          // 处理后端返回的响应数据
          const {code, message, data,token} = response.data;
          console.log(data);

          if (code === 200) {
            // 登录成功，处理JWT
            // const {token, user} = data; // 假设后端返回 { token, user }
            console.log("user:"+data)
            // 登录成功
            localStorage.setItem('jwt_token', token);
            localStorage.setItem('user_role', data.role);
            localStorage.setItem('username', data.username);
            ElMessage.success(message || "登录成功");
            this.$store.commit('setToken', token);
            this.$store.commit('setUser', data);



            // 提示用户登录成功
            ElMessage.success(message || "登录成功");

            // 根据角色跳转到不同的首页
            const dashboard = role === "student" ? "/home" :
                role === "instructor" ? "/home" :
                    "/home"; // 假设管理员的主页是 "/admin-home"
            this.$router.push(dashboard); // 跳转到对应首页
          } else {
            // 显示后端返回的错误信息
            ElMessage.error(message || "登录失败");
          }
        } catch (error) {
          // 错误处理，展示错误信息
          ElMessage.error(error.response?.data?.message || "服务器错误，请稍后再试");
        }
      });
    }
  }
  };
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}
.login-card {
  width: 400px;
  padding: 20px;
}
</style>

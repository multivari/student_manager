<template>
  <div class="home-page">
    <div class="statistics-cards">
      <el-card class="card" shadow="hover" v-for="(item, index) in stats" :key="index">
        <div class="card-header">
          <h3>{{ item.title }}</h3>
        </div>
        <div class="card-body">
          <h2>{{ item.value }}</h2>
        </div>
      </el-card>
    </div>

    <div class="recent-students">
      <h2 class="section-title">最近添加的学生</h2>
      <el-table :data="recentStudents" border>
        <el-table-column prop="name" label="学生姓名" width="180" />
        <el-table-column prop="email" label="邮箱" width="250" />
        <el-table-column prop="dob" label="出生日期" width="180" />
        <el-table-column prop="enrollmentDate" label="入学日期" width="180" />
        <el-table-column label="操作" width="120">
          <template #default="scope">
            <el-button size="small" @click="viewStudent(scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="cta-section">
      <el-button type="primary" class="cta-button" @click="navigateToStudentManagement">
        管理学生信息
      </el-button>
    </div>
  </div>
</template>

<script>
import { ElButton, ElCard, ElTable, ElTableColumn } from 'element-plus';


export default {
  name: 'HomePage',
  components: {
    ElButton,
    ElCard,
    ElTable,
    ElTableColumn,
  },
  data() {
    return {
      // 模拟的数据，可以通过API获取
      stats: [
        { title: '总学生数', value: 150 },
        { title: '已毕业学生', value: 50 },
        { title: '未毕业学生', value: 100 },
        { title: '新增学生', value: 10 },
      ],
      recentStudents: [
        { name: '张三', email: 'zhangsan@example.com', dob: '2000-05-10', enrollmentDate: '2022-09-01' },
        { name: '李四', email: 'lisi@example.com', dob: '2001-06-15', enrollmentDate: '2021-09-01' },
        { name: '王五', email: 'wangwu@example.com', dob: '1999-04-20', enrollmentDate: '2020-09-01' },
      ],
    };
  },
  methods: {
    viewStudent(student) {
      this.$router.push({ name: 'StudentDetails', params: { studentId: student.id } });
    },
    navigateToStudentManagement() {
      this.$router.push({ name: 'Students' });
    },
  },
};
</script>

<style scoped>
.home-page {
  padding: 20px;
  background-color: #f4f6f9;
}

.statistics-cards {
  display: flex;
  justify-content: space-between;
  margin-bottom: 40px;
}

.card {
  width: 24%;
  padding: 20px;
  background-color: #ffffff;
}

.card-header {
  font-size: 18px;
  color: #555;
  margin-bottom: 10px;
}

.card-body {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-size: 24px;
  font-weight: bold;
  color: #409EFF;
}

.recent-students {
  margin-bottom: 40px;
}

.section-title {
  font-size: 24px;
  font-weight: 600;
  color: #333;
  margin-bottom: 20px;
}

.cta-section {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.cta-button {
  padding: 10px 30px;
  font-size: 16px;
  border-radius: 25px;
  background-color: #409EFF;
  color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.cta-button:hover {
  background-color: #66b1ff;
  box-shadow: 0 6px 8px rgba(0, 0, 0, 0.15);
}
</style>

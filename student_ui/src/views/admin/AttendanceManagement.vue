<template>
  <div>
    <el-table :data="attendances" style="width: 100%">
      <el-table-column prop="attendanceId" label="考勤ID" />
      <el-table-column prop="studentName" label="学生姓名"  />
      <el-table-column prop="courseName" label="课程名称"  />
      <el-table-column prop="date" label="考勤日期" />
      <el-table-column prop="statusText" label="考勤状态" />  <!-- 显示中文状态 -->
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="editAttendance(scope.row)" size="small">编辑</el-button>
          <el-button @click="deleteAttendance(scope.row.attendanceId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-button type="primary" @click="addAttendance">添加考勤</el-button>

    <!-- 编辑考勤对话框 -->
    <el-dialog title="编辑考勤" v-model="dialogVisible">
      <el-form :model="form" ref="form" :rules="formRules" label-width="80px">

        <!-- 学生选择 -->
        <el-form-item label="学生" prop="studentId">
          <el-select v-model="form.studentId" placeholder="请选择学生">
            <el-option
                v-for="student in students"
                :key="student.studentId"
                :label="student.name"
                :value="student.studentId"
            />
          </el-select>
        </el-form-item>

        <!-- 课程选择 -->
        <el-form-item label="课程" prop="courseId">
          <el-select v-model="form.courseId" placeholder="请选择课程">
            <el-option
                v-for="course in courses"
                :key="course.courseId"
                :label="course.courseName"
                :value="course.courseId"
            />
          </el-select>
        </el-form-item>

        <!-- 考勤日期 -->
        <el-form-item label="考勤日期" prop="date">
          <el-date-picker v-model="form.date" type="date" placeholder="选择考勤日期" style="width: 100%"></el-date-picker>
        </el-form-item>

        <!-- 考勤状态 -->
        <el-form-item label="考勤状态" prop="status">
          <el-select v-model="form.status" placeholder="选择考勤状态" style="width: 100%">
            <el-option label="出勤" value="Present"></el-option>
            <el-option label="缺席" value="Absent"></el-option>
          </el-select>
        </el-form-item>

      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog">取 消</el-button>
          <el-button type="primary" @click="submitForm">确 定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import requests from '@/utils/request';
import { ElMessage } from 'element-plus';

export default {
  name: "AttendanceManagement",
  data() {
    return {
      attendances: [],
      students: [], // 存储学生列表
      courses: [], // 存储课程列表
      dialogVisible: false,
      form: {
        attendanceId: null,
        studentId: '', // 提交的学生ID
        courseId: '',  // 提交的课程ID
        date: '',
        status: ''
      },
      formRules: {
        studentId: [{ required: true, message: '请选择学生', trigger: 'blur' }],
        courseId: [{ required: true, message: '请选择课程', trigger: 'blur' }],
        date: [{ required: true, message: '请选择考勤日期', trigger: 'change' }],
        status: [{ required: true, message: '请选择考勤状态', trigger: 'change' }]
      }
    };
  },
  created() {
    this.fetchAttendances();
    this.fetchStudents();
    this.fetchCourses();
  },
  methods: {
    // 获取考勤记录
    fetchAttendances() {
      requests.get('/attendances')
          .then(response => {
            this.attendances = response.data.map(item => {
              // 映射状态为中文
              item.statusText = this.mapStatusToText(item.status);
              return item;
            });
          })
          .catch(() => ElMessage.error('获取考勤记录失败'));
    },
// 状态映射函数
    mapStatusToText(status) {
      switch (status) {
        case 'Present':
          return '出勤';
        case 'Absent':
          return '缺席';
        default:
          return '未知状态';
      }
    },

    // 获取学生列表
    fetchStudents() {
      requests.get('/students')
          .then(response => {
            this.students = response.data;
          })
          .catch(() => ElMessage.error('获取学生列表失败'));
    },

    // 获取课程列表
    fetchCourses() {
      requests.get('/courses')
          .then(response => {
            this.courses = response.data;
          })
          .catch(() => ElMessage.error('获取课程列表失败'));
    },

    // 添加考勤记录
    addAttendance() {
      this.resetForm();
      this.dialogVisible = true;
    },

    // 编辑考勤记录
    editAttendance(row) {
      this.form = { ...row };
      this.dialogVisible = true;
    },

    // 删除考勤记录
    deleteAttendance(attendanceId) {
      requests.delete(`/attendances/${attendanceId}`)
          .then(() => {
            ElMessage.success('删除成功');
            this.fetchAttendances();
          })
          .catch(() => ElMessage.error('删除失败'));
    },

    // 关闭对话框
    closeDialog() {
      this.dialogVisible = false;
    },

    // 重置表单
    resetForm() {
      this.form = {
        attendanceId: null,
        studentId: '',
        courseId: '',
        date: '',
        status: ''
      };
    },

    // 提交表单
    submitForm() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.attendanceId) {
            requests.put(`/attendances/${this.form.attendanceId}`, this.form)
                .then(() => {
                  ElMessage.success('更新成功');
                  this.fetchAttendances();
                  this.closeDialog();
                })
                .catch(() => ElMessage.error('更新失败'));
          } else {
            requests.post('/attendances', this.form)
                .then(() => {
                  ElMessage.success('添加成功');
                  this.fetchAttendances();
                  this.closeDialog();
                })
                .catch(() => ElMessage.error('添加失败'));
          }
        }
      });
    },

  }
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>

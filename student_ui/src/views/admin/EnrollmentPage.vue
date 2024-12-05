<template>
  <div>
    <el-table :data="enrollments" style="width: 100%">
      <el-table-column prop="enrollmentId" label="选课ID" />
      <el-table-column prop="studentName" label="学生姓名" />
      <el-table-column prop="courseName" label="课程名称" />
      <el-table-column prop="grade" label="成绩" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="editEnrollment(scope.row)" size="small">编辑</el-button>
          <el-button @click="deleteEnrollment(scope.row.enrollmentId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-button type="primary" @click="addEnrollment">添加选课</el-button>

    <!-- 编辑选课对话框 -->
    <el-dialog title="编辑选课" v-model="dialogVisible">
      <el-form :model="form" ref="form" :rules="formRules" label-width="80px">

        <!-- 学生选择 -->
        <el-form-item label="学生" prop="studentId">
          <el-select v-model="form.studentId" placeholder="请选择学生" @change="handleStudentChange">
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
          <el-select v-model="form.courseId" placeholder="请选择课程" @change="handleCourseChange">
            <el-option
                v-for="course in courses"
                :key="course.courseId"
                :label="course.courseName"
                :value="course.courseId"
            />
          </el-select>
        </el-form-item>

        <!-- 成绩输入 -->
        <el-form-item label="成绩" prop="grade">
          <el-input v-model="form.grade" autocomplete="off" type="number"></el-input>
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
  name: "EnrollmentPage",
  data() {
    return {
      enrollments: [],
      students: [], // 存储学生列表
      courses: [], // 存储课程列表
      dialogVisible: false,
      form: {
        enrollmentId: null,
        studentId: '', // 学生ID
        courseId: '',  // 课程ID
        grade: ''
      },
      formRules: {
        studentId: [
          { required: true, message: '请选择学生', trigger: 'change' }
        ],
        courseId: [
          { required: true, message: '请选择课程', trigger: 'change' }
        ],
        grade: [
          { required: true, message: '请输入成绩', trigger: 'blur' },
          { pattern: /^[0-9]{1,2}(\.[0-9]{1,2})?$/, message: '请输入有效成绩', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.fetchEnrollments();
    this.fetchStudents();
    this.fetchCourses();
  },
  methods: {
    // 获取选课信息
    fetchEnrollments() {
      requests.get('/enrollments')
          .then(response => {
            this.enrollments = response.data;
          })
          .catch(() => this.showError('获取选课信息失败'));
    },

    // 获取学生信息
    fetchStudents() {
      requests.get('/students')
          .then(response => {
            this.students = response.data;
          })
          .catch(() => this.showError('获取学生信息失败'));
    },

    // 获取课程信息
    fetchCourses() {
      requests.get('/courses')
          .then(response => {
            this.courses = response.data;
          })
          .catch(() => this.showError('获取课程信息失败'));
    },

    showError(message) {
      ElMessage.error(message);
    },

    // 添加选课
    addEnrollment() {
      this.resetForm();
      this.dialogVisible = true;
    },

    // 编辑选课
    editEnrollment(enrollment) {
      this.form = { ...enrollment };  // 填充表单
      this.dialogVisible = true;
    },

    // 删除选课
    deleteEnrollment(enrollmentId) {
      requests.delete(`/enrollments/${enrollmentId}`)
          .then(() => {
            this.fetchEnrollments();
            ElMessage.success('删除成功');
          })
          .catch(() => this.showError('删除失败'));
    },

    // 提交表单
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const request = this.form.enrollmentId
              ? requests.put(`/enrollments/${this.form.enrollmentId}`, this.form)
              : requests.post('/enrollments', this.form);

          request
              .then(() => {
                this.closeDialog();
                this.fetchEnrollments();
                ElMessage.success(this.form.enrollmentId ? '更新成功' : '添加成功');
              })
              .catch(() => this.showError('操作失败'));
        } else {
          ElMessage.error('表单验证失败，请检查输入项');
          return false;
        }
      });
    },

    closeDialog() {
      this.dialogVisible = false;
    },

    resetForm() {
      this.form = {
        enrollmentId: null,
        studentId: '',
        courseId: '',
        grade: ''
      };
    },

    handleStudentChange(value) {
      console.log(value);
    },

    handleCourseChange(value) {
      console.log(value);
    },


    // 获取学生姓名
    getStudentName(studentId) {
      const student = this.students.find(student => student.studentId === studentId);
      return student ? student.studentName : '';
    },

    // 获取课程名称
    getCourseName(courseId) {
      const course = this.courses.find(course => course.courseId === courseId);
      return course ? course.courseName : '';
    }
  }
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>

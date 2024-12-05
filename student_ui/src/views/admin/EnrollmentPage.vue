<template>
  <div>
    <el-table :data="enrollments" style="width: 100%">
      <el-table-column prop="enrollmentId" label="选课ID" />
      <el-table-column prop="studentId" label="学生ID" />
      <el-table-column prop="courseId" label="课程ID" />
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
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="课程ID" prop="courseId">
          <el-input v-model="form.courseId" autocomplete="off"></el-input>
        </el-form-item>

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
      dialogVisible: false,
      form: {
        enrollmentId: null,
        studentId: '',
        courseId: '',
        grade: ''
      },
      formRules: {
        studentId: [
          { required: true, message: '请输入学生ID', trigger: 'blur' }
        ],
        courseId: [
          { required: true, message: '请输入课程ID', trigger: 'blur' }
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
  },
  methods: {
    fetchEnrollments() {
      requests.get('/enrollments')
          .then(response => {
            this.enrollments = response.data;
          })
          .catch(() => this.showError('获取选课信息失败'));
    },

    showError(message) {
      ElMessage.error(message);
    },

    addEnrollment() {
      this.resetForm();
      this.dialogVisible = true;
    },

    editEnrollment(enrollment) {
      this.form = { ...enrollment };  // 填充表单
      this.dialogVisible = true;
    },

    deleteEnrollment(enrollmentId) {
      requests.delete(`/enrollments/${enrollmentId}`)
          .then(() => {
            this.fetchEnrollments();
            ElMessage.success('删除成功');
          })
          .catch(() => this.showError('删除失败'));
    },

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
  },
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>

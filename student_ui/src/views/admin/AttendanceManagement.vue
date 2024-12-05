<template>
  <div>
    <el-table :data="attendances" style="width: 100%">
      <el-table-column prop="attendanceId" label="考勤ID" />
      <el-table-column prop="studentId" label="学生ID" />
      <el-table-column prop="courseId" label="课程ID" />
      <el-table-column prop="date" label="考勤日期" />
      <el-table-column prop="status" label="考勤状态" />
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
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="课程ID" prop="courseId">
          <el-input v-model="form.courseId" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="考勤日期" prop="date">
          <el-date-picker v-model="form.date" type="date" placeholder="选择考勤日期" style="width: 100%"></el-date-picker>
        </el-form-item>

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
      dialogVisible: false,
      form: {
        attendanceId: null,
        studentId: '',
        courseId: '',
        date: '',
        status: ''
      },
      formRules: {
        studentId: [{ required: true, message: '请输入学生ID', trigger: 'blur' }],
        courseId: [{ required: true, message: '请输入课程ID', trigger: 'blur' }],
        date: [{ required: true, message: '请选择考勤日期', trigger: 'change' }],
        status: [{ required: true, message: '请选择考勤状态', trigger: 'change' }]
      }
    };
  },
  created() {
    this.fetchAttendances();
  },
  methods: {
    fetchAttendances() {
      requests.get('/attendances')
          .then(response => {
            this.attendances = response.data;
          })
          .catch(() => ElMessage.error('获取考勤记录失败'));
    },

    addAttendance() {
      this.resetForm();
      this.dialogVisible = true;
    },

    editAttendance(row) {
      this.form = { ...row };
      this.dialogVisible = true;
    },

    deleteAttendance(attendanceId) {
      requests.delete(`/attendances/${attendanceId}`)
          .then(() => {
            ElMessage.success('删除成功');
            this.fetchAttendances();
          })
          .catch(() => ElMessage.error('删除失败'));
    },

    closeDialog() {
      this.dialogVisible = false;
    },

    resetForm() {
      this.form = {
        attendanceId: null,
        studentId: '',
        courseId: '',
        date: '',
        status: ''
      };
    },

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
    }
  }
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>

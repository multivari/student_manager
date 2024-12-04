<template>
  <div>
    <!-- 学生列表 -->
    <el-table :data="students" style="width: 100%">
      <el-table-column prop="name" label="学生姓名" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column prop="phone" label="电话" />
      <el-table-column prop="dob" label="出生日期" />
      <el-table-column prop="enrollmentDate" label="入学日期" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="editStudent(scope.row)" size="small">编辑</el-button>
          <el-button @click="deleteStudent(scope.row.studentId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-button type="primary" @click="addStudent">添加学生</el-button>

    <!-- 编辑学生对话框 -->
    <el-dialog title="编辑学生" v-model="dialogVisible">
      <el-form :model="form" ref="form" :rules="formRules" label-width="80px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="出生日期" prop="dob">
          <el-date-picker
              v-model="form.dob"
              type="date"
              placeholder="选择出生日期"
              style="width: 100%"
          ></el-date-picker>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="入学日期" prop="enrollmentDate">
          <el-date-picker
              v-model="form.enrollmentDate"
              type="date"
              placeholder="选择入学日期"
              style="width: 100%"
          ></el-date-picker>
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
  name: "ManagementPage",
  data() {
    return {
      students: [],
      dialogVisible: false,
      form: {
        studentId: null,
        name: '',
        email: '',
        phone: '',
        dob: '',
        address: '',
        enrollmentDate: ''
      },
      formLabelWidth: '80px',
      formRules: {
        name: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }
        ],
        dob: [
          { required: true, message: '请选择出生日期', trigger: 'change' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        enrollmentDate: [
          { required: true, message: '请选择入学日期', trigger: 'change' }
        ]
      }
    };
  },
  created() {
    this.fetchStudents();
  },
  methods: {
    fetchStudents() {
      requests.get('/students')
          .then(response => {
            this.students = response.data;
          })
          .catch(() => this.showError('获取学生信息失败'));
    },

    showError(message) {
      ElMessage.error(message);
    },

    addStudent() {
      this.resetForm();
      this.dialogVisible = true; // 显示对话框
    },

    editStudent(student) {
      // 转换日期为 Date 对象
      if (student.dob) {
        student.dob = new Date(student.dob);
      }
      if (student.enrollmentDate) {
        student.enrollmentDate = new Date(student.enrollmentDate);
      }
      this.form = { ...student }; // 填充表单
      this.dialogVisible = true; // 显示对话框
    },

    deleteStudent(studentId) {
      requests.delete(`/students/${studentId}`)
          .then(() => {
            this.fetchStudents();
            ElMessage.success('删除成功');
          })
          .catch(() => this.showError('删除失败'));
    },

    submitForm() {
      if (this.form.dob) {
        this.form.dob = this.form.dob.toISOString().split('T')[0];  // 转换为 yyyy-MM-dd 格式
      }
      if (this.form.enrollmentDate) {
        this.form.enrollmentDate = this.form.enrollmentDate.toISOString().split('T')[0];  // 转换为 yyyy-MM-dd 格式
      }
      this.$refs.form.validate(valid => {
        if (valid) {
          const request = this.form.studentId
              ? requests.put(`/students/${this.form.studentId}`, this.form)
              : requests.post('/students', this.form);

          request
              .then(() => {
                this.closeDialog();
                this.fetchStudents();
                ElMessage.success(this.form.studentId ? '更新成功' : '添加成功');
              })
              .catch(() => this.showError('操作失败'));
        } else {
          ElMessage.error('表单验证失败，请检查输入项');
          return false;
        }
      });
    },

    closeDialog() {
      this.dialogVisible = false; // 关闭对话框
    },

    resetForm() {
      this.form = {
        studentId: null,
        name: '',
        email: '',
        phone: '',
        dob: null,
        address: '',
        enrollmentDate: null
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

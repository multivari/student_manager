<template>
  <div>
    <!-- 教师列表 -->
    <el-table :data="instructors" style="width: 100%">
      <el-table-column label="教师ID" prop="instructorId" />
      <el-table-column label="教师姓名" prop="name" />
      <el-table-column label="邮箱" prop="email" />
      <el-table-column label="电话" prop="phone" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="openEditDialog(scope.row)" size="small">编辑</el-button>
          <el-button @click="deleteInstructor(scope.row.instructorId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加教师按钮 -->
    <el-button type="primary" @click="openAddDialog">添加教师</el-button>

    <!-- 添加教师对话框 -->
    <el-dialog title="添加教师" v-model="addDialogVisible">
      <el-form :model="instructorForm" ref="instructorForm" :rules="formRules" label-width="100px">
        <el-form-item label="教师姓名" prop="name">
          <el-input v-model="instructorForm.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="instructorForm.email" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="instructorForm.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog('add')">取消</el-button>
          <el-button type="primary" @click="submitForm('add')">添加</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 编辑教师对话框 -->
    <el-dialog title="编辑教师" v-model="editDialogVisible">
      <el-form :model="instructorForm" ref="instructorForm" :rules="formRules" label-width="100px">
        <el-form-item label="教师姓名" prop="name">
          <el-input v-model="instructorForm.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="instructorForm.email" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="instructorForm.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog('edit')">取消</el-button>
          <el-button type="primary" @click="submitForm('edit')">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import requests from '@/utils/request';
import { ElMessage } from 'element-plus';

export default {
  name: "InstructorManagement",
  data() {
    return {
      instructors: [],
      addDialogVisible: false,
      editDialogVisible: false,
      instructorForm: {
        instructorId: null,
        name: '',
        email: '',
        phone: ''
      },
      formRules: {
        name: [
          { required: true, message: '请输入教师姓名', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入教师邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入有效的邮箱地址', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入教师电话', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.fetchInstructors();
  },
  methods: {
    fetchInstructors() {
      requests.get('/instructors')
          .then(response => {
            this.instructors = response.data;
          })
          .catch(() => ElMessage.error('获取教师列表失败'));
    },

    openAddDialog() {
      this.resetForm();
      this.addDialogVisible = true;
    },

    openEditDialog(instructor) {
      this.instructorForm = { ...instructor };
      this.editDialogVisible = true;
    },

    submitForm(action) {
      this.$refs.instructorForm.validate(valid => {
        if (valid) {
          const request = action === 'add'
              ? requests.post('/instructors', this.instructorForm)
              : requests.put(`/instructors/${this.instructorForm.instructorId}`, this.instructorForm);

          request
              .then(() => {
                this.fetchInstructors();
                ElMessage.success(action === 'add' ? '教师添加成功' : '教师编辑成功');
                this.closeDialog(action);
              })
              .catch(() => this.showError('操作失败'));
        } else {
          ElMessage.error('表单验证失败，请检查输入项');
        }
      });
    },

    closeDialog(action) {
      if (action === 'add') {
        this.addDialogVisible = false;
      } else {
        this.editDialogVisible = false;
      }
    },

    deleteInstructor(instructorId) {
      requests.delete(`/instructors/${instructorId}`)
          .then(() => {
            this.fetchInstructors();
            ElMessage.success('教师删除成功');
          })
          .catch(() => this.showError('删除教师失败'));
    },

    resetForm() {
      this.instructorForm = {
        instructorId: null,
        name: '',
        email: '',
        phone: ''
      };
    }
  }
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>

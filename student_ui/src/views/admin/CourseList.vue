<template>
  <div>
    <!-- 课程列表 -->
    <el-table :data="courses" style="width: 100%">
      <el-table-column label="课程ID" prop="courseId" />
      <el-table-column label="课程名称" prop="courseName" />
      <el-table-column label="学分" prop="credits" />
      <el-table-column label="教师" prop="instructorName" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="openEditDialog(scope.row)" size="small">编辑</el-button>
          <el-button @click="deleteCourse(scope.row.courseId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加课程按钮 -->
    <el-button type="primary" @click="openAddDialog">添加课程</el-button>

    <!-- 添加课程对话框 -->
    <el-dialog title="添加课程" v-model="addDialogVisible">
      <el-form :model="courseForm" ref="courseForm" :rules="formRules" label-width="100px">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="courseForm.courseName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="学分" prop="credits">
          <el-input v-model="courseForm.credits" type="number" autocomplete="off"></el-input>
        </el-form-item>

        <!-- 教师下拉框 -->
        <el-form-item label="教师" prop="instructorId">
          <el-select v-model="courseForm.instructorId" placeholder="请选择教师">
            <el-option
                v-for="instructor in instructors"
                :key="instructor.instructorId"
                :label="instructor.name"
                :value="instructor.instructorId"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="closeDialog('add')">取消</el-button>
          <el-button type="primary" @click="submitForm('add')">添加</el-button>
        </span>
      </template>
    </el-dialog>

    <!-- 编辑课程对话框 -->
    <el-dialog title="编辑课程" v-model="editDialogVisible">
      <el-form :model="courseForm" ref="courseForm" :rules="formRules" label-width="100px">
        <el-form-item label="课程名称" prop="courseName">
          <el-input v-model="courseForm.courseName" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="学分" prop="credits">
          <el-input v-model="courseForm.credits" type="number" autocomplete="off"></el-input>
        </el-form-item>

        <!-- 教师下拉框 -->
        <el-form-item label="教师" prop="instructorId">
          <el-select v-model="courseForm.instructorId" placeholder="请选择教师">
            <el-option
                v-for="instructor in instructors"
                :key="instructor.instructorId"
                :label="instructor.name"
                :value="instructor.instructorId"
            ></el-option>
          </el-select>
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
  name: "CourseList",
  data() {
    return {
      courses: [],
      instructors: [],  // 存储教师列表
      addDialogVisible: false,
      editDialogVisible: false,
      courseForm: {
        courseId: null,
        courseName: '',
        credits: 0,
        instructorId: null,  // 选择的教师ID
      },
      formRules: {
        courseName: [
          { required: true, message: '请输入课程名称', trigger: 'blur' }
        ],
        credits: [
          {
            required: true,
            message: '请输入学分',
            trigger: 'blur'
          },
          {
            validator: (rule, value, callback) => {
              if (!value) {
                callback(new Error('请输入学分'));
              } else if (!/^(\d+(\.\d{1,2})?)?$/.test(value)) {
                callback(new Error('请输入有效的学分'));
              } else {
                callback();
              }
            },
            trigger: 'blur'
          }
        ],
        instructorId: [
          { required: true, message: '请选择教师', trigger: 'blur' },
        ]
      }
    };
  },
  created() {
    this.fetchCourses();
    this.fetchInstructors();  // 获取教师列表
  },
  methods: {
    // 获取课程列表
    fetchCourses() {
      requests.get('/courses')
          .then(response => {
            this.courses = response.data;
          })
          .catch(() => this.showError('获取课程列表失败'));
    },

    // 获取教师列表
    fetchInstructors() {
      requests.get('/instructors')
          .then(response => {
            this.instructors = response.data;
          })
          .catch(() => this.showError('获取教师列表失败'));
    },

    // 显示错误信息
    showError(message) {
      ElMessage.error(message);
    },

    // 打开添加课程对话框
    openAddDialog() {
      this.resetForm();
      this.addDialogVisible = true;
    },

    // 打开编辑课程对话框
    openEditDialog(course) {
      this.courseForm = { ...course };
      this.editDialogVisible = true;
    },

    // 提交表单
    submitForm(action) {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          const request = action === 'add'
              ? requests.post('/courses', this.courseForm)
              : requests.put(`/courses/${this.courseForm.courseId}`, this.courseForm);

          request
              .then(() => {
                this.fetchCourses();
                ElMessage.success(action === 'add' ? '课程添加成功' : '课程编辑成功');
                this.closeDialog(action);
              })
              .catch(() => this.showError('操作失败'));
        } else {
          ElMessage.error('表单验证失败，请检查输入项');
        }
      });
    },

    // 关闭对话框
    closeDialog(action) {
      if (action === 'add') {
        this.addDialogVisible = false;
      } else {
        this.editDialogVisible = false;
      }
    },

    // 删除课程
    deleteCourse(courseId) {
      requests.delete(`/courses/${courseId}`)
          .then(() => {
            this.fetchCourses();
            ElMessage.success('课程删除成功');
          })
          .catch(() => this.showError('删除课程失败'));
    },

    // 重置表单
    resetForm() {
      this.courseForm = {
        courseId: null,
        courseName: '',
        credits: 0,
        instructorId: null,  // 初始化为空
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

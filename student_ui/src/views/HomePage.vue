<template>
  <div>
    <!-- 考勤表格 -->
    <el-table :data="attendances" style="width: 100%">
      <el-table-column prop="attendanceId" label="考勤ID" />
      <el-table-column prop="studentName" label="学生姓名" />
      <el-table-column prop="courseName" label="课程名称" />
      <el-table-column prop="date" label="考勤日期" />
      <el-table-column prop="statusText" label="考勤状态" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button @click="editAttendance(scope.row)" size="small">编辑</el-button>
          <el-button @click="deleteAttendance(scope.row.attendanceId)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-button type="primary" @click="addAttendance">添加考勤</el-button>

    <!-- 考勤图表 -->
    <div style="margin-top: 20px;">
      <line-chart :data="chartData" :options="chartOptions" />
    </div>

    <!-- 编辑考勤对话框 -->
    <el-dialog title="编辑考勤" v-model="dialogVisible">
      <el-form :model="form" ref="form" :rules="formRules" label-width="80px">
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
          <el-button @click="closeDialog">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import requests from '@/utils/request';
import { ElMessage } from 'element-plus';
import { Line } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, LineElement, PointElement, CategoryScale, LinearScale } from 'chart.js';

// 注册Chart.js模块
ChartJS.register(
    Title,
    Tooltip,
    Legend,
    LineElement,
    PointElement, // 必须注册 PointElement
    CategoryScale,
    LinearScale
);

export default {
  name: "HomePage",
  components: {
    LineChart: Line,
  },
  data() {
    return {
      attendances: [],
      students: [],
      courses: [],
      dialogVisible: false,
      form: {
        attendanceId: null,
        studentId: '',
        courseId: '',
        date: '',
        status: ''
      },
      formRules: {
        studentId: [{ required: true, message: '请选择学生', trigger: 'blur' }],
        courseId: [{ required: true, message: '请选择课程', trigger: 'blur' }],
        date: [{ required: true, message: '请选择考勤日期', trigger: 'change' }],
        status: [{ required: true, message: '请选择考勤状态', trigger: 'change' }]
      },
      chartData: {
        labels: [],
        datasets: [
          {
            label: '出勤人数',
            data: [],
            borderColor: '#4CAF50',
            backgroundColor: 'rgba(76, 175, 80, 0.2)',
            tension: 0.4,
            fill: true,
          },
          {
            label: '缺席人数',
            data: [],
            borderColor: '#F44336',
            backgroundColor: 'rgba(244, 67, 54, 0.2)',
            tension: 0.4,
            fill: true,
          }
        ]
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          title: {
            display: true,
            text: '各课程考勤情况'
          },
          legend: {
            position: 'top',
          },
        },
        scales: {
          x: {
            beginAtZero: true,
            title: {
              display: true,
              text: '课程名称'
            }
          },
          y: {
            beginAtZero: true,
            title: {
              display: true,
              text: '人数'
            }
          }
        }
      }
    };
  },
  created() {
    console.log("访问homePage")
    this.fetchAttendances();
    this.fetchStudents();
    this.fetchCourses();
  },
  methods: {
    fetchAttendances() {
      requests.get('/attendances')
          .then(response => {
            this.attendances = response.data.map(item => {
              item.statusText = this.mapStatusToText(item.status);
              return item;
            });
            this.updateChartData();
          })
          .catch(() => ElMessage.error('获取考勤记录失败'));
    },
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
    fetchStudents() {
      requests.get('/students')
          .then(response => {
            this.students = response.data;
          })
          .catch(() => ElMessage.error('获取学生列表失败'));
    },
    fetchCourses() {
      requests.get('/courses')
          .then(response => {
            this.courses = response.data;
          })
          .catch(() => ElMessage.error('获取课程列表失败'));
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
    },
    updateChartData() {
      const courseNames = [];
      const presentCounts = [];
      const absentCounts = [];

      this.courses.forEach(course => {
        courseNames.push(course.courseName);
        const presentCount = this.attendances.filter(attendance => attendance.courseName === course.courseName && attendance.status === 'Present').length;
        const absentCount = this.attendances.filter(attendance => attendance.courseName === course.courseName && attendance.status === 'Absent').length;

        presentCounts.push(presentCount);
        absentCounts.push(absentCount);
      });

      this.chartData.labels = courseNames;
      this.chartData.datasets[0].data = presentCounts;
      this.chartData.datasets[1].data = absentCounts;
    }
  }
};
</script>

<style scoped>
.dialog-footer {
  text-align: right;
}
</style>

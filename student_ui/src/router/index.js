import { createRouter, createWebHistory } from 'vue-router';
import LayoutContainer from '@/components/LayoutContainer.vue'; // 导入布局组件
import HomePage from '@/views/HomePage.vue';
import ManagementPage from '@/views/admin/ManagementPage.vue';
import SettingsPage from '@/views/admin/SettingsPage.vue';
import CourseList from "@/views/admin/CourseList.vue";
import InstructorManagement from "@/views/admin/InstructorManagement";
import EnrollmentPage from "@/views/admin/EnrollmentPage";
import AttendanceManagement from "@/views/admin/AttendanceManagement";

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: LayoutContainer, // 使用布局容器作为父组件
        children: [
            {
                path: 'home', // 首页路由
                name: 'Home',
                component: HomePage
            },
            {
                path: 'management', // 学生管理路由
                name: 'Management',
                component: ManagementPage
            },
            {
                path: 'settings', // 设置页面路由
                name: 'Settings',
                component: SettingsPage
            },
            {
                path: 'course',
                name: 'course',
                component: CourseList,  // 将 CourseList 页面作为主页
            },
            {
                path: 'teacher',
                name: 'teacher',
                component: InstructorManagement,  // 将 CourseList 页面作为主页teacher
            },
            {
                path: 'enroll',
                name: 'enroll',
                component: EnrollmentPage,  // 将 CourseList attendance
            },
            {
                path: 'attendance',
                name: 'attendance',
                component: AttendanceManagement,  // 将 CourseList attendance
            },
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;

import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import ManagementPage from '@/views/admin/ManagementPage.vue';
import SettingsPage from '@/views/admin/SettingsPage.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage
    },
    {
        path: '/management',
        name: 'Management',
        component: ManagementPage
    },
    {
        path: '/settings',
        name: 'Settings',
        component: SettingsPage
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;

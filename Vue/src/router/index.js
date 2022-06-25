import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../layout/Home')
    },
    {
        path: '/adminLogin',
        name: 'AdminLogin',
        component: () => import('../layout/AdminLogin')
    },
    {
        path: '/adminHome',
        name: 'AdminHome',
        component: () => import('../layout/AdminHome'),
        /*
            嵌套路由
            本项目中路由分成两层，第一层是App.vue中的router-view，根据第一层路由地址的不同，显示不同页面，这些页面都在layout文件夹里
            第二层路由是SuperAdminHome.vue和AdminHome.vue中的router-view，根据第二层路由的不同显示不同页面，这些页面都在views文件夹里
         */
        children: [
            {
                path: '',
                name: 'UserManage',
                component: () => import('../views/admin/UserManage')
            },
            {
                path: '/publishNews',
                name: 'PublishNews',
                component: () => import('../views/admin/PublishNews')
            },
            {
                path: '/newsListForNotFirst',
                name: 'NewsListForNotFirst',
                component: () => import('../views/admin/NewsListForNotFirst')
            }
        ]
    },
    {
        path: '/superAdminLogin',
        name: 'SuperAdminLogin',
        component: () => import('../layout/SuperAdminLogin')
    },
    {
        path: '/superAdminHome',
        name: 'SuperAdminHome',
        component: () => import('../layout/SuperAdminHome'),
        /*
            嵌套路由
            本项目中路由分成两层，第一层是App.vue中的router-view，根据第一层路由地址的不同，显示不同页面，这些页面都在layout文件夹里
            第二层路由是SuperAdminHome.vue和AdminHome.vue中的router-view，根据第二层路由的不同显示不同页面，这些页面都在views文件夹里
         */
        children: [
            {
                path: '',
                name: 'AdminList',
                component: () => import('../views/superAdmin/AdminList')
            },
            {
                path: '/adminRequest',
                name: 'AdminRequest',
                component: () => import('../views/superAdmin/AdminRequest')
            }
        ]
    },
    {
        path: '/searchResult',
        name: 'SearchResult',
        component: () => import('../layout/SearchResult')
    },
    {
        path: '/finishUserInfo',
        name: 'FinishUserInfo',
        component: () => import('../layout/FinishUserInfo')
    },
    {
        path: '/hotNews',
        name: 'HotNews',
        component: () => import('../layout/HotNews')
    },
    {
        path: '/latestNews',
        name: 'LatestNews',
        component: () => import('../layout/LatestNews')
    },
    {
        path: '/newsContent',
        name: 'NewsContent',
        component: () => import('../layout/NewsContent')
    },
    {
        path: '/typeContent',
        name: 'TypeContent',
        component: () => import('../layout/TypeContent')
    },
    {
        path: '/userHome',
        name: 'UserHome',
        component: () => import('../layout/UserHome'),
        children: [
            {
                path: '',
                name: 'SelfInfo',
                component: () => import('../views/user/SelfInfo')
            },
            {
                path: '/search',
                name: 'Search',
                component: () => import('../views/user/Search')
            },
            {
                path: '/requestToAdmin',
                name: 'RequestToAdmin',
                component: () => import('../views/user/RequestToAdmin')
            }
        ]
    },
    {
        path: '/userLogin',
        name: 'UserLogin',
        component: () => import('../layout/UserLogin')
    }

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router

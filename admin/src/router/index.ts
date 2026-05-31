import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('../views/layout/LayoutView.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('../views/dashboard/DashboardView.vue'),
        meta: { title: '仪表盘' }
      },
      {
        path: 'banner',
        name: 'Banner',
        component: () => import('../views/banner/BannerManage.vue'),
        meta: { title: '轮播图管理' }
      },
      {
        path: 'company',
        name: 'Company',
        component: () => import('../views/company/CompanyEdit.vue'),
        meta: { title: '公司信息' }
      },
      {
        path: 'business',
        name: 'Business',
        component: () => import('../views/business/BusinessManage.vue'),
        meta: { title: '业务领域' }
      },
      {
        path: 'case',
        name: 'Case',
        component: () => import('../views/case/CaseManage.vue'),
        meta: { title: '工程案例' }
      },
      {
        path: 'news',
        name: 'News',
        component: () => import('../views/news/NewsManage.vue'),
        meta: { title: '新闻动态' }
      },
      {
        path: 'partner',
        name: 'Partner',
        component: () => import('../views/partner/PartnerManage.vue'),
        meta: { title: '合作伙伴' }
      },
      {
        path: 'message',
        name: 'Message',
        component: () => import('../views/message/MessageManage.vue'),
        meta: { title: '留言管理' }
      },
      {
        path: 'config',
        name: 'Config',
        component: () => import('../views/config/ConfigManage.vue'),
        meta: { title: '站点配置' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

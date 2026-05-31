<template>
  <div class="layout-container">
    <!-- 左侧侧边栏 -->
    <aside class="sidebar">
      <div class="sidebar-header">
        <img src="https://via.placeholder.com/32x32/409eff/ffffff?text=H" alt="Logo" class="sidebar-logo" />
        <span class="sidebar-title">虹远水利 CMS</span>
      </div>
      <el-menu
        :default-active="activeMenu"
        class="sidebar-menu"
        background-color="#1a2332"
        text-color="#bfcbd9"
        active-text-color="#409eff"
        router
      >
        <el-menu-item index="/dashboard">
          <el-icon><Odometer /></el-icon>
          <span>仪表盘</span>
        </el-menu-item>
        <el-menu-item index="/banner">
          <el-icon><Picture /></el-icon>
          <span>轮播图管理</span>
        </el-menu-item>
        <el-menu-item index="/company">
          <el-icon><OfficeBuilding /></el-icon>
          <span>公司信息</span>
        </el-menu-item>
        <el-menu-item index="/business">
          <el-icon><Grid /></el-icon>
          <span>业务领域</span>
        </el-menu-item>
        <el-menu-item index="/case">
          <el-icon><FolderOpened /></el-icon>
          <span>工程案例</span>
        </el-menu-item>
        <el-menu-item index="/news">
          <el-icon><Document /></el-icon>
          <span>新闻动态</span>
        </el-menu-item>
        <el-menu-item index="/partner">
          <el-icon><Connection /></el-icon>
          <span>合作伙伴</span>
        </el-menu-item>
        <el-menu-item index="/message">
          <el-icon><ChatDotRound /></el-icon>
          <span>留言管理</span>
        </el-menu-item>
        <el-menu-item index="/config">
          <el-icon><Setting /></el-icon>
          <span>站点配置</span>
        </el-menu-item>
      </el-menu>
    </aside>

    <!-- 右侧内容区 -->
    <div class="main-container">
      <!-- 顶栏 -->
      <header class="topbar">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/dashboard' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item v-if="currentTitle">{{ currentTitle }}</el-breadcrumb-item>
        </el-breadcrumb>
        <div class="topbar-right">
          <span class="admin-name">管理员</span>
          <el-button type="danger" text @click="handleLogout">
            <el-icon><SwitchButton /></el-icon>
            退出
          </el-button>
        </div>
      </header>

      <!-- 内容区 -->
      <main class="content-area">
        <router-view />
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {
  Odometer,
  Picture,
  OfficeBuilding,
  Grid,
  FolderOpened,
  Document,
  Connection,
  ChatDotRound,
  Setting,
  SwitchButton
} from '@element-plus/icons-vue'

const route = useRoute()
const router = useRouter()

const activeMenu = computed(() => route.path)

const currentTitle = computed(() => {
  return (route.meta?.title as string) || ''
})

const handleLogout = () => {
  localStorage.removeItem('admin_token')
  router.push('/dashboard')
}
</script>

<style scoped>
.layout-container {
  display: flex;
  height: 100vh;
  overflow: hidden;
}

.sidebar {
  width: 220px;
  min-width: 220px;
  background-color: #1a2332;
  display: flex;
  flex-direction: column;
  overflow-y: auto;
}

.sidebar-header {
  display: flex;
  align-items: center;
  padding: 20px 16px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar-logo {
  width: 32px;
  height: 32px;
  border-radius: 4px;
  margin-right: 10px;
}

.sidebar-title {
  color: #ffffff;
  font-size: 16px;
  font-weight: bold;
  white-space: nowrap;
}

.sidebar-menu {
  flex: 1;
  border-right: none;
}

.main-container {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background-color: #f0f2f5;
}

.topbar {
  height: 60px;
  background-color: #ffffff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 24px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
  z-index: 10;
}

.topbar-right {
  display: flex;
  align-items: center;
  gap: 12px;
}

.admin-name {
  font-size: 14px;
  color: #606266;
}

.content-area {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}
</style>

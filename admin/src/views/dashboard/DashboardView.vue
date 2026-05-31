<template>
  <div class="dashboard">
    <!-- 欢迎信息 -->
    <el-card class="welcome-card" shadow="never">
      <div class="welcome-content">
        <h2>欢迎使用虹远水利内容管理系统</h2>
        <p>您可以在这里管理公司官网的所有内容，包括轮播图、公司信息、业务领域、工程案例、新闻动态等。</p>
      </div>
    </el-card>

    <!-- 统计卡片 -->
    <el-row :gutter="20" class="stat-row">
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-number">{{ stats.newsCount }}</div>
          <div class="stat-label">总新闻数</div>
          <el-icon class="stat-icon" :size="40"><Document /></el-icon>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-number">{{ stats.caseCount }}</div>
          <div class="stat-label">总案例数</div>
          <el-icon class="stat-icon" :size="40"><FolderOpened /></el-icon>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-number">{{ stats.unreadMessageCount }}</div>
          <div class="stat-label">未读留言数</div>
          <el-icon class="stat-icon" :size="40"><ChatDotRound /></el-icon>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-number">{{ stats.partnerCount }}</div>
          <div class="stat-label">合作伙伴数</div>
          <el-icon class="stat-icon" :size="40"><Connection /></el-icon>
        </el-card>
      </el-col>
    </el-row>

    <!-- 快捷操作 -->
    <el-card shadow="never" class="quick-actions">
      <template #header>
        <span class="card-title">快捷操作</span>
      </template>
      <el-row :gutter="16">
        <el-col :span="4" v-for="action in quickActions" :key="action.path">
          <div class="action-item" @click="router.push(action.path)">
            <el-icon :size="28" :color="action.color">
              <component :is="action.icon" />
            </el-icon>
            <span class="action-label">{{ action.label }}</span>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import {
  Document,
  FolderOpened,
  ChatDotRound,
  Connection,
  Picture,
  OfficeBuilding,
  Grid,
  Setting
} from '@element-plus/icons-vue'
import { getNewsList } from '../../api/news'
import { getCaseList } from '../../api/case'
import { getMessageList } from '../../api/message'
import { getPartnerList } from '../../api/partner'

const router = useRouter()

const stats = ref({
  newsCount: 0,
  caseCount: 0,
  unreadMessageCount: 0,
  partnerCount: 0
})

const quickActions = [
  { label: '轮播图管理', path: '/banner', icon: Picture, color: '#409eff' },
  { label: '公司信息', path: '/company', icon: OfficeBuilding, color: '#67c23a' },
  { label: '业务领域', path: '/business', icon: Grid, color: '#e6a23c' },
  { label: '工程案例', path: '/case', icon: FolderOpened, color: '#f56c6c' },
  { label: '新闻动态', path: '/news', icon: Document, color: '#909399' },
  { label: '站点配置', path: '/config', icon: Setting, color: '#9c27b0' }
]

const loadStats = async () => {
  try {
    const [newsRes, caseRes, messageRes, partnerRes] = await Promise.allSettled([
      getNewsList({ page: 1, size: 1 }),
      getCaseList({ page: 1, size: 1 }),
      getMessageList({ status: 0, page: 1, size: 1 }),
      getPartnerList()
    ])
    if (newsRes.status === 'fulfilled') {
      stats.value.newsCount = newsRes.value?.data?.total || 0
    }
    if (caseRes.status === 'fulfilled') {
      stats.value.caseCount = caseRes.value?.data?.total || 0
    }
    if (messageRes.status === 'fulfilled') {
      stats.value.unreadMessageCount = messageRes.value?.data?.total || 0
    }
    if (partnerRes.status === 'fulfilled') {
      stats.value.partnerCount = partnerRes.value?.data?.length || partnerRes.value?.data?.total || 0
    }
  } catch (e) {
    // 静默处理
  }
}

onMounted(() => {
  loadStats()
})
</script>

<style scoped>
.dashboard {
  max-width: 1200px;
}

.welcome-card {
  margin-bottom: 20px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
}

.welcome-content h2 {
  color: #ffffff;
  margin: 0 0 8px 0;
  font-size: 22px;
}

.welcome-content p {
  color: rgba(255, 255, 255, 0.85);
  margin: 0;
  font-size: 14px;
}

.stat-row {
  margin-bottom: 20px;
}

.stat-card {
  text-align: center;
  position: relative;
  overflow: hidden;
}

.stat-card .stat-number {
  font-size: 36px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 4px;
}

.stat-card .stat-label {
  font-size: 14px;
  color: #909399;
}

.stat-card .stat-icon {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  color: #e8edf3;
}

.quick-actions .card-title {
  font-size: 16px;
  font-weight: bold;
  color: #303133;
}

.action-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px 8px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.action-item:hover {
  background-color: #f5f7fa;
}

.action-label {
  margin-top: 8px;
  font-size: 12px;
  color: #606266;
}
</style>

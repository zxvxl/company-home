<template>
  <div class="case-detail-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">工程案例</h1>
        <p class="page-banner__subtitle">工程详情</p>
      </div>
    </div>

    <section class="section">
      <div class="container">
        <!-- 面包屑 -->
        <div class="breadcrumb">
          <NuxtLink to="/">首页</NuxtLink>
          <span class="breadcrumb__separator">/</span>
          <NuxtLink to="/cases">工程案例</NuxtLink>
          <span class="breadcrumb__separator">/</span>
          <span>{{ caseDetail?.title || '详情' }}</span>
        </div>

        <div class="case-detail" v-if="caseDetail">
          <!-- 项目标题 -->
          <h1 class="case-detail__title">{{ caseDetail.title }}</h1>

          <!-- 项目信息栏 -->
          <div class="case-detail__info">
            <div class="case-detail__info-item" v-if="caseDetail.location">
              <span class="case-detail__info-label">项目地点</span>
              <span class="case-detail__info-value">{{ caseDetail.location }}</span>
            </div>
            <div class="case-detail__info-item" v-if="caseDetail.projectDate">
              <span class="case-detail__info-label">工期</span>
              <span class="case-detail__info-value">{{ caseDetail.projectDate }}</span>
            </div>
            <div class="case-detail__info-item" v-if="caseDetail.projectScale">
              <span class="case-detail__info-label">工程规模</span>
              <span class="case-detail__info-value">{{ caseDetail.projectScale }}</span>
            </div>
            <div class="case-detail__info-item" v-if="caseDetail.categoryId">
              <span class="case-detail__info-label">项目分类</span>
              <span class="case-detail__info-value">水利工程</span>
            </div>
          </div>

          <!-- 封面大图 -->
          <div class="case-detail__cover" v-if="caseDetail.coverImage">
            <img :src="caseDetail.coverImage" :alt="caseDetail.title" />
          </div>

          <!-- 项目摘要 -->
          <div class="case-detail__summary" v-if="caseDetail.summary">
            <h3>项目概述</h3>
            <p>{{ caseDetail.summary }}</p>
          </div>

          <!-- 富文本详情 -->
          <div class="rich-content" v-if="caseDetail.content" v-html="caseDetail.content"></div>

          <!-- 无详情时显示默认 -->
          <div v-if="!caseDetail.content" class="case-detail__default">
            <h3>项目介绍</h3>
            <p>{{ caseDetail.summary || '暂无详细介绍' }}</p>
            <p>该项目由虹远水利工程有限公司承建，公司凭借专业的技术团队和丰富的施工经验，圆满完成了项目建设任务。工程质量优良，得到了业主方和监理单位的高度评价。</p>
          </div>
        </div>

        <div v-else class="case-detail__empty">
          <p>加载中...</p>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
const route = useRoute()
const { getCaseDetail } = useApi()

const id = route.params.id as string
const { data: caseRes } = await getCaseDetail(id)

const caseDetail = computed(() => {
  return caseRes.value?.data || {
    title: '案例详情',
    summary: '暂无数据',
    coverImage: '',
    content: '',
    location: '',
    projectDate: '',
    projectScale: '',
    categoryId: 0
  }
})

useHead({
  title: computed(() => caseDetail.value?.title || '案例详情')
})
</script>

<style scoped>
.case-detail__title {
  font-size: var(--font-size-3xl);
  font-weight: 700;
  color: var(--color-text);
  margin-bottom: var(--spacing-xl);
}

.case-detail__info {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: var(--spacing-md);
  padding: var(--spacing-xl);
  background: var(--color-bg-section);
  border-radius: var(--radius-md);
  margin-bottom: var(--spacing-2xl);
}

.case-detail__info-item {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-xs);
}

.case-detail__info-label {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  font-weight: 500;
}

.case-detail__info-value {
  font-size: var(--font-size-base);
  color: var(--color-text);
  font-weight: 600;
}

.case-detail__cover {
  margin-bottom: var(--spacing-2xl);
  border-radius: var(--radius-lg);
  overflow: hidden;
}

.case-detail__cover img {
  width: 100%;
  max-height: 500px;
  object-fit: cover;
}

.case-detail__summary {
  margin-bottom: var(--spacing-2xl);
  padding: var(--spacing-xl);
  border-left: 4px solid var(--color-primary-light);
  background: var(--color-bg-section);
  border-radius: 0 var(--radius-md) var(--radius-md) 0;
}

.case-detail__summary h3 {
  font-size: var(--font-size-lg);
  color: var(--color-primary);
  margin-bottom: var(--spacing-md);
}

.case-detail__summary p {
  color: var(--color-text-light);
  line-height: 1.8;
  margin: 0;
}

.case-detail__default {
  line-height: 2;
}

.case-detail__default h3 {
  font-size: var(--font-size-xl);
  color: var(--color-text);
  margin-bottom: var(--spacing-lg);
}

.case-detail__default p {
  color: var(--color-text-light);
  margin-bottom: var(--spacing-lg);
}

.case-detail__empty {
  text-align: center;
  padding: var(--spacing-3xl);
  color: var(--color-text-light);
}
</style>

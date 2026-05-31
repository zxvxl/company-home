<template>
  <div class="news-detail-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">新闻中心</h1>
        <p class="page-banner__subtitle">新闻详情</p>
      </div>
    </div>

    <section class="section">
      <div class="container">
        <!-- 面包屑 -->
        <div class="breadcrumb">
          <NuxtLink to="/">首页</NuxtLink>
          <span class="breadcrumb__separator">/</span>
          <NuxtLink to="/news">新闻中心</NuxtLink>
          <span class="breadcrumb__separator">/</span>
          <span>{{ newsDetail?.title || '详情' }}</span>
        </div>

        <article class="news-article" v-if="newsDetail">
          <!-- 标题 -->
          <h1 class="news-article__title">{{ newsDetail.title }}</h1>

          <!-- 元信息 -->
          <div class="news-article__meta">
            <span class="news-article__meta-item" v-if="newsDetail.author">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="news-article__meta-icon">
                <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                <circle cx="12" cy="7" r="4"/>
              </svg>
              {{ newsDetail.author }}
            </span>
            <span class="news-article__meta-item" v-if="newsDetail.publishTime">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="news-article__meta-icon">
                <rect x="3" y="4" width="18" height="18" rx="2" ry="2"/>
                <line x1="16" y1="2" x2="16" y2="6"/>
                <line x1="8" y1="2" x2="8" y2="6"/>
                <line x1="3" y1="10" x2="21" y2="10"/>
              </svg>
              {{ formatDate(newsDetail.publishTime) }}
            </span>
            <span class="news-article__meta-item" v-if="newsDetail.viewCount">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" class="news-article__meta-icon">
                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                <circle cx="12" cy="12" r="3"/>
              </svg>
              {{ newsDetail.viewCount }} 阅读
            </span>
          </div>

          <!-- 封面图 -->
          <div class="news-article__cover" v-if="newsDetail.coverImage">
            <img :src="newsDetail.coverImage" :alt="newsDetail.title" />
          </div>

          <!-- 正文 -->
          <div class="rich-content" v-if="newsDetail.content" v-html="newsDetail.content"></div>

          <!-- 无正文时显示摘要 -->
          <div v-if="!newsDetail.content" class="news-article__default">
            <p>{{ newsDetail.summary }}</p>
          </div>

          <!-- 上一篇/下一篇 -->
          <div class="news-article__nav">
            <NuxtLink
              v-if="prevId"
              :to="`/news/${prevId}`"
              class="news-article__nav-item news-article__nav-item--prev"
            >
              <span class="news-article__nav-label">&larr; 上一篇</span>
            </NuxtLink>
            <NuxtLink
              v-if="nextId"
              :to="`/news/${nextId}`"
              class="news-article__nav-item news-article__nav-item--next"
            >
              <span class="news-article__nav-label">下一篇 &rarr;</span>
            </NuxtLink>
          </div>
        </article>

        <div v-else class="news-article__empty">
          <p>加载中...</p>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
const route = useRoute()
const { getNewsDetail } = useApi()

const id = route.params.id as string
const { data: newsRes } = await getNewsDetail(id)

const newsDetail = computed(() => {
  return newsRes.value?.data || {
    title: '新闻详情',
    summary: '暂无数据',
    content: '',
    author: '',
    publishTime: '',
    viewCount: 0,
    coverImage: ''
  }
})

// 简单的上下篇（基于当前id计算）
const currentId = computed(() => parseInt(id))
const prevId = computed(() => currentId.value > 1 ? currentId.value - 1 : null)
const nextId = computed(() => currentId.value + 1)

const formatDate = (dateStr: string) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}年${month}月${day}日`
}

useHead({
  title: computed(() => newsDetail.value?.title || '新闻详情')
})
</script>

<style scoped>
.news-article {
  max-width: 800px;
  margin: 0 auto;
}

.news-article__title {
  font-size: var(--font-size-3xl);
  font-weight: 700;
  color: var(--color-text);
  margin-bottom: var(--spacing-lg);
  line-height: 1.4;
}

.news-article__meta {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: var(--spacing-xl);
  padding-bottom: var(--spacing-lg);
  margin-bottom: var(--spacing-2xl);
  border-bottom: 1px solid var(--color-border-light);
}

.news-article__meta-item {
  display: inline-flex;
  align-items: center;
  gap: var(--spacing-xs);
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
}

.news-article__meta-icon {
  width: 16px;
  height: 16px;
}

.news-article__cover {
  margin-bottom: var(--spacing-2xl);
  border-radius: var(--radius-lg);
  overflow: hidden;
}

.news-article__cover img {
  width: 100%;
  max-height: 450px;
  object-fit: cover;
}

.news-article__default {
  line-height: 2;
  font-size: var(--font-size-base);
  color: var(--color-text-light);
}

.news-article__default p {
  margin-bottom: var(--spacing-lg);
}

.news-article__nav {
  display: flex;
  justify-content: space-between;
  margin-top: var(--spacing-3xl);
  padding-top: var(--spacing-xl);
  border-top: 1px solid var(--color-border-light);
}

.news-article__nav-item {
  display: inline-flex;
  align-items: center;
  gap: var(--spacing-sm);
  padding: var(--spacing-md) var(--spacing-lg);
  border-radius: var(--radius-md);
  background: var(--color-bg-section);
  color: var(--color-text);
  text-decoration: none;
  font-size: var(--font-size-sm);
  transition: all var(--transition-fast);
}

.news-article__nav-item:hover {
  background: var(--color-primary);
  color: var(--color-white);
}

.news-article__nav-label {
  font-weight: 500;
}

.news-article__empty {
  text-align: center;
  padding: var(--spacing-3xl);
  color: var(--color-text-light);
}

@media (max-width: 768px) {
  .news-article__title {
    font-size: var(--font-size-2xl);
  }

  .news-article__meta {
    gap: var(--spacing-md);
  }

  .news-article__nav {
    flex-direction: column;
    gap: var(--spacing-md);
  }
}
</style>

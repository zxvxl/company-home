<template>
  <NuxtLink :to="`/news/${id}`" class="news-card">
    <div class="news-card__image">
      <img :src="coverImage || defaultImage" :alt="title" />
    </div>
    <div class="news-card__body">
      <div class="news-card__meta">
        <span class="news-card__date">{{ formatDate(publishTime) }}</span>
        <span class="news-card__category" v-if="category">{{ category }}</span>
      </div>
      <h3 class="news-card__title">{{ title }}</h3>
      <p class="news-card__summary" v-if="summary">{{ summary }}</p>
      <span class="news-card__readmore">阅读全文 &rarr;</span>
    </div>
  </NuxtLink>
</template>

<script setup lang="ts">
defineProps<{
  id: number
  title: string
  coverImage?: string
  summary?: string
  category?: string
  publishTime: string
}>()

const defaultImage = 'data:image/svg+xml,' + encodeURIComponent(`
  <svg width="400" height="250" xmlns="http://www.w3.org/2000/svg">
    <rect width="400" height="250" fill="#eef2f7"/>
    <text x="200" y="125" font-family="sans-serif" font-size="14" fill="#7f8c8d" text-anchor="middle">新闻图片</text>
  </svg>
`)

const formatDate = (dateStr: string) => {
  if (!dateStr) return ''
  const date = new Date(dateStr)
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  return `${year}-${month}-${day}`
}
</script>

<style scoped>
.news-card {
  display: flex;
  gap: var(--spacing-xl);
  padding: var(--spacing-lg);
  border-radius: var(--radius-md);
  background: var(--color-white);
  box-shadow: var(--shadow-sm);
  text-decoration: none;
  color: inherit;
  transition: all var(--transition-normal);
}

.news-card:hover {
  box-shadow: var(--shadow-md);
  transform: translateY(-2px);
}

.news-card__image {
  flex-shrink: 0;
  width: 240px;
  height: 160px;
  border-radius: var(--radius-md);
  overflow: hidden;
}

.news-card__image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform var(--transition-slow);
}

.news-card:hover .news-card__image img {
  transform: scale(1.05);
}

.news-card__body {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.news-card__meta {
  display: flex;
  align-items: center;
  gap: var(--spacing-md);
  margin-bottom: var(--spacing-sm);
}

.news-card__date {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
}

.news-card__category {
  font-size: var(--font-size-xs);
  color: var(--color-primary-light);
  background: rgba(41, 128, 185, 0.08);
  padding: 0.15rem 0.5rem;
  border-radius: var(--radius-sm);
  font-weight: 500;
}

.news-card__title {
  font-size: var(--font-size-lg);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-sm);
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  line-height: 1.5;
}

.news-card:hover .news-card__title {
  color: var(--color-primary-light);
}

.news-card__summary {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.7;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  margin-bottom: var(--spacing-md);
}

.news-card__readmore {
  margin-top: auto;
  font-size: var(--font-size-sm);
  color: var(--color-primary-light);
  font-weight: 500;
}

.news-card:hover .news-card__readmore {
  color: var(--color-accent);
}

@media (max-width: 768px) {
  .news-card {
    flex-direction: column;
    gap: var(--spacing-md);
  }

  .news-card__image {
    width: 100%;
    height: 200px;
  }
}
</style>

<template>
  <NuxtLink :to="`/cases/${id}`" class="case-card card">
    <div class="case-card__image">
      <img :src="coverImage || defaultImage" :alt="title" />
      <span class="case-card__category" v-if="category">{{ category }}</span>
    </div>
    <div class="case-card__body">
      <h3 class="case-card__title">{{ title }}</h3>
      <p class="case-card__summary" v-if="summary">{{ summary }}</p>
      <div class="case-card__meta">
        <span class="case-card__meta-item" v-if="location">
          <svg class="case-card__meta-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7z"/>
            <circle cx="12" cy="9" r="2.5"/>
          </svg>
          {{ location }}
        </span>
        <span class="case-card__meta-item" v-if="projectScale">
          {{ projectScale }}
        </span>
      </div>
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
  location?: string
  projectScale?: string
}>()

const defaultImage = 'data:image/svg+xml,' + encodeURIComponent(`
  <svg width="400" height="300" xmlns="http://www.w3.org/2000/svg">
    <rect width="400" height="300" fill="#e8f4f8"/>
    <text x="200" y="150" font-family="sans-serif" font-size="14" fill="#7f8c8d" text-anchor="middle">工程案例图片</text>
  </svg>
`)
</script>

<style scoped>
.case-card {
  display: block;
  text-decoration: none;
  color: inherit;
}

.case-card__image {
  position: relative;
  padding-top: 65%;
  overflow: hidden;
}

.case-card__image img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform var(--transition-slow);
}

.case-card:hover .case-card__image img {
  transform: scale(1.05);
}

.case-card__category {
  position: absolute;
  top: var(--spacing-md);
  left: var(--spacing-md);
  background: var(--color-accent);
  color: var(--color-white);
  padding: 0.25rem 0.75rem;
  border-radius: var(--radius-sm);
  font-size: var(--font-size-xs);
  font-weight: 600;
}

.case-card__body {
  padding: var(--spacing-lg);
}

.case-card__title {
  font-size: var(--font-size-lg);
  font-weight: 600;
  margin-bottom: var(--spacing-sm);
  color: var(--color-text);
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.case-card__summary {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.6;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  margin-bottom: var(--spacing-md);
}

.case-card__meta {
  display: flex;
  flex-wrap: wrap;
  gap: var(--spacing-md);
}

.case-card__meta-item {
  display: inline-flex;
  align-items: center;
  gap: 0.25rem;
  font-size: var(--font-size-xs);
  color: var(--color-text-light);
}

.case-card__meta-icon {
  width: 14px;
  height: 14px;
}
</style>

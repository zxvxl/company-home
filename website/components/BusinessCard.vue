<template>
  <div class="business-card card">
    <div class="business-card__icon">
      <img v-if="isImageIcon" :src="icon" :alt="title" class="business-card__icon-img" />
      <span v-else class="business-card__icon-text">{{ iconFallback }}</span>
    </div>
    <h3 class="business-card__title">{{ title }}</h3>
    <p class="business-card__desc">{{ summary }}</p>
    <NuxtLink v-if="id" :to="`/business/${id}`" class="business-card__link">
      了解详情 <span>&rarr;</span>
    </NuxtLink>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{
  id?: number
  title: string
  icon?: string
  summary: string
}>()

const isImageIcon = computed(() => {
  return props.icon && (props.icon.startsWith('http') || props.icon.startsWith('/'))
})

const iconFallback = computed(() => {
  return props.title.charAt(0)
})
</script>

<style scoped>
.business-card {
  padding: var(--spacing-2xl);
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: var(--spacing-md);
}

.business-card__icon {
  width: 72px;
  height: 72px;
  border-radius: var(--radius-lg);
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-light));
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: var(--spacing-sm);
}

.business-card__icon-img {
  width: 40px;
  height: 40px;
  object-fit: contain;
  filter: brightness(10);
}

.business-card__icon-text {
  font-size: var(--font-size-2xl);
  font-weight: 700;
  color: var(--color-white);
}

.business-card__title {
  font-size: var(--font-size-xl);
  font-weight: 600;
  color: var(--color-text);
}

.business-card__desc {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.6;
  margin: 0;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.business-card__link {
  margin-top: auto;
  padding-top: var(--spacing-md);
  font-size: var(--font-size-sm);
  color: var(--color-primary-light);
  font-weight: 500;
  text-decoration: none;
  display: inline-flex;
  align-items: center;
  gap: var(--spacing-xs);
  transition: all var(--transition-fast);
}

.business-card__link:hover {
  color: var(--color-accent);
  gap: var(--spacing-sm);
}
</style>

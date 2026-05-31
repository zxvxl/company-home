<template>
  <div class="section-title" :class="{ 'section-title--light': light }">
    <span class="section-title__tag" v-if="tag">{{ tag }}</span>
    <h2 class="section-title__heading">{{ title }}</h2>
    <p class="section-title__desc" v-if="subtitle">{{ subtitle }}</p>
    <div class="section-title__line">
      <span class="section-title__line-accent"></span>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps<{
  title: string
  subtitle?: string
  tag?: string
  light?: boolean
}>()
</script>

<style scoped>
.section-title {
  text-align: center;
  margin-bottom: var(--spacing-3xl);
}

.section-title__tag {
  display: inline-block;
  font-size: var(--font-size-sm);
  color: var(--color-accent);
  font-weight: 600;
  letter-spacing: 2px;
  text-transform: uppercase;
  margin-bottom: var(--spacing-sm);
}

.section-title__heading {
  font-size: var(--font-size-3xl);
  font-weight: 700;
  color: var(--color-text);
  margin-bottom: var(--spacing-md);
}

.section-title__desc {
  font-size: var(--font-size-base);
  color: var(--color-text-light);
  max-width: 600px;
  margin: 0 auto var(--spacing-lg);
  line-height: 1.6;
}

.section-title__line {
  display: flex;
  align-items: center;
  justify-content: center;
}

.section-title__line-accent {
  display: block;
  width: 60px;
  height: 3px;
  background: linear-gradient(90deg, var(--color-primary), var(--color-accent));
  border-radius: 2px;
}

/* 浅色模式（深色背景上） */
.section-title--light .section-title__heading {
  color: var(--color-white);
}

.section-title--light .section-title__desc {
  color: rgba(255, 255, 255, 0.8);
}

@media (max-width: 768px) {
  .section-title__heading {
    font-size: var(--font-size-2xl);
  }

  .section-title {
    margin-bottom: var(--spacing-2xl);
  }
}
</style>

<template>
  <div class="business-detail-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">业务领域</h1>
        <p class="page-banner__subtitle">专业水利工程服务</p>
      </div>
    </div>

    <section class="section">
      <div class="container">
        <!-- 面包屑导航 -->
        <div class="breadcrumb">
          <NuxtLink to="/">首页</NuxtLink>
          <span class="breadcrumb__separator">/</span>
          <NuxtLink to="/business">业务领域</NuxtLink>
          <span class="breadcrumb__separator">/</span>
          <span>{{ business?.title || '详情' }}</span>
        </div>

        <div class="detail" v-if="business">
          <!-- 标题 -->
          <h1 class="detail__title">{{ business.title }}</h1>

          <!-- 封面图 -->
          <div class="detail__cover" v-if="business.coverImage">
            <img :src="business.coverImage" :alt="business.title" />
          </div>

          <!-- 简要描述 -->
          <div class="detail__summary" v-if="business.summary">
            <p>{{ business.summary }}</p>
          </div>

          <!-- 富文本内容 -->
          <div class="rich-content" v-if="business.content" v-html="business.content"></div>

          <!-- 无内容时显示默认 -->
          <div v-if="!business.content" class="detail__default">
            <p>{{ business.summary }}</p>
            <p>虹远水利在该领域拥有丰富的工程经验和专业技术团队，已成功完成多项相关工程项目，积累了大量实践经验。我们将根据项目实际情况，制定科学合理的实施方案，确保工程质量和安全。</p>
            <p>如需了解更多详情或咨询合作事宜，欢迎联系我们。</p>
            <NuxtLink to="/contact" class="btn btn--primary" style="margin-top: 2rem;">联系我们</NuxtLink>
          </div>
        </div>

        <!-- 加载/错误状态 -->
        <div v-else class="detail__empty">
          <p>加载中...</p>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
const route = useRoute()
const { getBusinessDetail } = useApi()

const id = route.params.id as string
const { data: businessRes } = await getBusinessDetail(id)

const business = computed(() => {
  return businessRes.value?.data || {
    title: '业务详情',
    summary: '暂无数据',
    coverImage: '',
    content: ''
  }
})

useHead({
  title: computed(() => business.value?.title || '业务详情')
})
</script>

<style scoped>
.detail__title {
  font-size: var(--font-size-3xl);
  font-weight: 700;
  color: var(--color-text);
  margin-bottom: var(--spacing-xl);
  padding-bottom: var(--spacing-lg);
  border-bottom: 2px solid var(--color-border-light);
}

.detail__cover {
  margin-bottom: var(--spacing-2xl);
  border-radius: var(--radius-lg);
  overflow: hidden;
}

.detail__cover img {
  width: 100%;
  max-height: 450px;
  object-fit: cover;
}

.detail__summary {
  font-size: var(--font-size-lg);
  color: var(--color-text-light);
  line-height: 1.8;
  margin-bottom: var(--spacing-2xl);
  padding: var(--spacing-xl);
  background: var(--color-bg-section);
  border-radius: var(--radius-md);
  border-left: 4px solid var(--color-primary-light);
}

.detail__summary p {
  margin: 0;
}

.detail__default {
  line-height: 2;
  color: var(--color-text-light);
  font-size: var(--font-size-base);
}

.detail__default p {
  margin-bottom: var(--spacing-lg);
}

.detail__empty {
  text-align: center;
  padding: var(--spacing-3xl);
  color: var(--color-text-light);
}
</style>

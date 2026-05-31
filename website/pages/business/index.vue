<template>
  <div class="business-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">业务领域</h1>
        <p class="page-banner__subtitle">全方位水利工程解决方案</p>
      </div>
    </div>

    <!-- 业务列表 -->
    <section class="section">
      <div class="container">
        <div class="business-grid">
          <div v-for="item in businessList" :key="item.id" class="business-item card">
            <div class="business-item__image">
              <img v-if="item.coverImage" :src="item.coverImage" :alt="item.title" />
              <div v-else class="business-item__placeholder">
                <svg viewBox="0 0 400 200" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <rect width="400" height="200" fill="#e8f4f8"/>
                  <text x="200" y="100" font-family="sans-serif" font-size="16" fill="#2980b9" text-anchor="middle" font-weight="bold">{{ item.title }}</text>
                </svg>
              </div>
            </div>
            <div class="business-item__body">
              <h3 class="business-item__title">{{ item.title }}</h3>
              <p class="business-item__desc">{{ item.summary }}</p>
              <NuxtLink :to="`/business/${item.id}`" class="btn btn--primary btn--sm">
                了解详情
              </NuxtLink>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({ title: '业务领域' })

const { getBusinessList } = useApi()
const { data: businessData } = await getBusinessList()

const businessList = computed(() => {
  const list = businessData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, title: '水库大坝工程', coverImage: '', summary: '承建各类大中型水库大坝工程，涵盖混凝土坝、土石坝、拱坝等多种坝型的勘察设计与施工建设，确保工程安全运行，保障防洪蓄水功能。' },
    { id: 2, title: '河道综合治理', coverImage: '', summary: '提供河道清淤疏浚、护岸加固、堤防建设、生态修复等综合治理方案，改善水环境质量，提升河道行洪能力，打造生态河道景观。' },
    { id: 3, title: '农田水利灌溉', coverImage: '', summary: '规划设计高效节水灌溉系统，包括渠道衬砌、管道灌溉、喷灌滴灌等，提升农业用水效率，保障粮食生产安全。' },
    { id: 4, title: '防洪排涝工程', coverImage: '', summary: '建设防洪堤坝、排涝泵站、水闸等设施，构建完善的城市防洪排涝体系，提升区域防洪排涝能力。' },
    { id: 5, title: '水土保持工程', coverImage: '', summary: '实施坡面治理、沟道治理、植被恢复等水土保持措施，有效防治水土流失，保护生态环境。' },
    { id: 6, title: '城乡供水工程', coverImage: '', summary: '建设城乡供水管网、水处理厂站、调蓄设施等，保障城乡居民饮水安全和工业生产用水需求。' }
  ]
})
</script>

<style scoped>
.business-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: var(--spacing-xl);
}

.business-item {
  display: flex;
  flex-direction: column;
}

.business-item__image {
  height: 220px;
  overflow: hidden;
}

.business-item__image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform var(--transition-slow);
}

.business-item:hover .business-item__image img {
  transform: scale(1.05);
}

.business-item__placeholder {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #e8f4f8, #d4edda);
}

.business-item__placeholder svg {
  width: 100%;
  height: 100%;
}

.business-item__body {
  padding: var(--spacing-xl);
  flex: 1;
  display: flex;
  flex-direction: column;
}

.business-item__title {
  font-size: var(--font-size-xl);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-md);
}

.business-item__desc {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.8;
  margin-bottom: var(--spacing-lg);
  flex: 1;
}

@media (max-width: 768px) {
  .business-grid {
    grid-template-columns: 1fr;
  }
}
</style>

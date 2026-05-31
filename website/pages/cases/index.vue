<template>
  <div class="cases-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">工程案例</h1>
        <p class="page-banner__subtitle">匠心铸造每一项精品工程</p>
      </div>
    </div>

    <section class="section">
      <div class="container">
        <!-- 分类筛选 -->
        <div class="filter-bar">
          <button
            class="filter-bar__item"
            :class="{ 'filter-bar__item--active': !currentCategory }"
            @click="filterByCategory(null)"
          >
            全部
          </button>
          <button
            v-for="cat in categories"
            :key="cat.id"
            class="filter-bar__item"
            :class="{ 'filter-bar__item--active': currentCategory === cat.id }"
            @click="filterByCategory(cat.id)"
          >
            {{ cat.name }}
          </button>
        </div>

        <!-- 案例列表 -->
        <div class="grid grid--3 cases-grid">
          <CaseCard
            v-for="item in caseList"
            :key="item.id"
            :id="item.id"
            :title="item.title"
            :cover-image="item.coverImage"
            :summary="item.summary"
            :location="item.location"
            :project-scale="item.projectScale"
            :category="getCategoryName(item.categoryId)"
          />
        </div>

        <!-- 空状态 -->
        <div v-if="caseList.length === 0" class="empty-state">
          <p>暂无相关案例</p>
        </div>

        <!-- 分页 -->
        <div class="pagination" v-if="totalPages > 1">
          <button
            class="pagination__btn"
            :class="{ 'pagination__btn--disabled': currentPage <= 1 }"
            @click="changePage(currentPage - 1)"
            :disabled="currentPage <= 1"
          >
            &laquo;
          </button>
          <button
            v-for="page in displayPages"
            :key="page"
            class="pagination__btn"
            :class="{ 'pagination__btn--active': currentPage === page }"
            @click="changePage(page)"
          >
            {{ page }}
          </button>
          <button
            class="pagination__btn"
            :class="{ 'pagination__btn--disabled': currentPage >= totalPages }"
            @click="changePage(currentPage + 1)"
            :disabled="currentPage >= totalPages"
          >
            &raquo;
          </button>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({ title: '工程案例' })

const { getCaseList, getCaseCategories } = useApi()

const currentPage = ref(1)
const currentCategory = ref<number | null>(null)
const pageSize = 9

// 获取分类
const { data: categoriesData } = await getCaseCategories()
const categories = computed(() => {
  const list = categoriesData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, name: '水库大坝', sortOrder: 1 },
    { id: 2, name: '河道治理', sortOrder: 2 },
    { id: 3, name: '农田灌溉', sortOrder: 3 },
    { id: 4, name: '防洪排涝', sortOrder: 4 },
    { id: 5, name: '供水工程', sortOrder: 5 }
  ]
})

// 获取案例列表
const { data: casesData, refresh } = await getCaseList({
  categoryId: currentCategory.value || undefined,
  page: currentPage.value,
  size: pageSize
})

const caseList = computed(() => {
  const result = casesData.value?.data
  if (result?.records && result.records.length > 0) return result.records
  // 默认数据
  return [
    { id: 1, categoryId: 1, title: '千岛湖配水工程', coverImage: '', summary: '大型跨区域供水工程', location: '浙江省杭州市', projectScale: '日供水100万吨' },
    { id: 2, categoryId: 2, title: '永安溪综合治理工程', coverImage: '', summary: '河道生态修复与防洪能力提升', location: '浙江省台州市', projectScale: '治理河段28km' },
    { id: 3, categoryId: 1, title: '青山水库加固工程', coverImage: '', summary: '水库大坝安全加固', location: '浙江省宁波市', projectScale: '库容1.2亿m³' },
    { id: 4, categoryId: 3, title: '绿源农田灌溉项目', coverImage: '', summary: '高标准农田节水灌溉示范', location: '浙江省金华市', projectScale: '灌溉面积5.6万亩' },
    { id: 5, categoryId: 4, title: '城南防洪堤建设工程', coverImage: '', summary: '城市防洪体系建设', location: '浙江省温州市', projectScale: '防洪标准50年一遇' },
    { id: 6, categoryId: 5, title: '清泉供水管网工程', coverImage: '', summary: '城乡供水一体化项目', location: '浙江省湖州市', projectScale: '覆盖人口30万' }
  ]
})

const totalPages = computed(() => {
  const result = casesData.value?.data
  return result?.pages || 1
})

const displayPages = computed(() => {
  const pages: number[] = []
  const total = totalPages.value
  const current = currentPage.value
  const start = Math.max(1, current - 2)
  const end = Math.min(total, current + 2)
  for (let i = start; i <= end; i++) {
    pages.push(i)
  }
  return pages
})

const getCategoryName = (categoryId: number) => {
  const cat = categories.value.find((c: any) => c.id === categoryId)
  return cat?.name || ''
}

const filterByCategory = (categoryId: number | null) => {
  currentCategory.value = categoryId
  currentPage.value = 1
  refresh()
}

const changePage = (page: number) => {
  if (page < 1 || page > totalPages.value) return
  currentPage.value = page
  refresh()
  window.scrollTo({ top: 300, behavior: 'smooth' })
}
</script>

<style scoped>
.filter-bar {
  display: flex;
  flex-wrap: wrap;
  gap: var(--spacing-sm);
  margin-bottom: var(--spacing-2xl);
  padding-bottom: var(--spacing-lg);
  border-bottom: 1px solid var(--color-border-light);
}

.filter-bar__item {
  padding: 0.5rem 1.25rem;
  border-radius: 20px;
  font-size: var(--font-size-sm);
  font-weight: 500;
  background: var(--color-bg-section);
  color: var(--color-text);
  transition: all var(--transition-fast);
}

.filter-bar__item:hover {
  background: var(--color-primary-light);
  color: var(--color-white);
}

.filter-bar__item--active {
  background: var(--color-primary);
  color: var(--color-white);
}

.cases-grid {
  gap: var(--spacing-xl);
}

.empty-state {
  text-align: center;
  padding: var(--spacing-3xl);
  color: var(--color-text-light);
  font-size: var(--font-size-lg);
}
</style>

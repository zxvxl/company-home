<template>
  <div class="news-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">新闻中心</h1>
        <p class="page-banner__subtitle">了解虹远水利最新资讯与行业动态</p>
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

        <!-- 新闻列表 -->
        <div class="news-list">
          <NewsCard
            v-for="item in newsList"
            :key="item.id"
            :id="item.id"
            :title="item.title"
            :cover-image="item.coverImage"
            :summary="item.summary"
            :publish-time="item.publishTime"
            :category="getCategoryName(item.categoryId)"
          />
        </div>

        <!-- 空状态 -->
        <div v-if="newsList.length === 0" class="empty-state">
          <p>暂无相关新闻</p>
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
useHead({ title: '新闻中心' })

const { getNewsList, getNewsCategories } = useApi()

const currentPage = ref(1)
const currentCategory = ref<number | null>(null)
const pageSize = 8

// 获取分类
const { data: categoriesData } = await getNewsCategories()
const categories = computed(() => {
  const list = categoriesData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, name: '公司动态', sortOrder: 1 },
    { id: 2, name: '行业新闻', sortOrder: 2 },
    { id: 3, name: '媒体报道', sortOrder: 3 }
  ]
})

// 获取新闻列表
const { data: newsData, refresh } = await getNewsList({
  categoryId: currentCategory.value || undefined,
  page: currentPage.value,
  size: pageSize
})

const newsList = computed(() => {
  const result = newsData.value?.data
  if (result?.records && result.records.length > 0) return result.records
  return [
    { id: 1, categoryId: 1, title: '虹远水利中标省重点水利工程项目', coverImage: '', summary: '近日，公司成功中标浙江省重点水利工程——某流域综合治理项目，合同金额约3.8亿元，标志着公司在大型水利工程领域的竞争力进一步提升。', publishTime: '2024-03-15' },
    { id: 2, categoryId: 1, title: '公司荣获年度优质工程奖', coverImage: '', summary: '在全省水利工程质量评比中，我司承建的青山水库加固工程荣获"优质工程"称号，充分体现了公司在工程质量管控方面的卓越能力。', publishTime: '2024-03-10' },
    { id: 3, categoryId: 2, title: '国务院印发水利基础设施建设新规划', coverImage: '', summary: '国务院近日印发《全国水利基础设施建设"十四五"规划》，明确提出加大水利基础设施投入，这为行业发展带来新机遇。', publishTime: '2024-03-08' },
    { id: 4, categoryId: 1, title: '虹远水利技术创新成果发布', coverImage: '', summary: '公司研发的智慧水利监测系统通过省级技术鉴定，达到国内领先水平，将广泛应用于水库大坝安全监测领域。', publishTime: '2024-03-05' },
    { id: 5, categoryId: 3, title: '省电视台专题报道虹远水利生态治理成果', coverImage: '', summary: '浙江卫视《美丽浙江》栏目深入报道了我公司承建的永安溪生态治理工程，展示了工程建设带来的生态环境改善。', publishTime: '2024-02-28' },
    { id: 6, categoryId: 2, title: '2024年全国水利工作会议召开', coverImage: '', summary: '2024年全国水利工作会议在北京召开，会议总结了2023年水利工作，部署了2024年重点任务，强调加快构建现代水利基础设施网络。', publishTime: '2024-02-20' }
  ]
})

const totalPages = computed(() => {
  const result = newsData.value?.data
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

.news-list {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-xl);
}

.empty-state {
  text-align: center;
  padding: var(--spacing-3xl);
  color: var(--color-text-light);
  font-size: var(--font-size-lg);
}
</style>

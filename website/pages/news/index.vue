<template>
  <div class="news-page">
    <section class="page-banner">
      <div class="container">
        <h1>新闻中心</h1>
        <p>关注虹远动态，了解行业资讯</p>
      </div>
    </section>

    <section class="section">
      <div class="container">
        <!-- 分类标签 -->
        <div class="filter-bar">
          <button
            v-for="cat in categories"
            :key="cat.value"
            class="filter-btn"
            :class="{ active: activeCategory === cat.value }"
            @click="activeCategory = cat.value"
          >
            {{ cat.label }}
          </button>
        </div>

        <!-- 新闻列表 -->
        <div class="news-list">
          <article v-for="item in filteredNews" :key="item.title" class="news-item">
            <div class="news-date-block">
              <span class="news-day">{{ item.day }}</span>
              <span class="news-ym">{{ item.yearMonth }}</span>
            </div>
            <div class="news-main">
              <div class="news-meta">
                <span class="news-category-tag">{{ item.category }}</span>
              </div>
              <h2>{{ item.title }}</h2>
              <p>{{ item.summary }}</p>
            </div>
          </article>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '新闻中心 - 虹远水利工程有限公司'
})

const activeCategory = ref('all')

const categories = [
  { label: '全部', value: 'all' },
  { label: '公司动态', value: '公司动态' },
  { label: '工程动态', value: '工程动态' },
  { label: '行业新闻', value: '行业新闻' },
  { label: '党建工作', value: '党建工作' }
]

const allNews = [
  {
    day: '28',
    yearMonth: '2024-03',
    category: '公司动态',
    title: '虹远水利中标龙泉市引水工程PPP项目',
    summary: '项目总投资3.8亿元，建设内容包括新建水厂1座、输水管道86km，建成后将为龙泉市30万居民提供安全优质的饮用水源。这是公司在PPP模式下承接的最大供水项目。'
  },
  {
    day: '20',
    yearMonth: '2024-03',
    category: '工程动态',
    title: '龙湾水库除险加固工程主体完工',
    summary: '经过18个月的紧张施工，龙湾水库除险加固工程主体部分顺利完工。大坝加高3.5m、新建溢洪道1座，水库防洪标准提升至100年一遇。'
  },
  {
    day: '15',
    yearMonth: '2024-03',
    category: '行业新闻',
    title: '2024年全国水利工作会议在京召开',
    summary: '会议指出，2024年要加快推进水利基础设施建设，全年水利建设投资目标1.2万亿元。重点推进防洪薄弱环节建设、国家水网骨干工程建设。'
  },
  {
    day: '08',
    yearMonth: '2024-03',
    category: '党建工作',
    title: '公司党委组织"学习强国"学习交流活动',
    summary: '公司党委组织全体党员深入学习习近平总书记关于治水重要论述，推动党建工作与生产经营深度融合。'
  },
  {
    day: '02',
    yearMonth: '2024-03',
    category: '工程动态',
    title: '青田县防洪排涝工程顺利通过竣工验收',
    summary: '该工程新建防洪堤12.6km、排涝泵站3座，有效提升了青田城区防洪标准至50年一遇，保护人口15万人，保护耕地3.2万亩。'
  },
  {
    day: '25',
    yearMonth: '2024-02',
    category: '公司动态',
    title: '虹远水利荣获2023年度浙江省优质工程奖',
    summary: '公司承建的"仙居县永安溪生态治理工程"荣获2023年度浙江省水利优质工程奖"大禹杯"，这是公司连续第五年获此殊荣。'
  },
  {
    day: '18',
    yearMonth: '2024-02',
    category: '工程动态',
    title: '松阳灌区续建配套工程开工建设',
    summary: '项目总投资6200万元，将改造干渠28km、支渠45km，新增灌溉面积0.8万亩，改善灌溉面积2.3万亩，惠及农户3800余户。'
  },
  {
    day: '05',
    yearMonth: '2024-02',
    category: '公司动态',
    title: '公司召开2024年度工作会议暨表彰大会',
    summary: '会议总结了2023年经营成果（新签合同额28.6亿元），部署了2024年重点工作目标，并对年度优秀项目和先进个人进行了表彰。'
  },
  {
    day: '20',
    yearMonth: '2024-01',
    category: '行业新闻',
    title: '水利部印发2024年水利工程建设质量管理指导意见',
    summary: '意见强调要全面提升水利工程建设质量管理水平，推进智慧工地建设，加强工程实体质量和施工安全管控。'
  },
  {
    day: '10',
    yearMonth: '2024-01',
    category: '党建工作',
    title: '公司荣获省级"党建工作先进单位"称号',
    summary: '凭借在基层党建、廉政建设、社会责任等方面的突出表现，公司被授予浙江省建筑行业"党建工作先进单位"荣誉称号。'
  }
]

const filteredNews = computed(() => {
  if (activeCategory.value === 'all') return allNews
  return allNews.filter(n => n.category === activeCategory.value)
})
</script>

<style scoped>
.page-banner {
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-dark));
  padding: calc(var(--header-height) + var(--space-4xl)) 0 var(--space-4xl);
  color: var(--color-white);
  text-align: center;
}

.page-banner h1 {
  font-size: var(--font-size-4xl);
  font-weight: 800;
  margin-bottom: var(--space-sm);
}

.page-banner p {
  font-size: var(--font-size-lg);
  opacity: 0.8;
}

.filter-bar {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-sm);
  margin-bottom: var(--space-2xl);
  justify-content: center;
}

.filter-btn {
  padding: var(--space-sm) var(--space-lg);
  border-radius: var(--radius-full);
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  background: var(--color-gray-100);
  border: 1px solid var(--color-gray-200);
  transition: all var(--transition-fast);
}

.filter-btn:hover {
  border-color: var(--color-primary-light);
  color: var(--color-primary);
}

.filter-btn.active {
  background: var(--color-primary);
  color: var(--color-white);
  border-color: var(--color-primary);
}

.news-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
}

.news-item {
  display: flex;
  gap: var(--space-xl);
  padding: var(--space-xl);
  background: var(--color-white);
  border-radius: var(--radius-lg);
  box-shadow: var(--shadow-sm);
  border: 1px solid var(--color-gray-200);
  transition: all var(--transition-base);
}

.news-item:hover {
  box-shadow: var(--shadow-md);
  border-color: var(--color-primary-light);
}

.news-date-block {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: 80px;
  padding: var(--space-md);
  background: var(--color-primary);
  border-radius: var(--radius-md);
  color: var(--color-white);
  flex-shrink: 0;
}

.news-day {
  font-size: var(--font-size-3xl);
  font-weight: 800;
  line-height: 1;
}

.news-ym {
  font-size: var(--font-size-xs);
  opacity: 0.8;
  margin-top: var(--space-xs);
}

.news-meta {
  margin-bottom: var(--space-sm);
}

.news-category-tag {
  display: inline-block;
  background: rgba(26, 82, 118, 0.08);
  color: var(--color-primary);
  padding: 2px var(--space-sm);
  border-radius: var(--radius-sm);
  font-size: var(--font-size-xs);
  font-weight: 500;
}

.news-main h2 {
  font-size: var(--font-size-lg);
  color: var(--text-primary);
  margin-bottom: var(--space-sm);
  line-height: 1.4;
}

.news-main p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.8;
}

@media (max-width: 768px) {
  .news-item {
    flex-direction: column;
    gap: var(--space-md);
  }

  .news-date-block {
    flex-direction: row;
    gap: var(--space-sm);
    min-width: auto;
    align-self: flex-start;
  }

  .news-day {
    font-size: var(--font-size-xl);
  }
}
</style>

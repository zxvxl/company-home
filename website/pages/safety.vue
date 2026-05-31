<template>
  <div class="safety-page">
    <!-- Banner -->
    <section class="page-banner">
      <div class="banner-overlay"></div>
      <div class="container banner-content">
        <h1>安全生产</h1>
        <p>安全第一 · 预防为主 · 综合治理</p>
      </div>
    </section>

    <!-- 安全理念 -->
    <section class="section safety-philosophy">
      <div class="container">
        <div class="philosophy-grid">
          <div class="philosophy-card">
            <div class="philosophy-icon">🛡️</div>
            <h3>安全方针</h3>
            <p class="philosophy-text">{{ safetyPolicy.principle }}</p>
          </div>
          <div class="philosophy-card">
            <div class="philosophy-icon">🎯</div>
            <h3>安全目标</h3>
            <p class="philosophy-text">{{ safetyPolicy.goal }}</p>
          </div>
          <div class="philosophy-card">
            <div class="philosophy-icon">📋</div>
            <h3>安全承诺</h3>
            <p class="philosophy-text">{{ safetyPolicy.commitment }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 安全数据 -->
    <section class="section safety-stats-section">
      <div class="container">
        <h2 class="section-title">安全数据</h2>
        <p class="section-subtitle">用数据说话，以实力证明</p>
        <div class="stats-grid">
          <div v-for="stat in safetyStats" :key="stat.label" class="stat-card">
            <div class="stat-number">
              {{ stat.value }}<span class="stat-suffix">{{ stat.suffix }}</span>
            </div>
            <div class="stat-label">{{ stat.label }}</div>
            <div class="stat-desc">{{ stat.desc }}</div>
          </div>
        </div>
      </div>
    </section>

    <!-- 安全管理体系 -->
    <section class="section safety-system">
      <div class="container">
        <h2 class="section-title">安全管理体系</h2>
        <p class="section-subtitle">科学管理，系统保障</p>
        <div class="system-grid">
          <div v-for="item in managementSystem" :key="item.title" class="system-card">
            <div class="system-icon">{{ item.icon }}</div>
            <h3>{{ item.title }}</h3>
            <p>{{ item.desc }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 安全动态 -->
    <section class="section safety-news-section">
      <div class="container">
        <h2 class="section-title">安全动态</h2>
        <p class="section-subtitle">了解最新安全生产资讯</p>
        <div class="news-list">
          <div v-for="item in safetyNewsList" :key="item.title" class="news-item">
            <div class="news-date">
              <span class="news-day">{{ item.day }}</span>
              <span class="news-month">{{ item.month }}</span>
            </div>
            <div class="news-content">
              <h3>{{ item.title }}</h3>
              <p>{{ item.summary }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '安全生产 - 虹远水利工程有限公司'
})

const { getSafetyNews } = useApi()

// 安全方针
const safetyPolicy = {
  principle: '安全第一、预防为主、综合治理',
  goal: '零事故、零伤害、零污染',
  commitment: '为每一位员工创造安全健康的工作环境，确保每一项工程安全优质交付'
}

// 安全数据统计
const safetyStats = [
  {
    value: '2800',
    suffix: '+天',
    label: '连续安全生产',
    desc: '自2016年以来无重大安全事故'
  },
  {
    value: '200',
    suffix: '+场/年',
    label: '安全培训',
    desc: '年度安全教育培训覆盖全员'
  },
  {
    value: '100',
    suffix: '%',
    label: '全员持证上岗',
    desc: '特种作业人员持证率100%'
  },
  {
    value: '100',
    suffix: '%',
    label: '安全培训覆盖率',
    desc: '新进员工三级安全教育覆盖率'
  }
]

// 安全管理体系
const managementSystem = [
  {
    icon: '📚',
    title: '制度保障',
    desc: '建立健全安全生产责任制、安全操作规程等管理制度300余项'
  },
  {
    icon: '👨‍🏫',
    title: '教育培训',
    desc: '常态化安全教育培训，专项安全技术交底，应急演练定期开展'
  },
  {
    icon: '🔍',
    title: '隐患排查',
    desc: '推行安全隐患排查治理闭环管理，做到早发现、早整改、早消除'
  },
  {
    icon: '📡',
    title: '智慧监管',
    desc: '运用物联网、AI技术实现施工现场安全实时监控与预警'
  },
  {
    icon: '🚨',
    title: '应急管理',
    desc: '制定完善的应急预案，定期开展防洪、防坍塌等专项应急演练'
  },
  {
    icon: '✅',
    title: '标准化建设',
    desc: '按水利安全生产标准化一级企业标准进行管理，持续改进提升'
  }
]

// Fallback 安全动态数据
const fallbackNews = [
  {
    day: '20',
    month: '2024-03',
    title: '公司开展2024年"安全生产月"系列活动',
    summary: '以"人人讲安全、个个会应急"为主题，组织安全知识竞赛、应急演练等活动。'
  },
  {
    day: '15',
    month: '2024-03',
    title: '清风水库项目部获评"省级安全文明标准化工地"',
    summary: '通过省水利厅安全生产标准化考核，获得安全文明标准化工地称号。'
  },
  {
    day: '08',
    month: '2024-02',
    title: '公司组织冬季施工安全专项检查',
    summary: '重点检查各项目部防寒保暖、用电安全、消防安全等措施落实情况。'
  },
  {
    day: '25',
    month: '2024-01',
    title: '2024年度安全生产工作会议顺利召开',
    summary: '总结2023年安全生产工作，部署2024年度安全生产重点任务和目标。'
  },
  {
    day: '10',
    month: '2024-01',
    title: '公司举办特种作业人员安全培训班',
    summary: '组织50余名特种作业人员参加安全技能提升培训，巩固安全操作规范。'
  }
]

// 尝试从 CMS 获取安全动态
const safetyNewsList = ref(fallbackNews)

onMounted(async () => {
  try {
    const result = await getSafetyNews(1, 5)
    if (result && result.records && result.records.length > 0) {
      safetyNewsList.value = result.records.map(item => ({
        day: item.publishDate?.substring(8, 10) || '01',
        month: item.publishDate?.substring(0, 7) || '2024-01',
        title: item.title,
        summary: item.summary || ''
      }))
    }
  } catch {
    // 使用 fallback 数据
  }
})
</script>

<style scoped>
.page-banner {
  position: relative;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #1a5276, #0e3a53);
  color: var(--color-white);
  text-align: center;
}

.banner-overlay {
  position: absolute;
  inset: 0;
  background: radial-gradient(ellipse at center, rgba(41, 128, 185, 0.2), transparent 70%);
}

.banner-content {
  position: relative;
}

.banner-content h1 {
  font-size: clamp(2rem, 4vw, 3rem);
  font-weight: 800;
  margin-bottom: var(--space-sm);
}

.banner-content p {
  font-size: var(--font-size-lg);
  opacity: 0.8;
}

/* 安全理念 */
.safety-philosophy {
  background: var(--color-gray-50);
}

.philosophy-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--space-xl);
}

.philosophy-card {
  background: var(--color-white);
  border-radius: var(--radius-xl);
  padding: var(--space-2xl);
  text-align: center;
  border: 1px solid var(--color-gray-200);
  transition: all var(--transition-base);
}

.philosophy-card:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-md);
  transform: translateY(-4px);
}

.philosophy-icon {
  font-size: 3rem;
  margin-bottom: var(--space-md);
}

.philosophy-card h3 {
  font-size: var(--font-size-xl);
  color: var(--color-primary);
  margin-bottom: var(--space-md);
}

.philosophy-text {
  font-size: var(--font-size-lg);
  color: var(--text-secondary);
  line-height: 1.8;
  font-weight: 500;
}

/* 安全数据 */
.safety-stats-section {
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-dark));
  color: var(--color-white);
}

.safety-stats-section .section-title,
.safety-stats-section .section-subtitle {
  color: var(--color-white);
}

.safety-stats-section .section-subtitle {
  opacity: 0.8;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: var(--space-xl);
}

.stat-card {
  text-align: center;
  padding: var(--space-xl);
  background: rgba(255, 255, 255, 0.1);
  border-radius: var(--radius-lg);
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.stat-number {
  font-size: var(--font-size-4xl);
  font-weight: 800;
  line-height: 1.2;
  color: var(--color-accent);
}

.stat-suffix {
  font-size: var(--font-size-lg);
}

.stat-label {
  font-size: var(--font-size-base);
  font-weight: 600;
  margin-top: var(--space-sm);
  margin-bottom: var(--space-xs);
}

.stat-desc {
  font-size: var(--font-size-sm);
  opacity: 0.7;
}

/* 安全管理体系 */
.safety-system {
  background: var(--color-gray-50);
}

.system-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--space-xl);
}

.system-card {
  background: var(--color-white);
  border-radius: var(--radius-lg);
  padding: var(--space-2xl);
  border: 1px solid var(--color-gray-200);
  transition: all var(--transition-base);
}

.system-card:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-md);
}

.system-icon {
  font-size: 2.5rem;
  margin-bottom: var(--space-md);
}

.system-card h3 {
  font-size: var(--font-size-lg);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.system-card p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
}

/* 安全动态 */
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
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-lg);
  transition: all var(--transition-base);
}

.news-item:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-sm);
}

.news-date {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: 70px;
  padding: var(--space-md);
  background: var(--color-primary);
  border-radius: var(--radius-md);
  color: var(--color-white);
  flex-shrink: 0;
}

.news-day {
  font-size: var(--font-size-2xl);
  font-weight: 800;
  line-height: 1;
}

.news-month {
  font-size: var(--font-size-xs);
  opacity: 0.8;
  margin-top: var(--space-xs);
}

.news-content h3 {
  font-size: var(--font-size-lg);
  color: var(--text-primary);
  margin-bottom: var(--space-sm);
}

.news-content p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
}

@media (max-width: 1024px) {
  .philosophy-grid {
    grid-template-columns: 1fr;
  }

  .stats-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .system-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .page-banner {
    height: 220px;
  }

  .stats-grid {
    grid-template-columns: 1fr;
  }

  .system-grid {
    grid-template-columns: 1fr;
  }

  .news-item {
    flex-direction: column;
  }
}
</style>

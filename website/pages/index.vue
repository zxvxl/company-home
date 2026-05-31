<template>
  <div class="home">
    <!-- Hero Banner -->
    <section class="hero">
      <div class="hero-overlay"></div>
      <div class="container hero-content">
        <h1 class="hero-title">虹远水利工程有限公司</h1>
        <p class="hero-subtitle">专注水利工程建设 · 服务国家水利事业</p>
        <p class="hero-desc">
          水利工程施工总承包壹级资质 | 30+年行业经验 | 500+精品工程
        </p>
        <div class="hero-actions">
          <NuxtLink to="/business" class="btn btn-accent btn-lg">了解业务</NuxtLink>
          <NuxtLink to="/contact" class="btn btn-outline btn-lg">联系我们</NuxtLink>
        </div>
      </div>
    </section>

    <!-- 公司简介 -->
    <section class="section about-section">
      <div class="container">
        <h2 class="section-title">关于虹远水利</h2>
        <p class="section-subtitle">以水为本，润泽万方</p>
        <div class="about-grid">
          <div class="about-text">
            <p>
              虹远水利工程有限公司成立于1992年，是一家集水利工程设计、施工、咨询于一体的综合性水利企业。
              公司持有水利水电工程施工总承包壹级资质、市政公用工程总承包贰级资质，以及多项专业承包资质。
            </p>
            <p>
              三十余年来，公司秉承"以水为本、质量至上"的经营理念，先后承建了数百项水利工程，
              涵盖水库大坝、河道治理、农田灌溉、防洪排涝、水土保持、城乡供水等领域，
              工程质量优良率达98%以上，多次荣获省部级优质工程奖。
            </p>
            <NuxtLink to="/about" class="btn btn-primary">了解更多</NuxtLink>
          </div>
          <div class="about-stats">
            <div v-for="stat in companyStats" :key="stat.label" class="stat-item">
              <span class="stat-number">{{ stat.number }}</span>
              <span class="stat-unit">{{ stat.unit }}</span>
              <span class="stat-label">{{ stat.label }}</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 业务领域 -->
    <section class="section business-section">
      <div class="container">
        <h2 class="section-title">业务领域</h2>
        <p class="section-subtitle">六大核心业务，全方位水利解决方案</p>
        <div class="grid grid-3 business-grid">
          <div v-for="item in businessAreas" :key="item.title" class="business-card card">
            <div class="business-icon">{{ item.icon }}</div>
            <h3>{{ item.title }}</h3>
            <p>{{ item.desc }}</p>
          </div>
        </div>
        <div class="section-action">
          <NuxtLink to="/business" class="btn btn-primary">查看全部业务</NuxtLink>
        </div>
      </div>
    </section>

    <!-- 数字成就 -->
    <section class="section achievements-section">
      <div class="container">
        <div class="achievements-grid">
          <div v-for="item in achievements" :key="item.label" class="achievement-item">
            <div class="achievement-number">{{ item.number }}<span class="achievement-suffix">{{ item.suffix }}</span></div>
            <div class="achievement-label">{{ item.label }}</div>
          </div>
        </div>
      </div>
    </section>

    <!-- 工程案例 -->
    <section class="section cases-section">
      <div class="container">
        <h2 class="section-title">精品工程</h2>
        <p class="section-subtitle">三十年精工细作，铸就水利丰碑</p>
        <div class="grid grid-3">
          <div v-for="item in featuredCases" :key="item.title" class="case-card card">
            <div class="case-img" :style="{ background: item.color }">
              <span class="case-tag">{{ item.tag }}</span>
            </div>
            <div class="case-info">
              <h3>{{ item.title }}</h3>
              <p>{{ item.desc }}</p>
              <div class="case-meta">
                <span>{{ item.location }}</span>
                <span>{{ item.year }}</span>
              </div>
            </div>
          </div>
        </div>
        <div class="section-action">
          <NuxtLink to="/cases" class="btn btn-primary">查看更多案例</NuxtLink>
        </div>
      </div>
    </section>

    <!-- 项目地图分布 -->
    <ProjectMap />

    <!-- 新闻动态 -->
    <section class="section news-section">
      <div class="container">
        <h2 class="section-title">新闻动态</h2>
        <p class="section-subtitle">了解虹远最新资讯</p>
        <div class="grid grid-3">
          <div v-for="item in latestNews" :key="item.title" class="news-card card">
            <div class="news-date">
              <span class="news-day">{{ item.day }}</span>
              <span class="news-month">{{ item.month }}</span>
            </div>
            <div class="news-content">
              <span class="news-tag">{{ item.tag }}</span>
              <h3>{{ item.title }}</h3>
              <p>{{ item.summary }}</p>
            </div>
          </div>
        </div>
        <div class="section-action">
          <NuxtLink to="/news" class="btn btn-primary">更多新闻</NuxtLink>
        </div>
      </div>
    </section>

    <!-- 合作伙伴 -->
    <section class="section partners-section">
      <div class="container">
        <h2 class="section-title">合作伙伴</h2>
        <p class="section-subtitle">与行业领先企业携手共进</p>
        <div class="partners-grid">
          <div v-for="partner in partners" :key="partner" class="partner-item">
            {{ partner }}
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '虹远水利工程有限公司 - 专注水利工程建设'
})

// 公司数据统计
const companyStats = [
  { number: '30', unit: '年+', label: '行业经验' },
  { number: '500', unit: '+', label: '完成工程' },
  { number: '98', unit: '%', label: '优良率' },
  { number: '1200', unit: '+', label: '员工团队' }
]

// 业务领域
const businessAreas = [
  {
    icon: '🏗️',
    title: '水库大坝工程',
    desc: '承建各类大中小型水库、拦河坝、蓄水枢纽工程，涵盖土石坝、混凝土坝、拱坝等多种坝型。'
  },
  {
    icon: '🌊',
    title: '河道治理工程',
    desc: '河道整治、堤防加固、清淤疏浚、生态护岸，打造安全生态水系。'
  },
  {
    icon: '🌾',
    title: '农田灌溉工程',
    desc: '灌区建设与改造、节水灌溉、渠系配套、泵站工程，保障农业用水安全。'
  },
  {
    icon: '🛡️',
    title: '防洪排涝工程',
    desc: '防洪堤建设、排涝泵站、涝区治理、城市防洪体系建设。'
  },
  {
    icon: '🌱',
    title: '水土保持工程',
    desc: '坡面治理、沟道治理、小流域综合治理、矿山水土保持。'
  },
  {
    icon: '💧',
    title: '供水工程',
    desc: '城乡供水一体化、饮水安全工程、输水管道、水厂建设。'
  }
]

// 数字成就
const achievements = [
  { number: '500', suffix: '+', label: '已完成工程项目' },
  { number: '120', suffix: '亿', label: '累计合同金额' },
  { number: '30', suffix: '个', label: '覆盖省市地区' },
  { number: '56', suffix: '项', label: '省部级荣誉奖项' }
]

// 精选案例
const featuredCases = [
  {
    title: '龙湾水库除险加固工程',
    desc: '总库容2860万m³，大坝加高加固及溢洪道改造',
    tag: '水库大坝',
    location: '浙江·温州',
    year: '2023',
    color: 'linear-gradient(135deg, #1a5276, #2980b9)'
  },
  {
    title: '瓯江河道综合治理工程',
    desc: '治理河道长度28.5km，新建生态护岸及景观工程',
    tag: '河道治理',
    location: '浙江·丽水',
    year: '2022',
    color: 'linear-gradient(135deg, #2980b9, #3498db)'
  },
  {
    title: '万亩高标准农田灌溉工程',
    desc: '灌溉面积1.2万亩，建设高效节水灌溉系统',
    tag: '农田灌溉',
    location: '浙江·金华',
    year: '2023',
    color: 'linear-gradient(135deg, #27ae60, #2ecc71)'
  }
]

// 最新新闻
const latestNews = [
  {
    day: '28',
    month: '2024-03',
    tag: '公司动态',
    title: '虹远水利中标龙泉市引水工程PPP项目',
    summary: '项目总投资3.8亿元，将为龙泉市30万居民提供安全优质的饮用水源。'
  },
  {
    day: '15',
    month: '2024-03',
    tag: '行业新闻',
    title: '2024年全国水利工作会议召开',
    summary: '会议强调加快水利基础设施建设，推进智慧水利发展。'
  },
  {
    day: '02',
    month: '2024-03',
    tag: '工程动态',
    title: '青田县防洪排涝工程顺利通过验收',
    summary: '该工程有效提升了青田城区防洪标准至50年一遇。'
  }
]

// 合作伙伴
const partners = [
  '中国水利水电科学研究院',
  '浙江省水利设计院',
  '河海大学',
  '中国电建',
  '浙江省水利厅',
  '大禹节水集团'
]
</script>

<style scoped>
/* Hero */
.hero {
  position: relative;
  height: 100vh;
  min-height: 600px;
  display: flex;
  align-items: center;
  background:
    linear-gradient(135deg, rgba(26, 82, 118, 0.92), rgba(14, 58, 83, 0.88)),
    url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 600"><rect fill="%231a5276" width="1200" height="600"/></svg>');
  background-size: cover;
  background-position: center;
}

.hero-overlay {
  position: absolute;
  inset: 0;
  background: radial-gradient(ellipse at 30% 50%, rgba(41, 128, 185, 0.3), transparent 70%);
}

.hero-content {
  position: relative;
  text-align: center;
  color: var(--color-white);
}

.hero-title {
  font-size: clamp(2.5rem, 5vw, 4rem);
  font-weight: 800;
  margin-bottom: var(--space-md);
  letter-spacing: 0.05em;
}

.hero-subtitle {
  font-size: clamp(1.2rem, 2.5vw, 1.8rem);
  font-weight: 300;
  margin-bottom: var(--space-md);
  opacity: 0.9;
}

.hero-desc {
  font-size: var(--font-size-lg);
  opacity: 0.75;
  margin-bottom: var(--space-2xl);
}

.hero-actions {
  display: flex;
  gap: var(--space-lg);
  justify-content: center;
  flex-wrap: wrap;
}

/* About Section */
.about-grid {
  display: grid;
  grid-template-columns: 1.5fr 1fr;
  gap: var(--space-3xl);
  align-items: center;
}

.about-text p {
  font-size: var(--font-size-lg);
  line-height: 1.9;
  color: var(--text-secondary);
  margin-bottom: var(--space-lg);
}

.about-stats {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: var(--space-xl);
}

.stat-item {
  background: var(--color-gray-50);
  border-radius: var(--radius-lg);
  padding: var(--space-xl);
  text-align: center;
  border: 1px solid var(--color-gray-200);
}

.stat-number {
  font-size: var(--font-size-4xl);
  font-weight: 800;
  color: var(--color-primary);
  line-height: 1;
}

.stat-unit {
  font-size: var(--font-size-lg);
  color: var(--color-accent);
  font-weight: 600;
}

.stat-label {
  display: block;
  margin-top: var(--space-xs);
  font-size: var(--font-size-sm);
  color: var(--text-muted);
}

/* Business Section */
.business-section {
  background: var(--color-gray-50);
}

.business-card {
  padding: var(--space-2xl);
  text-align: center;
}

.business-icon {
  font-size: 3rem;
  margin-bottom: var(--space-md);
}

.business-card h3 {
  font-size: var(--font-size-xl);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.business-card p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
}

.section-action {
  text-align: center;
  margin-top: var(--space-3xl);
}

/* Achievements */
.achievements-section {
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-dark));
  color: var(--color-white);
}

.achievements-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: var(--space-xl);
  text-align: center;
}

.achievement-number {
  font-size: var(--font-size-5xl);
  font-weight: 800;
  line-height: 1.2;
}

.achievement-suffix {
  font-size: var(--font-size-2xl);
  opacity: 0.8;
}

.achievement-label {
  font-size: var(--font-size-base);
  opacity: 0.8;
  margin-top: var(--space-sm);
}

/* Cases */
.case-card {
  overflow: hidden;
}

.case-img {
  height: 200px;
  display: flex;
  align-items: flex-end;
  padding: var(--space-md);
  position: relative;
}

.case-tag {
  background: var(--color-accent);
  color: var(--color-white);
  padding: var(--space-xs) var(--space-sm);
  border-radius: var(--radius-sm);
  font-size: var(--font-size-xs);
  font-weight: 600;
}

.case-info {
  padding: var(--space-lg);
}

.case-info h3 {
  font-size: var(--font-size-lg);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.case-info p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  margin-bottom: var(--space-md);
  line-height: 1.6;
}

.case-meta {
  display: flex;
  justify-content: space-between;
  font-size: var(--font-size-xs);
  color: var(--text-muted);
}

/* News */
.news-section {
  background: var(--color-gray-50);
}

.news-card {
  display: flex;
  gap: var(--space-lg);
  padding: var(--space-lg);
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

.news-tag {
  display: inline-block;
  background: rgba(26, 82, 118, 0.1);
  color: var(--color-primary);
  padding: 2px var(--space-sm);
  border-radius: var(--radius-sm);
  font-size: var(--font-size-xs);
  margin-bottom: var(--space-sm);
}

.news-content h3 {
  font-size: var(--font-size-base);
  color: var(--text-primary);
  margin-bottom: var(--space-sm);
  line-height: 1.4;
}

.news-content p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.6;
}

/* Partners */
.partners-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--space-lg);
}

.partner-item {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-xl) var(--space-lg);
  background: var(--color-gray-50);
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-md);
  font-size: var(--font-size-base);
  font-weight: 500;
  color: var(--text-secondary);
  transition: all var(--transition-base);
}

.partner-item:hover {
  border-color: var(--color-primary-light);
  color: var(--color-primary);
  box-shadow: var(--shadow-md);
}

@media (max-width: 1024px) {
  .about-grid {
    grid-template-columns: 1fr;
  }

  .achievements-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .partners-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .hero {
    min-height: 500px;
  }

  .about-stats {
    grid-template-columns: repeat(2, 1fr);
  }

  .achievements-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: var(--space-lg);
  }

  .news-card {
    flex-direction: column;
  }

  .partners-grid {
    grid-template-columns: 1fr;
  }
}
</style>

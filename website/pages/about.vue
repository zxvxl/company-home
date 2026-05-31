<template>
  <div class="about-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">关于我们</h1>
        <p class="page-banner__subtitle">了解虹远水利的发展历程与企业文化</p>
      </div>
    </div>

    <!-- 公司简介 -->
    <section class="section about-intro">
      <div class="container">
        <SectionTitle title="公司简介" subtitle="专注水利工程二十载" tag="ABOUT" />
        <div class="about-intro__grid">
          <div class="about-intro__content">
            <p>
              虹远水利工程有限公司成立于2003年，注册资本5000万元，总部位于浙江省杭州市。公司是一家集水利工程勘察设计、施工建设、运营管理于一体的综合性水利企业，具备水利水电工程施工总承包壹级、水利行业（水利工程）专业甲级设计资质。
            </p>
            <p>
              公司现有员工300余人，其中高级工程师50余人、中级工程师100余人，拥有一级建造师40余人、注册水利工程师20余人。技术力量雄厚，管理体系完善，先后通过ISO9001质量管理体系认证、ISO14001环境管理体系认证和OHSAS18001职业健康安全管理体系认证。
            </p>
            <p>
              二十余年来，公司累计完成各类水利工程项目500余项，工程合格率100%，优良率90%以上。业务覆盖水库大坝、河道治理、农田灌溉、防洪排涝、水土保持、城乡供水等多个领域，服务区域遍及浙江、江苏、安徽、江西、福建等20多个省市。
            </p>
          </div>
          <div class="about-intro__image">
            <div class="about-intro__placeholder">
              <svg viewBox="0 0 500 350" fill="none" xmlns="http://www.w3.org/2000/svg">
                <rect width="500" height="350" fill="#e8f4f8" rx="12"/>
                <rect x="50" y="200" width="60" height="100" fill="rgba(26,82,118,0.2)" rx="4"/>
                <rect x="130" y="160" width="60" height="140" fill="rgba(26,82,118,0.3)" rx="4"/>
                <rect x="210" y="120" width="60" height="180" fill="rgba(41,128,185,0.4)" rx="4"/>
                <rect x="290" y="80" width="60" height="220" fill="rgba(41,128,185,0.5)" rx="4"/>
                <rect x="370" y="50" width="60" height="250" fill="rgba(26,82,118,0.6)" rx="4"/>
                <text x="250" y="330" font-family="sans-serif" font-size="12" fill="#7f8c8d" text-anchor="middle">企业发展示意图</text>
              </svg>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 发展历程 -->
    <section class="section section--gray timeline-section">
      <div class="container">
        <SectionTitle title="发展历程" subtitle="回顾虹远水利的成长轨迹" tag="HISTORY" />
        <div class="timeline">
          <div
            v-for="(item, index) in historyData"
            :key="index"
            class="timeline__item"
            :class="{ 'timeline__item--right': index % 2 !== 0 }"
          >
            <div class="timeline__dot"></div>
            <div class="timeline__content">
              <span class="timeline__year">{{ item.year }}</span>
              <p class="timeline__text">{{ item.event }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 企业文化 -->
    <section class="section culture-section">
      <div class="container">
        <SectionTitle title="企业文化" subtitle="以水为媒，润泽万家" tag="CULTURE" />
        <div class="culture-section__grid">
          <div class="culture-card">
            <div class="culture-card__icon">
              <svg viewBox="0 0 48 48" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="24" cy="24" r="20"/>
                <path d="M24 14v10l6 4"/>
              </svg>
            </div>
            <h3 class="culture-card__title">企业使命</h3>
            <p class="culture-card__text">以专业水利技术服务社会，保障水安全、改善水环境、传承水文化，为建设美丽中国贡献力量。</p>
          </div>
          <div class="culture-card">
            <div class="culture-card__icon">
              <svg viewBox="0 0 48 48" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M4 24c0-11 8.95-20 20-20s20 9 20 20"/>
                <path d="M24 14l-4 10h8l-4 10"/>
              </svg>
            </div>
            <h3 class="culture-card__title">企业愿景</h3>
            <p class="culture-card__text">成为国内领先的水利工程综合服务商，打造百年水利企业品牌，实现经济效益与社会效益的统一。</p>
          </div>
          <div class="culture-card">
            <div class="culture-card__icon">
              <svg viewBox="0 0 48 48" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M24 4l5.5 11 12.5 2-9 8.5 2 12.5-11-5.5-11 5.5 2-12.5-9-8.5 12.5-2z"/>
              </svg>
            </div>
            <h3 class="culture-card__title">核心价值观</h3>
            <p class="culture-card__text">诚信为本、质量至上、创新驱动、合作共赢。以工匠精神打造每一项工程，以诚信品格服务每一位客户。</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 资质荣誉 -->
    <section class="section section--gray qualification-section">
      <div class="container">
        <SectionTitle title="资质荣誉" subtitle="专业资质保障工程品质" tag="QUALIFICATION" />
        <div class="qualification-grid">
          <div class="qualification-item" v-for="item in qualifications" :key="item.title">
            <div class="qualification-item__icon">
              <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M12 2l3.09 6.26L22 9.27l-5 4.87 1.18 6.88L12 17.77l-6.18 3.25L7 14.14 2 9.27l6.91-1.01L12 2z"/>
              </svg>
            </div>
            <h4 class="qualification-item__title">{{ item.title }}</h4>
            <p class="qualification-item__desc">{{ item.desc }}</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({ title: '关于我们' })

const { getCompanyInfo } = useApi()
const { data: companyData } = await getCompanyInfo()

// 发展历程数据（优先从API获取，否则用默认数据）
const historyData = computed(() => {
  const company = companyData.value?.data
  if (company?.history) {
    try {
      return JSON.parse(company.history)
    } catch (e) {}
  }
  return [
    { year: '2003', event: '虹远水利工程有限公司在杭州成立，注册资本500万元，开始承接中小型水利工程项目' },
    { year: '2006', event: '取得水利水电工程施工总承包叁级资质，完成首个千万级项目' },
    { year: '2009', event: '资质升级为贰级，员工规模突破100人，业务拓展至周边省份' },
    { year: '2012', event: '取得施工总承包壹级资质，中标首个亿元级水库加固工程' },
    { year: '2015', event: '取得水利行业甲级设计资质，形成设计施工一体化服务能力' },
    { year: '2018', event: '员工规模达300人，年营业额突破10亿元，业务覆盖全国20个省市' },
    { year: '2021', event: '投入智慧水利技术研发，成立数字化事业部，推动企业数字化转型' },
    { year: '2024', event: '累计完成工程项目超500项，荣获"全国水利行业AAA级信用企业"称号' }
  ]
})

// 资质数据
const qualifications = [
  { title: '施工总承包壹级', desc: '水利水电工程施工总承包壹级资质' },
  { title: '甲级设计资质', desc: '水利行业（水利工程）专业甲级设计资质' },
  { title: 'ISO三体系认证', desc: '质量/环境/职业健康安全管理体系认证' },
  { title: 'AAA信用企业', desc: '全国水利行业AAA级信用企业' },
  { title: '高新技术企业', desc: '国家级高新技术企业认定' },
  { title: '安全生产许可证', desc: '建筑施工安全生产许可证' }
]
</script>

<style scoped>
/* 公司简介 */
.about-intro__grid {
  display: grid;
  grid-template-columns: 1.2fr 1fr;
  gap: var(--spacing-3xl);
  align-items: center;
}

.about-intro__content p {
  font-size: var(--font-size-base);
  color: var(--color-text-light);
  line-height: 2;
  margin-bottom: var(--spacing-lg);
  text-indent: 2em;
}

.about-intro__placeholder {
  border-radius: var(--radius-lg);
  overflow: hidden;
  box-shadow: var(--shadow-md);
}

.about-intro__placeholder svg {
  width: 100%;
  height: auto;
}

/* 发展历程时间线 */
.timeline {
  position: relative;
  max-width: 800px;
  margin: 0 auto;
  padding: var(--spacing-xl) 0;
}

.timeline::before {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 2px;
  background: linear-gradient(180deg, var(--color-primary-light), var(--color-primary));
}

.timeline__item {
  position: relative;
  width: 50%;
  padding-right: var(--spacing-2xl);
  margin-bottom: var(--spacing-2xl);
}

.timeline__item--right {
  margin-left: 50%;
  padding-right: 0;
  padding-left: var(--spacing-2xl);
}

.timeline__dot {
  position: absolute;
  top: 0.5rem;
  right: -8px;
  width: 16px;
  height: 16px;
  border-radius: var(--radius-full);
  background: var(--color-primary-light);
  border: 3px solid var(--color-white);
  box-shadow: var(--shadow-sm);
}

.timeline__item--right .timeline__dot {
  right: auto;
  left: -8px;
}

.timeline__content {
  background: var(--color-white);
  padding: var(--spacing-lg);
  border-radius: var(--radius-md);
  box-shadow: var(--shadow-sm);
}

.timeline__year {
  display: inline-block;
  font-size: var(--font-size-lg);
  font-weight: 700;
  color: var(--color-primary);
  margin-bottom: var(--spacing-sm);
}

.timeline__text {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.6;
  margin: 0;
}

/* 企业文化 */
.culture-section__grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--spacing-xl);
}

.culture-card {
  text-align: center;
  padding: var(--spacing-2xl);
  background: var(--color-white);
  border-radius: var(--radius-lg);
  box-shadow: var(--shadow-sm);
  transition: all var(--transition-normal);
}

.culture-card:hover {
  transform: translateY(-6px);
  box-shadow: var(--shadow-lg);
}

.culture-card__icon {
  width: 64px;
  height: 64px;
  margin: 0 auto var(--spacing-lg);
  color: var(--color-primary-light);
}

.culture-card__icon svg {
  width: 100%;
  height: 100%;
}

.culture-card__title {
  font-size: var(--font-size-xl);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-md);
}

.culture-card__text {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.8;
  margin: 0;
}

/* 资质荣誉 */
.qualification-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--spacing-xl);
}

.qualification-item {
  text-align: center;
  padding: var(--spacing-xl);
  background: var(--color-white);
  border-radius: var(--radius-md);
  box-shadow: var(--shadow-sm);
  transition: all var(--transition-normal);
}

.qualification-item:hover {
  transform: translateY(-4px);
  box-shadow: var(--shadow-md);
}

.qualification-item__icon {
  width: 40px;
  height: 40px;
  margin: 0 auto var(--spacing-md);
  color: var(--color-accent);
}

.qualification-item__icon svg {
  width: 100%;
  height: 100%;
}

.qualification-item__title {
  font-size: var(--font-size-base);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-sm);
}

.qualification-item__desc {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  margin: 0;
}

@media (max-width: 1024px) {
  .about-intro__grid {
    grid-template-columns: 1fr;
  }

  .culture-section__grid,
  .qualification-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .culture-section__grid,
  .qualification-grid {
    grid-template-columns: 1fr;
  }

  .timeline::before {
    left: 20px;
  }

  .timeline__item {
    width: 100%;
    padding-left: var(--spacing-3xl);
    padding-right: 0;
  }

  .timeline__item--right {
    margin-left: 0;
    padding-left: var(--spacing-3xl);
  }

  .timeline__dot {
    left: 12px;
    right: auto;
  }

  .timeline__item--right .timeline__dot {
    left: 12px;
  }
}
</style>

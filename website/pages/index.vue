<template>
  <div class="home">
    <!-- Hero Banner 轮播 -->
    <HeroBanner :slides="bannerSlides" />

    <!-- 公司简介区 -->
    <section class="section about-section">
      <div class="container">
        <div class="about-section__grid">
          <div class="about-section__content">
            <SectionTitle title="关于虹远水利" subtitle="专注水利工程二十载，铸就行业标杆" tag="ABOUT US" />
            <p class="about-section__text">
              虹远水利工程有限公司成立于2003年，是一家集水利工程勘察设计、施工建设、运营管理于一体的综合性水利企业。公司拥有水利工程施工总承包壹级资质、水利行业甲级设计资质，业务覆盖水库大坝、河道治理、农田灌溉、防洪排涝、水土保持、供水工程等多个领域。
            </p>
            <p class="about-section__text">
              二十余年来，公司始终秉承"水润万物、利泽天下"的企业理念，累计完成水利工程项目超过500项，服务区域遍及全国20多个省市，以卓越的工程品质和专业的技术服务赢得了业界的广泛认可。
            </p>
            <NuxtLink to="/about" class="btn btn--primary">
              了解更多
            </NuxtLink>
          </div>
          <div class="about-section__image">
            <div class="about-section__image-wrapper">
              <div class="about-section__image-placeholder">
                <svg viewBox="0 0 400 300" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <rect width="400" height="300" fill="#e8f4f8"/>
                  <path d="M50 250 L100 180 L150 200 L200 140 L250 160 L300 100 L350 130" stroke="#2980b9" stroke-width="3" fill="none"/>
                  <path d="M50 250 L100 180 L150 200 L200 140 L250 160 L300 100 L350 130 L350 250 Z" fill="rgba(41,128,185,0.1)"/>
                  <circle cx="200" cy="100" r="30" fill="rgba(26,82,118,0.1)" stroke="#1a5276" stroke-width="2"/>
                  <text x="200" y="105" font-family="sans-serif" font-size="10" fill="#1a5276" text-anchor="middle">水利</text>
                </svg>
              </div>
              <div class="about-section__badge">
                <span class="about-section__badge-number">20+</span>
                <span class="about-section__badge-text">年行业经验</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 核心业务区 -->
    <section class="section section--gray business-section">
      <div class="container">
        <SectionTitle
          title="核心业务领域"
          subtitle="提供全方位水利工程解决方案，助力水利基础设施建设"
          tag="OUR SERVICES"
        />
        <div class="grid grid--3 business-section__grid">
          <BusinessCard
            v-for="item in businessList"
            :key="item.id"
            :id="item.id"
            :title="item.title"
            :icon="item.icon"
            :summary="item.summary"
          />
        </div>
      </div>
    </section>

    <!-- 数字成就区 -->
    <section class="section section--dark stats-section">
      <div class="container">
        <SectionTitle title="企业实力" subtitle="用数字见证我们的成长与实力" :light="true" />
        <div class="stats-section__grid">
          <StatCounter
            v-for="stat in statsData"
            :key="stat.label"
            :target-number="stat.value"
            :label="stat.label"
            :suffix="stat.suffix"
          />
        </div>
      </div>
    </section>

    <!-- 精选案例区 -->
    <section class="section cases-section">
      <div class="container">
        <SectionTitle
          title="精选工程案例"
          subtitle="匠心铸造每一项工程，以品质赢得信赖"
          tag="PROJECTS"
        />
        <div class="grid grid--4 cases-section__grid">
          <CaseCard
            v-for="item in featuredCases"
            :key="item.id"
            :id="item.id"
            :title="item.title"
            :cover-image="item.coverImage"
            :summary="item.summary"
            :location="item.location"
            :project-scale="item.projectScale"
            category="精选案例"
          />
        </div>
        <div class="cases-section__more">
          <NuxtLink to="/cases" class="btn btn--outline">查看更多案例</NuxtLink>
        </div>
      </div>
    </section>

    <!-- 最新新闻区 -->
    <section class="section section--gray news-section">
      <div class="container">
        <SectionTitle
          title="新闻动态"
          subtitle="了解虹远水利最新资讯与行业动态"
          tag="NEWS"
        />
        <div class="news-section__list">
          <NewsCard
            v-for="item in latestNews"
            :key="item.id"
            :id="item.id"
            :title="item.title"
            :cover-image="item.coverImage"
            :summary="item.summary"
            :publish-time="item.publishTime"
            category="公司动态"
          />
        </div>
        <div class="news-section__more">
          <NuxtLink to="/news" class="btn btn--outline">查看更多新闻</NuxtLink>
        </div>
      </div>
    </section>

    <!-- 合作伙伴区 -->
    <section class="section partners-section">
      <div class="container">
        <SectionTitle
          title="合作伙伴"
          subtitle="与行业领军企业携手共进"
          tag="PARTNERS"
        />
        <div class="partners-section__grid">
          <div
            v-for="partner in partnerList"
            :key="partner.id"
            class="partners-section__item"
          >
            <img v-if="partner.logoUrl" :src="partner.logoUrl" :alt="partner.name" />
            <span v-else class="partners-section__placeholder">{{ partner.name }}</span>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import type { Banner, Business, CaseItem, NewsItem, Partner } from '~/composables/useApi'

useHead({ title: '首页' })

const { getBanners, getCompanyInfo, getBusinessList, getFeaturedCases, getLatestNews, getPartnerList, getStats } = useApi()

// 获取数据
const { data: bannersData } = await getBanners()
const { data: businessData } = await getBusinessList()
const { data: casesData } = await getFeaturedCases()
const { data: newsData } = await getLatestNews(4)
const { data: partnersData } = await getPartnerList()
const { data: statsConfigData } = await getStats()

// 轮播图数据（带回退）
const bannerSlides = computed(() => {
  const banners = bannersData.value?.data
  if (banners && banners.length > 0) {
    return banners.map(b => ({
      title: b.title,
      subtitle: b.subtitle,
      imageUrl: b.imageUrl,
      linkUrl: b.linkUrl
    }))
  }
  // 默认轮播数据
  return [
    {
      title: '虹远水利 · 润泽万家',
      subtitle: '二十年专注水利工程建设，以专业技术和匠心品质，打造精品水利工程，守护一方水安全',
      linkUrl: '/about'
    },
    {
      title: '水库大坝 · 固若金汤',
      subtitle: '承建各类大中型水库大坝工程，确保防洪安全、供水保障、生态平衡',
      linkUrl: '/business'
    },
    {
      title: '河道治理 · 碧水长流',
      subtitle: '科学规划、综合治理，让每一条河流恢复生机，造福两岸百姓',
      linkUrl: '/cases'
    }
  ]
})

// 业务列表（带回退）
const businessList = computed(() => {
  const list = businessData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, title: '水库大坝', icon: '', summary: '承建各类大中型水库大坝工程，涵盖混凝土坝、土石坝、拱坝等多种坝型的设计与施工' },
    { id: 2, title: '河道治理', icon: '', summary: '提供河道清淤、护岸加固、生态修复等综合治理方案，改善水环境质量' },
    { id: 3, title: '农田灌溉', icon: '', summary: '规划设计高效节水灌溉系统，提升农业用水效率，保障粮食生产安全' },
    { id: 4, title: '防洪排涝', icon: '', summary: '建设防洪堤坝、排涝泵站等设施，提升区域防洪排涝能力，保障人民生命财产安全' },
    { id: 5, title: '水土保持', icon: '', summary: '实施坡面治理、沟道治理、植被恢复等措施，有效防治水土流失' },
    { id: 6, title: '供水工程', icon: '', summary: '建设城乡供水管网、水处理设施，保障居民用水安全和工业生产用水需求' }
  ]
})

// 精选案例（带回退）
const featuredCases = computed(() => {
  const list = casesData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, title: '千岛湖配水工程', coverImage: '', summary: '大型跨区域供水工程，总投资约120亿元', location: '浙江省杭州市', projectScale: '日供水量100万吨' },
    { id: 2, title: '永安溪综合治理', coverImage: '', summary: '河道生态修复与防洪能力提升综合工程', location: '浙江省台州市', projectScale: '治理河段28公里' },
    { id: 3, title: '青山水库加固工程', coverImage: '', summary: '水库大坝安全加固与溢洪道改建工程', location: '浙江省宁波市', projectScale: '库容1.2亿立方米' },
    { id: 4, title: '绿源农田灌溉项目', coverImage: '', summary: '高标准农田节水灌溉示范工程', location: '浙江省金华市', projectScale: '灌溉面积5.6万亩' }
  ]
})

// 最新新闻（带回退）
const latestNews = computed(() => {
  const list = newsData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, title: '虹远水利中标省重点水利工程项目', coverImage: '', summary: '近日，公司成功中标浙江省重点水利工程——某流域综合治理项目，合同金额约3.8亿元。', publishTime: '2024-03-15' },
    { id: 2, title: '公司荣获年度优质工程奖', coverImage: '', summary: '在全省水利工程质量评比中，我司承建的青山水库加固工程荣获"优质工程"称号。', publishTime: '2024-03-10' },
    { id: 3, title: '虹远水利技术创新成果发布', coverImage: '', summary: '公司研发的智慧水利监测系统通过省级技术鉴定，达到国内领先水平。', publishTime: '2024-03-05' }
  ]
})

// 合作伙伴（带回退）
const partnerList = computed(() => {
  const list = partnersData.value?.data
  if (list && list.length > 0) return list
  return [
    { id: 1, name: '中国水利水电集团', logoUrl: '' },
    { id: 2, name: '浙江省水利厅', logoUrl: '' },
    { id: 3, name: '中国电建集团', logoUrl: '' },
    { id: 4, name: '南水北调集团', logoUrl: '' },
    { id: 5, name: '长江水利委员会', logoUrl: '' },
    { id: 6, name: '黄河水利委员会', logoUrl: '' },
    { id: 7, name: '浙江水利水电学院', logoUrl: '' },
    { id: 8, name: '河海大学', logoUrl: '' }
  ]
})

// 数字统计（带回退）
const statsData = computed(() => {
  const config = statsConfigData.value?.data
  return [
    { value: config?.projectCount ? parseInt(config.projectCount) : 500, label: '完工项目', suffix: '+' },
    { value: config?.serviceYears ? parseInt(config.serviceYears) : 20, label: '服务年限', suffix: '+' },
    { value: config?.teamSize ? parseInt(config.teamSize) : 300, label: '团队规模', suffix: '+' },
    { value: config?.clientCount ? parseInt(config.clientCount) : 200, label: '合作客户', suffix: '+' }
  ]
})
</script>

<style scoped>
/* 公司简介区 */
.about-section__grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--spacing-3xl);
  align-items: center;
}

.about-section__content .section-title {
  text-align: left;
  margin-bottom: var(--spacing-xl);
}

.about-section__text {
  font-size: var(--font-size-base);
  color: var(--color-text-light);
  line-height: 1.8;
  margin-bottom: var(--spacing-lg);
}

.about-section__image-wrapper {
  position: relative;
}

.about-section__image-placeholder {
  border-radius: var(--radius-lg);
  overflow: hidden;
  box-shadow: var(--shadow-lg);
}

.about-section__image-placeholder svg {
  width: 100%;
  height: auto;
}

.about-section__badge {
  position: absolute;
  bottom: -20px;
  left: -20px;
  background: var(--color-accent);
  color: var(--color-white);
  padding: var(--spacing-lg) var(--spacing-xl);
  border-radius: var(--radius-md);
  text-align: center;
  box-shadow: var(--shadow-lg);
}

.about-section__badge-number {
  display: block;
  font-size: var(--font-size-3xl);
  font-weight: 800;
  line-height: 1;
}

.about-section__badge-text {
  display: block;
  font-size: var(--font-size-sm);
  margin-top: var(--spacing-xs);
}

/* 业务区 */
.business-section__grid {
  gap: var(--spacing-xl);
}

/* 统计区 */
.stats-section {
  background: linear-gradient(135deg, var(--color-primary-dark) 0%, var(--color-primary) 100%);
  position: relative;
  overflow: hidden;
}

.stats-section::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url("data:image/svg+xml,%3Csvg width='100' height='100' xmlns='http://www.w3.org/2000/svg'%3E%3Ccircle cx='50' cy='50' r='2' fill='rgba(255,255,255,0.03)'/%3E%3C/svg%3E") repeat;
}

.stats-section__grid {
  position: relative;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: var(--spacing-xl);
}

/* 案例区 */
.cases-section__more {
  text-align: center;
  margin-top: var(--spacing-2xl);
}

/* 新闻区 */
.news-section__list {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-xl);
}

.news-section__more {
  text-align: center;
  margin-top: var(--spacing-2xl);
}

/* 合作伙伴区 */
.partners-section__grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: var(--spacing-xl);
}

.partners-section__item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 80px;
  padding: var(--spacing-md) var(--spacing-xl);
  border: 1px solid var(--color-border-light);
  border-radius: var(--radius-md);
  transition: all var(--transition-normal);
  background: var(--color-white);
}

.partners-section__item:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-sm);
}

.partners-section__item img {
  max-height: 40px;
  max-width: 100%;
  object-fit: contain;
  filter: grayscale(100%);
  opacity: 0.7;
  transition: all var(--transition-normal);
}

.partners-section__item:hover img {
  filter: grayscale(0);
  opacity: 1;
}

.partners-section__placeholder {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  font-weight: 500;
  text-align: center;
}

@media (max-width: 1024px) {
  .about-section__grid {
    grid-template-columns: 1fr;
    gap: var(--spacing-2xl);
  }

  .stats-section__grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .partners-section__grid {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media (max-width: 768px) {
  .stats-section__grid {
    grid-template-columns: repeat(2, 1fr);
    gap: var(--spacing-md);
  }

  .partners-section__grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .about-section__badge {
    bottom: -10px;
    left: auto;
    right: 10px;
    padding: var(--spacing-md) var(--spacing-lg);
  }

  .about-section__badge-number {
    font-size: var(--font-size-2xl);
  }
}
</style>

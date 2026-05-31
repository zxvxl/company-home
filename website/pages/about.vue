<template>
  <div class="about-page">
    <!-- Page Banner -->
    <section class="page-banner">
      <div class="container">
        <h1>关于虹远</h1>
        <p>三十年深耕水利，铸就行业典范</p>
      </div>
    </section>

    <!-- 公司概况 -->
    <section class="section">
      <div class="container">
        <div class="intro-grid">
          <div class="intro-content">
            <h2>公司概况</h2>
            <p>
              虹远水利工程有限公司创建于1992年，总部位于浙江省杭州市。公司注册资金2.8亿元，
              现有员工1200余人，其中高级工程师86人、一级建造师52人、各类中级职称人员320余人。
            </p>
            <p>
              公司持有水利水电工程施工总承包壹级资质、市政公用工程施工总承包贰级资质、
              地基基础工程专业承包壹级资质、河湖整治工程专业承包壹级资质等多项资质。
              是国家高新技术企业、浙江省建筑业百强企业。
            </p>
            <p>
              三十余年来，公司参与建设了500余项水利水电工程，工程遍布浙江、江西、安徽、福建等
              30个省市自治区，累计完成工程合同额超过120亿元，多次荣获大禹奖、鲁班奖等殊荣。
            </p>
          </div>
          <div class="intro-image">
            <div class="image-placeholder">
              <span>公司大楼</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 发展历程 -->
    <section class="section history-section">
      <div class="container">
        <h2 class="section-title">发展历程</h2>
        <p class="section-subtitle">三十年砥砺前行</p>
        <div class="timeline">
          <div v-for="item in timeline" :key="item.year" class="timeline-item">
            <div class="timeline-year">{{ item.year }}</div>
            <div class="timeline-content">
              <h3>{{ item.title }}</h3>
              <p>{{ item.desc }}</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 资质荣誉 -->
    <section class="section">
      <div class="container">
        <h2 class="section-title">资质荣誉</h2>
        <p class="section-subtitle">实力铸就品质，品质赢得荣誉</p>
        <div class="grid grid-4">
          <div v-for="item in honors" :key="item.title" class="honor-card card">
            <div class="honor-icon">{{ item.icon }}</div>
            <h3>{{ item.title }}</h3>
            <p>{{ item.desc }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 企业文化 -->
    <section class="section culture-section">
      <div class="container">
        <h2 class="section-title">企业文化</h2>
        <div class="culture-grid">
          <div v-for="item in culture" :key="item.title" class="culture-item">
            <h3>{{ item.title }}</h3>
            <p>{{ item.content }}</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '关于我们 - 虹远水利工程有限公司'
})

const timeline = [
  { year: '1992', title: '公司创立', desc: '虹远水利工程公司在杭州成立，初期专注小型水利施工。' },
  { year: '1998', title: '资质升级', desc: '获得水利水电施工总承包贰级资质，业务范围扩展至中型水利项目。' },
  { year: '2005', title: '跨省发展', desc: '业务拓展至浙江、江西、安徽等6省，年产值突破5亿元。' },
  { year: '2010', title: '壹级资质', desc: '获得水利水电施工总承包壹级资质，承建能力跃升至大型水利枢纽。' },
  { year: '2015', title: '科技创新', desc: '成立技术研发中心，获评国家高新技术企业，取得30余项专利。' },
  { year: '2020', title: '数字化转型', desc: '引入BIM技术和智慧工地管理系统，推动水利工程数字化建设。' },
  { year: '2024', title: '百亿跨越', desc: '累计合同额突破120亿元，在建项目覆盖全国30个省市。' }
]

const honors = [
  { icon: '🏆', title: '大禹奖', desc: '中国水利工程优质奖（大禹奖）获得者' },
  { icon: '🏅', title: '壹级资质', desc: '水利水电工程施工总承包壹级' },
  { icon: '📋', title: 'ISO认证', desc: 'ISO9001/14001/45001三体系认证' },
  { icon: '⭐', title: '高新企业', desc: '国家高新技术企业认定' },
  { icon: '🎖️', title: '百强企业', desc: '浙江省建筑业百强企业' },
  { icon: '📊', title: 'AAA信用', desc: '企业信用评价AAA等级' },
  { icon: '🔬', title: '技术中心', desc: '省级企业技术中心' },
  { icon: '🌟', title: '文明单位', desc: '省级文明施工企业' }
]

const culture = [
  { title: '企业使命', content: '以水利工程建设服务社会，保障水安全，改善水环境，推动水利事业发展。' },
  { title: '企业愿景', content: '成为国内领先的水利工程综合服务商，打造百年水利品牌。' },
  { title: '核心价值观', content: '以水为本、质量至上、科技引领、诚信共赢。' },
  { title: '企业精神', content: '勇于担当、精益求精、创新进取、协作共赢。' }
]
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

.intro-grid {
  display: grid;
  grid-template-columns: 1.5fr 1fr;
  gap: var(--space-3xl);
  align-items: center;
}

.intro-content h2 {
  font-size: var(--font-size-2xl);
  color: var(--color-primary);
  margin-bottom: var(--space-lg);
}

.intro-content p {
  font-size: var(--font-size-base);
  line-height: 1.9;
  color: var(--text-secondary);
  margin-bottom: var(--space-lg);
}

.image-placeholder {
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-light));
  border-radius: var(--radius-lg);
  height: 350px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--color-white);
  font-size: var(--font-size-xl);
  opacity: 0.8;
}

/* Timeline */
.history-section {
  background: var(--color-gray-50);
}

.timeline {
  position: relative;
  max-width: 800px;
  margin: 0 auto;
  padding-left: var(--space-3xl);
}

.timeline::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  width: 3px;
  background: var(--color-primary-light);
}

.timeline-item {
  position: relative;
  margin-bottom: var(--space-2xl);
  padding-left: var(--space-xl);
}

.timeline-item::before {
  content: '';
  position: absolute;
  left: calc(-1 * var(--space-3xl) - 6px);
  top: 8px;
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: var(--color-accent);
  border: 3px solid var(--color-white);
  box-shadow: var(--shadow-sm);
}

.timeline-year {
  font-size: var(--font-size-xl);
  font-weight: 800;
  color: var(--color-primary);
  margin-bottom: var(--space-xs);
}

.timeline-content h3 {
  font-size: var(--font-size-lg);
  color: var(--text-primary);
  margin-bottom: var(--space-xs);
}

.timeline-content p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
}

/* Honors */
.honor-card {
  padding: var(--space-xl);
  text-align: center;
}

.honor-icon {
  font-size: 2.5rem;
  margin-bottom: var(--space-md);
}

.honor-card h3 {
  font-size: var(--font-size-base);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.honor-card p {
  font-size: var(--font-size-sm);
  color: var(--text-muted);
}

/* Culture */
.culture-section {
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-dark));
  color: var(--color-white);
}

.culture-section .section-title {
  color: var(--color-white);
}

.culture-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: var(--space-xl);
}

.culture-item {
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  border-radius: var(--radius-lg);
  padding: var(--space-2xl);
}

.culture-item h3 {
  font-size: var(--font-size-xl);
  margin-bottom: var(--space-md);
  color: var(--color-accent);
}

.culture-item p {
  font-size: var(--font-size-base);
  line-height: 1.8;
  opacity: 0.9;
}

@media (max-width: 768px) {
  .intro-grid {
    grid-template-columns: 1fr;
  }

  .culture-grid {
    grid-template-columns: 1fr;
  }
}
</style>

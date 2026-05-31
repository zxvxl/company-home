<template>
  <div class="business-page">
    <section class="page-banner">
      <div class="container">
        <h1>业务领域</h1>
        <p>六大核心业务板块，全方位水利工程解决方案</p>
      </div>
    </section>

    <section class="section">
      <div class="container">
        <div class="business-list">
          <div
            v-for="(item, index) in businessAreas"
            :key="item.title"
            class="business-item"
            :class="{ reverse: index % 2 === 1 }"
          >
            <div class="business-visual" :style="{ background: item.gradient }">
              <span class="business-icon-large">{{ item.icon }}</span>
              <span class="business-number">0{{ index + 1 }}</span>
            </div>
            <div class="business-detail">
              <h2>{{ item.title }}</h2>
              <p class="business-desc">{{ item.desc }}</p>
              <div class="business-features">
                <h4>核心能力</h4>
                <ul>
                  <li v-for="feat in item.features" :key="feat">{{ feat }}</li>
                </ul>
              </div>
              <div class="business-stats">
                <div class="bstat">
                  <span class="bstat-num">{{ item.projectCount }}</span>
                  <span class="bstat-label">完成项目</span>
                </div>
                <div class="bstat">
                  <span class="bstat-num">{{ item.coverage }}</span>
                  <span class="bstat-label">覆盖地区</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA -->
    <section class="section cta-section">
      <div class="container cta-content">
        <h2>需要水利工程服务？</h2>
        <p>我们的专业团队随时为您提供咨询和解决方案</p>
        <NuxtLink to="/contact" class="btn btn-accent btn-lg">联系我们</NuxtLink>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '业务领域 - 虹远水利工程有限公司'
})

const businessAreas = [
  {
    icon: '🏗️',
    title: '水库大坝工程',
    desc: '承建各类大中小型水库、拦河坝和蓄水枢纽工程。具备土石坝、混凝土面板堆石坝、碾压混凝土坝、拱坝等多种坝型的设计与施工能力。',
    features: ['大坝新建与除险加固', '溢洪道设计与施工', '坝体防渗与灌浆处理', '水库清淤与库容恢复', '大坝安全监测系统建设'],
    projectCount: '86+',
    coverage: '18省',
    gradient: 'linear-gradient(135deg, #1a5276, #2471a3)'
  },
  {
    icon: '🌊',
    title: '河道治理工程',
    desc: '专注河道综合整治与生态修复。通过科学规划和先进工艺，实现防洪安全与生态环境的和谐统一。',
    features: ['河道整治与清淤疏浚', '堤防加固与新建', '生态护岸工程', '河道生态修复', '水生态环境治理'],
    projectCount: '120+',
    coverage: '22省',
    gradient: 'linear-gradient(135deg, #2980b9, #3498db)'
  },
  {
    icon: '🌾',
    title: '农田灌溉工程',
    desc: '致力于农田水利基础设施建设，提升灌溉保障能力。推广高效节水灌溉技术，助力现代农业发展。',
    features: ['灌区建设与续建配套', '高效节水灌溉', '泵站建设与改造', '渠系衬砌与防渗', '灌溉自动化控制'],
    projectCount: '95+',
    coverage: '15省',
    gradient: 'linear-gradient(135deg, #27ae60, #2ecc71)'
  },
  {
    icon: '🛡️',
    title: '防洪排涝工程',
    desc: '构建完善的防洪排涝体系，保障人民生命财产安全。涵盖城市防洪、流域治理、涝区排水等多个领域。',
    features: ['防洪堤建设与加固', '排涝泵站工程', '城市防洪体系建设', '山洪灾害防治', '涝区综合治理'],
    projectCount: '78+',
    coverage: '20省',
    gradient: 'linear-gradient(135deg, #e67e22, #f39c12)'
  },
  {
    icon: '🌱',
    title: '水土保持工程',
    desc: '以小流域为单元，实施坡面治理、沟道治理等综合措施，有效控制水土流失，改善生态环境。',
    features: ['小流域综合治理', '坡面水土保持', '矿山生态修复', '生态清洁小流域建设', '水土保持监测'],
    projectCount: '65+',
    coverage: '12省',
    gradient: 'linear-gradient(135deg, #16a085, #1abc9c)'
  },
  {
    icon: '💧',
    title: '供水工程',
    desc: '从水源到管网，提供城乡供水一体化解决方案。确保饮水安全，提升供水保障能力。',
    features: ['城乡供水一体化', '饮水安全工程', '长距离输水管道', '水厂建设与改造', '管网检测与修复'],
    projectCount: '56+',
    coverage: '10省',
    gradient: 'linear-gradient(135deg, #2c3e50, #34495e)'
  }
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

.business-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-4xl);
}

.business-item {
  display: grid;
  grid-template-columns: 1fr 1.2fr;
  gap: var(--space-3xl);
  align-items: center;
}

.business-item.reverse {
  grid-template-columns: 1.2fr 1fr;
}

.business-item.reverse .business-visual {
  order: 2;
}

.business-visual {
  border-radius: var(--radius-xl);
  height: 320px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.business-icon-large {
  font-size: 5rem;
}

.business-number {
  position: absolute;
  bottom: var(--space-lg);
  right: var(--space-lg);
  font-size: 4rem;
  font-weight: 900;
  color: rgba(255, 255, 255, 0.15);
}

.business-detail h2 {
  font-size: var(--font-size-2xl);
  color: var(--color-primary);
  margin-bottom: var(--space-md);
}

.business-desc {
  font-size: var(--font-size-base);
  line-height: 1.8;
  color: var(--text-secondary);
  margin-bottom: var(--space-xl);
}

.business-features h4 {
  font-size: var(--font-size-base);
  color: var(--text-primary);
  margin-bottom: var(--space-sm);
}

.business-features ul {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-sm);
}

.business-features li {
  background: var(--color-gray-100);
  padding: var(--space-xs) var(--space-md);
  border-radius: var(--radius-full);
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
}

.business-stats {
  display: flex;
  gap: var(--space-2xl);
  margin-top: var(--space-xl);
  padding-top: var(--space-xl);
  border-top: 1px solid var(--color-gray-200);
}

.bstat-num {
  display: block;
  font-size: var(--font-size-2xl);
  font-weight: 800;
  color: var(--color-primary);
}

.bstat-label {
  font-size: var(--font-size-sm);
  color: var(--text-muted);
}

/* CTA */
.cta-section {
  background: var(--color-gray-50);
}

.cta-content {
  text-align: center;
}

.cta-content h2 {
  font-size: var(--font-size-3xl);
  color: var(--color-primary);
  margin-bottom: var(--space-md);
}

.cta-content p {
  font-size: var(--font-size-lg);
  color: var(--text-secondary);
  margin-bottom: var(--space-2xl);
}

@media (max-width: 768px) {
  .business-item,
  .business-item.reverse {
    grid-template-columns: 1fr;
  }

  .business-item.reverse .business-visual {
    order: 0;
  }

  .business-visual {
    height: 200px;
  }
}
</style>

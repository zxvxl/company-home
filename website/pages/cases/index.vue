<template>
  <div class="cases-page">
    <section class="page-banner">
      <div class="container">
        <h1>工程案例</h1>
        <p>精品工程见证实力，匠心品质铸就口碑</p>
      </div>
    </section>

    <!-- 筛选 -->
    <section class="section">
      <div class="container">
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

        <div class="grid grid-3">
          <div v-for="item in filteredCases" :key="item.title" class="case-card card">
            <div class="case-cover" :style="{ background: item.gradient }">
              <span class="case-tag">{{ item.category }}</span>
              <span class="case-year">{{ item.year }}</span>
            </div>
            <div class="case-body">
              <h3>{{ item.title }}</h3>
              <p>{{ item.desc }}</p>
              <div class="case-info-row">
                <div class="case-info-item">
                  <span class="info-label">项目地点</span>
                  <span class="info-value">{{ item.location }}</span>
                </div>
                <div class="case-info-item">
                  <span class="info-label">投资规模</span>
                  <span class="info-value">{{ item.investment }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '工程案例 - 虹远水利工程有限公司'
})

const activeCategory = ref('all')

const categories = [
  { label: '全部', value: 'all' },
  { label: '水库大坝', value: '水库大坝' },
  { label: '河道治理', value: '河道治理' },
  { label: '农田灌溉', value: '农田灌溉' },
  { label: '防洪排涝', value: '防洪排涝' },
  { label: '供水工程', value: '供水工程' }
]

const allCases = [
  {
    title: '龙湾水库除险加固工程',
    desc: '对总库容2860万m³的龙湾水库实施大坝加高加固、溢洪道改造、放水设施更新等综合除险措施。',
    category: '水库大坝',
    location: '浙江·温州',
    year: '2023',
    investment: '1.2亿元',
    gradient: 'linear-gradient(135deg, #1a5276, #2980b9)'
  },
  {
    title: '瓯江河道综合治理工程',
    desc: '治理河道长度28.5km，新建生态护岸32km，建设亲水平台和滨水景观带。',
    category: '河道治理',
    location: '浙江·丽水',
    year: '2022',
    investment: '2.8亿元',
    gradient: 'linear-gradient(135deg, #2980b9, #3498db)'
  },
  {
    title: '万亩高标准农田灌溉工程',
    desc: '建设灌溉面积1.2万亩的高效节水灌溉系统，含泵站2座、干支渠38km。',
    category: '农田灌溉',
    location: '浙江·金华',
    year: '2023',
    investment: '8600万元',
    gradient: 'linear-gradient(135deg, #27ae60, #2ecc71)'
  },
  {
    title: '青田县城防洪排涝工程',
    desc: '新建防洪堤12.6km、排涝泵站3座，将城区防洪标准提升至50年一遇。',
    category: '防洪排涝',
    location: '浙江·青田',
    year: '2023',
    investment: '3.5亿元',
    gradient: 'linear-gradient(135deg, #e67e22, #f39c12)'
  },
  {
    title: '龙泉市城乡供水一体化工程',
    desc: '新建水厂1座（日供水5万吨）、输水管道86km，惠及30万居民。',
    category: '供水工程',
    location: '浙江·龙泉',
    year: '2024',
    investment: '3.8亿元',
    gradient: 'linear-gradient(135deg, #2c3e50, #34495e)'
  },
  {
    title: '仙居县永安溪生态治理工程',
    desc: '综合治理永安溪18km河段，建设生态湿地200亩，恢复水生态系统。',
    category: '河道治理',
    location: '浙江·仙居',
    year: '2022',
    investment: '1.6亿元',
    gradient: 'linear-gradient(135deg, #16a085, #1abc9c)'
  },
  {
    title: '缙云县潜明水库新建工程',
    desc: '新建中型水库1座，总库容1520万m³，年供水量2000万m³。',
    category: '水库大坝',
    location: '浙江·缙云',
    year: '2021',
    investment: '4.2亿元',
    gradient: 'linear-gradient(135deg, #1a5276, #154360)'
  },
  {
    title: '松阳县灌区续建配套工程',
    desc: '改造干渠28km、支渠45km，新增灌溉面积0.8万亩，改善灌溉面积2.3万亩。',
    category: '农田灌溉',
    location: '浙江·松阳',
    year: '2022',
    investment: '6200万元',
    gradient: 'linear-gradient(135deg, #27ae60, #229954)'
  },
  {
    title: '温岭市排涝泵站群工程',
    desc: '新建排涝泵站5座，总排涝流量85m³/s，保护面积12万亩。',
    category: '防洪排涝',
    location: '浙江·温岭',
    year: '2023',
    investment: '2.1亿元',
    gradient: 'linear-gradient(135deg, #d35400, #e67e22)'
  }
]

const filteredCases = computed(() => {
  if (activeCategory.value === 'all') return allCases
  return allCases.filter(c => c.category === activeCategory.value)
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

.case-cover {
  height: 180px;
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  padding: var(--space-md);
}

.case-tag {
  background: rgba(255, 255, 255, 0.2);
  color: var(--color-white);
  padding: var(--space-xs) var(--space-sm);
  border-radius: var(--radius-sm);
  font-size: var(--font-size-xs);
  backdrop-filter: blur(4px);
}

.case-year {
  color: rgba(255, 255, 255, 0.7);
  font-size: var(--font-size-sm);
}

.case-body {
  padding: var(--space-lg);
}

.case-body h3 {
  font-size: var(--font-size-lg);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.case-body p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
  margin-bottom: var(--space-md);
}

.case-info-row {
  display: flex;
  gap: var(--space-xl);
  padding-top: var(--space-md);
  border-top: 1px solid var(--color-gray-200);
}

.info-label {
  display: block;
  font-size: var(--font-size-xs);
  color: var(--text-muted);
  margin-bottom: 2px;
}

.info-value {
  font-size: var(--font-size-sm);
  font-weight: 600;
  color: var(--text-primary);
}
</style>

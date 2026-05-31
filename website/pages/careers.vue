<template>
  <div class="careers-page">
    <!-- Banner -->
    <section class="page-banner">
      <div class="banner-overlay"></div>
      <div class="container banner-content">
        <h1>人才招聘</h1>
        <p>以人为本 · 共创未来</p>
      </div>
    </section>

    <!-- 招聘理念 -->
    <section class="section philosophy-section">
      <div class="container">
        <div class="philosophy-content">
          <h2>以人为本，共创未来</h2>
          <p>
            虹远水利始终坚持人才是第一资源的理念，为员工搭建广阔的发展平台。
            我们期待有理想、有热情、有专业能力的你加入我们，一起为水利事业贡献力量！
          </p>
        </div>
      </div>
    </section>

    <!-- 员工福利 -->
    <section class="section benefits-section">
      <div class="container">
        <h2 class="section-title">员工福利</h2>
        <p class="section-subtitle">完善的福利保障，助力个人成长</p>
        <div class="benefits-grid">
          <div v-for="benefit in benefits" :key="benefit.title" class="benefit-card">
            <div class="benefit-icon">{{ benefit.icon }}</div>
            <h3>{{ benefit.title }}</h3>
            <p>{{ benefit.desc }}</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 在招岗位 -->
    <section class="section jobs-section">
      <div class="container">
        <h2 class="section-title">在招岗位</h2>
        <p class="section-subtitle">寻找适合你的机会</p>
        <div class="jobs-list">
          <div
            v-for="(job, index) in jobs"
            :key="job.title"
            class="job-card"
          >
            <div class="job-header" @click="toggleJob(index)">
              <div class="job-main">
                <h3>{{ job.title }}</h3>
                <div class="job-tags">
                  <span class="tag tag-location">{{ job.location }}</span>
                  <span class="tag tag-edu">{{ job.education }}</span>
                  <span class="tag tag-salary">{{ job.salary }}</span>
                </div>
              </div>
              <div class="job-meta">
                <span class="job-date">{{ job.date }}</span>
                <span class="job-toggle" :class="{ open: expandedJob === index }">
                  ▼
                </span>
              </div>
            </div>

            <Transition name="expand">
              <div v-if="expandedJob === index" class="job-detail">
                <div class="detail-section">
                  <h4>岗位职责</h4>
                  <ul>
                    <li v-for="duty in job.duties" :key="duty">{{ duty }}</li>
                  </ul>
                </div>
                <div class="detail-section">
                  <h4>任职要求</h4>
                  <ul>
                    <li v-for="req in job.requirements" :key="req">{{ req }}</li>
                  </ul>
                </div>
              </div>
            </Transition>
          </div>
        </div>
      </div>
    </section>

    <!-- 投递方式 -->
    <section class="section apply-section">
      <div class="container">
        <div class="apply-card">
          <h2>投递方式</h2>
          <p class="apply-desc">
            如您对以上岗位感兴趣，请将简历发送至我们的招聘邮箱，邮件标题请注明"应聘岗位+姓名"。
          </p>
          <div class="apply-info">
            <div class="apply-item">
              <span class="apply-label">招聘邮箱：</span>
              <span class="apply-value">hr@hongyuan-water.com</span>
            </div>
            <div class="apply-item">
              <span class="apply-label">联系电话：</span>
              <span class="apply-value">0871-65888888 转 人力资源部</span>
            </div>
            <div class="apply-item">
              <span class="apply-label">公司地址：</span>
              <span class="apply-value">云南省昆明市盘龙区白云路168号虹远大厦12层</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '人才招聘 - 虹远水利工程有限公司'
})

const expandedJob = ref<number | null>(null)

function toggleJob(index: number) {
  expandedJob.value = expandedJob.value === index ? null : index
}

// 员工福利
const benefits = [
  {
    icon: '🏥',
    title: '五险一金',
    desc: '入职即缴纳五险一金，额外补充商业保险'
  },
  {
    icon: '🏖️',
    title: '带薪年假',
    desc: '入职即享带薪年假，工龄越长假期越多'
  },
  {
    icon: '💰',
    title: '项目津贴',
    desc: '外派项目享受交通、住宿、餐饮等多项津贴'
  },
  {
    icon: '📖',
    title: '技术培训',
    desc: '定期组织专业技术培训，支持考取执业资格证书'
  },
  {
    icon: '📈',
    title: '晋升通道',
    desc: '技术和管理双通道晋升，能力突出者快速提拔'
  }
]

// 在招岗位数据
const jobs = [
  {
    title: '水利工程项目经理',
    location: '昆明/项目所在地',
    education: '本科',
    salary: '15-25K',
    date: '2024-03-20',
    duties: [
      '全面负责水利工程项目的施工组织和管理',
      '编制施工组织设计和专项施工方案',
      '协调项目部各部门工作，保证工期、质量和安全目标',
      '负责与业主、监理、设计等单位的沟通协调',
      '控制项目成本，定期编报工程进度款'
    ],
    requirements: [
      '水利水电工程或相关专业本科及以上学历',
      '持有水利水电工程一级建造师证书',
      '5年以上水利工程施工管理经验',
      '熟悉水利工程施工规范和验收标准',
      '具备较强的组织协调和沟通能力'
    ]
  },
  {
    title: '水利水电工程师',
    location: '昆明',
    education: '本科',
    salary: '10-18K',
    date: '2024-03-18',
    duties: [
      '负责水利水电工程的设计和技术管理',
      '编写工程技术方案和施工图纸审查',
      '参与工程投标，编写技术标书',
      '跟踪项目施工过程，解决现场技术问题',
      '参与工程竣工验收和技术资料整理'
    ],
    requirements: [
      '水利水电工程、水工结构等相关专业本科学历',
      '3年以上水利工程设计或技术管理经验',
      '熟练使用AutoCAD、水利计算相关软件',
      '了解水利行业设计规范和技术标准',
      '持有工程师及以上职称者优先'
    ]
  },
  {
    title: '工程造价师',
    location: '昆明',
    education: '本科',
    salary: '12-20K',
    date: '2024-03-15',
    duties: [
      '负责水利工程项目的投标报价和预结算',
      '编制工程量清单、招标控制价',
      '审核施工图预算，控制项目成本',
      '办理工程变更和签证的造价审核',
      '参与合同商务条款的谈判和审核'
    ],
    requirements: [
      '工程造价、水利水电等相关专业本科学历',
      '持有注册造价工程师证书',
      '3年以上水利工程造价相关工作经验',
      '熟练使用广联达、鹏业等造价软件',
      '熟悉水利工程定额和计价规范'
    ]
  },
  {
    title: '施工现场技术员',
    location: '项目所在地',
    education: '大专',
    salary: '8-12K',
    date: '2024-03-12',
    duties: [
      '负责施工现场的技术指导和质量检查',
      '做好施工测量放样和技术交底',
      '及时整理施工技术资料和隐蔽工程记录',
      '配合监理工程师进行工序验收',
      '协助编写施工日志和周报'
    ],
    requirements: [
      '水利工程、土木工程相关专业大专及以上学历',
      '1年以上水利工程施工经验，应届毕业生可培养',
      '能适应项目现场工作环境',
      '具备基本的测量和制图能力',
      '工作认真负责，有团队合作精神'
    ]
  },
  {
    title: '安全员',
    location: '项目所在地',
    education: '大专',
    salary: '7-10K',
    date: '2024-03-10',
    duties: [
      '负责项目施工现场的安全管理工作',
      '组织安全教育培训和安全技术交底',
      '开展日常安全巡查，排查安全隐患',
      '处理安全事故，编写安全事故报告',
      '负责安全生产资料的收集整理和归档'
    ],
    requirements: [
      '安全工程、水利工程等相关专业大专及以上学历',
      '持有安全员C证（水利类）',
      '1年以上施工现场安全管理经验',
      '熟悉水利工程施工安全规范',
      '工作细心负责，具备较强的安全意识'
    ]
  }
]
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

/* 招聘理念 */
.philosophy-content {
  text-align: center;
  max-width: 700px;
  margin: 0 auto;
}

.philosophy-content h2 {
  font-size: var(--font-size-3xl);
  color: var(--color-primary);
  margin-bottom: var(--space-lg);
}

.philosophy-content p {
  font-size: var(--font-size-lg);
  color: var(--text-secondary);
  line-height: 1.9;
}

/* 员工福利 */
.benefits-section {
  background: var(--color-gray-50);
}

.benefits-grid {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: var(--space-lg);
}

.benefit-card {
  background: var(--color-white);
  border-radius: var(--radius-lg);
  padding: var(--space-xl);
  text-align: center;
  border: 1px solid var(--color-gray-200);
  transition: all var(--transition-base);
}

.benefit-card:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-md);
  transform: translateY(-4px);
}

.benefit-icon {
  font-size: 2.5rem;
  margin-bottom: var(--space-md);
}

.benefit-card h3 {
  font-size: var(--font-size-base);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.benefit-card p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.6;
}

/* 在招岗位 */
.jobs-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.job-card {
  background: var(--color-white);
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-lg);
  overflow: hidden;
  transition: all var(--transition-base);
}

.job-card:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-sm);
}

.job-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: var(--space-xl);
  cursor: pointer;
}

.job-main h3 {
  font-size: var(--font-size-lg);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.job-tags {
  display: flex;
  gap: var(--space-sm);
  flex-wrap: wrap;
}

.tag {
  padding: var(--space-xs) var(--space-sm);
  border-radius: var(--radius-sm);
  font-size: var(--font-size-xs);
  font-weight: 500;
}

.tag-location {
  background: rgba(26, 82, 118, 0.1);
  color: var(--color-primary);
}

.tag-edu {
  background: rgba(39, 174, 96, 0.1);
  color: #27ae60;
}

.tag-salary {
  background: rgba(230, 126, 34, 0.1);
  color: #e67e22;
  font-weight: 700;
}

.job-meta {
  display: flex;
  align-items: center;
  gap: var(--space-md);
  flex-shrink: 0;
}

.job-date {
  font-size: var(--font-size-sm);
  color: var(--text-muted);
}

.job-toggle {
  font-size: var(--font-size-sm);
  color: var(--text-muted);
  transition: transform var(--transition-fast);
}

.job-toggle.open {
  transform: rotate(180deg);
}

/* 岗位详情 */
.job-detail {
  padding: 0 var(--space-xl) var(--space-xl);
  border-top: 1px solid var(--color-gray-100);
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--space-xl);
}

.detail-section h4 {
  font-size: var(--font-size-base);
  color: var(--color-primary);
  margin-bottom: var(--space-md);
  padding-top: var(--space-lg);
}

.detail-section ul {
  list-style: none;
  padding: 0;
}

.detail-section li {
  position: relative;
  padding-left: var(--space-lg);
  margin-bottom: var(--space-sm);
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
}

.detail-section li::before {
  content: '•';
  position: absolute;
  left: 0;
  color: var(--color-accent);
  font-weight: 700;
}

/* 投递方式 */
.apply-section {
  background: var(--color-gray-50);
}

.apply-card {
  max-width: 700px;
  margin: 0 auto;
  background: var(--color-white);
  border-radius: var(--radius-xl);
  padding: var(--space-3xl);
  text-align: center;
  border: 1px solid var(--color-gray-200);
  box-shadow: var(--shadow-md);
}

.apply-card h2 {
  font-size: var(--font-size-2xl);
  color: var(--color-primary);
  margin-bottom: var(--space-md);
}

.apply-desc {
  font-size: var(--font-size-base);
  color: var(--text-secondary);
  margin-bottom: var(--space-xl);
  line-height: 1.7;
}

.apply-info {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
  text-align: left;
}

.apply-item {
  display: flex;
  gap: var(--space-sm);
  padding: var(--space-md);
  background: var(--color-gray-50);
  border-radius: var(--radius-md);
}

.apply-label {
  color: var(--text-muted);
  font-size: var(--font-size-sm);
  flex-shrink: 0;
}

.apply-value {
  font-size: var(--font-size-sm);
  color: var(--color-primary);
  font-weight: 500;
}

/* Transitions */
.expand-enter-active,
.expand-leave-active {
  transition: all 0.3s ease;
  overflow: hidden;
}

.expand-enter-from,
.expand-leave-to {
  opacity: 0;
  max-height: 0;
}

@media (max-width: 1024px) {
  .benefits-grid {
    grid-template-columns: repeat(3, 1fr);
  }

  .job-detail {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .page-banner {
    height: 220px;
  }

  .benefits-grid {
    grid-template-columns: repeat(2, 1fr);
  }

  .job-header {
    flex-direction: column;
    align-items: flex-start;
    gap: var(--space-md);
  }

  .job-meta {
    width: 100%;
    justify-content: space-between;
  }
}
</style>

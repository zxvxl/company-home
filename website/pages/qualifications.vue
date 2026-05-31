<template>
  <div class="qualifications-page">
    <!-- Banner -->
    <section class="page-banner">
      <div class="banner-overlay"></div>
      <div class="container banner-content">
        <h1>资质荣誉</h1>
        <p>专业资质保障 · 行业荣誉认可</p>
      </div>
    </section>

    <!-- 内容区 -->
    <section class="section qualifications-content">
      <div class="container">
        <div class="qual-grid">
          <!-- 左栏：企业资质 -->
          <div class="qual-column">
            <h2 class="column-title">
              <span class="title-icon">📜</span>
              企业资质
            </h2>
            <div class="cert-list">
              <div
                v-for="cert in certificates"
                :key="cert.name"
                class="cert-card"
                @click="openModal(cert)"
              >
                <div class="cert-img" :style="{ background: cert.color }">
                  <span class="cert-img-icon">{{ cert.icon }}</span>
                </div>
                <div class="cert-info">
                  <h3>{{ cert.name }}</h3>
                  <p class="cert-issuer">
                    <span class="label">发证机构：</span>{{ cert.issuer }}
                  </p>
                  <p class="cert-validity">
                    <span class="label">有效期：</span>{{ cert.validity }}
                  </p>
                </div>
              </div>
            </div>
          </div>

          <!-- 右栏：荣誉奖项 -->
          <div class="qual-column">
            <h2 class="column-title">
              <span class="title-icon">🏆</span>
              荣誉奖项
            </h2>
            <div class="honor-list">
              <div v-for="honor in honors" :key="honor.name" class="honor-item">
                <div class="honor-year">{{ honor.year }}</div>
                <div class="honor-info">
                  <h3>{{ honor.name }}</h3>
                  <p>{{ honor.org }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 证书放大 Modal -->
    <Teleport to="body">
      <div v-if="modalVisible" class="modal-overlay" @click="closeModal">
        <div class="modal-content" @click.stop>
          <button class="modal-close" @click="closeModal">&times;</button>
          <div class="modal-img" :style="{ background: selectedCert?.color }">
            <span class="modal-img-icon">{{ selectedCert?.icon }}</span>
          </div>
          <div class="modal-info">
            <h3>{{ selectedCert?.name }}</h3>
            <p><strong>发证机构：</strong>{{ selectedCert?.issuer }}</p>
            <p><strong>有效期：</strong>{{ selectedCert?.validity }}</p>
            <p><strong>证书编号：</strong>{{ selectedCert?.certNo }}</p>
          </div>
        </div>
      </div>
    </Teleport>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '资质荣誉 - 虹远水利工程有限公司'
})

interface Certificate {
  name: string
  issuer: string
  validity: string
  certNo: string
  icon: string
  color: string
}

const modalVisible = ref(false)
const selectedCert = ref<Certificate | null>(null)

function openModal(cert: Certificate) {
  selectedCert.value = cert
  modalVisible.value = true
}

function closeModal() {
  modalVisible.value = false
  selectedCert.value = null
}

// Fallback 静态数据
const certificates: Certificate[] = [
  {
    name: '水利水电工程施工总承包壹级',
    issuer: '中华人民共和国住房和城乡建设部',
    validity: '2022.01 - 2027.01',
    certNo: 'D153000000',
    icon: '🏗️',
    color: 'linear-gradient(135deg, #1a5276, #2980b9)'
  },
  {
    name: '水利行业设计乙级',
    issuer: '中华人民共和国水利部',
    validity: '2021.06 - 2026.06',
    certNo: 'A253000000',
    icon: '📐',
    color: 'linear-gradient(135deg, #2c3e50, #34495e)'
  },
  {
    name: '市政公用工程施工总承包贰级',
    issuer: '云南省住房和城乡建设厅',
    validity: '2023.03 - 2028.03',
    certNo: 'D253000000',
    icon: '🏙️',
    color: 'linear-gradient(135deg, #16a085, #1abc9c)'
  },
  {
    name: '环保工程专业承包贰级',
    issuer: '云南省住房和城乡建设厅',
    validity: '2022.09 - 2027.09',
    certNo: 'D253000001',
    icon: '🌿',
    color: 'linear-gradient(135deg, #27ae60, #2ecc71)'
  },
  {
    name: 'ISO9001质量管理体系认证',
    issuer: '中国质量认证中心（CQC）',
    validity: '2023.01 - 2026.01',
    certNo: '00123Q10000R0M',
    icon: '✅',
    color: 'linear-gradient(135deg, #2980b9, #3498db)'
  }
]

const honors = [
  { year: '2024', name: '省优质水利工程奖', org: '云南省水利厅' },
  { year: '2023', name: '省优质水利工程奖', org: '云南省水利厅' },
  { year: '2022', name: '省优质水利工程奖', org: '云南省水利厅' },
  { year: '2021', name: '省优质水利工程奖', org: '云南省水利厅' },
  { year: '2020', name: '省优质水利工程奖', org: '云南省水利厅' },
  { year: '2023', name: '国家高新技术企业', org: '科学技术部' },
  { year: '2024', name: 'AAA级信用企业', org: '云南省水利水电行业协会' },
  { year: '2023', name: '安全生产标准化一级企业', org: '云南省应急管理厅' }
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

.qualifications-content {
  padding: var(--space-4xl) 0;
}

.qual-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--space-3xl);
}

.column-title {
  display: flex;
  align-items: center;
  gap: var(--space-sm);
  font-size: var(--font-size-2xl);
  color: var(--color-primary);
  margin-bottom: var(--space-xl);
  padding-bottom: var(--space-md);
  border-bottom: 2px solid var(--color-primary-light);
}

.title-icon {
  font-size: 1.5rem;
}

/* 证书卡片 */
.cert-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-lg);
}

.cert-card {
  display: flex;
  gap: var(--space-lg);
  padding: var(--space-lg);
  background: var(--color-white);
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-lg);
  cursor: pointer;
  transition: all var(--transition-base);
}

.cert-card:hover {
  border-color: var(--color-primary-light);
  box-shadow: var(--shadow-md);
  transform: translateY(-2px);
}

.cert-img {
  width: 80px;
  height: 80px;
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.cert-img-icon {
  font-size: 2rem;
}

.cert-info h3 {
  font-size: var(--font-size-base);
  color: var(--text-primary);
  margin-bottom: var(--space-xs);
}

.cert-issuer,
.cert-validity {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  margin-bottom: var(--space-xs);
}

.cert-info .label {
  color: var(--text-muted);
}

/* 荣誉列表 */
.honor-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.honor-item {
  display: flex;
  align-items: center;
  gap: var(--space-lg);
  padding: var(--space-lg);
  background: var(--color-white);
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-lg);
  transition: all var(--transition-base);
}

.honor-item:hover {
  border-color: var(--color-accent);
  box-shadow: var(--shadow-sm);
}

.honor-year {
  min-width: 60px;
  padding: var(--space-sm) var(--space-md);
  background: linear-gradient(135deg, #e67e22, #f39c12);
  color: var(--color-white);
  font-weight: 700;
  font-size: var(--font-size-sm);
  border-radius: var(--radius-md);
  text-align: center;
}

.honor-info h3 {
  font-size: var(--font-size-base);
  color: var(--text-primary);
  margin-bottom: var(--space-xs);
}

.honor-info p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
}

/* Modal */
.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
  padding: var(--space-xl);
}

.modal-content {
  background: var(--color-white);
  border-radius: var(--radius-xl);
  max-width: 500px;
  width: 100%;
  overflow: hidden;
  position: relative;
}

.modal-close {
  position: absolute;
  top: var(--space-md);
  right: var(--space-md);
  font-size: 2rem;
  color: var(--text-secondary);
  cursor: pointer;
  z-index: 1;
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.9);
}

.modal-close:hover {
  color: var(--color-primary);
}

.modal-img {
  width: 100%;
  height: 250px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-img-icon {
  font-size: 5rem;
}

.modal-info {
  padding: var(--space-xl);
}

.modal-info h3 {
  font-size: var(--font-size-xl);
  color: var(--color-primary);
  margin-bottom: var(--space-md);
}

.modal-info p {
  font-size: var(--font-size-base);
  color: var(--text-secondary);
  margin-bottom: var(--space-sm);
}

@media (max-width: 1024px) {
  .qual-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 768px) {
  .page-banner {
    height: 220px;
  }

  .cert-card {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .honor-item {
    flex-direction: column;
    text-align: center;
  }
}
</style>

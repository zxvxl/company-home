<template>
  <div class="contact-page">
    <section class="page-banner">
      <div class="container">
        <h1>联系我们</h1>
        <p>期待与您的合作，共创水利事业新篇章</p>
      </div>
    </section>

    <section class="section">
      <div class="container">
        <div class="contact-grid">
          <!-- 联系信息 -->
          <div class="contact-info">
            <h2>联系方式</h2>
            <div class="info-list">
              <div class="info-item">
                <div class="info-icon">📍</div>
                <div class="info-detail">
                  <h4>公司地址</h4>
                  <p>浙江省杭州市西湖区文三路388号虹远大厦</p>
                  <p class="info-sub">邮编：310012</p>
                </div>
              </div>
              <div class="info-item">
                <div class="info-icon">📞</div>
                <div class="info-detail">
                  <h4>联系电话</h4>
                  <p>总机：0571-8888-6666</p>
                  <p>业务部：0571-8888-6601</p>
                  <p>人力资源部：0571-8888-6602</p>
                </div>
              </div>
              <div class="info-item">
                <div class="info-icon">📧</div>
                <div class="info-detail">
                  <h4>电子邮箱</h4>
                  <p>商务合作：business@hongyuan-water.com</p>
                  <p>人才招聘：hr@hongyuan-water.com</p>
                  <p>综合咨询：info@hongyuan-water.com</p>
                </div>
              </div>
              <div class="info-item">
                <div class="info-icon">🕐</div>
                <div class="info-detail">
                  <h4>工作时间</h4>
                  <p>周一至周五：8:30 - 17:30</p>
                  <p>周末及法定节假日休息</p>
                </div>
              </div>
            </div>
          </div>

          <!-- 留言表单 -->
          <div class="contact-form-wrapper">
            <h2>在线留言</h2>
            <p class="form-hint">如有业务咨询或合作意向，请填写以下表单，我们将在24小时内与您联系。</p>
            <form class="contact-form" @submit.prevent="handleSubmit">
              <div class="form-row">
                <div class="form-group">
                  <label>姓名 <span class="required">*</span></label>
                  <input v-model="form.name" type="text" placeholder="请输入您的姓名" required />
                </div>
                <div class="form-group">
                  <label>电话 <span class="required">*</span></label>
                  <input v-model="form.phone" type="tel" placeholder="请输入联系电话" required />
                </div>
              </div>
              <div class="form-row">
                <div class="form-group">
                  <label>单位名称</label>
                  <input v-model="form.company" type="text" placeholder="请输入单位名称" />
                </div>
                <div class="form-group">
                  <label>邮箱</label>
                  <input v-model="form.email" type="email" placeholder="请输入邮箱地址" />
                </div>
              </div>
              <div class="form-group">
                <label>咨询类型</label>
                <select v-model="form.type">
                  <option value="">请选择咨询类型</option>
                  <option value="business">业务合作</option>
                  <option value="project">项目咨询</option>
                  <option value="recruit">人才招聘</option>
                  <option value="other">其他</option>
                </select>
              </div>
              <div class="form-group">
                <label>留言内容 <span class="required">*</span></label>
                <textarea v-model="form.message" rows="5" placeholder="请输入留言内容..." required></textarea>
              </div>
              <button type="submit" class="btn btn-primary btn-lg submit-btn">
                {{ submitted ? '已提交' : '提交留言' }}
              </button>
            </form>
          </div>
        </div>
      </div>
    </section>

    <!-- 分支机构 -->
    <section class="section branches-section">
      <div class="container">
        <h2 class="section-title">分支机构</h2>
        <div class="grid grid-3">
          <div v-for="branch in branches" :key="branch.name" class="branch-card card">
            <h3>{{ branch.name }}</h3>
            <p>{{ branch.address }}</p>
            <span class="branch-tel">{{ branch.tel }}</span>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({
  title: '联系我们 - 虹远水利工程有限公司'
})

const submitted = ref(false)

const form = reactive({
  name: '',
  phone: '',
  company: '',
  email: '',
  type: '',
  message: ''
})

function handleSubmit() {
  // 表单提交 - 后续对接 CMS 接口
  submitted.value = true
  setTimeout(() => {
    submitted.value = false
  }, 3000)
}

const branches = [
  { name: '丽水分公司', address: '浙江省丽水市莲都区花园路128号', tel: '0578-2100-888' },
  { name: '温州分公司', address: '浙江省温州市鹿城区望江路66号', tel: '0577-8800-666' },
  { name: '金华分公司', address: '浙江省金华市婺城区双溪西路99号', tel: '0579-8200-888' },
  { name: '江西项目部', address: '江西省南昌市红谷滩区凤凰洲路188号', tel: '0791-8600-888' },
  { name: '安徽项目部', address: '安徽省合肥市包河区芜湖路168号', tel: '0551-6300-888' },
  { name: '福建项目部', address: '福建省福州市鼓楼区五四路260号', tel: '0591-8700-888' }
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

.contact-grid {
  display: grid;
  grid-template-columns: 1fr 1.3fr;
  gap: var(--space-4xl);
}

.contact-info h2,
.contact-form-wrapper h2 {
  font-size: var(--font-size-2xl);
  color: var(--color-primary);
  margin-bottom: var(--space-xl);
}

.info-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-xl);
}

.info-item {
  display: flex;
  gap: var(--space-lg);
}

.info-icon {
  font-size: 1.8rem;
  flex-shrink: 0;
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: var(--color-gray-50);
  border-radius: var(--radius-md);
}

.info-detail h4 {
  font-size: var(--font-size-base);
  color: var(--text-primary);
  margin-bottom: var(--space-xs);
}

.info-detail p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  line-height: 1.7;
}

.info-sub {
  font-size: var(--font-size-xs) !important;
  color: var(--text-muted) !important;
}

/* Form */
.form-hint {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  margin-bottom: var(--space-xl);
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--space-lg);
}

.form-group {
  margin-bottom: var(--space-lg);
}

.form-group label {
  display: block;
  font-size: var(--font-size-sm);
  font-weight: 500;
  color: var(--text-primary);
  margin-bottom: var(--space-sm);
}

.required {
  color: #e74c3c;
}

.form-group input,
.form-group select,
.form-group textarea {
  width: 100%;
  padding: var(--space-sm) var(--space-md);
  border: 1px solid var(--color-gray-300);
  border-radius: var(--radius-md);
  font-size: var(--font-size-base);
  font-family: var(--font-sans);
  transition: border-color var(--transition-fast);
  background: var(--color-white);
}

.form-group input:focus,
.form-group select:focus,
.form-group textarea:focus {
  outline: none;
  border-color: var(--color-primary-light);
  box-shadow: 0 0 0 3px rgba(41, 128, 185, 0.1);
}

.form-group textarea {
  resize: vertical;
  min-height: 120px;
}

.submit-btn {
  width: 100%;
}

/* Branches */
.branches-section {
  background: var(--color-gray-50);
}

.branch-card {
  padding: var(--space-xl);
}

.branch-card h3 {
  font-size: var(--font-size-lg);
  color: var(--color-primary);
  margin-bottom: var(--space-sm);
}

.branch-card p {
  font-size: var(--font-size-sm);
  color: var(--text-secondary);
  margin-bottom: var(--space-sm);
  line-height: 1.6;
}

.branch-tel {
  font-size: var(--font-size-sm);
  color: var(--color-accent);
  font-weight: 500;
}

@media (max-width: 768px) {
  .contact-grid {
    grid-template-columns: 1fr;
    gap: var(--space-3xl);
  }

  .form-row {
    grid-template-columns: 1fr;
  }
}
</style>

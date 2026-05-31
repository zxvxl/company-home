<template>
  <div class="contact-page">
    <!-- 页面 Banner -->
    <div class="page-banner">
      <div class="page-banner__content">
        <h1 class="page-banner__title">联系我们</h1>
        <p class="page-banner__subtitle">期待与您的合作，共创美好未来</p>
      </div>
    </div>

    <section class="section">
      <div class="container">
        <div class="contact-grid">
          <!-- 左侧：联系信息 -->
          <div class="contact-info">
            <h2 class="contact-info__title">联系方式</h2>
            <p class="contact-info__desc">如有任何水利工程方面的需求或合作意向，欢迎随时与我们联系。</p>

            <div class="contact-info__list">
              <div class="contact-info__item">
                <div class="contact-info__icon">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7z"/>
                    <circle cx="12" cy="9" r="2.5"/>
                  </svg>
                </div>
                <div class="contact-info__content">
                  <h4>公司地址</h4>
                  <p>浙江省杭州市西湖区文三路478号<br>虹远水利大厦16楼</p>
                </div>
              </div>

              <div class="contact-info__item">
                <div class="contact-info__icon">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"/>
                  </svg>
                </div>
                <div class="contact-info__content">
                  <h4>联系电话</h4>
                  <p>0571-88888888<br>0571-88888887（业务咨询）</p>
                </div>
              </div>

              <div class="contact-info__item">
                <div class="contact-info__icon">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/>
                    <polyline points="22,6 12,13 2,6"/>
                  </svg>
                </div>
                <div class="contact-info__content">
                  <h4>电子邮箱</h4>
                  <p>contact@hongyuan-water.com<br>business@hongyuan-water.com</p>
                </div>
              </div>

              <div class="contact-info__item">
                <div class="contact-info__icon">
                  <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <rect x="2" y="3" width="20" height="14" rx="2" ry="2"/>
                    <line x1="8" y1="21" x2="16" y2="21"/>
                    <line x1="12" y1="17" x2="12" y2="21"/>
                  </svg>
                </div>
                <div class="contact-info__content">
                  <h4>传真</h4>
                  <p>0571-88888889</p>
                </div>
              </div>
            </div>

            <!-- 工作时间 -->
            <div class="contact-info__hours">
              <h4>办公时间</h4>
              <p>周一至周五：08:30 - 17:30</p>
              <p>周六：09:00 - 12:00（值班）</p>
            </div>
          </div>

          <!-- 右侧：留言表单 -->
          <div class="contact-form-wrapper">
            <ContactForm />
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
useHead({ title: '联系我们' })
</script>

<style scoped>
.contact-grid {
  display: grid;
  grid-template-columns: 1fr 1.2fr;
  gap: var(--spacing-3xl);
  align-items: flex-start;
}

.contact-info__title {
  font-size: var(--font-size-2xl);
  font-weight: 700;
  color: var(--color-text);
  margin-bottom: var(--spacing-md);
}

.contact-info__desc {
  font-size: var(--font-size-base);
  color: var(--color-text-light);
  line-height: 1.6;
  margin-bottom: var(--spacing-2xl);
}

.contact-info__list {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-xl);
  margin-bottom: var(--spacing-2xl);
}

.contact-info__item {
  display: flex;
  gap: var(--spacing-lg);
}

.contact-info__icon {
  width: 48px;
  height: 48px;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: var(--radius-md);
  background: linear-gradient(135deg, var(--color-primary), var(--color-primary-light));
  color: var(--color-white);
}

.contact-info__icon svg {
  width: 22px;
  height: 22px;
}

.contact-info__content h4 {
  font-size: var(--font-size-base);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-xs);
}

.contact-info__content p {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  line-height: 1.6;
  margin: 0;
}

.contact-info__hours {
  padding: var(--spacing-lg);
  background: var(--color-bg-section);
  border-radius: var(--radius-md);
  border-left: 4px solid var(--color-accent);
}

.contact-info__hours h4 {
  font-size: var(--font-size-base);
  font-weight: 600;
  color: var(--color-text);
  margin-bottom: var(--spacing-sm);
}

.contact-info__hours p {
  font-size: var(--font-size-sm);
  color: var(--color-text-light);
  margin-bottom: var(--spacing-xs);
}

@media (max-width: 1024px) {
  .contact-grid {
    grid-template-columns: 1fr;
    gap: var(--spacing-2xl);
  }
}
</style>

<template>
  <footer class="footer">
    <div class="container">
      <div class="footer-grid">
        <!-- 公司信息 -->
        <div class="footer-brand">
          <h3 class="footer-logo">虹远水利工程有限公司</h3>
          <p class="footer-desc">
            专注水利工程建设，服务国家水利事业。以科技创新驱动发展，为客户提供优质的水利工程解决方案。
          </p>
          <div class="footer-cert">
            <span>水利工程施工总承包壹级</span>
            <span>ISO9001质量管理体系认证</span>
          </div>
        </div>

        <!-- 业务领域 -->
        <div class="footer-links">
          <h4>业务领域</h4>
          <ul>
            <li><NuxtLink to="/business">水库大坝工程</NuxtLink></li>
            <li><NuxtLink to="/business">河道治理工程</NuxtLink></li>
            <li><NuxtLink to="/business">农田灌溉工程</NuxtLink></li>
            <li><NuxtLink to="/business">防洪排涝工程</NuxtLink></li>
            <li><NuxtLink to="/business">水土保持工程</NuxtLink></li>
            <li><NuxtLink to="/business">供水工程</NuxtLink></li>
          </ul>
        </div>

        <!-- 快速导航 -->
        <div class="footer-links">
          <h4>快速导航</h4>
          <ul>
            <li><NuxtLink to="/about">关于我们</NuxtLink></li>
            <li><NuxtLink to="/cases">工程案例</NuxtLink></li>
            <li><NuxtLink to="/news">新闻中心</NuxtLink></li>
            <li><NuxtLink to="/contact">联系我们</NuxtLink></li>
          </ul>
        </div>

        <!-- 联系方式 -->
        <div class="footer-contact">
          <h4>联系方式</h4>
          <ul>
            <li>
              <span class="contact-label">电话：</span>
              <span>0571-8888-6666</span>
            </li>
            <li>
              <span class="contact-label">邮箱：</span>
              <span>info@hongyuan-water.com</span>
            </li>
            <li>
              <span class="contact-label">地址：</span>
              <span>浙江省杭州市西湖区文三路388号</span>
            </li>
            <li>
              <span class="contact-label">邮编：</span>
              <span>310012</span>
            </li>
          </ul>
        </div>
      </div>

      <div class="footer-bottom">
        <p>&copy; {{ currentYear }} 虹远水利工程有限公司 版权所有</p>
        <p class="footer-icp">浙ICP备XXXXXXXX号-1</p>
      </div>
    </div>
  </footer>
</template>

<script setup lang="ts">
const currentYear = new Date().getFullYear()
</script>

<style scoped>
.footer {
  background: var(--color-primary-dark);
  color: rgba(255, 255, 255, 0.85);
  padding: var(--space-4xl) 0 var(--space-xl);
}

.footer-grid {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr 1.5fr;
  gap: var(--space-3xl);
  margin-bottom: var(--space-3xl);
}

.footer-logo {
  color: var(--color-white);
  font-size: var(--font-size-xl);
  margin-bottom: var(--space-md);
}

.footer-desc {
  font-size: var(--font-size-sm);
  line-height: 1.8;
  margin-bottom: var(--space-md);
  color: rgba(255, 255, 255, 0.7);
}

.footer-cert {
  display: flex;
  flex-direction: column;
  gap: var(--space-xs);
  font-size: var(--font-size-xs);
  color: var(--color-accent);
}

.footer-links h4,
.footer-contact h4 {
  color: var(--color-white);
  font-size: var(--font-size-base);
  margin-bottom: var(--space-lg);
  position: relative;
  padding-bottom: var(--space-sm);
}

.footer-links h4::after,
.footer-contact h4::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 30px;
  height: 2px;
  background: var(--color-accent);
}

.footer-links ul,
.footer-contact ul {
  display: flex;
  flex-direction: column;
  gap: var(--space-sm);
}

.footer-links a {
  font-size: var(--font-size-sm);
  color: rgba(255, 255, 255, 0.7);
  transition: color var(--transition-fast);
}

.footer-links a:hover {
  color: var(--color-accent);
}

.footer-contact li {
  font-size: var(--font-size-sm);
  display: flex;
  gap: var(--space-xs);
}

.contact-label {
  color: rgba(255, 255, 255, 0.5);
  flex-shrink: 0;
}

.footer-bottom {
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  padding-top: var(--space-xl);
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: var(--font-size-sm);
  color: rgba(255, 255, 255, 0.5);
}

@media (max-width: 1024px) {
  .footer-grid {
    grid-template-columns: 1fr 1fr;
  }
}

@media (max-width: 768px) {
  .footer-grid {
    grid-template-columns: 1fr;
    gap: var(--space-xl);
  }

  .footer-bottom {
    flex-direction: column;
    gap: var(--space-sm);
    text-align: center;
  }
}
</style>

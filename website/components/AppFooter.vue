<template>
  <footer class="footer">
    <div class="container">
      <div class="footer__grid">
        <!-- 公司简介 -->
        <div class="footer__section">
          <h3 class="footer__title">虹远水利工程有限公司</h3>
          <p class="footer__desc">
            专业从事水利工程设计、施工与管理，致力于为客户提供水库大坝、河道治理、农田灌溉、防洪排涝等全方位水利工程解决方案。以卓越品质和专业服务，守护水安全、造福千万家。
          </p>
          <div class="footer__social">
            <span class="footer__social-label">关注我们</span>
          </div>
        </div>

        <!-- 快速导航 -->
        <div class="footer__section">
          <h3 class="footer__title">快速导航</h3>
          <nav class="footer__links">
            <NuxtLink to="/" class="footer__link">首页</NuxtLink>
            <NuxtLink to="/about" class="footer__link">关于我们</NuxtLink>
            <NuxtLink to="/business" class="footer__link">业务领域</NuxtLink>
            <NuxtLink to="/cases" class="footer__link">工程案例</NuxtLink>
            <NuxtLink to="/news" class="footer__link">新闻中心</NuxtLink>
            <NuxtLink to="/contact" class="footer__link">联系我们</NuxtLink>
          </nav>
        </div>

        <!-- 联系方式 -->
        <div class="footer__section">
          <h3 class="footer__title">联系方式</h3>
          <ul class="footer__contact">
            <li class="footer__contact-item">
              <span class="footer__contact-icon">&#128205;</span>
              <span>浙江省杭州市西湖区文三路478号</span>
            </li>
            <li class="footer__contact-item">
              <span class="footer__contact-icon">&#128222;</span>
              <span>0571-88888888</span>
            </li>
            <li class="footer__contact-item">
              <span class="footer__contact-icon">&#128231;</span>
              <span>contact@hongyuan-water.com</span>
            </li>
            <li class="footer__contact-item">
              <span class="footer__contact-icon">&#128224;</span>
              <span>0571-88888889</span>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- 版权栏 -->
    <div class="footer__bottom">
      <div class="container footer__bottom-inner">
        <p class="footer__copyright">
          &copy; {{ currentYear }} 虹远水利工程有限公司 版权所有
        </p>
        <p class="footer__icp">
          <a href="https://beian.miit.gov.cn/" target="_blank" rel="noopener">浙ICP备2024XXXXXXXX号-1</a>
        </p>
      </div>
    </div>
  </footer>
</template>

<script setup lang="ts">
const currentYear = new Date().getFullYear()
</script>

<style scoped>
.footer {
  background: var(--color-bg-dark);
  color: rgba(255, 255, 255, 0.85);
}

.footer__grid {
  display: grid;
  grid-template-columns: 1.5fr 1fr 1.2fr;
  gap: var(--spacing-3xl);
  padding: var(--spacing-3xl) 0;
}

.footer__title {
  font-size: var(--font-size-lg);
  font-weight: 600;
  color: var(--color-white);
  margin-bottom: var(--spacing-lg);
  padding-bottom: var(--spacing-sm);
  border-bottom: 2px solid var(--color-accent);
  display: inline-block;
}

.footer__desc {
  font-size: var(--font-size-sm);
  line-height: 1.8;
  color: rgba(255, 255, 255, 0.7);
  margin-bottom: var(--spacing-lg);
}

.footer__social-label {
  font-size: var(--font-size-sm);
  color: rgba(255, 255, 255, 0.6);
}

.footer__links {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-md);
}

.footer__link {
  font-size: var(--font-size-sm);
  color: rgba(255, 255, 255, 0.7);
  text-decoration: none;
  transition: all var(--transition-fast);
  display: inline-flex;
  align-items: center;
}

.footer__link::before {
  content: '›';
  margin-right: var(--spacing-sm);
  color: var(--color-accent);
}

.footer__link:hover {
  color: var(--color-white);
  padding-left: var(--spacing-sm);
}

.footer__contact {
  display: flex;
  flex-direction: column;
  gap: var(--spacing-md);
}

.footer__contact-item {
  display: flex;
  align-items: flex-start;
  gap: var(--spacing-sm);
  font-size: var(--font-size-sm);
  color: rgba(255, 255, 255, 0.7);
  line-height: 1.6;
}

.footer__contact-icon {
  flex-shrink: 0;
  width: 20px;
  text-align: center;
}

.footer__bottom {
  border-top: 1px solid rgba(255, 255, 255, 0.1);
  padding: var(--spacing-lg) 0;
}

.footer__bottom-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.footer__copyright,
.footer__icp {
  font-size: var(--font-size-sm);
  color: rgba(255, 255, 255, 0.5);
  margin: 0;
}

.footer__icp a {
  color: rgba(255, 255, 255, 0.5);
  text-decoration: none;
}

.footer__icp a:hover {
  color: rgba(255, 255, 255, 0.8);
}

@media (max-width: 1024px) {
  .footer__grid {
    grid-template-columns: 1fr 1fr;
  }
}

@media (max-width: 768px) {
  .footer__grid {
    grid-template-columns: 1fr;
    gap: var(--spacing-2xl);
  }

  .footer__bottom-inner {
    flex-direction: column;
    gap: var(--spacing-sm);
    text-align: center;
  }
}
</style>

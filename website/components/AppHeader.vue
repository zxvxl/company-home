<template>
  <header class="header" :class="{ 'header--scrolled': isScrolled }">
    <div class="container header__inner">
      <!-- Logo -->
      <NuxtLink to="/" class="header__logo">
        <div class="header__logo-icon">
          <svg viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
            <circle cx="20" cy="20" r="18" fill="currentColor" opacity="0.1"/>
            <path d="M10 28C12 22 16 18 20 16C24 18 28 22 30 28" stroke="currentColor" stroke-width="2.5" stroke-linecap="round"/>
            <path d="M8 30H32" stroke="currentColor" stroke-width="2.5" stroke-linecap="round"/>
            <path d="M20 10V16" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
            <circle cx="20" cy="8" r="2" fill="currentColor"/>
          </svg>
        </div>
        <div class="header__logo-text">
          <span class="header__logo-name">虹远水利</span>
          <span class="header__logo-sub">HONGYUAN HYDRAULIC</span>
        </div>
      </NuxtLink>

      <!-- 桌面导航 -->
      <nav class="header__nav">
        <NuxtLink
          v-for="item in navItems"
          :key="item.path"
          :to="item.path"
          class="header__nav-item"
          :class="{ 'header__nav-item--active': isActive(item.path) }"
        >
          {{ item.label }}
        </NuxtLink>
      </nav>

      <!-- 移动端汉堡菜单 -->
      <button class="header__hamburger" @click="toggleMobile" :class="{ 'header__hamburger--open': isMobileOpen }">
        <span></span>
        <span></span>
        <span></span>
      </button>
    </div>

    <!-- 移动端抽屉菜单 -->
    <Transition name="slide">
      <div v-if="isMobileOpen" class="header__mobile">
        <nav class="header__mobile-nav">
          <NuxtLink
            v-for="item in navItems"
            :key="item.path"
            :to="item.path"
            class="header__mobile-item"
            @click="isMobileOpen = false"
          >
            {{ item.label }}
          </NuxtLink>
        </nav>
      </div>
    </Transition>
  </header>
</template>

<script setup lang="ts">
const route = useRoute()
const isScrolled = ref(false)
const isMobileOpen = ref(false)

const navItems = [
  { label: '首页', path: '/' },
  { label: '关于我们', path: '/about' },
  { label: '业务领域', path: '/business' },
  { label: '工程案例', path: '/cases' },
  { label: '新闻中心', path: '/news' },
  { label: '联系我们', path: '/contact' }
]

const isActive = (path: string) => {
  if (path === '/') return route.path === '/'
  return route.path.startsWith(path)
}

const toggleMobile = () => {
  isMobileOpen.value = !isMobileOpen.value
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})

const handleScroll = () => {
  isScrolled.value = window.scrollY > 20
}

// 路由变化时关闭移动菜单
watch(() => route.path, () => {
  isMobileOpen.value = false
})
</script>

<style scoped>
.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  height: var(--header-height);
  background: var(--color-white);
  transition: all var(--transition-normal);
}

.header--scrolled {
  box-shadow: 0 2px 20px rgba(0, 0, 0, 0.08);
}

.header__inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
}

.header__logo {
  display: flex;
  align-items: center;
  gap: 0.75rem;
  text-decoration: none;
  color: var(--color-primary);
}

.header__logo-icon {
  width: 40px;
  height: 40px;
  color: var(--color-primary);
}

.header__logo-icon svg {
  width: 100%;
  height: 100%;
}

.header__logo-text {
  display: flex;
  flex-direction: column;
}

.header__logo-name {
  font-size: 1.25rem;
  font-weight: 700;
  color: var(--color-primary);
  line-height: 1.2;
}

.header__logo-sub {
  font-size: 0.625rem;
  color: var(--color-text-light);
  letter-spacing: 1px;
  text-transform: uppercase;
}

.header__nav {
  display: flex;
  align-items: center;
  gap: 0;
}

.header__nav-item {
  padding: 0.5rem 1.25rem;
  font-size: var(--font-size-base);
  color: var(--color-text);
  font-weight: 500;
  text-decoration: none;
  position: relative;
  transition: color var(--transition-fast);
}

.header__nav-item::after {
  content: '';
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 0;
  height: 2px;
  background: var(--color-primary);
  transition: width var(--transition-normal);
}

.header__nav-item:hover {
  color: var(--color-primary);
}

.header__nav-item:hover::after,
.header__nav-item--active::after {
  width: 60%;
}

.header__nav-item--active {
  color: var(--color-primary);
}

/* 汉堡菜单 */
.header__hamburger {
  display: none;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 36px;
  height: 36px;
  background: none;
  gap: 5px;
  padding: 0;
}

.header__hamburger span {
  display: block;
  width: 24px;
  height: 2px;
  background: var(--color-text);
  transition: all var(--transition-normal);
  border-radius: 2px;
}

.header__hamburger--open span:nth-child(1) {
  transform: rotate(45deg) translate(5px, 5px);
}

.header__hamburger--open span:nth-child(2) {
  opacity: 0;
}

.header__hamburger--open span:nth-child(3) {
  transform: rotate(-45deg) translate(5px, -5px);
}

/* 移动端菜单 */
.header__mobile {
  position: fixed;
  top: var(--header-height);
  left: 0;
  right: 0;
  bottom: 0;
  background: var(--color-white);
  z-index: 999;
  overflow-y: auto;
}

.header__mobile-nav {
  display: flex;
  flex-direction: column;
  padding: var(--spacing-xl);
}

.header__mobile-item {
  display: block;
  padding: 1rem 0;
  font-size: var(--font-size-lg);
  color: var(--color-text);
  border-bottom: 1px solid var(--color-border-light);
  text-decoration: none;
  font-weight: 500;
}

.header__mobile-item:hover {
  color: var(--color-primary);
}

/* 过渡动画 */
.slide-enter-active,
.slide-leave-active {
  transition: transform var(--transition-normal), opacity var(--transition-normal);
}

.slide-enter-from,
.slide-leave-to {
  transform: translateX(100%);
  opacity: 0;
}

@media (max-width: 1024px) {
  .header__nav {
    display: none;
  }

  .header__hamburger {
    display: flex;
  }
}
</style>

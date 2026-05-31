<template>
  <header class="header" :class="{ scrolled: isScrolled }">
    <div class="container header-inner">
      <NuxtLink to="/" class="logo">
        <span class="logo-icon">&#9776;</span>
        <span class="logo-text">虹远水利</span>
      </NuxtLink>

      <nav class="nav" :class="{ open: menuOpen }">
        <NuxtLink
          v-for="item in navItems"
          :key="item.path"
          :to="item.path"
          class="nav-link"
          @click="menuOpen = false"
        >
          {{ item.label }}
        </NuxtLink>
      </nav>

      <button class="menu-toggle" @click="menuOpen = !menuOpen" aria-label="菜单">
        <span class="bar"></span>
        <span class="bar"></span>
        <span class="bar"></span>
      </button>
    </div>
  </header>
</template>

<script setup lang="ts">
const isScrolled = ref(false)
const menuOpen = ref(false)

const navItems = [
  { label: '首页', path: '/' },
  { label: '关于我们', path: '/about' },
  { label: '业务领域', path: '/business' },
  { label: '工程案例', path: '/cases' },
  { label: '新闻中心', path: '/news' },
  { label: '联系我们', path: '/contact' }
]

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})

function handleScroll() {
  isScrolled.value = window.scrollY > 50
}
</script>

<style scoped>
.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1000;
  height: var(--header-height);
  background: transparent;
  transition: all var(--transition-base);
}

.header.scrolled {
  background: rgba(26, 82, 118, 0.95);
  backdrop-filter: blur(10px);
  box-shadow: var(--shadow-md);
}

.header-inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 100%;
}

.logo {
  display: flex;
  align-items: center;
  gap: var(--space-sm);
  color: var(--color-white);
  font-size: var(--font-size-xl);
  font-weight: 700;
}

.logo-icon {
  font-size: var(--font-size-2xl);
  color: var(--color-accent);
}

.nav {
  display: flex;
  align-items: center;
  gap: var(--space-lg);
}

.nav-link {
  color: rgba(255, 255, 255, 0.9);
  font-size: var(--font-size-base);
  font-weight: 500;
  padding: var(--space-xs) var(--space-sm);
  border-radius: var(--radius-sm);
  transition: all var(--transition-fast);
  position: relative;
}

.nav-link::after {
  content: '';
  position: absolute;
  bottom: -2px;
  left: 50%;
  width: 0;
  height: 2px;
  background: var(--color-accent);
  transition: all var(--transition-base);
  transform: translateX(-50%);
}

.nav-link:hover::after,
.nav-link.router-link-active::after {
  width: 80%;
}

.nav-link:hover {
  color: var(--color-white);
}

.nav-link.router-link-active {
  color: var(--color-accent);
}

.menu-toggle {
  display: none;
  flex-direction: column;
  gap: 5px;
  padding: var(--space-sm);
}

.bar {
  display: block;
  width: 24px;
  height: 2px;
  background: var(--color-white);
  border-radius: 2px;
  transition: all var(--transition-fast);
}

@media (max-width: 768px) {
  .menu-toggle {
    display: flex;
  }

  .nav {
    position: fixed;
    top: var(--header-height);
    left: 0;
    right: 0;
    background: rgba(26, 82, 118, 0.98);
    flex-direction: column;
    padding: var(--space-xl);
    gap: var(--space-md);
    transform: translateY(-100%);
    opacity: 0;
    pointer-events: none;
    transition: all var(--transition-base);
  }

  .nav.open {
    transform: translateY(0);
    opacity: 1;
    pointer-events: all;
  }

  .nav-link {
    font-size: var(--font-size-lg);
    padding: var(--space-sm) 0;
  }
}
</style>

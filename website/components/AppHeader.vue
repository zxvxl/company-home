<template>
  <header class="header" :class="{ scrolled: isScrolled }">
    <div class="container header-inner">
      <NuxtLink to="/" class="logo">
        <span class="logo-icon">&#9776;</span>
        <span class="logo-text">虹远水利</span>
      </NuxtLink>

      <nav class="nav" :class="{ open: menuOpen }">
        <template v-for="item in navItems" :key="item.path || item.label">
          <!-- 有子菜单的导航项 -->
          <div v-if="item.children" class="nav-dropdown">
            <span class="nav-link dropdown-trigger" @click="toggleDropdown(item.label)">
              {{ item.label }}
              <span class="dropdown-arrow">▾</span>
            </span>
            <div class="dropdown-menu" :class="{ show: activeDropdown === item.label }">
              <NuxtLink
                v-for="child in item.children"
                :key="child.path"
                :to="child.path"
                class="dropdown-item"
                @click="closeAll"
              >
                {{ child.label }}
              </NuxtLink>
            </div>
          </div>

          <!-- 普通导航项 -->
          <NuxtLink
            v-else
            :to="item.path"
            class="nav-link"
            @click="closeAll"
          >
            {{ item.label }}
          </NuxtLink>
        </template>
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
interface NavChild {
  label: string
  path: string
}

interface NavItem {
  label: string
  path?: string
  children?: NavChild[]
}

const isScrolled = ref(false)
const menuOpen = ref(false)
const activeDropdown = ref<string | null>(null)

const navItems: NavItem[] = [
  { label: '首页', path: '/' },
  {
    label: '关于我们',
    children: [
      { label: '公司简介', path: '/about' },
      { label: '资质荣誉', path: '/qualifications' },
      { label: '安全生产', path: '/safety' }
    ]
  },
  { label: '业务领域', path: '/business' },
  { label: '工程案例', path: '/cases' },
  { label: '新闻中心', path: '/news' },
  { label: '人才招聘', path: '/careers' },
  { label: '联系我们', path: '/contact' }
]

function toggleDropdown(label: string) {
  activeDropdown.value = activeDropdown.value === label ? null : label
}

function closeAll() {
  menuOpen.value = false
  activeDropdown.value = null
}

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
  document.addEventListener('click', handleClickOutside)
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
  document.removeEventListener('click', handleClickOutside)
})

function handleScroll() {
  isScrolled.value = window.scrollY > 50
}

function handleClickOutside(e: Event) {
  const target = e.target as HTMLElement
  if (!target.closest('.nav-dropdown')) {
    activeDropdown.value = null
  }
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
  cursor: pointer;
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

/* Dropdown */
.nav-dropdown {
  position: relative;
}

.dropdown-trigger {
  display: flex;
  align-items: center;
  gap: 4px;
  user-select: none;
}

.dropdown-arrow {
  font-size: 0.75rem;
  transition: transform var(--transition-fast);
}

.dropdown-menu {
  position: absolute;
  top: 100%;
  left: 50%;
  transform: translateX(-50%) translateY(10px);
  min-width: 140px;
  background: rgba(26, 82, 118, 0.98);
  backdrop-filter: blur(10px);
  border-radius: var(--radius-md);
  padding: var(--space-sm) 0;
  opacity: 0;
  visibility: hidden;
  transition: all var(--transition-fast);
  box-shadow: var(--shadow-lg);
}

.nav-dropdown:hover .dropdown-menu,
.dropdown-menu.show {
  opacity: 1;
  visibility: visible;
  transform: translateX(-50%) translateY(4px);
}

.dropdown-item {
  display: block;
  padding: var(--space-sm) var(--space-lg);
  color: rgba(255, 255, 255, 0.85);
  font-size: var(--font-size-sm);
  white-space: nowrap;
  transition: all var(--transition-fast);
}

.dropdown-item:hover {
  color: var(--color-accent);
  background: rgba(255, 255, 255, 0.05);
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

  .dropdown-menu {
    position: static;
    transform: none;
    background: rgba(255, 255, 255, 0.05);
    border-radius: var(--radius-sm);
    margin-top: var(--space-sm);
    padding: var(--space-xs) 0;
    box-shadow: none;
  }

  .nav-dropdown:hover .dropdown-menu {
    transform: none;
  }

  .dropdown-menu.show {
    transform: none;
  }

  .dropdown-item {
    padding-left: var(--space-xl);
  }
}
</style>

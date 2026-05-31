<template>
  <section class="hero">
    <div class="hero__slides">
      <TransitionGroup name="fade">
        <div
          v-for="(slide, index) in slides"
          :key="index"
          v-show="currentIndex === index"
          class="hero__slide"
          :style="{ backgroundImage: `url(${slide.imageUrl || defaultBg})` }"
        >
          <div class="hero__overlay"></div>
          <div class="container hero__content">
            <h1 class="hero__title">{{ slide.title }}</h1>
            <p class="hero__subtitle">{{ slide.subtitle }}</p>
            <div class="hero__actions" v-if="slide.linkUrl">
              <NuxtLink :to="slide.linkUrl" class="btn btn--accent btn--lg">
                了解详情
                <span class="hero__arrow">&rarr;</span>
              </NuxtLink>
            </div>
          </div>
        </div>
      </TransitionGroup>
    </div>

    <!-- 左右箭头 -->
    <button class="hero__arrow-btn hero__arrow-btn--prev" @click="prev" aria-label="上一张">
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
        <polyline points="15,18 9,12 15,6"/>
      </svg>
    </button>
    <button class="hero__arrow-btn hero__arrow-btn--next" @click="next" aria-label="下一张">
      <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
        <polyline points="9,6 15,12 9,18"/>
      </svg>
    </button>

    <!-- 圆点指示器 -->
    <div class="hero__dots">
      <button
        v-for="(_, index) in slides"
        :key="index"
        class="hero__dot"
        :class="{ 'hero__dot--active': currentIndex === index }"
        @click="goTo(index)"
        :aria-label="`跳转到第${index + 1}张`"
      ></button>
    </div>
  </section>
</template>

<script setup lang="ts">
interface Slide {
  title: string
  subtitle: string
  imageUrl?: string
  linkUrl?: string
}

const props = withDefaults(defineProps<{
  slides: Slide[]
  interval?: number
}>(), {
  interval: 5000
})

const currentIndex = ref(0)
let timer: ReturnType<typeof setInterval> | null = null

const defaultBg = 'data:image/svg+xml,' + encodeURIComponent(`
  <svg width="1920" height="600" xmlns="http://www.w3.org/2000/svg">
    <defs>
      <linearGradient id="bg" x1="0%" y1="0%" x2="100%" y2="100%">
        <stop offset="0%" style="stop-color:#1a5276"/>
        <stop offset="100%" style="stop-color:#2980b9"/>
      </linearGradient>
    </defs>
    <rect width="1920" height="600" fill="url(#bg)"/>
  </svg>
`)

const next = () => {
  currentIndex.value = (currentIndex.value + 1) % props.slides.length
}

const prev = () => {
  currentIndex.value = (currentIndex.value - 1 + props.slides.length) % props.slides.length
}

const goTo = (index: number) => {
  currentIndex.value = index
}

const startAutoPlay = () => {
  if (timer) clearInterval(timer)
  timer = setInterval(next, props.interval)
}

const stopAutoPlay = () => {
  if (timer) {
    clearInterval(timer)
    timer = null
  }
}

onMounted(() => {
  if (props.slides.length > 1) {
    startAutoPlay()
  }
})

onUnmounted(() => {
  stopAutoPlay()
})
</script>

<style scoped>
.hero {
  position: relative;
  height: 100vh;
  min-height: 500px;
  max-height: 800px;
  overflow: hidden;
}

.hero__slides {
  position: relative;
  width: 100%;
  height: 100%;
}

.hero__slide {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
}

.hero__overlay {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, rgba(26, 82, 118, 0.85) 0%, rgba(41, 128, 185, 0.6) 100%);
}

.hero__content {
  position: relative;
  z-index: 2;
  color: var(--color-white);
  max-width: 700px;
}

.hero__title {
  font-size: clamp(2rem, 5vw, 3.5rem);
  font-weight: 700;
  margin-bottom: var(--spacing-lg);
  color: var(--color-white);
  line-height: 1.2;
  text-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
}

.hero__subtitle {
  font-size: clamp(1rem, 2vw, 1.25rem);
  opacity: 0.9;
  margin-bottom: var(--spacing-2xl);
  line-height: 1.8;
}

.hero__actions {
  display: flex;
  gap: var(--spacing-md);
}

.hero__arrow {
  display: inline-block;
  transition: transform var(--transition-fast);
}

.btn:hover .hero__arrow {
  transform: translateX(4px);
}

/* 箭头按钮 */
.hero__arrow-btn {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 48px;
  height: 48px;
  border-radius: var(--radius-full);
  background: rgba(255, 255, 255, 0.15);
  backdrop-filter: blur(4px);
  color: var(--color-white);
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all var(--transition-normal);
  z-index: 10;
}

.hero__arrow-btn svg {
  width: 24px;
  height: 24px;
}

.hero__arrow-btn:hover {
  background: rgba(255, 255, 255, 0.3);
  transform: translateY(-50%) scale(1.1);
}

.hero__arrow-btn--prev {
  left: 2rem;
}

.hero__arrow-btn--next {
  right: 2rem;
}

/* 圆点指示器 */
.hero__dots {
  position: absolute;
  bottom: 2rem;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 0.75rem;
  z-index: 10;
}

.hero__dot {
  width: 12px;
  height: 12px;
  border-radius: var(--radius-full);
  background: rgba(255, 255, 255, 0.4);
  transition: all var(--transition-normal);
  padding: 0;
}

.hero__dot--active {
  background: var(--color-white);
  width: 32px;
  border-radius: 6px;
}

/* 过渡动画 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.8s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@media (max-width: 768px) {
  .hero {
    height: 60vh;
    min-height: 400px;
  }

  .hero__arrow-btn {
    width: 36px;
    height: 36px;
  }

  .hero__arrow-btn svg {
    width: 18px;
    height: 18px;
  }

  .hero__arrow-btn--prev {
    left: 1rem;
  }

  .hero__arrow-btn--next {
    right: 1rem;
  }
}
</style>

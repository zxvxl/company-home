<template>
  <div class="stat-counter" ref="counterRef">
    <div class="stat-counter__number">
      <span class="stat-counter__value">{{ displayValue }}</span>
      <span class="stat-counter__suffix">{{ suffix }}</span>
    </div>
    <p class="stat-counter__label">{{ label }}</p>
  </div>
</template>

<script setup lang="ts">
const props = withDefaults(defineProps<{
  targetNumber: number
  label: string
  suffix?: string
  duration?: number
}>(), {
  suffix: '+',
  duration: 2000
})

const counterRef = ref<HTMLElement | null>(null)
const displayValue = ref(0)
const hasAnimated = ref(false)

const animateCounter = () => {
  if (hasAnimated.value) return
  hasAnimated.value = true

  const start = 0
  const end = props.targetNumber
  const startTime = performance.now()

  const easeOutQuart = (t: number) => 1 - Math.pow(1 - t, 4)

  const update = (currentTime: number) => {
    const elapsed = currentTime - startTime
    const progress = Math.min(elapsed / props.duration, 1)
    const easedProgress = easeOutQuart(progress)

    displayValue.value = Math.round(start + (end - start) * easedProgress)

    if (progress < 1) {
      requestAnimationFrame(update)
    }
  }

  requestAnimationFrame(update)
}

onMounted(() => {
  if (!counterRef.value) return

  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          animateCounter()
          observer.disconnect()
        }
      })
    },
    { threshold: 0.3 }
  )

  observer.observe(counterRef.value)
})
</script>

<style scoped>
.stat-counter {
  text-align: center;
  padding: var(--spacing-xl);
}

.stat-counter__number {
  display: flex;
  align-items: baseline;
  justify-content: center;
  gap: 0.25rem;
  margin-bottom: var(--spacing-sm);
}

.stat-counter__value {
  font-size: clamp(2.5rem, 5vw, 3.5rem);
  font-weight: 800;
  color: var(--color-accent);
  line-height: 1;
}

.stat-counter__suffix {
  font-size: var(--font-size-2xl);
  font-weight: 700;
  color: var(--color-accent);
}

.stat-counter__label {
  font-size: var(--font-size-base);
  color: rgba(255, 255, 255, 0.85);
  margin: 0;
  font-weight: 500;
}
</style>

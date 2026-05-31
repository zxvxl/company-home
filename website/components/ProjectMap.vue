<template>
  <section class="section project-map-section">
    <div class="container">
      <h2 class="section-title">项目分布</h2>
      <p class="section-subtitle">工程足迹遍布云南省各州市</p>

      <div class="map-wrapper">
        <!-- 简化版云南省地图 SVG -->
        <div class="map-container">
          <svg
            viewBox="0 0 600 500"
            class="yunnan-map"
            xmlns="http://www.w3.org/2000/svg"
          >
            <!-- 云南省轮廓（简化） -->
            <path
              class="map-outline"
              d="M120,80 L180,60 L250,50 L320,55 L380,45 L440,60 L500,80 L530,120 L540,170 L520,220 L530,270 L510,320 L480,360 L450,390 L400,420 L350,440 L300,450 L250,440 L200,420 L160,380 L130,340 L100,290 L80,240 L90,190 L100,140 Z"
              fill="rgba(26, 82, 118, 0.08)"
              stroke="#1a5276"
              stroke-width="2"
            />

            <!-- 项目标注点 -->
            <g
              v-for="project in projects"
              :key="project.name"
              class="map-point-group"
              @mouseenter="activeProject = project"
              @mouseleave="activeProject = null"
            >
              <circle
                :cx="project.x"
                :cy="project.y"
                r="8"
                class="map-point"
              />
              <circle
                :cx="project.x"
                :cy="project.y"
                r="14"
                class="map-point-pulse"
              />
              <text
                :x="project.x"
                :y="project.y + 24"
                class="map-label"
                text-anchor="middle"
              >
                {{ project.city }}
              </text>
            </g>
          </svg>

          <!-- Hover 提示框 -->
          <Transition name="fade">
            <div
              v-if="activeProject"
              class="map-tooltip"
              :style="{
                left: `${(activeProject.x / 600) * 100}%`,
                top: `${(activeProject.y / 500) * 100 - 12}%`
              }"
            >
              <h4>{{ activeProject.name }}</h4>
              <p>{{ activeProject.city }} · {{ activeProject.type }}</p>
            </div>
          </Transition>
        </div>

        <!-- 项目列表 -->
        <div class="project-list">
          <div
            v-for="project in projects"
            :key="project.name"
            class="project-item"
            :class="{ active: activeProject?.name === project.name }"
            @mouseenter="activeProject = project"
            @mouseleave="activeProject = null"
          >
            <div class="project-dot"></div>
            <div class="project-info">
              <h4>{{ project.name }}</h4>
              <p>{{ project.city }} · {{ project.type }}</p>
            </div>
          </div>
        </div>
      </div>

      <!-- 底部统计 -->
      <div class="map-stats">
        <p class="map-stats-text">
          项目遍布云南省 <strong>16</strong> 个州市，累计完成工程 <strong>500+</strong> 个
        </p>
      </div>
    </div>
  </section>
</template>

<script setup lang="ts">
interface MapProject {
  name: string
  city: string
  type: string
  x: number
  y: number
}

const activeProject = ref<MapProject | null>(null)

const projects: MapProject[] = [
  { name: '清风水库除险加固工程', city: '玉溪', type: '水库大坝', x: 310, y: 320 },
  { name: '盘江城区段综合治理工程', city: '曲靖', type: '河道治理', x: 380, y: 180 },
  { name: '万亩高标准农田节水灌溉', city: '红河', type: '农田灌溉', x: 350, y: 380 },
  { name: '龙潭河防洪工程', city: '楚雄', type: '防洪排涝', x: 240, y: 250 },
  { name: '城乡一体化供水工程', city: '大理', type: '供水工程', x: 170, y: 220 },
  { name: '小流域水土保持治理工程', city: '昭通', type: '水土保持', x: 400, y: 90 }
]
</script>

<style scoped>
.project-map-section {
  background: var(--color-gray-50);
}

.map-wrapper {
  display: grid;
  grid-template-columns: 2fr 1fr;
  gap: var(--space-2xl);
  align-items: center;
  margin-top: var(--space-2xl);
}

.map-container {
  position: relative;
  background: var(--color-white);
  border-radius: var(--radius-xl);
  padding: var(--space-xl);
  border: 1px solid var(--color-gray-200);
  box-shadow: var(--shadow-sm);
}

.yunnan-map {
  width: 100%;
  height: auto;
}

.map-outline {
  transition: all var(--transition-base);
}

.map-point {
  fill: #e67e22;
  stroke: var(--color-white);
  stroke-width: 2;
  cursor: pointer;
  transition: all var(--transition-fast);
}

.map-point-pulse {
  fill: rgba(230, 126, 34, 0.3);
  animation: pulse 2s infinite;
}

.map-point-group:hover .map-point {
  fill: #1a5276;
  r: 10;
}

.map-label {
  font-size: 11px;
  fill: var(--color-primary);
  font-weight: 500;
}

@keyframes pulse {
  0% {
    r: 8;
    opacity: 0.8;
  }
  100% {
    r: 20;
    opacity: 0;
  }
}

/* Tooltip */
.map-tooltip {
  position: absolute;
  transform: translate(-50%, -100%);
  background: var(--color-primary);
  color: var(--color-white);
  padding: var(--space-sm) var(--space-md);
  border-radius: var(--radius-md);
  white-space: nowrap;
  pointer-events: none;
  z-index: 10;
}

.map-tooltip h4 {
  font-size: var(--font-size-sm);
  font-weight: 600;
  margin-bottom: 2px;
}

.map-tooltip p {
  font-size: var(--font-size-xs);
  opacity: 0.8;
}

.map-tooltip::after {
  content: '';
  position: absolute;
  bottom: -6px;
  left: 50%;
  transform: translateX(-50%);
  border-left: 6px solid transparent;
  border-right: 6px solid transparent;
  border-top: 6px solid var(--color-primary);
}

/* Project List */
.project-list {
  display: flex;
  flex-direction: column;
  gap: var(--space-md);
}

.project-item {
  display: flex;
  align-items: center;
  gap: var(--space-md);
  padding: var(--space-md) var(--space-lg);
  background: var(--color-white);
  border: 1px solid var(--color-gray-200);
  border-radius: var(--radius-md);
  cursor: pointer;
  transition: all var(--transition-base);
}

.project-item:hover,
.project-item.active {
  border-color: var(--color-accent);
  box-shadow: var(--shadow-sm);
}

.project-item.active {
  background: rgba(230, 126, 34, 0.05);
}

.project-dot {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background: #e67e22;
  flex-shrink: 0;
}

.project-info h4 {
  font-size: var(--font-size-sm);
  color: var(--text-primary);
  margin-bottom: 2px;
}

.project-info p {
  font-size: var(--font-size-xs);
  color: var(--text-secondary);
}

/* Stats */
.map-stats {
  text-align: center;
  margin-top: var(--space-2xl);
  padding: var(--space-xl);
  background: var(--color-white);
  border-radius: var(--radius-lg);
  border: 1px solid var(--color-gray-200);
}

.map-stats-text {
  font-size: var(--font-size-lg);
  color: var(--text-secondary);
}

.map-stats-text strong {
  color: var(--color-primary);
  font-size: var(--font-size-2xl);
}

/* Transitions */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

@media (max-width: 1024px) {
  .map-wrapper {
    grid-template-columns: 1fr;
  }

  .project-list {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 768px) {
  .project-list {
    grid-template-columns: 1fr;
  }
}
</style>

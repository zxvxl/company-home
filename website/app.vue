<template>
  <div id="app">
    <AppHeader />
    <main class="main-content">
      <NuxtPage />
    </main>
    <AppFooter />
  </div>
</template>

<script setup lang="ts">
useHead({
  titleTemplate: (title) => title ? `${title} - 虹远水利工程有限公司` : '虹远水利工程有限公司'
})
</script>

<style scoped>
.main-content {
  min-height: calc(100vh - var(--header-height) - 300px);
  padding-top: var(--header-height);
}
</style>

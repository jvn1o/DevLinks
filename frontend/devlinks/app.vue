<script setup lang="ts">
  import 'bootstrap/dist/css/bootstrap.min.css';
  import 'bootstrap-icons/font/bootstrap-icons.css';
</script>

<template>
  <div class="app-wrapper">
    <NuxtLayout>
      <div class="page-container">
        <NuxtPage />
      </div>
    </NuxtLayout>
  </div>
</template>

<style scoped>
.app-wrapper {
  min-height: 100vh; /* 전체 화면 높이 */
  display: flex;
  flex-direction: column;
}

.page-container {
  min-height: 600px; /* 원하는 최소 높이(px, vh 등으로 설정 가능) */
  padding: 1rem;
}
</style>
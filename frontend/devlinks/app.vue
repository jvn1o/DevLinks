<script setup lang="ts">
  import 'bootstrap/dist/css/bootstrap.min.css';
  import 'bootstrap-icons/font/bootstrap-icons.css';
</script>

<template>
  <div class="app-wrapper">
    <NuxtLayout>
      <div class="container-fluid page-container">
        <NuxtPage />
      </div>
    </NuxtLayout>
  </div>
</template>

<style>
html, body {
  width: 100%;
  min-width: 390px; /* 최소 너비 보장 */
  height: 100%; /* 화면의 높이를 100%로 설정 */
  margin: 0;
  padding: 0;
}

.app-wrapper {
  display: flex;
  flex-direction: column;
  width: 100%; /* 부모 요소에 width 100% 적용 */
  min-width: 390px; /* 최소 너비 보장 */
  height: 100%; /* 전체 높이 100% */
}

.page-container {
  flex-grow: 1; /* 페이지 컨텐츠가 남은 공간을 차지하도록 설정 */
  min-height: 600px; /* 최소 높이 보장 */
  padding: 1rem;
}
</style>
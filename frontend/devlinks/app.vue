<script setup lang="ts">
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
</script>

<template>
  <div class="app-wrapper">
    <NuxtLayout>
      <div class="container page-container">
        <NuxtPage />
      </div>
    </NuxtLayout>
  </div>
</template>

<style>
/* html과 body에 대한 최소 너비 및 높이 강제 설정 */
html, body {
  width: 100%; /* 화면 너비 100% */
  min-width: 390px; /* 최소 너비 390px 강제 */
  height: 100%; /* 화면 높이 100% */
  margin: 0;
  padding: 0;
  overflow-x: hidden; /* 수평 스크롤 방지 */
}

/* 앱 래퍼의 너비와 높이 강제 설정 */
.app-wrapper {
  display: flex;
  flex-direction: column;
  width: 100%; /* 부모의 너비 100% */
  min-width: 390px; /* 최소 너비 강제 */
  max-width: 100%; /* 최대 너비를 100%로 설정 */
  height: 100%; /* 높이 100% */
  overflow-x: hidden; /* 수평 스크롤 방지 */
}

/* 페이지 컨테이너 스타일 설정 */
.page-container {
  padding: 1rem;
  min-height: 600px; /* 최소 높이 설정 */
  flex-grow: 1; /* 남은 공간을 채우도록 설정 */
  flex-shrink: 0; /* 축소되지 않도록 설정 */
}
</style>

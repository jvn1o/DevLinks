<template>
  <nav
      :class="{
      'navbar-dark bg-dark': darkMode,
      'navbar-light bg-light': !darkMode
    }"
      class="navbar p-0 w-auto"
  >
    <!-- 우측 아이콘 (북마크, 프로필) -->
    <div class="d-flex justify-content-center align-items-center p-2" style="border-right: 1px solid #CED3D9 !important;">
      <div class="w-32 h-32 p-1">
        <NuxtLink to="/signin">
          <img src="/assets/image/icon/profile.svg" alt="Profile" width="32" height="32" class="rounded-circle"/>
        </NuxtLink>
      </div>
      <div class="w-25 h-25 p-1">
        <NuxtLink to="/mypage/bookmark" class="me-3">
          <img src="/assets/image/icon/bookmark.svg" alt="Bookmark" width="24" height="24"/>
        </NuxtLink>
      </div>
      <div class="w-25 h-25 p-1">
        <NuxtLink to="/mypage/alarm" class="me-3">
          <img src="/assets/image/icon/alarm.svg" alt="Alarm" width="24" height="24"/>
        </NuxtLink>
      </div>
    </div>

    <!-- 로고 (홈 이동) -->
    <div class="d-flex align-items-stretch ps-3 border-end" style="height: 56px;">
      <NuxtLink to="/" class="navbar-brand d-flex align-items-center">
        <img src="/assets/image/DevLinks.svg" alt="Logo" width="28" height="32" />
      </NuxtLink>
    </div>

    <!-- 네비게이션 탭 -->
    <div class="container-fluid p-0">

      <!-- 햄버거 버튼: 모바일일 때만 표시 -->
      <button
          v-if="isMobileDevice"
          class="navbar-toggler custom-toggler ps-3"
          style="border: none; padding: 4px 6px;"
          type="button"
          @click="toggleSidebar"
      >
        <img src="/image/icon/hamburger.svg" alt="Menu" width="32" height="32" />
      </button>

      <!-- 네비게이션 탭: 항상 렌더링되게, 조건부 렌더링 X -->
      <div v-if="!isMobileDevice" class="ps-2">
        <ul class="navbar-nav d-flex flex-row">
          <li v-for="(tab, index) in tabs" :key="index" class="nav-item px-2">
            <NuxtLink :to="`/category/${slugify(tab)}`" class="nav-link fw-bold text-primary">
              {{ tab }}
            </NuxtLink>
          </li>
        </ul>
      </div>

    </div>

    <!-- 사이드바 -->
    <Sidebar
        v-if="isMobileDevice"
        :isSidebarOpen="isSidebarOpen"
        :toggleSidebar="toggleSidebar"
        :tabs="tabs"
        :slugify="slugify"
    />

    <!-- 글 작성 -->
    <NuxtLink to="/" class="ms-auto navbar-brand">
      <img src="/assets/image/icon/write.svg" alt="Logo" width="90" height="20"/>
    </NuxtLink>
  </nav>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useWindowSize } from '@vueuse/core'
import Sidebar from './Sidebar.vue'
import { defineProps } from 'vue';

const props = defineProps({
  darkMode: Boolean,
});

const { width } = useWindowSize()
const isMobileDevice = computed(() => width.value < 1100)

// ✅ 모든 특수문자와 공백 등을 안전한 slug로 변환하는 함수
const slugify = (text) => {
  return text
      .toLowerCase()
      .replace(/ & /g, '-')   // 의미 보존 (예: API & Docs → api-and-docs)
      .replace(/ \/ /g, '-')   // 슬래시 제거 (예: UI / UX → ui-ux)
      .replace(/[^\w\-]+/g, '-')  // 나머지 특수문자 제거
      .replace(/\-\-+/g, '-')     // 연속된 하이픈 정리
      .replace(/^-+|-+$/g, '');   // 양쪽 끝 하이픈 제거
}

const tabs = [
  "Algorithm & Data Structures",
  "API & Documentation",
  "Cloud & DevOps",
  "Testing & QA",
  "UI / UX"
];

const isSidebarOpen = ref(false)
const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value
}
</script>

<style scoped>
.navbar-dark {
  background-color: #343a40 !important;
}

.navbar-light {
  background-color: #f8f9fa !important;
}

.navbar {
  border-bottom: 1px solid #CED3D9;
  box-shadow: 0px 2px 4px rgba(206, 211, 217, 0.2); /* 부드러운 그림자 효과 */
  flex-wrap: nowrap;
}

.navbar-nav .nav-link {
  font-weight: 100;
  color: #333;
  transition: color 0.3s;
  padding-right: 15px;
}

.navbar-nav .nav-link:hover {
  color: #007bff;
}

.navbar-brand img {
  cursor: pointer;
}

.d-flex img {
  cursor: pointer;
}

/* 햄버거 버튼 클릭 시 테두리 제거 */
.navbar-toggler:focus {
  outline: none;
  box-shadow: none;
}

</style>

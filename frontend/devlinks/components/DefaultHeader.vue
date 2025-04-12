<template>
  <nav class="navbar navbar-expand-xl navbar-light bg-light p-0">
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


    <!--  네비게이션 탭  -->
    <div class="container-fluid p-0">

      <!-- 햄버거 버튼 -->
      <button
          class="navbar-toggler custom-toggler ps-3"
          style="border: none; padding: 4px 6px;"
          type="button"
          @click="toggleSidebar"
      >
        <img src="/image/icon/hamburger.svg" alt="Menu" width="32" height="32" />
      </button>

      <!-- width가 넓을 때 네비게이션 탭 -->
      <div class="collapse navbar-collapse ps-2" id="navbarNav">
        <ul class="navbar-nav">
          <li v-for="(tab, index) in tabs" :key="index" class="nav-item">
            <NuxtLink :to="`/category/${tab.toLowerCase().replace(/ /g, '-')}`" class="nav-link">
              {{ tab }}
            </NuxtLink>
          </li>
        </ul>
      </div>
    </div>


    <!-- 사이드바 -->
    <aside
        class="sidebar bg-white shadow position-fixed top-0 start-0 h-100 p-3"
        :class="{ 'show': isSidebarOpen }"
    >
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h5 class="m-3">Category</h5>
        <button class="btn-close" @click="toggleSidebar"></button>
      </div>
      <ul class="nav flex-column">
        <li class="nav-item p-2" v-for="(tab, index) in tabs" :key="index">
          <NuxtLink :to="`/category/${tab.toLowerCase().replace(/ /g, '-')}`" class="nav-link">
            {{ tab }}
          </NuxtLink>
        </li>
      </ul>
    </aside>


    <!-- 오버레이 배경 -->
    <div
        class="sidebar-overlay"
        v-if="isSidebarOpen"
        @click="toggleSidebar"
    ></div>


    <!-- 글 작성 -->
    <NuxtLink to="/" class="navbar-brand">
      <img src="/assets/image/icon/write.svg" alt="Logo" width="90" height="20"/>
    </NuxtLink>
  </nav>
</template>

<script setup>
import { ref } from 'vue'

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

/* 사이드바 위치 및 애니메이션 */
.sidebar {
  width: 60%;
  transform: translateX(-100%);
  transition: transform 0.3s ease-in-out;
  z-index: 1050; /* modal보다 낮고 dropdown보다 높게 */
}
.sidebar.show {
  transform: translateX(0);
}

/* 사이드바 내 네비게이션 항목 텍스트 크기 */
.sidebar .nav-link {
  display: inline;
  font-size: 1.4rem; /* 18px 정도 */
  font-weight: 500;
}

/* 배경 어둡게 처리 */
.sidebar-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: 1040;
}
</style>

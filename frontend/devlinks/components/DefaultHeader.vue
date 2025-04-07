<template>
  <nav class="navbar navbar-expand-xl navbar-light bg-light p-0">
    <!-- 우측 아이콘 (북마크, 프로필) -->
    <div class="d-flex justify-content-center align-items-center p-2">
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
    <div class="d-flex p-0">
      <NuxtLink to="/" class="navbar-brand">
        <img src="/assets/image/DevLinks.svg" alt="Logo" width="28" height="28"/>
      </NuxtLink>
    </div>

    <!--  네비게이션 탭  -->
    <div class="container-fluid p-0">
      <!-- width가 줄어들 때 햄버거 버튼 -->
      <button class="navbar-toggler custom-toggler" style="border: none; padding: 4px 6px;" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <img src="/image/icon/hamburger.svg" alt="Menu" width="32" height="32" />
      </button>

      <!-- width가 넓을 때 네비게이션 탭 -->
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li v-for="(tab, index) in tabs" :key="index" class="nav-item">
            <NuxtLink :to="`/${tab.toLowerCase().replace(/ /g, '-')}`" class="nav-link">
              {{ tab }}
            </NuxtLink>
          </li>
        </ul>
      </div>
    </div>


    <!-- 글 작성 -->
    <NuxtLink to="/" class="navbar-brand">
      <img src="/assets/image/icon/write.svg" alt="Logo" width="90" height="20"/>
    </NuxtLink>
  </nav>
</template>

<script setup>
const tabs = [
  "Algorithm & Data Structures",
  "API & Documentation",
  "Cloud & DevOps",
  "Testing & QA",
  "UI / UX"
];
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
</style>

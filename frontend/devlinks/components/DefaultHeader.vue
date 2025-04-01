<script setup>
const tabs = ["Algorithm & Data Structures", "API & Documentation", "Cloud & DevOps", "Testing & QA", "UI / UX"];
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="w-137">
      <!-- 우측 아이콘 (북마크, 프로필) -->
      <div class="d-flex align-items-center">
        <NuxtLink to="/login">
          <img src="/assets/image/icon/profile.svg" alt="Profile" width="32" height="32" class="rounded-circle"/>
        </NuxtLink>
        <NuxtLink to="/mypage/bookmark" class="me-3">
          <img src="/assets/image/icon/bookmark.svg" alt="Bookmark" width="24" height="24"/>
        </NuxtLink>
        <NuxtLink to="/mypage/bookmark" class="me-3">
          <img src="/assets/image/icon/bookmark.svg" alt="Bookmark" width="24" height="24"/>
        </NuxtLink>
      </div>
    </div>

    <div class="container">
      <!-- 로고 (홈 이동) -->
      <NuxtLink to="/" class="navbar-brand">
        <img src="/assets/image/DevLinks.svg" alt="Logo" width="40" height="40"/>
      </NuxtLink>

      <!-- 네비게이션 메뉴 -->
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav mx-auto">
          <li v-for="(tab, index) in tabs" :key="index" class="nav-item">
            <NuxtLink :to="`/${tab.toLowerCase().replace(/ /g, '-')}`" class="nav-link">
              {{ tab }}
            </NuxtLink>
          </li>
        </ul>
      </div>
    </div>

    <!-- 로고 (홈 이동) -->
    <NuxtLink to="/" class="navbar-brand">
      <img src="/assets/image/DevLinks.svg" alt="Logo" width="40" height="40"/>
    </NuxtLink>
  </nav>
</template>

<style scoped>
.navbar-nav .nav-link {
  font-weight: 500;
  color: #333;
  transition: color 0.3s;
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

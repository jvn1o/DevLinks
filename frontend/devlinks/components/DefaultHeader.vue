<script setup>
const tabs = ["Algorithm & Data Structures", "API & Documentation", "Cloud & DevOps", "Testing & QA", "UI / UX"];
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
      <!-- 로고 (홈 이동) -->
      <NuxtLink to="/" class="navbar-brand">
        <img src="/logo.png" alt="Logo" width="40" height="40" />
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

      <!-- 우측 아이콘 (북마크, 프로필) -->
      <div class="d-flex align-items-center">
        <NuxtLink to="/mypage/bookmark" class="me-3">
          <img src="/bookmark-icon.png" alt="Bookmark" width="24" height="24" />
        </NuxtLink>
        <NuxtLink to="/login">
          <img src="/profile-icon.png" alt="Profile" width="32" height="32" class="rounded-circle" />
        </NuxtLink>
      </div>
    </div>
  </nav>
</template>

<style scoped>
/* Bootstrap 사용 시 최소한의 스타일 조정 */
.bi {
  cursor: pointer;
}
</style>

<script setup>
const tabs = ["Algorithm & Data Structures", "API & Documentation", "Cloud & DevOps", "Testing & QA", "UI / UX"];
const screenWidth = ref(0); // 초기값을 0으로 설정

const updateScreenWidth = () => {
  if (typeof window !== "undefined") {
    screenWidth.value = window.innerWidth;
  }
};

onMounted(() => {
  updateScreenWidth(); // 컴포넌트 마운트 후 window.innerWidth 설정
  window.addEventListener("resize", updateScreenWidth);
});

onUnmounted(() => {
  window.removeEventListener("resize", updateScreenWidth);
});
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light p-0">
    <!-- 우측 아이콘 (북마크, 프로필) -->
    <div class="d-flex justify-content-center align-items-center p-2">
      <div class="w-32 h-32 p-1">
        <NuxtLink to="/login">
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

    <div class="container p-0 w-max-content">
      <!-- 로고 (홈 이동) -->
      <div class="d-flex p-0">
        <NuxtLink to="/" class="navbar-brand">
          <img src="/assets/image/DevLinks.svg" alt="Logo" width="32" height="32"/>
        </NuxtLink>
      </div>

      <!-- 네비게이션 메뉴 -->
      <div v-if="screenWidth > 390" class="collapse navbar-collapse">
        <ul class="navbar-nav">
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
      <img src="/assets/image/icon/write.svg" alt="Logo" width="90" height="20"/>
    </NuxtLink>
  </nav>
</template>

<style scoped>
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

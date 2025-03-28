<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();

// 클릭 이벤트 예제
const goToProfile = () => {
  router.push('/profile');
};
</script>

<template>
  <header class="d-flex justify-content-between align-items-center px-3 py-2 border-bottom bg-white">
    <!-- 로고 -->
    <NuxtLink to="/" class="d-flex align-items-center text-decoration-none">
      <img src="/assets/image/DevLinks.svg" alt="DevLinks" height="30">
    </NuxtLink>

    <!-- 우측 아이콘 & 버튼 -->
    <div class="d-flex align-items-center gap-3">
      <button class="btn btn-outline-primary btn-sm">Write a Post</button>
      <i class="bi bi-bookmark fs-5"></i>
      <i class="bi bi-gear fs-5"></i>
      <img src="/assets/image/DevLinks.svg" alt="Profile" class="rounded-circle" width="32" height="32" @click="goToProfile">
    </div>
  </header>
</template>

<style scoped>
/* Bootstrap 사용 시 최소한의 스타일 조정 */
.bi {
  cursor: pointer;
}
</style>

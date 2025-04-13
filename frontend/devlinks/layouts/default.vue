<script setup lang="ts">
import { ref, onMounted } from 'vue';
import Header from '~/components/DefaultHeader.vue';
import Footer from '~/components/DefaultFooter.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';

const darkMode = ref(false);

// onMounted 훅을 사용하여 클라이언트에서만 접근
onMounted(() => {
  if (typeof window !== 'undefined') { // process.client를 사용하지 않고 window 객체로 확인
    darkMode.value = localStorage.getItem('darkMode') === 'true';
    // 다크 모드 상태에 따라 documentElement 클래스 추가
    document.documentElement.classList.toggle('dark-mode', darkMode.value);
  }
});

const toggleDarkMode = (newMode: boolean) => {
  darkMode.value = newMode;

  if (typeof window !== 'undefined') { // 클라이언트에서만 실행
    localStorage.setItem('darkMode', String(newMode));
    // 다크 모드 상태 변경 시 documentElement 클래스 추가
    document.documentElement.classList.toggle('dark-mode', newMode);
  }
};
</script>

<template>
  <div :class="{ 'dark-mode': darkMode }" class="layout-root">
    <!-- default header -->
    <Header :darkMode="darkMode" @update:darkMode="toggleDarkMode" />

    <!-- 페이지 콘텐츠 -->
    <slot />

    <!-- default footer -->
    <Footer :darkMode="darkMode" @update:darkMode="toggleDarkMode" />
  </div>
</template>

<style scoped>
.layout-root {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.dark-mode {
  background-color: #121212; /* 다크 배경 */
  color: white; /* 다크 텍스트 색 */
}
</style>

<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

// 전역 다크모드 상태
const darkMode = ref(false)

// 클라이언트에서 로컬스토리지 값 불러오기
onMounted(() => {
  if (process.client) {
    const saved = localStorage.getItem('darkMode')
    if (saved !== null) darkMode.value = saved === 'true'
  }
})

// darkMode 변경되면 <html>에 클래스 붙이고 저장
watch(
    () => darkMode.value,
    (val) => {
      if (process.client) {
        document.documentElement.classList.toggle('dark-mode', val)
        localStorage.setItem('darkMode', val.toString())
      }
    },
    { immediate: true }
)
</script>

<template>
  <NuxtLayout :darkMode="darkMode" @update:darkMode="darkMode = $event">
    <div class="page-container">
      <NuxtPage />
    </div>
  </NuxtLayout>
</template>

<style>

/* 라이트 모드 */
:root {
  --bg-main: #f3f4f6;
  --text-main: #212529;
  --text-primary: #66b2ff;
  --text-subtle: #6c757d;

  --bg-card: #f8f9fa;
  --card-border: #dee2e6;
  --card-hover-border: #495057;
  --card-text: #212529;

  --link-color: #212529;
  --link-hover-color: #000000;
  --icon-filter: none;

  --select-bg: #ffffff;
  --select-text: #212529;
  --select-border: #ced4da;

  --footer-bg: #f3f4f6;
}

/* 다크 모드 */
.dark-mode {
  --bg-main: #212529;
  --text-main: #e9ecef;
  --text-primary: #4dabf7;
  --text-subtle: #adb5bd;

  --bg-card: #212529;
  --card-border: #495057;
  --card-hover-border: #adb5bd;
  --card-text: #e9ecef;

  --link-color: #adb5bd;
  --link-hover-color: #ffffff;
  --icon-filter: brightness(0) invert(1);

  --select-bg: #343a40;
  --select-text: #e9ecef;
  --select-border: #495057;

  --footer-bg: #212529;
}

/* 전역 적용 */
body {
  background-color: var(--bg-main);
  color: var(--text-main);
}

.card {
  background-color: var(--bg-card);
  color: var(--card-text);
  border-color: var(--card-border);
}

.text-primary {
  color: var(--text-primary) !important;
}

.page-container {
  min-height: 600px;
  padding: 1rem;
  background-color: var(--bg-main);
}

img, svg {
  filter: var(--icon-filter);
}

.img-non-filter {
  filter: none !important;
}
</style>

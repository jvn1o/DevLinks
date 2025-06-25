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
body {
  /* 전역 적용 */
  background-color: var(--bg-main);
  color: var(--text-main);

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
}

/* 라이트 모드 */
:root {
  --bg-main: #f3f4f6;
  --bg-sub: #f0f2f5;
  --text-main: #212529;
  --text-primary: #66b2ff;
  --text-subtle: #6c757d;
  --text-active: #0d6efd;

  --bg-card: #f8f9fa;
  --card-border: #dee2e6;
  --card-hover-border: #495057;

  --bg-modal: var(--bg-card);

  --link-color: var(--text-main);
  --link-hover-color: #000000;
  --icon-filter: none;

  --select-text: var(--text-main);
  --select-border: #bcc0c4;
  --select-arrow: url("data:image/svg+xml,%3Csvg fill='%23212529' viewBox='0 0 20 20' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M5.23 7.21a.75.75 0 011.06.02L10 10.94l3.71-3.71a.75.75 0 111.06 1.06l-4.24 4.24a.75.75 0 01-1.06 0L5.23 8.27a.75.75 0 01.02-1.06z'/%3E%3C/svg%3E");

  --footer-bg: var(--bg-main);
}

/* 다크 모드 */
.dark-mode {
  --bg-main: #212529;
  --bg-sub: #3c4046;
  --text-main: #e9ecef;
  --text-primary: #4dabf7;
  --text-subtle: #adb5bd;

  --bg-card: #3f464d;
  --card-border: #495057;
  --card-hover-border: #adb5bd;

  --bg-modal: #585d65;

  --link-color: #adb5bd;
  --link-hover-color: #ffffff;
  --icon-filter: brightness(0) invert(1);

  --select-text: #f1f3f5;
  --select-border: #5c6166;
  --select-arrow: url("data:image/svg+xml,%3Csvg fill='%23f1f3f5' viewBox='0 0 20 20' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M5.23 7.21a.75.75 0 011.06.02L10 10.94l3.71-3.71a.75.75 0 111.06 1.06l-4.24 4.24a.75.75 0 01-1.06 0L5.23 8.27a.75.75 0 01.02-1.06z'/%3E%3C/svg%3E");

  --footer-bg: #212529;
}

/* select form --- */
.form-select {
  background-image: var(--select-arrow);
  background-color: var(--bg-sub);
  background-size: 1.25rem 1.25rem;
  font-size: 0.75rem;
  color: var(--select-text);
  border: 1px solid var(--select-border);
  transition: all 0.3s ease;
  box-shadow: none;
}

.form-select:focus {
  border-color: var(--text-primary);
  outline: none;
  box-shadow: 0 0 0 0.2rem rgba(102, 178, 255, 0.2);
}

/* card --- */
.card {
  background-color: var(--bg-card);
  color: var(--text-main);
  border-color: var(--card-border);
}

.text-primary {
  color: var(--text-primary) !important;
}

.text-muted {
  color: var(--text-subtle) !important;
}

</style>

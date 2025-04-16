<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Header from '~/components/DefaultHeader.vue'
import Footer from '~/components/DefaultFooter.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

const darkMode = ref(false)

// 클라이언트에서만 localStorage 접근
onMounted(() => {
  if (typeof window !== 'undefined') {
    darkMode.value = localStorage.getItem('darkMode') === 'true'
    document.documentElement.classList.toggle('dark-mode', darkMode.value)
  }
})

// 다크모드 상태가 바뀔 때마다 html 클래스와 localStorage에 반영
watch(darkMode, (newVal) => {
  if (typeof window !== 'undefined') {
    localStorage.setItem('darkMode', String(newVal))
    document.documentElement.classList.toggle('dark-mode', newVal)
  }
})
</script>

<template>
  <div :class="{ 'bg-dark text-white': darkMode }" class="layout-root">
    <!-- Header & Footer에 v-model로 다크모드 상태 양방향 바인딩 -->
    <Header v-model:darkMode="darkMode" />

    <!-- 페이지 콘텐츠 -->
    <slot />

    <Footer v-model:darkMode="darkMode" />
  </div>
</template>

<style scoped>
.layout-root {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
</style>

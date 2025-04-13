<script setup lang="ts">
  import 'bootstrap/dist/css/bootstrap.min.css';
  import 'bootstrap-icons/font/bootstrap-icons.css';
</script>

<template>
    <NuxtLayout>
      <div class="page-container">
        <NuxtPage />
      </div>
    </NuxtLayout>
</template>

<style>
.page-container {
  min-height: 600px; /* 원하는 최소 높이(px, vh 등으로 설정 가능) */
  padding: 1rem;
}
</style>
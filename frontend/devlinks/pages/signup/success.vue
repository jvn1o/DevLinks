<script setup>
definePageMeta({
  layout: 'auth'
})
</script>

<template>
  <main class="container d-flex flex-column align-items-center justify-content-center text-center vh-70">
    <img src="../../assets/image/icon/checked.png" alt="Success Icon" class="mb-4" style="width: 100px; height: 100px;" />
    <div class="fs-2 fw-bold text-dark mb-4">
      Your account was successfully created!
    </div>
    <RouterLink to="/login" class="btn btn-primary px-4 py-2">
      Back to Login
    </RouterLink>
  </main>
</template>

<style scoped>
/* Bootstrap에는 vh-70 클래스가 없으므로 직접 정의 */
.vh-70 {
  height: 70vh;
}
</style>
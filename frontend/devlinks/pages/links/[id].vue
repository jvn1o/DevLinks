<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();

if (route.params.id === 'create') {
  // create는 실제 동적 ID가 아니므로 리다이렉트
  router.replace('/links/create');
}
</script>

<template>
  <div>
    <h1>안녕</h1>
    <div>반가워요</div>
  </div>
</template>

<style scoped>

</style>
<script setup lang="ts">
import { computed } from 'vue';

const props = defineProps<{
  currentPage: number;
  totalPages: number;
  pagesToShow: number[];
  darkMode: boolean;
}>();

const emit = defineEmits<{
  (e: 'update:page', value: number): void;
}>();

const isFirst = computed(() => props.currentPage === 1);
const isLast = computed(() => props.currentPage === props.totalPages);

const pageClass = (page: number) => {
  return {
    'page-item': true,
    active: page === props.currentPage,
  };
};

const pageLinkClass = computed(() => ({
  'page-link': true,
}));
</script>

<template>
  <nav :class="{ 'dark-mode': darkMode }">
    <ul class="pagination justify-content-center">

      <!-- 이전 버튼 -->
      <li :class="['page-item', { disabled: isFirst }]">
        <button
            :class="pageLinkClass"
            @click="$emit('update:page', currentPage - 1)"
            :disabled="isFirst"
        >
          이전
        </button>
      </li>

      <!-- 페이지 숫자 -->
      <li v-for="page in pagesToShow" :key="page" :class="pageClass(page)">
        <button
            :class="pageLinkClass"
            @click="$emit('update:page', page)"
        >
          {{ page }}
        </button>
      </li>

      <!-- 다음 버튼 -->
      <li :class="['page-item', { disabled: isLast }]">
        <button
            :class="pageLinkClass"
            @click="$emit('update:page', currentPage + 1)"
            :disabled="isLast"
        >
          다음
        </button>
      </li>
    </ul>
  </nav>
</template>

<style scoped>
.dark-mode {
  .page-link {
    background-color: var(--bg-card);
    color: var(--text-main);
    border: 1px solid var(--card-border);
  }

  .page-link:hover {
    background-color: var(--bg-sub);
    border-color: var(--card-hover-border);
  }

  .page-item.active .page-link {
    background-color: var(--text-primary);
    border-color: var(--text-primary);
  }

  .page-item.disabled .page-link {
    opacity: 0.5;
    cursor: not-allowed;
  }
}
</style>
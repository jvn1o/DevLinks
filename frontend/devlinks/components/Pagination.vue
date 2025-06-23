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
  'bg-dark text-white border-secondary': props.darkMode,
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
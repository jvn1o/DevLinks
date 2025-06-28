<script setup lang="ts">
import {computed} from 'vue'

const props = defineProps<{
  totalItems: number
  itemsPerPage: number
  currentPage: number
}>()

const emit = defineEmits<{
  (e: 'update:currentPage', value: number): void
}>()

const totalPages = computed(() =>
    Math.ceil(props.totalItems / props.itemsPerPage)
)

const isFirst = computed(() => props.currentPage === 1)
const isLast = computed(() => props.currentPage === totalPages.value)

const pagesToShow = computed(() => {
  const maxButtons = 5
  let startPage = Math.max(props.currentPage - 2, 1)
  let endPage = startPage + maxButtons - 1

  if (endPage > totalPages.value) {
    endPage = totalPages.value
    startPage = Math.max(endPage - maxButtons + 1, 1)
  }

  const pages = []
  for (let i = startPage; i <= endPage; i++) {
    pages.push(i)
  }
  return pages
})

const pageClass = (page: number) => ({
  'page-item': true,
  active: page === props.currentPage
})

const pageLinkClass = computed(() => ({
  'page-link': true
}))

const handlePageChange = (page: number) => {
  window.scrollTo({top: 0, behavior: 'smooth'})
  emit('update:currentPage', page)
}
</script>

<template>
  <nav>
    <ul class="pagination justify-content-center">

      <!-- 이전 버튼 -->
      <li :class="['page-item', { disabled: isFirst }]">
        <button
            :class="pageLinkClass"
            @click="handlePageChange(currentPage - 1)"
            :disabled="isFirst"
        >
          이전
        </button>
      </li>

      <!-- 페이지 숫자 -->
      <li v-for="page in pagesToShow" :key="page" :class="pageClass(page)">
        <button
            :class="pageLinkClass"
            @click="handlePageChange(page)"
        >
          {{ page }}
        </button>
      </li>

      <!-- 다음 버튼 -->
      <li :class="['page-item', { disabled: isLast }]">
        <button
            :class="pageLinkClass"
            @click="handlePageChange(currentPage + 1)"
            :disabled="isLast"
        >
          다음
        </button>
      </li>

    </ul>
  </nav>
</template>

<style scoped>
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
</style>
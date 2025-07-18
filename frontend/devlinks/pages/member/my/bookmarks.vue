<script setup lang="ts">
import { ref, computed, watch, onMounted } from 'vue'
import axios from 'axios'
import MyBookmark from '~/components/MyBookmark.vue'
import DeleteConfirmModal from '~/components/DeleteConfirmModal.vue'

// 로그인된 사용자 ID (실제 프로젝트에서는 토큰에서 추출)
const memberId = 1 // 예시

const bookmarks = ref([])

const fetchBookmarks = async () => {
  const { data } = await axios.get(`/api/v1/member/${memberId}/bookmark`)
  bookmarks.value = data.items
}

const currentPage = ref(1)
const itemsPerPage = 8

const pagedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  return bookmarks.value.slice(start, start + itemsPerPage)
})

onMounted(() => {
  fetchBookmarks()
})

watch(currentPage, () => {
  window.scrollTo({ top: 0, behavior: 'smooth' })
})

// 북마크 해제 모달 처리
const deleteModal = ref()

const handleUnBookmark = async (id: number) => {
  const confirmed = await deleteModal.value.show()
  if (!confirmed) return

  await axios.delete(`/api/v1/member/${memberId}/bookmark/${id}`)
  // 목록에서 제거
  bookmarks.value = bookmarks.value.filter(item => item.id !== id)
}
</script>

<template>
  <DeleteConfirmModal ref="deleteModal" />

  <section class="d-flex justify-content-between align-items-center px-md-5 px-sm-3 py-2 flex-wrap gap-2">
    <h1 class="fs-4 fw-bold mx-4 md-4">내가 북마크한 링크</h1>

    <div class="p-3 w-100">
      <div
          v-if="bookmarks.length === 0"
          class="text-muted text-center"
      >
        북마크한 항목이 없습니다.
      </div>

      <div v-else>
        <div
            v-for="item in pagedItems"
            :key="item.id"
            class="mb-4"
        >
          <MyBookmark
              :item="item"
              @unbookmark="handleUnBookmark"
          />
        </div>
      </div>
    </div>
  </section>

  <div class="d-flex justify-content-center my-4">
    <Pagination
        :totalItems="bookmarks.length"
        :itemsPerPage="itemsPerPage"
        v-model:currentPage="currentPage"
    />
  </div>
</template>

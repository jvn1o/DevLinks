<script setup lang="ts">
import { useRouter } from 'vue-router'

interface card {
  id: number
  title: string
  image: string
  category: string
  price: string
  bookmarkCount?: number
  viewCount?: number
}

const props = defineProps<{ item: card }>()
const emit = defineEmits<{
  (e: 'unBookmark', id: number): void
}>()

const router = useRouter()

const goToDetail = () => {
  router.push(`/programs/${props.item.id}`) // 상세 페이지 경로
}

const unBookmark = () => {
  emit('unBookmark', props.item.id)
}
</script>

<template>
  <div class="card flex-sm-row hover-card cursor-pointer" @click="goToDetail">
    <div class="card-img-wrapper">
      <img :src="item.image" class="card-img-top img-non-filter" :alt="item.title" />
    </div>

    <div class="card-body d-flex flex-column justify-content-center">
      <h5 class="card-title fs-5 text-truncate fw-bold mb-3" :title="item.title">
        {{ item.title }}
      </h5>

      <p class="text-category fs-6 text-truncate mb-3">{{ item.category }}</p>

      <div class="justify-content-between fs-6 text-muted mb-3">
        <span class="bi bi-eye me-1"></span>({{ item.viewCount || 0 }})
        <span class="bi bi-bookmark-fill text-warning ms-3"></span>({{ item.bookmarkCount || 0 }})
      </div>

      <div class="fw-bold fs-6 mb-2">
        {{ item.price }}
      </div>

      <div class="d-flex justify-content-end">
        <button class="btn btn-sm btn-outline-warning" @click.stop="unBookmark">북마크 해제</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-img-wrapper img {
  height: 239px;
  object-fit: cover;
}

.hover-card {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.hover-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}

.text-category {
  font-weight: bold;
  color: var(--text-active);
}

.cursor-pointer {
  cursor: pointer;
}
</style>

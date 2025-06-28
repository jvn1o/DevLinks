<script setup lang="ts">
import {useRouter} from 'vue-router'

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
  (e: 'delete', id: number): void
}>()

const router = useRouter()
const priceWords = computed(() => {
  return props.item.price.match(/\S+|\s+/g) || []
})

const goToEdit = () => {
  router.push(`/member/my/links/edit/${props.item.id}`)
}

const deleteConfirmClick = () => {
  emit('delete', props.item.id); // 삭제 요청은 부모가 모달 통해 처리
}
</script>

<template>
  <div class="card flex-sm-row">
    <div class="card-img-wrapper">
      <img :src="item.image" class="card-img-top img-non-filter" :alt="item.title"/>
    </div>

    <div class="card-body d-flex flex-column">
      <h5 class="card-title fs-5 text-truncate fw-bold mb-3" :title="item.title">
        {{ item.title }}
      </h5>

      <p class="text-category fs-6 text-truncate mb-3">{{ item.category }}</p>

      <div class="justify-content-between fs-6 text-muted mb-3">
        <span class="bi bi-eye me-1"></span>({{ item.viewCount || 0 }})
        <span class="bi bi-bookmark-fill text-warning ms-3"></span>({{ item.bookmarkCount || 0 }})
      </div>

      <div class="fw-bold fs-6">
        <template v-for="(word, index) in priceWords" :key="index">
          <span :class="{ 'text-danger': word === 'Paid' }">{{ word }}</span>
          <span v-if="index !== priceWords.length - 1"> </span>
        </template>

        <div class="d-flex justify-content-end gap-2">
          <button class="btn btn-sm btn-outline-primary" @click="goToEdit">수정</button>
          <button class="btn btn-sm btn-outline-danger" @click="deleteConfirmClick">삭제</button>
        </div>
      </div>

    </div>
  </div>
</template>

<style scoped>
.card-img-wrapper img {
  height: 239px;
  object-fit: cover;
}

/* md 이상일 때는 row layout 으로, 이미지 너비 고정 */
@media (min-width: 768px) and (max-width: 991.98px) {
  .card-img-wrapper {
    width: 50%;
    flex-shrink: 0;
  }
}

@media (min-width: 576px) and (max-width: 768px) {
  .card-img-wrapper {
    width: 35%;
    flex-shrink: 0;
  }
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
</style>

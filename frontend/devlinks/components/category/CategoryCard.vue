<script setup>
import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import Toast from '@/components/Toast.vue'
import useMemberDetails from '~/composables/useMemberDetails'
import useBookmarks from '@/composables/useBookmarks'

const props = defineProps({
  item: {
    type: Object,
    required: true,
  },
})

const router = useRouter()
const route = useRoute()

const isBookmarked = ref(false)
const toastMessage = ref('')

const { memberId, isAnonymous } = useMemberDetails()
const { toggleBookmark: toggleBookmarkAPI } = useBookmarks()

const toggleBookmark = async () => {
  // 비로그인 유저가 북마크 버튼을 누르면 로그인 이후
  if (isAnonymous()) {
    const currentPath = route.fullPath
    router.push(`/login?redirect=${encodeURIComponent(currentPath)}`)
    return
  }

  try {
    isBookmarked.value = !isBookmarked.value
    await toggleBookmarkAPI(memberId.value, props.item.id)
    toastMessage.value = isBookmarked.value ? 'Bookmark added!' : 'Bookmark removed!'
  } catch (e) {
    isBookmarked.value = !isBookmarked.value // 실패 시 원래대로 롤백
    toastMessage.value = 'Failed to update bookmark.'
  }
}
</script>

<template>
  <NuxtLink :to="`/links/${item.id}`"
            class="card h-100 hover-card"
            style="text-decoration: none !important;">
    <div class="card-img-wrapper">
      <img :src="item.image" class="card-img-top" :alt="item.title"/>
    </div>

    <div class="card-body d-flex flex-column">
      <div class="d-flex flex-column">

        <h5 class="card-title fs-6 text-truncate fw-bold" :title="item.title">
          {{ item.title }}
        </h5>

        <div class="d-flex justify-content-between align-items-center">
          <p class="card-text text-muted text-truncate mb-0">
            {{ item.category }}
          </p>
          <button class="bookmark-btn btn p-0 ms-2 flex-shrink-0"
                  @click.stop.prevent="toggleBookmark"
                  style="border: none !important;"
          >
          <!--    의도치 않은 페이지 이동을 막기 위해 stop.prevent    -->
            <i
                :class="[
                'bi',
                isBookmarked ? 'bi-bookmark-fill text-warning' : 'bi-bookmark'
              ]"
            ></i>
          </button>

        </div>

      </div>

      <div class="d-flex justify-content-between mt-2">
        <div v-if="item.bookmarkCount">
          <span class="bi bi-bookmark-fill text-warning"></span>({{ item.bookmarkCount || 0 }})
          <span class="bi bi-eye"></span>({{ item.viewCount || 0 }})
        </div>

        <div
            class="fw-bold mt-auto"
            :class="{
            'text-danger': item.price !== 'Free',
            'text-info': item.price === 'Free'
          }"
        >
          {{ item.price }}
        </div>
      </div>

    </div>
  </NuxtLink>

  <Toast :message="toastMessage" />
</template>

<style scoped>

.card-img-wrapper img {
  height: 225px;
  object-fit: cover;
}

.hover-card {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.hover-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  background-color: #f8f9fa;
}
</style>
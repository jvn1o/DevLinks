<script setup lang="ts">
import {onMounted, ref} from 'vue'
import {useRoute, useRouter} from 'vue-router'
import {Swiper, SwiperSlide} from 'swiper/vue'
import axios from 'axios'

const route = useRoute()
const router = useRouter()

// 서버 랜더링용
// const link = ref<any>(null)

const link = ref({
  title: 'Learn Algorithms',
  category: {name: 'Algorithm & Data Structures'},
  priceType: 'Paid',
  language: 'Korean',
  description: `이 링크는 알고리즘 학습에 도움이 되는 자료들을 포함하고 있습니다.\n\n무료와 유료가 혼합된 콘텐츠로 구성되어 있습니다.`,
  images: [
    {url: '/image/figma.png'},
    {url: '/image/notion.png'},
  ],
})
// const loading = ref(true)
//
// onMounted(async () => {
//   const id = route.params.id
//
//   if (id === 'create') {
//     router.replace('/member/links/create')
//     return
//   }
//
//   try {
//     const { data } = await axios.get(`/api/v1/links/detail/${id}`)
//     link.value = data
//   } catch (err) {
//     console.error(err)
//     router.replace('/404')
//   } finally {
//     loading.value = false
//   }
// })
</script>

<template>
  <div class="container py-5" v-if="!loading && link">
    <!-- 이미지 슬라이더 -->
    <div class="swiper-container ms-4 mb-3" v-if="link.images?.length">
      <Swiper
          :breakpoints="{
          0: {
            slidesPerView: 1.2,
            spaceBetween: 16,
          },
          768: {
            slidesPerView: 1.5,
            spaceBetween: 12,
          },
          1200: {
            slidesPerView: 3,
            spaceBetween: 14,
          },
        }"
          :pagination="false"
          :navigation="false"
          class="custom-swiper"
      >
        <SwiperSlide v-for="(image, index) in link.images" :key="index">
          <img
              :src="image.url"
              class="rounded shadow-sm"
              style="width: auto; height: 200px; object-fit: contain;"
          />
        </SwiperSlide>
      </Swiper>
    </div>

    <!-- 텍스트 정보 -->
    <div class="px-4 px-md-4">
      <h2 class="fw-bold mb-3">{{ link.title }}</h2>
      <p class="mb-2 text-muted"><strong>카테고리:</strong> {{ link.category?.name }}</p>
      <p class="mb-2 text-muted"><strong>가격:</strong> {{ link.priceType }}</p>
      <p class="mb-2 text-muted"><strong>언어:</strong> {{ link.language }}</p>
      <p class="text-body mt-3" style="white-space: pre-wrap;">{{ link.description }}</p>
    </div>
  </div>

  <div v-else class="text-center py-5">
    <span>로딩 중...</span>
  </div>
</template>

<style scoped>
/* swiper 네비게이션 스타일 조정 */
.swiper-button-prev,
.swiper-button-next {
  color: #333;
}
</style>

<script setup>
import { ref, computed } from 'vue';
import { watch } from 'vue';
import axios from 'axios';
import { useMemberApi } from '~/composables/useMemberApi';
import CategoryCard from '~/components/category/CategoryCard.vue';
import img from 'assets/image/figma.png';

// const links = ref([]); 서버 랜더링 용
const links = ref([
  { id: 1, title: 'Learn Algorithms', category: 'Algorithm & Data Structures', price: 'Free & Paid', bookmarkCount: 45, image: img },
  { id: 2, title: 'API Basics', category: 'API & Documentation', price: 'Paid', bookmarkCount: 40, image: img },
  { id: 3, title: 'Cloud Fundamentals', category: 'Cloud & DevOps', price: 'Free', bookmarkCount: 35, image: img },
  { id: 4, title: 'Testing Tips', category: 'Testing & QA', price: 'Paid', bookmarkCount: 50, image: img },
  { id: 5, title: 'UI Design Basics', category: 'UI / UX', price: 'Free', bookmarkCount: 47, image: img },
  { id: 6, title: 'Data Structures', category: 'Algorithm & Data Structures', price: 'Paid', bookmarkCount: 42, image: img },
  { id: 7, title: 'Advanced APIs', category: 'API & Documentation', price: 'Free', bookmarkCount: 39, image: img },
  { id: 8, title: 'DevOps Pipeline', category: 'Cloud & DevOps', price: 'Paid', bookmarkCount: 48, image: img },
  { id: 9, title: 'React Basics', category: 'Frontend', price: 'Free', bookmarkCount: 30, image: img },
]); // 임시용

const selectedCategorySlug = ref("algorithm-data-structures");
const selectedSort = ref("newest");
const selectedPrice = ref(null); // Free, Paid, Free & Paid

const api = useMemberApi()

const fetchLinks = async () => {
  try {
    let response
    if (isLoggedIn.value) {  // 회원일 때 tokken 소유
      response = await api.get(`/api/v1/links/category/${selectedCategorySlug.value}`, {
        params: {
          sort: selectedSort.value,
          pricetype: selectedPrice.value ?? '',
        },
      })
    } else {
      response = await axios.get(`/api/v1/links/category/${selectedCategorySlug.value}`, {
        params: {
          sort: selectedSort.value,
          pricetype: selectedPrice.value ?? '',
        },
      })
    }
    links.value = response.data
  } catch (error) {
    console.error('링크 목록 로딩 실패', error)
  }
}

const currentPage = ref(1);
const itemsPerPage = 8;

const pagedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  return links.value.slice(start, start + itemsPerPage)
})

watchEffect(() => {
  fetchLinks();
});

// selectedPrice 또는 selectedSort 변경
watch([selectedPrice, selectedSort], () => {
  currentPage.value = 1;  // 필터나 정렬이 바뀌면 페이지 1로 초기화하는 게 일반적
  fetchLinks();
});

// selectedCategorySlug 변경
watch(selectedCategorySlug, () => {
  currentPage.value = 1;
  fetchLinks();
});

// currentPage 변경
watch(currentPage, () => {
  window.scrollTo({ top: 0, behavior: 'smooth' });
});

</script>

<template>

  <div class="d-flex justify-content-between align-items-center px-3 py-2 flex-wrap gap-2">
    <div class="fw-bold ps-2">
      Page {{ currentPage }}
    </div>

    <!-- Filter & Sort -->
    <div class="d-flex flex-wrap gap-2">
      <!-- Price Type -->
      <select v-model="selectedPrice" class="form-select w-auto">
        <option :value="null">All</option>
        <option value="FREE">Free</option>
        <option value="PAID">Paid</option>
        <option value="FREE_PAID">Free & Paid</option>
      </select>

      <!-- Time Type -->
      <select v-model="selectedSort" class="form-select w-auto">
        <option value="newest">Newest</option>
        <option value="popular">Most Popular</option>
      </select>
    </div>
  </div>

  <!-- Links  -->
  <div class="row p-3">
    <div
        v-for="item in pagedItems"
        :key="item.id"
        class="col-md-6 col-xl-3 mb-4"
    >
      <CategoryCard :item="item" :darkMode="darkMode" />
    </div>
  </div>

  <div class="d-flex justify-content-center my-4">
    <nav>
      <Pagination
          :totalItems="links.length"
          :itemsPerPage="itemsPerPage"
          v-model:currentPage="currentPage"
          :darkMode="darkMode"
      />
    </nav>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import CategoryCard from '~/components/category/CategoryCard.vue';
import img from '~/assets/image/figma.png';

const route = useRoute();
const categorySlug = route.params.id; // URL에서 category id 가져오기

const selectedFilter = ref('newest');
const selectedPrice = ref('all');
const currentPage = ref(1);
const itemsPerPage = 8;

// ex) /api/resources?category=Algorithm-And-Data-Structures
const { data: items, refresh } = await useFetch(() =>
    `/api/resources?category=${encodeURIComponent(categorySlug)}&price=${selectedPrice.value}&sort=${selectedFilter.value}&page=${currentPage.value}&size=${itemsPerPage}`
);

// 필터, 가격, 페이지가 바뀌면 자동으로 refresh 호출
watch([selectedFilter, selectedPrice, currentPage], () => {
  refresh();
});
</script>

<template>
  <!-- 카드 리스트 -->
  <div class="row p-3">
<!--    v-for="item in items"-->
    <div
        v-for="item in [
      { id: 1, title: 'Learn Algorithms', category: 'Algorithm & Data Structures', price: 'Free & Paid', bookmarkCount: 45, image: img },
      { id: 2, title: 'API Basics', category: 'API & Documentation', price: 'Paid', bookmarkCount: 40, image: img },
      { id: 3, title: 'Cloud Fundamentals', category: 'Cloud & DevOps', price: 'Free', bookmarkCount: 35, image: img },
      { id: 4, title: 'Testing Tips', category: 'Testing & QA', price: 'Paid', bookmarkCount: 50, image: img },
      { id: 5, title: 'UI Design Basics', category: 'UI / UX', price: 'Free', bookmarkCount: 47, image: img },
      { id: 6, title: 'Data Structures', category: 'Algorithm & Data Structures', price: 'Paid', bookmarkCount: 42, image: img },
      { id: 7, title: 'Advanced APIs', category: 'API & Documentation', price: 'Free', bookmarkCount: 39, image: img },
      { id: 8, title: 'DevOps Pipeline', category: 'Cloud & DevOps', price: 'Paid', bookmarkCount: 48, image: img }
    ]"
        :key="item.id"
        class="col-lg-3 col-md-4 col-sm-6 mt-4 mb-4"
    >
      <CategoryCard :item="item" />
    </div>
  </div>
</template>

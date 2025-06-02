<script setup>
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import CategoryCard from '~/components/category/CategoryCard.vue';

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
  <div class="row">
    <div
        v-for="(item, index) in items"
        :key="index"
        class="col-md-3 col-sm-6 mb-4"
    >
      <CategoryCard :item="item" />
    </div>
  </div>
</template>

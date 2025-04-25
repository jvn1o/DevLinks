<script setup>
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import CategoryCard from '~/components/category/CategoryCard.vue';

const route = useRoute();
const categorySlug = route.params.id; // URL에서 category id 가져오기

const selectedFilter = ref('newest');
const selectedPrice = ref('all');
const currentPage = ref(1);
const itemsPerPage = 8;

// 예: /api/resources?category=Algorithm-And-Data-Structures
const { data: items } = await useFetch(() => `/api/resources?category=${categorySlug}`);

const sortLabel = computed(() =>
    selectedFilter.value === 'newest'
        ? 'Newest'
        : selectedFilter.value === 'popular'
            ? 'Popular'
            : 'Alphabetical'
);

const priceLabel = computed(() =>
    selectedPrice.value === 'all'
        ? 'Free + Paid'
        : selectedPrice.value === 'free'
            ? 'Free'
            : 'Paid'
);

const filteredItems = computed(() => {
  if (!items.value) return [];

  const filtered = items.value.filter(item => {
    if (selectedPrice.value === 'free') return item.price === 'Free';
    if (selectedPrice.value === 'paid') return item.price === 'Paid';
    return true;
  });

  return filtered.slice(
      (currentPage.value - 1) * itemsPerPage,
      currentPage.value * itemsPerPage
  );
});

const totalPages = computed(() => {
  if (!items.value) return 1;
  const filtered = items.value.filter(item => {
    if (selectedPrice.value === 'free') return item.price === 'Free';
    if (selectedPrice.value === 'paid') return item.price === 'Paid';
    return true;
  });
  return Math.ceil(filtered.length / itemsPerPage);
});
</script>

<template>
  <!-- 카드 리스트 -->
  <div class="row">
    <div
        v-for="(item, index) in filteredItems"
        :key="index"
        class="col-md-3 col-sm-6 mb-4"
    >
      <CategoryCard :item="item" />
    </div>
  </div>
</template>

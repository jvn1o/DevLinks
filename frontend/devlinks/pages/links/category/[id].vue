<script setup>
import { ref, computed } from 'vue';
import { watch } from 'vue';
import CategoryCard from '~/components/category/CategoryCard.vue';
import img from 'assets/image/figma.png';

const links = ref([]);
const selectedCategorySlug = ref("algorithm-data-structures");
const selectedSort = ref("newest");
const selectedPrice = ref(null); // Free, Paid, Free & Paid

const fetchLinks = async () => {
  try {
    const { data } = await axios.get(`/api/v1/links/category/${selectedCategorySlug.value}`, {
      params: {
        sort: selectedSort.value,
        pricetype: selectedPrice.value ?? '', // null 이면 필터링 없이 반환
      },
    });
    links.value = data;
  } catch (err) {
    console.error("링크 목록 로딩 실패", err);
  }
};



const currentPage = ref(1);
const itemsPerPage = 8;

const dummyItems = [
  { id: 1, title: 'Learn Algorithms', category: 'Algorithm & Data Structures', price: 'Free & Paid', bookmarkCount: 45, image: img },
  { id: 2, title: 'API Basics', category: 'API & Documentation', price: 'Paid', bookmarkCount: 40, image: img },
  { id: 3, title: 'Cloud Fundamentals', category: 'Cloud & DevOps', price: 'Free', bookmarkCount: 35, image: img },
  { id: 4, title: 'Testing Tips', category: 'Testing & QA', price: 'Paid', bookmarkCount: 50, image: img },
  { id: 5, title: 'UI Design Basics', category: 'UI / UX', price: 'Free', bookmarkCount: 47, image: img },
  { id: 6, title: 'Data Structures', category: 'Algorithm & Data Structures', price: 'Paid', bookmarkCount: 42, image: img },
  { id: 7, title: 'Advanced APIs', category: 'API & Documentation', price: 'Free', bookmarkCount: 39, image: img },
  { id: 8, title: 'DevOps Pipeline', category: 'Cloud & DevOps', price: 'Paid', bookmarkCount: 48, image: img },
  { id: 9, title: 'React Basics', category: 'Frontend', price: 'Free', bookmarkCount: 30, image: img },
];

const totalItems = ref(dummyItems.length);
const totalPages = computed(() => Math.ceil(totalItems.value / itemsPerPage));

const pagedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage;
  return dummyItems.slice(start, start + itemsPerPage);
});

// 최대 5개 페이지 버튼만 보여주도록 범위 계산
const pagesToShow = computed(() => {
  const maxButtons = 5;
  let startPage = Math.max(currentPage.value - 2, 1);
  let endPage = startPage + maxButtons - 1;

  if (endPage > totalPages.value) {
    endPage = totalPages.value;
    startPage = Math.max(endPage - maxButtons + 1, 1);
  }

  const pages = [];
  for (let i = startPage; i <= endPage; i++) {
    pages.push(i);
  }
  return pages;
});



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
  fetchLinks();
});

</script>

<template>
  <div class="d-flex flex-wrap gap-2 p-3 justify-content-end">


    <!-- Price Type -->
    <select v-model="selectedPrice" class="form-select w-auto">
      <option :value="null">All</option>
      <option value="FREE">Free</option>
      <option value="PAID">Paid</option>
      <option value="FREE_PAID">Free & Paid</option>
    </select>

    <!-- Sort -->
    <select v-model="selectedSort" class="form-select w-auto">
      <option value="newest">Newest</option>
      <option value="popular">Most Popular</option>
    </select>
  </div>



  <!-- Links  -->
  <div class="row p-3">
    <div
        v-for="item in pagedItems"
        :key="item.id"
        class="col-lg-3 col-md-4 col-sm-6 mb-4"
    >
      <CategoryCard :item="item" />
    </div>
  </div>

  <div class="d-flex justify-content-center my-4">
    <nav>
      <Pagination
          :currentPage="currentPage"
          :totalPages="totalPages"
          :pagesToShow="pagesToShow"
          :darkMode="darkMode"
          @update:page="(val) => currentPage = val"
      />
    </nav>
  </div>
</template>

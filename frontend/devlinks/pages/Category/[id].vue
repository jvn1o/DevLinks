<script setup>
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import CategoryCard from '~/components/category/CategoryCard.vue';
import img from '~/assets/image/figma.png';

const route = useRoute();
const categorySlug = route.params.id;

const selectedFilter = ref('newest');
const selectedPrice = ref('all');
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
  { id: 10, title: 'Vue.js Deep Dive', category: 'Frontend', price: 'Paid', bookmarkCount: 55, image: img },
  { id: 11, title: 'Docker Essentials', category: 'DevOps', price: 'Free & Paid', bookmarkCount: 44, image: img },
  { id: 12, title: 'Kubernetes Intro', category: 'DevOps', price: 'Paid', bookmarkCount: 37, image: img },
  { id: 13, title: 'Node.js Guide', category: 'Backend', price: 'Free', bookmarkCount: 41, image: img },
  { id: 14, title: 'Python for Data Science', category: 'Data Science', price: 'Paid', bookmarkCount: 53, image: img },
  { id: 15, title: 'Machine Learning Basics', category: 'Data Science', price: 'Free & Paid', bookmarkCount: 49, image: img },
  { id: 16, title: 'CSS Grid Layout', category: 'Frontend', price: 'Free', bookmarkCount: 29, image: img },
  { id: 17, title: 'JavaScript ES6+', category: 'Frontend', price: 'Paid', bookmarkCount: 60, image: img },
  { id: 18, title: 'React Hooks Deep Dive', category: 'Frontend', price: 'Free', bookmarkCount: 34, image: img },
  { id: 19, title: 'TypeScript Basics', category: 'Frontend', price: 'Paid', bookmarkCount: 38, image: img },
  { id: 20, title: 'GraphQL Essentials', category: 'Backend', price: 'Free & Paid', bookmarkCount: 42, image: img },
  { id: 21, title: 'SQL Optimization', category: 'Database', price: 'Paid', bookmarkCount: 44, image: img },
  { id: 22, title: 'MongoDB for Beginners', category: 'Database', price: 'Free', bookmarkCount: 36, image: img },
  { id: 23, title: 'AWS Cloud Basics', category: 'Cloud', price: 'Paid', bookmarkCount: 52, image: img },
  { id: 24, title: 'Azure Fundamentals', category: 'Cloud', price: 'Free', bookmarkCount: 33, image: img },
  { id: 25, title: 'Git & GitHub Workflow', category: 'Tools', price: 'Free & Paid', bookmarkCount: 57, image: img },
  { id: 26, title: 'CI/CD Pipeline', category: 'DevOps', price: 'Paid', bookmarkCount: 39, image: img },
  { id: 27, title: 'Linux Command Line', category: 'Tools', price: 'Free', bookmarkCount: 40, image: img },
  { id: 28, title: 'Docker Compose', category: 'DevOps', price: 'Paid', bookmarkCount: 45, image: img },
  { id: 29, title: 'REST API Design', category: 'Backend', price: 'Free', bookmarkCount: 50, image: img },
  { id: 30, title: 'Microservices Architecture', category: 'Backend', price: 'Paid', bookmarkCount: 43, image: img },
  { id: 31, title: 'UX Research Methods', category: 'UI / UX', price: 'Free', bookmarkCount: 31, image: img },
  { id: 32, title: 'Graph Theory Basics', category: 'Algorithm & Data Structures', price: 'Free', bookmarkCount: 27, image: img },
  { id: 33, title: 'Neural Networks Intro', category: 'Data Science', price: 'Paid', bookmarkCount: 46, image: img },
  { id: 34, title: 'Jest Testing Framework', category: 'Testing & QA', price: 'Free & Paid', bookmarkCount: 29, image: img },
  { id: 35, title: 'Sass for CSS', category: 'Frontend', price: 'Free', bookmarkCount: 38, image: img },
  { id: 36, title: 'Webpack Basics', category: 'Frontend', price: 'Paid', bookmarkCount: 35, image: img },
  { id: 37, title: 'Tailwind CSS Guide', category: 'Frontend', price: 'Free', bookmarkCount: 41, image: img },
  { id: 38, title: 'Serverless Architecture', category: 'Cloud', price: 'Paid', bookmarkCount: 45, image: img },
  { id: 39, title: 'ElasticSearch Intro', category: 'Database', price: 'Free', bookmarkCount: 32, image: img },
  { id: 40, title: 'Redis Caching', category: 'Database', price: 'Paid', bookmarkCount: 40, image: img },
  { id: 41, title: 'Prometheus Monitoring', category: 'DevOps', price: 'Free', bookmarkCount: 28, image: img },
  { id: 42, title: 'OpenAPI Spec', category: 'API & Documentation', price: 'Paid', bookmarkCount: 33, image: img }
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
</script>

<template>
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
      <ul class="pagination">
        <li class="page-item" :class="{ disabled: currentPage === 1 }">
          <button class="page-link" @click="currentPage--" :disabled="currentPage === 1">이전</button>
        </li>

        <li
            v-for="page in pagesToShow"
            :key="page"
            class="page-item"
            :class="{ active: page === currentPage }"
        >
          <button class="page-link" @click="currentPage = page">{{ page }}</button>
        </li>

        <li class="page-item" :class="{ disabled: currentPage === totalPages }">
          <button class="page-link" @click="currentPage++" :disabled="currentPage === totalPages">다음</button>
        </li>
      </ul>
    </nav>
  </div>
</template>

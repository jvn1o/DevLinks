<script setup lang="ts">
import {ref, computed, watch, onMounted} from 'vue';
import img from 'assets/image/figma.png';
import axios from "axios";

// const links = ref([]); 서버 랜더링 용
const links = ref([
  {
    id: 1,
    title: 'Learn Algorithms',
    category: 'Algorithm & Data Structures',
    price: 'Free & Paid',
    bookmarkCount: 45,
    image: img
  },
  {id: 2, title: 'API Basics', category: 'API & Documentation', price: 'Paid', bookmarkCount: 40, image: img},
  {id: 3, title: 'Cloud Fundamentals', category: 'Cloud & DevOps', price: 'Free', bookmarkCount: 35, image: img},
  {id: 4, title: 'Testing Tips', category: 'Testing & QA', price: 'Paid', bookmarkCount: 50, image: img},
  {id: 5, title: 'UI Design Basics', category: 'UI / UX', price: 'Free', bookmarkCount: 47, image: img},
  {
    id: 6,
    title: 'Data Structures',
    category: 'Algorithm & Data Structures',
    price: 'Paid',
    bookmarkCount: 42,
    image: img
  },
  {id: 7, title: 'Advanced APIs', category: 'API & Documentation', price: 'Free', bookmarkCount: 39, image: img},
  {id: 8, title: 'DevOps Pipeline', category: 'Cloud & DevOps', price: 'Paid', bookmarkCount: 48, image: img},
  {id: 9, title: 'React Basics', category: 'Frontend', price: 'Free', bookmarkCount: 30, image: img},
]); // 임시용

const fetchLinks = async () => {
  const {data} = await axios.get('/api/v1/member/link');

  links.value = data.items;
};

const currentPage = ref(1);
const itemsPerPage = 8;

const pagedItems = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage
  return links.value.slice(start, start + itemsPerPage)
})

onMounted(() => {
  fetchLinks();
});

// currentPage 변경
watch(currentPage, () => {
  window.scrollTo({top: 0, behavior: 'smooth'});
});

const deleteModal = ref()

const handleDelete = async (id: number) => {
  const confirmed = await deleteModal.value.show()
  if (!confirmed) return

  await axios.delete(`/api/v1/links/detail/${id}`)
  // 삭제 후 리스트에서 제거하는 처리 필요
}
</script>

<template>
  <DeleteConfirmModal ref="deleteModal"/>

  <div class="d-flex justify-content-between align-items-center px-md-5 px-sm-3 py-2 flex-wrap gap-2">
    <!-- Links  -->
    <div class="p-3 w-100">
      <div
          v-for="item in pagedItems"
          :key="item.id"
          class="mb-4"
      >
        <MyCard
            :item="item"
            @delete="handleDelete"
        />
      </div>
    </div>
  </div>

  <div class="d-flex justify-content-center my-4">
    <Pagination
        :totalItems="links.length"
        :itemsPerPage="itemsPerPage"
        v-model:currentPage="currentPage"
    />
  </div>

</template>

<style scoped>

</style>
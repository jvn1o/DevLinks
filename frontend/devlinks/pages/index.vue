<script>
import { ref, computed } from 'vue';

export default {
  name: "index.vue",
  setup() {
    const items = ref([
      { title: "Notion", category: "Docs", image: "/assets/image/notion.png", price: "Free", rating: "14870" },
      { title: "Figma", category: "UI & UX", image: "/assets/image/figma.png", price: "Free", rating: "9980" }
    ]);
    const selectedFilter = ref("newest");
    const selectedPrice = ref("all");
    const currentPage = ref(1);
    const itemsPerPage = 8;

    // 선택된 필터 라벨
    const filterLabel = computed(() => {
      return selectedFilter.value === "newest" ? "Newest" :
          selectedFilter.value === "popular" ? "Popular" :
              "Alphabetical";
    });

    // 선택된 가격 라벨
    const priceLabel = computed(() => {
      return selectedPrice.value === "all" ? "Free + Paid" :
          selectedPrice.value === "free" ? "Free" :
              "Paid";
    });

    // 필터링 로직
    const filteredItems = computed(() => {
      return items.value.filter((item) => {
        if (selectedPrice.value === "free") return item.price === "Free";
        if (selectedPrice.value === "paid") return item.price !== "Free";
        return true;
      }).slice((currentPage.value - 1) * itemsPerPage, currentPage.value * itemsPerPage);
    });

    // 전체 페이지 수 계산
    const totalPages = computed(() => Math.ceil(items.value.length / itemsPerPage));

    return { items, selectedFilter, selectedPrice, currentPage, itemsPerPage, filteredItems, totalPages };
  }
}
</script>

<template>
  <main>
    <div class="container mt-4">
      <!-- 제목 -->
      <h2 class="mb-3">Algorithm & Data Structures</h2>

      <!-- 필터 -->
      <div class="d-flex align-items-center">
        <label for="filter" class="me-2">Filter by:</label>

        <!-- 필터 드롭다운 -->
        <div class="custom-dropdown me-3" @click="toggleFilterDropdown">
          <span>{{ filterLabel }}</span>
          <select v-model="selectedFilter">
            <option value="newest">newest</option>
            <option value="popular">popular</option>
            <option value="alphabetical">alphabetical</option>
          </select>
        </div>

        <!-- 가격 필터 드롭다운 -->
        <div class="custom-dropdown" @click="togglePriceDropdown">
          <span>{{ priceLabel }}</span>
          <select v-model="selectedPrice">
            <option value="all">free + paid</option>
            <option value="free">free</option>
            <option value="paid">paid</option>
          </select>
        </div>
      </div>

      <!-- 카드 리스트 -->
      <div class="row">
        <div v-for="(item, index) in filteredItems" :key="index" class="col-md-3 col-sm-6 mb-4">
          <div class="card">
            <img :src="item.image" class="card-img-top" :alt="item.title">
            <div class="card-body">
              <h5 class="card-title">{{ item.title }}</h5>
              <p class="card-text text-muted">{{ item.category }}</p>
              <p class="text-warning" v-if="item.rating">⭐ ({{ item.rating }})</p>
              <p class="fw-bold" :class="{'text-success': item.price === 'Free', 'text-primary': item.price !== 'Free'}">
                {{ item.price }}
              </p>
              <button class="btn btn-outline-primary btn-sm">
                <i class="bi bi-bookmark"></i>
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- 페이지네이션 -->
      <nav>
        <ul class="pagination justify-content-center mt-3">
          <li v-for="page in totalPages" :key="page" class="page-item" :class="{ active: currentPage === page }">
            <button class="page-link" @click="currentPage = page">{{ page }}</button>
          </li>
        </ul>
      </nav>
    </div>
  </main>
</template>

<style scoped>

.card {
  border-radius: 10px;
  overflow: hidden;
}

.card img {
  height: 150px;
  object-fit: cover;
}

/* 공통 스타일 */
.custom-dropdown {
  position: relative;
  display: inline-block;
  cursor: pointer;
  padding-right: 20px;
  font-weight: 500;
}

/* 화살표 스타일 추가 */
.custom-dropdown::after {
  content: "▼";
  position: absolute;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
  font-size: 12px;
  color: #333;
}

/* select 숨기기 */
.custom-dropdown select {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  pointer-events: none;
}
</style>
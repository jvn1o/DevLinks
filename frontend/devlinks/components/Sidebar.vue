<template>
  <aside
      class="sidebar bg-white shadow position-fixed top-0 start-0 h-100 p-3"
      :class="{ 'show': isSidebarOpen }"
  >
    <div class="d-flex justify-content-between align-items-center mb-4">
      <div class="d-flex align-items-center mb-3 p-4">
        <img src="/assets/image/DevLinks.svg" class="me-2" width="32" />
        <h1 class="fs-2 p-1">Categories</h1>
      </div>
      <button class="btn-close" @click="toggleSidebar"></button>
    </div>
    <ul class="nav flex-column">
      <li class="nav-item p-3" v-for="(tab, index) in tabs" :key="index">
        <NuxtLink
            :to="`/category/${slugify(tab)}`"
            class="nav-link"
            @click="toggleSidebar"
        >
          {{ tab }}
        </NuxtLink>
      </li>
    </ul>
  </aside>
</template>

<script setup>
defineProps({
  isSidebarOpen: Boolean,
  toggleSidebar: Function,
  tabs: Array,
  slugify: Function
})
</script>

<style scoped>
.sidebar {
  width: 100%;
  transform: translateX(-100%);
  transition: transform 0.3s ease-in-out;
  z-index: 1050;
}
.sidebar.show {
  transform: translateX(0);
}
.sidebar .nav-link {
  display: inline;
  font-size: 2rem;
  font-weight: 500;
}
</style>

<script setup>
defineProps({
  isSidebarOpen: Boolean,
  toggleSidebar: Function,
  tabs: Array,
  slugify: Function,
  darkMode: Boolean,
})
</script>

<template>
  <aside
      class="sidebar p-3 position-fixed top-0 start-0 h-100"
      :class="{
        'bg-white': !darkMode,
        'bg-dark': darkMode,
        'show': isSidebarOpen
      }"
  >
    <div class="d-flex justify-content-between align-items-center mb-4">
      <div class="d-flex align-items-center mb-3 p-4">
        <img src="/assets/image/DevLinks.svg" class="me-2" width="32" />
        <h1 class="fs-2 p-1">Categories</h1>
      </div>
      <button
          class="btn-close custom-close mb-3 p-4"
          :class="{ 'dark' : darkMode }"
          @click="toggleSidebar"
      ></button>
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

.custom-close {
  width: 20px;
  height: 20px;
  background-size: 20px 20px;
}

.custom-close:focus {
  outline: none;
  box-shadow: none;
}

.btn-close.dark {
  filter: brightness(0) invert(1);
}

@media (max-width: 576px) {
  .sidebar .nav-link {
    font-size: 1.3rem;
  }
  .custom-close {
    width: 16px;
    height: 16px;
    background-size: 16px 16px;
  }
}
</style>

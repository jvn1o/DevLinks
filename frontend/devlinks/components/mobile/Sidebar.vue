<script setup>
import {ref, watch} from 'vue'
import DevLinks from "~/components/logo/DevLinks.vue";
import {useRoute} from 'vue-router'

const props = defineProps({
  isSidebarOpen: Boolean,
  toggleSidebar: Function,
  tabs: Array,
  slugify: Function,
  darkMode: Boolean,
  navigateWithAuth: Function,
})

const route = useRoute()
const isActiveTab = (tab) => route.path === `/links/category/${slugify(tab)}`
const isActiveRoute = (path) => route.path === path

const asideRef = ref(null)

watch(
    () => props.darkMode,
    (newVal) => {
      if (!asideRef.value) return
      if (newVal) {
        asideRef.value.classList.add('dark-mode')
      } else {
        asideRef.value.classList.remove('dark-mode')
      }
    },
    {immediate: true}
)

// 휠 내림 방지
watch(
    () => props.isSidebarOpen,
    (isOpen) => {
      if (isOpen) {
        document.body.style.overflow = 'hidden'
      } else {
        document.body.style.overflow = ''
      }
    }
)
</script>

<template>
  <aside
      ref="asideRef"
      class="sidebar p-2 position-fixed top-0 start-0 h-100"
      :class="{ show: isSidebarOpen }"
      role="navigation"
      aria-label="Sidebar navigation"
  >
    <div class="d-flex justify-content-between align-items-center">
      <div class="d-flex align-items-center p-4 gap-3">
        <DevLinks/>
      </div>
      <button
          class="btn-close p-4"
          @click="toggleSidebar"
          aria-label="Close sidebar"
      />
    </div>

    <div class="nav-item px-4 text-end">
      <hr class="mt-1 mb-2"/>
      <i class="bi bi-tags"></i>
      <span
          class="fw-bold"
          style="color: #004085;"
      >
          CATEGORIES
        </span>
    </div>

    <!-- Tab   -->
    <ul class="nav flex-column">
      <li
          class="nav-item p-3"
          v-for="(tab, index) in tabs"
          :key="index"
          @click="toggleSidebar"
      >
        <NuxtLink
            :to="`/links/category/${slugify(tab)}`"
            class="nav-link"
            :class="{ active: isActiveTab(tab) }"
        >
          {{ tab }}
        </NuxtLink>
      </li>

      <li class="nav-item px-4 mt-2 text-end">
        <hr class="mt-1 mb-2"/>
        <i class="bi bi-person-circle"></i>
        <span
            class="fw-bold"
            style="color: #004085;"
        >
          MY PAGE
        </span>
      </li>

      <li class="nav-item p-3"
          @click="toggleSidebar"
      >
        <button
            class="nav-link bg-transparent border-0 text-start w-100"
            @click="navigateWithAuth('/member')"
            :class="{ active: isActiveRoute('/member') }"
        >
          Profile
        </button>
      </li>

      <li class="nav-item p-3"
          @click="toggleSidebar"
      >
        <button
            class="nav-link bg-transparent border-0 text-start w-100"
            @click="navigateWithAuth('/member/links')"
            :class="{ active: isActiveRoute('/member/links') }"
        >
          Links
        </button>
      </li>

      <li class="nav-item p-3"
          @click="toggleSidebar"
      >
        <button
            class="nav-link bg-transparent border-0 text-start w-100"
            @click="navigateWithAuth('/member/bookmarks')"
            :class="{ active: isActiveRoute('/member/bookmarks') }"
        >
          Bookmarks
        </button>
      </li>

      <li class="nav-item p-3"
          @click="toggleSidebar"
      >
        <button
            class="nav-link bg-transparent border-0 text-start w-100"
            @click="navigateWithAuth('/member/alarms')"
            :class="{ active: isActiveRoute('/member/alarms') }"
        >
          Alarms
        </button>
      </li>

    </ul>

  </aside>

  <Overlay
      :visible="isSidebarOpen"
      :onClick="toggleSidebar"
  />
</template>

<style scoped>
.sidebar {
  width: 100%;
  background-color: var(--bg-main);
  color: var(--text-main);
  transform: translateX(-100%);
  transition: transform 0.3s ease-in-out;
  z-index: 1050;

  .p-3 {
    padding: 0.9rem !important;
  }
}

.sidebar.show {
  transform: translateX(0);
}

.sidebar .nav-link {
  font-size: 2rem;
  font-weight: 500;
  color: var(--text-main);
}

.sidebar .nav-link:hover {
  color: var(--text-primary);
}

.active {
  border-left: 4px solid var(--text-primary) !important;
  font-weight: 600;
  color: var(--text-active) !important;
  padding-left: 12px;
}

.btn-close {
  width: 20px;
  height: 20px;
  background-size: 20px 20px;
  filter: var(--icon-filter);
}

.btn-close:focus {
  outline: none;
  box-shadow: none;
}

@media (max-width: 576px) {
  .sidebar .nav-link {
    font-size: 1.1rem;
  }

  .btn-close {
    width: 16px;
    height: 16px;
    background-size: 16px 16px;
  }
}

</style>

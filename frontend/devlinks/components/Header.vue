<script setup>
import {computed, ref} from 'vue'
import Sidebar from "~/components/mobile/Sidebar.vue";
import LoginPromptModal from '~/components/LoginPromptModal.vue'
import {useWindowSize} from '@vueuse/core'
import useMemberDetails from '~/composables/useMemberDetails'
import defaultProfileImg from 'assets/image/icon/default_profile.svg'
import {slugify} from '~/utils/slugify.js'

// 다크모드 상태를 props로 전달받음
const props = defineProps({darkMode: Boolean,})
const emit = defineEmits(['update:darkMode'])

const { width } = useWindowSize()
const isMobileDevice = computed(() => width.value < 1193.75)

// member 상태 가져오기
const {
  isAnonymous,
  profileImgSrc,
} = useMemberDetails()

const resolvedProfileImg = computed(() =>
    isAnonymous() || !profileImgSrc ? defaultProfileImg : profileImgSrc
)

const tabs = [
  "Algorithm & Data Structures",
  "API & Documentation",
  "Cloud & DevOps",
  "Testing & QA",
  "UI / UX"
];

const isSidebarOpen = ref(false)
const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value
}

// 로그인 유도 모달 참조
const modalRef = ref()

// 프로필 이동
const handleProfileClick = async () => {
  if (isAnonymous()) {
    await modalRef.value?.show()
    return
  }
  router.push('/member/index')
}

// 북마크 이동
const goToBookmarks = async () => {
  if (isAnonymous()) {
    const confirm = await modalRef.value?.show()
    return
  }
  router.push('/member/bookmarks')
}

// 알림 이동
const goToAlarms = async () => {
  if (isAnonymous()) {
    const confirm = await modalRef.value?.show()
    return
  }
  router.push('/member/alrams')
}

// 글 작성 이동
const goToPost = async () => {
  if (isAnonymous()) {
    const confirm = await modalRef.value?.show()
    return
  }
  router.push('/links/create')
}
</script>

<template>
  <nav :class="['navbar p-0 w-auto', { 'navbar-dark bg-dark': darkMode }]">
    <!-- 아이콘 (프로필, 북마크, 알람) -->
    <div
        v-if="!isMobileDevice"
        class="d-flex justify-content-center align-items-center p-2 border-end"
        :style="{'border-color': darkMode ? '#495057' : '#CED3D9'}"
    >
      <!-- 프로필 -->
      <div class="w-32 h-32 p-1">
        <button
            @click="handleProfileClick"
            class="btn p-0 border-0 bg-transparent"
            aria-label="프로필"
        >
          <img
              :src="resolvedProfileImg"
              alt="Profile"
              width="32"
              height="32"
              class="rounded-circle"
          />
        </button>
      </div>

      <!-- 북마크 아이콘 -->
      <div class="w-25 h-25 p-1">
        <button @click="goToBookmarks" class="btn p-0 border-0 bg-transparent me-3">
          <img
              src="/assets/image/icon/bookmark.svg"
              alt="Bookmark"
              width="30"
              height="30"
              :class="{'icon-dark': darkMode}"
          />
        </button>
      </div>

      <!-- 알람 아이콘 -->
      <div class="w-25 h-25 p-1">
        <button @click="goToAlarms" class="btn p-0 border-0 bg-transparent me-3">
          <img
              src="/assets/image/icon/alarm.svg"
              alt="Alarm"
              width="30"
              height="30"
              :class="{'icon-dark': darkMode}"
          />
        </button>
      </div>
    </div>

    <!-- 로고 (홈 이동) -->
    <div
        class="d-flex align-items-stretch ps-3 border-end"
        :style="{'height': '56px', 'border-color': darkMode ? '#495057' : '#CED3D9'}"
    >
      <NuxtLink to="/" class="navbar-brand d-flex align-items-center">
        <img
            src="/assets/image/DevLinks.svg"
            alt="Logo"
            width="28"
            height="32"
        />
      </NuxtLink>
    </div>

    <!-- 네비게이션 탭 -->
    <div class="container-fluid p-0">
      <!-- 햄버거 버튼: 모바일일 때만 표시 -->
      <button
          v-if="isMobileDevice"
          class="navbar-toggler custom-toggler ps-3"
          :class="{'icon-dark': darkMode}"
          :style="{'border': 'none', 'padding': '4px 6px'}"
          type="button"
          @click="toggleSidebar"
      >
        <img src="/image/icon/hamburger.svg" alt="Menu" width="32" height="32" />
      </button>

      <!-- 네비게이션 탭: 항상 렌더링되게, 조건부 렌더링 X -->
      <div v-if="!isMobileDevice" class="ps-2">
        <ul class="navbar-nav d-flex flex-row">
          <li v-for="(tab, index) in tabs" :key="index" class="nav-item px-2">
            <NuxtLink
                :to="`/links/category/${slugify(tab)}`"
                class="nav-link fw-bold text-primary"
            >
              {{ tab }}
            </NuxtLink>
          </li>
        </ul>
      </div>
    </div>

    <!-- 사이드바 -->
    <Sidebar
        v-if="isMobileDevice"
        :isSidebarOpen="isSidebarOpen"
        :toggleSidebar="toggleSidebar"
        :tabs="tabs"
        :slugify="slugify"
        :darkMode="darkMode"
    />

    <!-- 글 작성 -->
    <button @click="goToPost" class="ms-auto navbar-brand btn p-0 border-0 bg-transparent">
      <img src="/assets/image/icon/post.svg" alt="글 작성" width="90" height="20" />
    </button>

    <LoginPromptModal ref="modalRef" />
  </nav>
</template>

<style scoped>

/* 기본 네비게이션 스타일 */
.navbar {
  box-shadow: 0px 2px 4px rgba(206, 211, 217, 0.2);
  flex-wrap: nowrap;
  border-bottom: 1px solid #CED3D9;
}

/* 기본 nav-link 스타일 */
.navbar-nav .nav-link {
  font-weight: 100;
  transition: color 0.3s;
  padding-right: 15px;
}

.nav-link:hover {
  color: #0d6efd !important;
}

.navbar-brand img,
.d-flex img {
  cursor: pointer;
}

/* 햄버거 버튼 포커스 시 테두리 제거 */
.navbar-toggler:focus {
  outline: none;
  box-shadow: none;
}

/* 다크모드일 때 아이콘 색상 반전 */
.icon-dark {
  filter: brightness(0) invert(1);
}
</style>

<script setup>
import {computed, ref} from 'vue'
import {useRoute} from 'vue-router'
import Sidebar from "~/components/mobile/Sidebar.vue";
import LoginPromptModal from '~/components/LoginPromptModal.vue'
import {useWindowSize} from '@vueuse/core'
import useMemberDetails from '~/composables/useMemberDetails'
import defaultProfileImg from 'assets/image/icon/default_profile.svg'
import {slugify} from '~/utils/slugify.js'

const props = defineProps({
  darkMode: Boolean,
  hideOnScroll: Boolean
})

const route = useRoute()

const {width} = useWindowSize()
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

const isActiveTab = (tab) => route.path === `/links/category/${slugify(tab)}`


const isSidebarOpen = ref(false)
const toggleSidebar = () => {
  isSidebarOpen.value = !isSidebarOpen.value
}

// 로그인 유도 모달 참조
const modalRef = ref(null)
const {navigateWithAuth} = useAuthRedirect(isAnonymous, modalRef)

// 프로필 이동
const handleProfileClick = () => navigateWithAuth('/member/my')

// 내가 쓴 글 이동
const goToLinks = () => navigateWithAuth('/member/my/links')

// 북마크 이동
const goToBookmarks = () => navigateWithAuth('/member/my/bookmarks')

// 알림 이동
const goToAlarms = () => navigateWithAuth('/member/my/alarms')

// 글 작성 이동
const goToPost = () => navigateWithAuth('/member/links/create')
</script>

<template>
  <!-- 로그인 모달 랜더링 시간을 위해 맨위에 배치-->
  <LoginPromptModal ref="modalRef" :darkMode="darkMode"/>

  <header :class="['main-header', { 'hide-on-scroll': hideOnScroll }]">
    <nav class="navbar p-0 w-auto">
      <!-- 아이콘 (프로필, 북마크, 알람) -->
      <div
          v-if="!isMobileDevice"
          class="d-flex justify-content-center align-items-center"
          style="border-color: var(--card-border)"
      >
        <!-- 프로필 -->
        <div class="w-32 ps-2">
          <button
              @click="handleProfileClick"
              class="btn p-0 border-0 bg-transparent"
              aria-label="프로필"
          >
            <img
                :src="resolvedProfileImg"
                alt="Profile"
                width="25"
                height="25"
                class="rounded-circle img-non-filter"
            />
          </button>
        </div>

        <!-- 내가 쓴 글 아이콘 -->
        <div class="w-32 ps-2">
          <button @click="goToLinks" class="btn p-0 border-0 bg-transparent">
            <i class="bi bi-journals fs-5"
               :class="darkMode ? 'text-white' : 'text-dark'"
            />
          </button>
        </div>

        <!-- 북마크 아이콘 -->
        <div class="w-32 ps-2">
          <button @click="goToBookmarks" class="btn p-0 border-0 bg-transparent">
            <i class="bi bi-bookmark fs-5"
               :class="darkMode ? 'text-white' : 'text-dark'"
            />
          </button>
        </div>

        <!-- 알람 아이콘 -->
        <div class="w-32 px-2">
          <button @click="goToAlarms" class="btn p-0 border-0 bg-transparent">
            <i class="bi bi-bell fs-5"
               :class="darkMode ? 'text-white' : 'text-dark'"
            />
          </button>
        </div>
      </div>

      <!-- 로고 (홈 이동) -->
      <div
          class="d-flex align-items-stretch ps-3 border-start border-end"
          style="height: 56px; border-color: var(--card-border)"
      >
        <NuxtLink to="/" class="navbar-brand d-flex align-items-center">
          <img
              class="img-non-filter"
              src="/assets/image/DevLinks.svg"
              alt="Logo"
              width="26"
              height="26"
          />
        </NuxtLink>
      </div>

      <!-- 네비게이션 탭 -->
      <div class="container-fluid p-0">
        <!-- 햄버거 버튼: 모바일일 때만 표시 -->
        <button
            v-if="isMobileDevice"
            class="navbar-toggler custom-toggler ps-3"
            :style="{'border': 'none', 'padding': '4px 6px'}"
            type="button"
            @click="toggleSidebar"
        >
          <img src="/image/icon/hamburger.svg" alt="Menu" width="32" height="32"/>
        </button>

        <!-- 네비게이션 탭: 항상 렌더링되게, 조건부 렌더링 X -->
        <div class="ps-2">
          <div v-if="!isMobileDevice">
            <ul class="navbar-nav d-flex flex-row">
              <li v-for="(tab, index) in tabs" :key="index" class="nav-item">
                <NuxtLink
                    :to="`/links/category/${slugify(tab)}`"
                    :class="[
                    'nav-link fw-bold text-primary d-flex',
                    { active : isActiveTab(tab) }
                  ]"
                >
                  {{ tab }}
                  <div class="active-dot-wrapper">
                    <span v-if="isActiveTab(tab) "
                          class="active-dot"
                    />
                  </div>
                </NuxtLink>
              </li>
            </ul>
          </div>
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
          :navigateWithAuth="navigateWithAuth"
      />

      <!-- 글 작성 -->
      <button
          @click="goToPost"
          class="d-flex align-items-center ms-auto navbar-brand btn p-0 border-0 bg-transparent"
      >
        <i v-if="!darkMode" class="bi bi-pencil-square fs-5" />

        <i v-else class="bi bi-pencil-square fs-5"
           :class="darkMode ? 'text-white' : 'text-dark'"
        />
      </button>
    </nav>
  </header>
</template>

<style scoped>
.main-header {
  position: sticky;
  top: 0;
  z-index: 999;
  transition: transform 0.3s ease-in-out;
  background-color: var(--bg-main);
}

.active-dot-wrapper {
  width: 10px;
  height: 10px;

  .active-dot {
    display: inline-block;
    top: 50%;
    width: 3px;
    height: 3px;
    margin-left: 6px;
    background-color: #0d6efd;
    border-radius: 50%;
    vertical-align: top;
  }
}

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
  padding: 0 8px;
}

.nav-link:hover {
  color: #0d6efd !important;
}

/* 활성화된 nav-link 스타일 */
.navbar-nav .nav-link.active {
  color: var(--text-active) !important;
  text-shadow: 0 0 1px rgba(13, 110, 253, 0.4);
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

/* 아래로 스크롤 시 숨김 */
.hide-on-scroll {
  transform: translateY(-100%);
}
</style>

<template>
  <footer
      :class="{
    'n-footer-dark': darkMode,
    'n-footer-light': !darkMode} "
      class="n-footer py-5 mt-5"
  >
    <div class="container">
      <div class="row">
        <!-- Product Section -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Product</h5>
          <ul class="list-unstyled">
            <li><NuxtLink to="/features" class="text-decoration-none">Features</NuxtLink></li>
            <li><NuxtLink to="/docs" class="text-decoration-none">Documentation</NuxtLink></li>
            <li><NuxtLink to="/blog" class="text-decoration-none">Blog</NuxtLink></li>
          </ul>
        </div>

        <!-- Community Section -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Community</h5>
          <ul class="list-unstyled">
            <li>
              <a href="https://github.com/jvn1o" target="_blank" class="text-decoration-none">
                GitHub
              </a>
            </li>
            <li>
              <a href="https://discord.gg/yourcommunity" target="_blank" class="text-decoration-none">
                Discord
              </a>
            </li>
            <li>
              <a href="https://www.notion.so/9abf00bcde874b929db4315dda03238f?pvs=4" target="_blank" class="text-decoration-none">
                Notion
              </a>
            </li>
          </ul>
        </div>

        <!-- Legal Section -->
        <div class="col-md-3 mb-4">
          <h5 class="fw-bold text-primary">Legal</h5>
          <ul class="list-unstyled">
            <li><NuxtLink to="/terms" class="text-decoration-none">Terms</NuxtLink></li>
            <li><NuxtLink to="/privacy" class="text-decoration-none">Privacy</NuxtLink></li>
          </ul>
        </div>

        <!-- DarkMode + Language -->
        <div class="col-md-3 mb-4">
          <h6 class="fw-bold text-primary">Theme</h6>
          <!-- 다크 모드 토글 버튼 -->
          <div class="toggle-wrapper mt-4">
            <label class="switch w-25">
              <input type="checkbox" @change="toggleDarkMode" :checked="darkMode" />
              <span class="slider round">
                <i v-if="darkMode" class="bi bi-moon"></i>
                <i v-if="!darkMode" class="bi bi-sun"></i>
              </span>
            </label>
          </div>

          <div class="mt-4">
            <label for="lang" class="fw-bold text-primary form-label">Language</label>
            <select id="lang" class="form-select">
              <option>Korean</option>
              <option>English</option>
            </select>
          </div>
        </div>
      </div>

      <!-- Bottom Section -->
      <div class="text-center mt-5">
        <div class="d-flex justify-content-center gap-3 mb-3">
          <a href="https://github.com/jvn1o" target="_blank">
            <img src="/assets/image/icon/github.svg" alt="GitHub" width="24" />
          </a>
          <a href="https://discord.gg/yourcommunity" target="_blank">
            <img src="/assets/image/icon/discord.svg" alt="Discord" width="24" />
          </a>
        </div>

        <small class="text-muted">
          © {{ new Date().getFullYear() }} DevLinks. Built by jvn1o_
        </small>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { computed, watch, onMounted, defineEmits, defineProps } from 'vue';

// 부모로부터 받은 다크 모드 상태
const props = defineProps({
  darkMode: Boolean,
});

// 부모로 이벤트 전송
const emit = defineEmits(['update:darkMode']);

// ✅ computed: darkMode 값을 기반으로 UI에서 사용할 상태 계산
const isDark = computed(() => props.darkMode);

// ✅ toggle 함수: 다크 모드 상태를 반전시켜 부모에게 전달
const toggleDarkMode = () => {
  emit('update:darkMode', !props.darkMode);
};

// ✅ watch: darkMode 상태가 변경될 때 side effect 처리
watch(
    () => props.darkMode,
    (newVal) => {
      // 클라이언트에서만 실행
      if (process.client) {
        // HTML에 클래스 적용
        document.documentElement.classList.toggle('dark-mode', newVal);
        // 로컬 스토리지에 저장
        localStorage.setItem('darkMode', newVal.toString());
      }
    },
    { immediate: true } // 마운트 시에도 즉시 실행
);

// ✅ 최초 마운트 시 저장된 모드 불러오기
onMounted(() => {
  if (process.client) {
    const savedMode = localStorage.getItem('darkMode');
    if (savedMode !== null) {
      emit('update:darkMode', savedMode === 'true');
    }
  }
});
</script>

<style scoped>
.n-footer-dark {
  background-color: #212529;
  color: #e9ecef;
}

/* 링크 색상 - 상태에 따라 */
.n-footer-dark a {
  color: #adb5bd;
}

.n-footer-dark a:hover {
  color: #ffffff;
  text-decoration: underline;
}

/* 셀렉트 박스 (언어 선택) */
select.form-select {
  font-size: 0.9rem;
  transition: background-color 0.3s, color 0.3s, border 0.3s;
}

.n-footer-dark select.form-select {
  background-color: #343a40;
  color: #e9ecef;
  border: 1px solid #495057;
}

/* 다크 모드 토글 스위치 */
.toggle-wrapper {
  position: relative;
}

.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  transition: 0.4s;
  border-radius: 34px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  border-radius: 50%;
  left: 4px;
  bottom: 4px;
  background-color: white;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #4cd137;
}

input:checked + .slider:before {
  transform: translateX(26px);
}

/* 아이콘 위치 및 색상 */
.slider i {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  font-size: 1.3rem;
  pointer-events: none;
}

.slider i.bi-sun {
  right: 8px;
  color: #f39c12;
}

.slider i.bi-moon {
  left: 9px;
  color: #f1c40f;
}

/* 다크모드에서 muted 텍스트도 밝게 */
.n-footer-dark .text-muted {
  color: #adb5bd !important;
}

.n-footer-light .text-muted {
  color: #6c757d !important;
}

.n-footer-dark img {
  filter: brightness(0) invert(1);
}
</style>

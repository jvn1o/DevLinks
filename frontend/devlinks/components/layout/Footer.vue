<script setup>
import { defineProps, defineEmits } from 'vue'

const props = defineProps({
  darkMode: Boolean,
})
const emit = defineEmits(['update:darkMode'])

const toggleDarkMode = () => {
  emit('update:darkMode', !props.darkMode)
}
</script>

<template>
  <footer
      class="n-footer py-5"
  >
    <div class="container">
      <div class="row">
        <!-- Product Section -->
        <div class="col-md-3 mb-4 ps-3">
          <h5 class="fw-bold text-primary">Product</h5>
          <ul class="list-unstyled">
            <li>
              <NuxtLink to="/features" class="text-decoration-none">Features</NuxtLink>
            </li>
            <li>
              <NuxtLink to="/docs" class="text-decoration-none">Documentation</NuxtLink>
            </li>
            <li>
              <NuxtLink to="/blog" class="text-decoration-none">Blog</NuxtLink>
            </li>
          </ul>
        </div>

        <!-- Community Section -->
        <div class="col-md-3 mb-4 ps-3">
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
              <a href="https://www.notion.so/DevLinks-0585a337df2145c4abbb59a6e9f273ed" target="_blank"
                 class="text-decoration-none">
                Notion
              </a>
            </li>
          </ul>
        </div>

        <!-- Legal Section -->
        <div class="col-md-3 mb-4 ps-3">
          <h5 class="fw-bold text-primary">Legal</h5>
          <ul class="list-unstyled">
            <li>
              <NuxtLink to="/terms" class="text-decoration-none">Terms</NuxtLink>
            </li>
            <li>
              <NuxtLink to="/privacy" class="text-decoration-none">Privacy</NuxtLink>
            </li>
          </ul>
        </div>

        <!-- DarkMode + Language -->
        <div class="col-md-3 mb-4 ps-3">
          <h6 class="fw-bold text-primary">Theme</h6>
          <!-- 다크 모드 토글 버튼 -->
          <div class="toggle-wrapper mt-4">
            <label class="switch">
              <input type="checkbox" @change="toggleDarkMode" :checked="darkMode"/>
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
            <img src="/assets/image/icon/github.svg" alt="GitHub" width="24"/>
          </a>
          <a href="https://discord.gg/yourcommunity" target="_blank">
            <img src="/assets/image/icon/discord.svg" alt="Discord" width="24"/>
          </a>
        </div>

        <small class="text-muted">
          © {{ new Date().getFullYear() }} DevLinks. Built by jvn1o_
        </small>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.toggle-wrapper {
  position: relative;
}

.switch {
  position: relative;
  display: inline-block;
  width: clamp(48px, 20vw, 60px); /* 최소 48px, 최대 60px */
  height: clamp(26px, 10vw, 34px); /* 최소 26px, 최대 34px */
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
  transition: background-color 0.4s ease;
  border-radius: 9999px; /* pill shape */
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 6px;
}

.slider:before {
  content: "";
  position: absolute;
  height: 60%;
  aspect-ratio: 1;
  border-radius: 50%;
  left: 4px;
  background-color: white;
  transition: transform 0.3s ease;
}

input:checked + .slider {
  background-color: #4cd137;
}

input:checked + .slider:before {
  transform: translateX( calc(100% + 10px) );
}

/* 아이콘 스타일 */
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

/* 다크 모드 */
.n-footer {
  background-color: var(--footer-bg);
  color: var(--text-main);
}

.n-footer a {
  color: var(--link-color);
  transition: color 0.2s;
}

.n-footer a:hover {
  color: var(--link-hover-color);
  text-decoration: underline;
}

.n-footer select.form-select {
  background-color: var(--select-bg);
  color: var(--select-text);
  border: 1px solid var(--select-border);
}

.n-footer .text-muted {
  color: var(--text-subtle) !important;
}

.n-footer img {
  filter: var(--icon-filter);
}

/* --------------------------------------------------- */

/* 셀렉트 박스 (언어 선택) */
select.form-select {
  font-size: 0.9rem;
  transition: background-color 0.3s, color 0.3s, border 0.3s;
}

@media (max-width: 576px) {
  .n-footer.py-5 {
    padding-top: 0 !important;
  }
}
</style>

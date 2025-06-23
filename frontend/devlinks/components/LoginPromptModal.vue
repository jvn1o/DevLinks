<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const props = defineProps({
  darkMode: Boolean
})

const visible = ref(false)
const resolveCallback = ref(null)

const router = useRouter()

const show = () => {
  visible.value = true
  return new Promise((resolve) => {
    resolveCallback.value = resolve
  })
}

const onConfirm = () => {
  visible.value = false
  resolveCallback.value?.(true)
}

const onCancel = () => {
  visible.value = false
  resolveCallback.value?.(false)
}

defineExpose({ show })

// 휠 내림 방지
watch(visible, (val) => {
  if (val) {
    document.body.style.overflow = 'hidden'
  } else {
    document.body.style.overflow = ''
  }
})
</script>

<template>
  <div
      v-if="visible"
      class="position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center z-3 p-3"
      :class="{ 'dark-mode': darkMode }"
  >
    <div class="login-modal-box">
      <div class="text-center mb-3">
        <h6 class="fw-semibold mb-0">로그인이 필요합니다</h6>
      </div>
      <div class="mb-3 text-center">
        <p class="mb-0">
          해당 기능은 로그인 후 이용할 수 있습니다.<br />
          로그인하시겠습니까?
        </p>
      </div>
      <div class="d-flex flex-column flex-sm-row justify-content-center gap-4">
        <button class="btn btn-primary small-btn" @click="onConfirm">로그인</button>
        <button class="btn btn-secondary small-btn" @click="onCancel">취소</button>
      </div>
    </div>
  </div>

  <Overlay :visible="visible" :onClick="onCancel" />
</template>

<style scoped>
.login-modal-box {
  background-color: var(--bg-modal);
  color: var(--text-main);
  border-radius: 0.75rem;
  box-shadow: 0 0 12px rgba(0, 0, 0, 0.25);
  padding: 0.85rem;
  width: 100%;
  max-width: 290px;
  animation: fadeIn 0.25s ease;
  font-size: 0.75rem;
}

.small-btn {
  font-size: 0.75rem;
  padding: 0.4rem 1.1rem;
  border-radius: 0.375rem;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>

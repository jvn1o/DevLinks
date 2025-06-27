<script setup lang="ts">
import { ref, watch } from 'vue'

const props = defineProps({
  darkMode: Boolean
})

const visible = ref(false)
const resolveCallback = ref<((confirmed: boolean) => void) | null>(null)

const show = () => {
  visible.value = true
  return new Promise<boolean>((resolve) => {
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

watch(visible, (val) => {
  document.body.style.overflow = val ? 'hidden' : ''
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
        <h6 class="fw-semibold mb-0">정말 삭제하시겠습니까?</h6>
      </div>
      <div class="mb-3 text-center">
        <p class="mb-0">삭제된 항목은 복구할 수 없습니다.</p>
      </div>
      <div class="d-flex flex-column flex-sm-row justify-content-center gap-2">
        <button class="btn btn-danger small-btn" @click="onConfirm">삭제</button>
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
  max-width: 300px;
  animation: fadeIn 0.25s ease;
  font-size: 0.85rem;
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

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
  router.push(`/login?redirect=${encodeURIComponent(window.location.pathname)}`)
}

const onCancel = () => {
  visible.value = false
  resolveCallback.value?.(false)
}

defineExpose({ show })
</script>

<template>
  <div
      v-if="visible"
      :class="[
      'position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center z-3 p-3',
      darkMode ? 'bg-dark bg-opacity-75' : 'bg-dark bg-opacity-50'
    ]"
  >
    <div
        :class="[
        'p-4 w-100',
        'rounded-3',
        'shadow-lg',
        'animation-fadeIn',
        darkMode ? 'bg-secondary text-light' : 'bg-white text-dark'
      ]"
        style="max-width: 450px; font-size: 1.1rem;"
    >
      <div class="text-center mb-3">
        <h5 class="fw-semibold mb-0">로그인이 필요합니다</h5>
      </div>
      <div class="mb-3 text-center">
        <p class="mb-0">
          해당 기능은 로그인 후 이용할 수 있습니다.<br />
          로그인하시겠습니까?
        </p>
      </div>
      <div class="d-flex flex-column flex-sm-row justify-content-end gap-2">
        <button class="btn btn-primary w-100 w-sm-auto" @click="onConfirm">로그인</button>
        <button class="btn btn-secondary w-100 w-sm-auto" @click="onCancel">취소</button>
      </div>
    </div>
  </div>
</template>


<style scoped>
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

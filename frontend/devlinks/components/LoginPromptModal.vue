<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

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
  <div class="modal-backdrop" v-if="visible">
    <div class="modal-container">
      <div class="modal-header">
        <h4 class="modal-title">로그인이 필요합니다</h4>
      </div>
      <div class="modal-body">
        <p>해당 기능은 로그인 후 이용할 수 있습니다.
          <br/>로그인하시겠습니까?
        </p>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" @click="onConfirm">로그인</button>
        <button class="btn btn-secondary" @click="onCancel">취소</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
  padding: 1rem;
}

.modal-container {
  background: white;
  padding: 25px;
  width: 100%;
  max-width: 450px;
  font-size: 1.1rem;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.2);
  animation: fadeIn 0.3s ease;

  .modal-header{
    justify-content: center;

    .modal-title {
      font-weight: bold;
      margin-bottom: 0;
    }
  }

  .modal-body {
    margin: 15px 0;
    text-align: center;
  }

  .modal-footer {
    display: flex;
    justify-content: flex-end;
    gap: 10px;

    .btn {
      padding: 0.55rem 1.1rem;
      font-size: 1.125rem;
      font-weight: 600;
      border-radius: 8px;
    }
  }
}

/* 반응형 */
@media (max-width: 576px) {
  .modal-container {
    height: auto;
    padding: 1.5rem 1rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }

  .modal-footer {
    width: 100%;
    flex-direction: column;
    gap: 0.5rem;
  }

  .btn {
    width: 100%;
  }
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


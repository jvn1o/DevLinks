<template>
  <div v-if="visible" class="toast-notification">
    {{ message }}
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'

const props = defineProps({
  message: String,
  duration: { type: Number, default: 3000 }
})

const visible = ref(false)

watch(() => props.message, (newVal) => {
  if (newVal) {
    visible.value = true
    setTimeout(() => {
      visible.value = false
    }, props.duration)
  }
})
</script>

<style scoped>
.toast-notification {
  position: fixed;
  top: 60px;
  right: 20px;
  background: #333;
  color: white;
  padding: 12px 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0,0,0,0.3);
  z-index: 9999;
  font-weight: 600;
}
</style>

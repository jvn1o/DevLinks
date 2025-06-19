<script setup>
const props = defineProps({
  visible: Boolean,
  onClick: Function,
})
</script>

<template>
  <div
      v-if="visible"
      class="overlay position-fixed top-0 end-0 h-100"
      @click="onClick"
      aria-hidden="true"
  />
</template>

<style scoped>
.overlay {
  width: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  cursor: pointer;
  z-index: 1040;
  top: 0;
  right: 0;
  position: fixed;
  height: 100vh;
}
</style>

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
  position: fixed;
  inset: 0; /* top/right/bottom/left 0 */
  background-color: rgba(0, 0, 0, 0.45);
  cursor: pointer;
  z-index: 1;
}
</style>

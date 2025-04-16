<script setup>
defineProps({
  item: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div class="card h-100">
    <div class="card-img-wrapper">
      <img :src="item.image" class="card-img-top" :alt="item.title" />
    </div>
    <div class="card-body d-flex flex-column">
      <h5 class="card-title fs-6 text-truncate" :title="item.title">
        {{ item.title }}
      </h5>
      <p class="card-text text-muted text-truncate">
        {{ item.category }}
      </p>
      <p class="text-warning" v-if="item.rating">
        <span class="bi bi-bookmark"></span> ({{ item.rating }})
      </p>
      <p
          class="fw-bold mt-auto"
          :class="{
          'text-danger': item.price !== 'Free',
          'text-primary': item.price === 'Free',
        }"
      >
        {{ item.price }}
      </p>
      <button class="btn btn-sm">
        <i class="bi bi-bookmark"></i>
      </button>
    </div>
  </div>
</template>

<style scoped>
.card-img-wrapper img {
  height: 150px;
  object-fit: cover;
}
</style>
<script setup>
defineProps({
  item: {
    type: Object,
    required: true,
  },
});
</script>

<template>
  <div class="card h-100 hover-card">
    <div class="card-img-wrapper">
      <img :src="item.image" class="card-img-top" :alt="item.title"/>
    </div>
    <div class="card-body d-flex flex-column">
<!--      <div class="d-flex justify-content-between">-->
<!--        <div>-->
<!--          <h5 class="card-title fs-6 text-truncate fw-bold " :title="item.title">-->
<!--            {{ item.title }}-->
<!--          </h5>-->
<!--          <p class="card-text text-muted text-truncate">-->
<!--            {{ item.category }}-->
<!--          </p>-->
<!--        </div>-->
<!--        <button class="btn">-->
<!--          <i class="bi bi-bookmark"></i>-->
<!--        </button>-->
<!--      </div>-->
      <div class="d-flex flex-column">
        <h5 class="card-title fs-6 text-truncate fw-bold" :title="item.title">
          {{ item.title }}
        </h5>
        <div class="d-flex justify-content-between align-items-center">
          <p class="card-text text-muted text-truncate mb-0">
            {{ item.category }}
          </p>
          <button class="btn p-0 ms-2 flex-shrink-0">
            <i class="bi bi-bookmark"></i>
          </button>
        </div>
      </div>
      <div class="d-flex justify-content-between mt-2">
        <div v-if="item.rating">
          <span class="bi bi-bookmark text-warning"></span> ({{ item.rating }})
        </div>
        <div
            class="fw-bold mt-auto"
            :class="{
            'text-danger': item.price !== 'Free'
          }"
        >
          {{ item.price }}
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.card-img-wrapper img {
  height: 225px;
  object-fit: cover;
}

.hover-card {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.hover-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  background-color: #f8f9fa;
}
</style>
<script setup lang="ts">
import { ref, watch } from 'vue';

const emit = defineEmits(['update-filters']);

// 선택된 필터들 저장
const selectedFilters = ref({
  category: 'All',
  pricing: 'All',
  sort: 'Popularity'
});

// 카테고리 필터 토글
const toggleCategory = (category) => {
  if (selectedFilters.value.category.includes(category)) {
    selectedFilters.value.category = selectedFilters.value.category.filter(item => item !== category);
  } else {
    selectedFilters.value.category.push(category);
  }
  emit('update-filters', selectedFilters.value);
};

// 가격 필터 변경
const updatePricing = (event) => {
  selectedFilters.value.pricing = event.target.value;
  emit('update-filters', selectedFilters.value);
};

// 정렬 필터 변경
const updateSort = (event) => {
  selectedFilters.value.sort = event.target.value;
  emit('update-filters', selectedFilters.value);
};
</script>

<template>
  <div class="container p-3 border rounded">
    <div class="row mt-3">
      <!-- Category 필터 -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Category</legend>
          <div class="form-check" v-for="(category, index) in ['All', 'Algorithm & Data Structures', 'API & Documentation', 'Cloud & DevOps', 'Testing & QA', 'UI / UX']" :key="index">
            <input class="form-check-input" type="checkbox" :id="'category' + index" :value="category" @change="toggleCategory(category)">
            <label class="form-check-label" :for="'category' + index">{{ category }}</label>
          </div>
        </fieldset>
      </div>

      <!-- Pricing 필터 -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Pricing</legend>
          <div v-for="(option, index) in ['All', 'Free', 'Freemium', 'Paid']" :key="index" class="form-check">
            <input class="form-check-input" type="radio" name="pricing" :id="'pricing' + index" :value="option" @change="updatePricing">
            <label class="form-check-label" :for="'pricing' + index">{{ option }}</label>
          </div>
        </fieldset>
      </div>

      <!-- Sort 필터 -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Sort</legend>
          <div v-for="(option, index) in ['Popularity', 'Newest', 'Alphabetical']" :key="index" class="form-check">
            <input class="form-check-input" type="radio" name="sort" :id="'sort' + index" :value="option" @change="updateSort">
            <label class="form-check-label" :for="'sort' + index">{{ option }}</label>
          </div>
        </fieldset>
      </div>
    </div>
  </div>
<!--  <div class="container p-3 border rounded">-->
<!--    <div class="row mt-3">-->
<!--      &lt;!&ndash; Category 필터 &ndash;&gt;-->
<!--      <div class="col-md-3">-->
<!--        <fieldset>-->
<!--          <legend class="h6">Category</legend>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="checkbox" id="categoryAll" checked>-->
<!--            <label class="form-check-label" for="categoryAll">All</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="checkbox" id="category1">-->
<!--            <label class="form-check-label" for="category1">Algorithm & Data Structures</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="checkbox" id="category2">-->
<!--            <label class="form-check-label" for="category2">API & Documentation</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="checkbox" id="category3">-->
<!--            <label class="form-check-label" for="category3">Cloud & DevOps</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="checkbox" id="category4">-->
<!--            <label class="form-check-label" for="category4">Testing & QA</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="checkbox" id="category5">-->
<!--            <label class="form-check-label" for="category5">UI / UX</label>-->
<!--          </div>-->
<!--        </fieldset>-->
<!--      </div>-->

<!--      &lt;!&ndash; Pricing 필터 &ndash;&gt;-->
<!--      <div class="col-md-3">-->
<!--        <fieldset>-->
<!--          <legend class="h6">Pricing</legend>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing" id="pricingAll" checked>-->
<!--            <label class="form-check-label" for="pricingAll">All</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing" id="pricingFree">-->
<!--            <label class="form-check-label" for="pricingFree">Free</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing" id="pricingFreemium">-->
<!--            <label class="form-check-label" for="pricingFreemium">Freemium</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing" id="pricingPaid">-->
<!--            <label class="form-check-label" for="pricingPaid">Paid</label>-->
<!--          </div>-->
<!--        </fieldset>-->
<!--      </div>-->

<!--      &lt;!&ndash; Sort 필터 &ndash;&gt;-->
<!--      <div class="col-md-3">-->
<!--        <fieldset>-->
<!--          <legend class="h6">Sort</legend>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="sort" id="sortPopularity" checked>-->
<!--            <label class="form-check-label" for="sortPopularity">Popularity</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="sort" id="sortNewest">-->
<!--            <label class="form-check-label" for="sortNewest">Newest</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="sort" id="sortAlphabetical">-->
<!--            <label class="form-check-label" for="sortAlphabetical">Alphabetical</label>-->
<!--          </div>-->
<!--        </fieldset>-->
<!--      </div>-->

<!--      &lt;!&ndash; Pricing 필터 (중복) &ndash;&gt;-->
<!--      <div class="col-md-3">-->
<!--        <fieldset>-->
<!--          <legend class="h6">Pricing</legend>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing2" id="pricingAll2" checked>-->
<!--            <label class="form-check-label" for="pricingAll2">All</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing2" id="pricingFree2">-->
<!--            <label class="form-check-label" for="pricingFree2">Free</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing2" id="pricingFreemium2">-->
<!--            <label class="form-check-label" for="pricingFreemium2">Freemium</label>-->
<!--          </div>-->
<!--          <div class="form-check">-->
<!--            <input class="form-check-input" type="radio" name="pricing2" id="pricingPaid2">-->
<!--            <label class="form-check-label" for="pricingPaid2">Paid</label>-->
<!--          </div>-->
<!--        </fieldset>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->

</template>

<style scoped>

</style>
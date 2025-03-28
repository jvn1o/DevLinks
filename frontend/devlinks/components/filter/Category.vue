<script setup lang="ts">

</script>

<template>
  <div class="container p-3 border rounded">
    <div class="d-flex justify-content-between align-items-center">
      <h5>Filter</h5>
      <button class="btn btn-outline-secondary btn-sm">
        <i class="bi bi-arrow-up"></i>
      </button>
    </div>

    <div class="row mt-3">
      <!-- Category 필터 -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Category</legend>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="categoryAll">
            <label class="form-check-label" for="categoryAll">All</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="category1">
            <label class="form-check-label" for="category1">Algorithm & Data Structures</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="category2" checked>
            <label class="form-check-label" for="category2">API & Documentation</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="category3">
            <label class="form-check-label" for="category3">Cloud & DevOps</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="category4" checked>
            <label class="form-check-label" for="category4">Testing & QA</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="category5">
            <label class="form-check-label" for="category5">UI / UX</label>
          </div>
        </fieldset>
      </div>

      <!-- Pricing 필터 -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Pricing</legend>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing" id="pricingAll" checked>
            <label class="form-check-label" for="pricingAll">All</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing" id="pricingFree">
            <label class="form-check-label" for="pricingFree">Free</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing" id="pricingFreemium">
            <label class="form-check-label" for="pricingFreemium">Freemium</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing" id="pricingPaid">
            <label class="form-check-label" for="pricingPaid">Paid</label>
          </div>
        </fieldset>
      </div>

      <!-- Sort 필터 -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Sort</legend>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="sort" id="sortPopularity" checked>
            <label class="form-check-label" for="sortPopularity">Popularity</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="sort" id="sortNewest">
            <label class="form-check-label" for="sortNewest">Newest</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="sort" id="sortAlphabetical">
            <label class="form-check-label" for="sortAlphabetical">Alphabetical</label>
          </div>
        </fieldset>
      </div>

      <!-- Pricing 필터 (중복) -->
      <div class="col-md-3">
        <fieldset>
          <legend class="h6">Pricing</legend>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing2" id="pricingAll2" checked>
            <label class="form-check-label" for="pricingAll2">All</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing2" id="pricingFree2">
            <label class="form-check-label" for="pricingFree2">Free</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing2" id="pricingFreemium2">
            <label class="form-check-label" for="pricingFreemium2">Freemium</label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="radio" name="pricing2" id="pricingPaid2">
            <label class="form-check-label" for="pricingPaid2">Paid</label>
          </div>
        </fieldset>
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>
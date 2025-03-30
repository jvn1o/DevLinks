<script>
import Category from "~/components/filter/Filter.vue";
import { ref } from 'vue';

const selectedFilters = ref([]);


export default {
  name: "index.vue",
  components: {Category}
}
</script>

<template>
  <main>
    <div class="pt-4">
      <Category></Category>
      <div>
        <div>Results</div>

        <!-- 선택된 필터 표시 -->
        <div class="container border rounded">

        </div>
      </div>

    </div>
  </main>
</template>

<style scoped>

</style>
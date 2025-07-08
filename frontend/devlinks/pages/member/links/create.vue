<script setup>
import axios from 'axios'
import { ref } from 'vue'
import TiptapEditor from '~/components/TiptapEditor.vue'
import { slugify } from '~/utils/slugify.js'

const title = ref('')
const category = ref('')
const categories = [
  'Algorithm & Data Structures',
  'API & Documentation',
  'Cloud & DevOps',
  'Testing & QA',
  'UI / UX',
]

const priceType = ref('')
const priceTypes = ['Free', 'Paid', 'Free & Paid']

const description = ref('')
const imageFile = ref(null)
const imagePreview = ref(null)

function onImageSelected(event) {
  const file = event.target.files[0]
  if (file) {
    imageFile.value = file
    imagePreview.value = URL.createObjectURL(file)
  } else {
    imageFile.value = null
    imagePreview.value = null
  }
}

async function submitForm() {
  const plainDesc = description.value?.toString().trim();

  if (!title.value || !category.value || !priceType.value || !plainDesc) {
    alert('Please fill out all fields.');
    return;
  }

  const dto = {
    title: title.value,
    categorySlug: slugify(category.value),
    priceType: priceType.value,
    description: plainDesc,
  }

  const formData = new FormData();
  formData.append('data', new Blob([JSON.stringify(dto)], { type: 'application/json' }));

  if (imageFile.value) {
    formData.append('image', imageFile.value);
  }

  try {
    const response = await axios.post('/member/links/create', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });

    alert('Posted Successfully!');
    console.log('Server Response:', response.data);

    // 초기화
    title.value = '';
    category.value = '';
    priceType.value = '';
    description.value = '';
    imageFile.value = null;
    imagePreview.value = null;

  } catch (error) {
    console.error('Posted Failed:', error);
    alert('Error');
  }
}
</script>

<template>
  <div class="container py-5">
    <h2 class="mb-4">Write a Post</h2>

    <form @submit.prevent="submitForm" class="row flex-column g-3">
      <!-- Thumbnail -->
      <div class="col-12 col-md-5">
        <label class="form-label fw-bold">Thumbnail</label>
        <input
            type="file"
            accept="image/*"
            @change="onImageSelected"
            class="form-control"
            :multiple="false"
        />
        <div v-if="imagePreview" class="mt-3">
          <img :src="imagePreview" alt="Image preview" style="max-width: 200px;"/>
        </div>
      </div>

      <!-- Title -->
      <div class="col-12 col-md-6">
        <label class="form-label fw-bold">Title</label>
        <input v-model="title" type="text" class="form-control" placeholder="Enter a title" required/>
      </div>

      <!-- Category -->
      <div class="col-12 col-sm-6 col-md-3">
        <label class="form-label fw-bold">Category</label>
        <select
            v-model="category"
            class="form-select bg-white text-muted"
            :class="{ 'text-dark': category }"
            required
        >
          <option value="" disabled>Select Category</option>
          <option v-for="cat in categories" :key="cat" :value="cat">{{ cat }}</option>
        </select>
      </div>

      <!-- PriceType -->
      <div class="col-12 col-sm-6 col-md-2">
        <label class="form-label fw-bold">PriceType</label>
        <select
            v-model="priceType"
            class="form-select bg-white text-muted"
            :class="{ 'text-dark': priceType }"
            required
        >
          <option value="" disabled>Select PriceType</option>
          <option v-for="price in priceTypes" :key="price" :value="price">{{ price }}</option>
        </select>
      </div>

      <!-- Description -->
      <div class="col-12">
        <label class="form-label fw-bold">Description</label>
        <TiptapEditor v-model="description" />
      </div>

      <!-- submit -->
      <div class="col-12 d-flex justify-content-end">
        <button type="submit" class="btn btn-primary">submit</button>
      </div>
    </form>
  </div>
</template>

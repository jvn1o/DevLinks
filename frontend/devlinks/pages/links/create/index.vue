<script setup>
import axios from 'axios'
import { ref } from 'vue'
import TiptapEditor from '@/components/TiptapEditor.vue'

// npm install axios, npm install @tiptap/vue-3 @tiptap/starter-kit @tiptap/extension-image

const title = ref('')
const category = ref('')
const categories = [
  'Algorithm & Data Structures',
  'API & Documentation',
  'Cloud & DevOps',
  'Testing & QA',
  'UI / UX',
]

// 모든 특수문자와 공백 등을 안전한 slug로 변환하는 함수
const slugify = (text) => {
  return text
      .toLowerCase()
      .trim()
      .replace(/ & /g, ' and ')   // 의미 보존 (예: API & Docs → api-and-docs)
      .replace(/ \/ /g, '-')      // 슬래시 제거 (예: UI / UX → ui-ux)
      .replace(/[^\w\s-]/g, '')   // 나머지 특수문자 제거
      .replace(/\s+/g, '-')
      .replace(/--+/g, '-')       // 연속된 하이픈 정리
      .replace(/^-+|-+$/g, '');   // 양쪽 끝 하이픈 제거
}

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

  const formData = new FormData();
  formData.append('title', title.value);
  formData.append('category', slugify(category.value)); // slugify 처리
  formData.append('priceType', priceType.value);
  formData.append('description', plainDesc); // 문자열 강제 처리

  if (imageFile.value) {
    formData.append('image', imageFile.value);
  }

  try {
    const response = await axios.post('/api/v1/links/create', formData, {
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
      <div class="col-5">
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
      <div class="col-12 col-md-3">
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
      <div class="col-12 col-md-2">
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

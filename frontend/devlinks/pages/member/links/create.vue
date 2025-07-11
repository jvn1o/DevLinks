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
      validateStatus: () => true, // axios가 상태코드 관계없이 catch로 빠지지 않게 함
    });

    const status = response.status;

    if (status === 201 || status === 200) {
      alert('Posted Successfully!');
      console.log('Server Response:', response.data);

      // 초기화
      title.value = '';
      category.value = '';
      priceType.value = '';
      description.value = '';
      imageFile.value = null;
      imagePreview.value = null;

    } else if (status === 400) {
      alert('요청 형식이 잘못되었습니다. 모든 필드를 정확히 입력했는지 확인해 주세요.');
    } else if (status === 401) {
      alert('인증되지 않았습니다. 로그인 후 다시 시도해 주세요.');
    } else if (status === 409) {
      alert('이미 존재하는 링크입니다.');
    } else if (status >= 500) {
      alert('서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.');
    } else {
      alert(`알 수 없는 오류가 발생했습니다 (Status: ${status})`);
    }

  } catch (error) {
    console.error('Posted Failed:', error);
    alert('네트워크 오류 또는 예기치 못한 문제가 발생했습니다.');
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

<script setup>
import { useSignupForm } from "../composables/useSingupForm";

const {
  profileImg,
  previewImage,
  onFileChange,
  name,
  memberId,
  nickname,
  emailId,
  emailDomain,
  birth,
  password,
  confirmPassword,
  isChecked,
  isMatch,
  message,
  nameError,
  nicknameError,
  passwordError,
  memberIdError,
  emailError,
  submitHandler,
  validHandler
} = useSignupForm();
</script>

<template>
  <form @submit.prevent="submitHandler" class="container">
    <div class="row flex-column">

      <!--  ProfileImg    -->
      <div class="mb-3 col-12 col-md-6">
        <label for="profileImg" class="form-label">Profile Image</label>
        <div v-if="previewImage" class="mt-2">
          <img :src="previewImage" alt="Preview" style="max-width: 150px; max-height: 150px;" />
        </div>
        <input type="file" @change="onFileChange" accept="image/*" class="form-control" id="profileImg" />
      </div>

      <!-- Name -->
      <div class="mb-3 col-12 col-md-4">
        <label for="name" class="form-label">Name</label>
        <input v-model="name" type="text" class="form-control" id="name" placeholder="Name">
        <div class="text-danger" v-if="nameError">{{ nameError }}</div>
      </div>

      <!-- Nickname -->
      <div class="mb-3 col-12 col-md-3">
        <label for="nickname" class="form-label">Nickname</label>
        <input v-model="nickname" type="text" class="form-control" id="nickname" placeholder="Nickname">
        <div class="text-danger" v-if="nicknameError">{{ nicknameError }}</div>
      </div>

      <!-- ID & 중복확인 -->
      <div class="mb-3 col-12 col-md-4">
        <label for="id" class="form-label">ID</label>
        <div class="input-group">
          <input
              v-model="memberId"
              @input="memberId = memberId.replace(/[^A-Za-z0-9]/g, '')"
              type="text"
              class="form-control"
              id="id"
              placeholder="ID"
          />
          <button @click="validHandler" type="button" class="btn btn-outline-primary">Verify</button>
        </div>
        <div class="form-text text-success" v-if="isChecked">ID verified</div>
        <div class="form-text text-danger" v-else>{{ memberIdError || "Please verify the ID" }}</div>
      </div>

      <!-- Password -->
      <div class="mb-3 col-12 col-md-6">
        <label for="password" class="form-label">Password</label>
        <input
            v-model="password"
            @input="password = password.replace(/[^A-Za-z0-9@$!%*?&]/g, '')"
            type="password"
            class="form-control"
            id="password"
            placeholder="Password"
        />
        <div class="text-danger" v-if="passwordError">{{ passwordError }}</div>
      </div>

      <!-- Confirm Password -->
      <div class="mb-3 col-12 col-md-6">
        <label for="confirm-password" class="form-label">Confirm Password</label>
        <input
            v-model="confirmPassword"
            @input="confirmPassword = confirmPassword.replace(/[^A-Za-z0-9@$!%*?&]/g, '')"
            type="password"
            class="form-control"
            id="confirmPassword"
            placeholder="Confirm Password"
        />
        <div class="form-text" :class="isMatch ? 'text-success' : (confirmPassword ? 'text-danger' : '')">
          {{ message }}
        </div>
      </div>

      <!-- Email -->
      <div class="mb-3 col-12 col-md-6">
        <label for="email" class="form-label">Email</label>
        <div class="input-group">
          <input
              v-model="emailId"
              @input="emailId = emailId.replace(/[^A-Za-z0-9]/g, '')"
              type="text"
              class="form-control"
              id="emailId"
              placeholder="email"
          />
          <span class="input-group-text">@</span>
          <select v-model="emailDomain" class="form-select">
            <option value="" disabled selected hidden></option>
            <option value="gmail.com">gmail.com</option>
            <option value="naver.com">naver.com</option>
            <option value="daum.net">daum.net</option>
            <option value="yahoo.com">yahoo.com</option>
          </select>
        </div>
        <div class="text-danger" v-if="emailError">{{ emailError }}</div>
      </div>

      <!-- Birth -->
      <div class="mb-4 col-12 col-md-3">
        <label for="birth" class="form-label">Birth</label>
        <input v-model="birth" type="date" class="form-control" id="birth" required>
      </div>

      <!-- Submit -->
      <div class="col-12 d-flex justify-content-end">
        <button type="submit" class="btn btn-primary w-25 w-md-25">Register</button>
      </div>
    </div>
  </form>
</template>
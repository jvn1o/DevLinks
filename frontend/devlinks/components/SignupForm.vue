<script setup>
import { useSignupForm } from "../composables/useSingupForm";

const {
  name,
  userId,
  nickname,
  email,
  birth,
  password,
  confirmPassword,
  isChecked,
  isMatch,
  message,
  submitHandler,
  validHandler
} = useSignupForm();
</script>

<template>
  <form @submit.prevent="submitHandler" class="container">
    <div class="row flex-column">
      <!-- Name -->
      <div class="mb-3 col-12 col-md-4">
        <label for="name" class="form-label">Name</label>
        <input v-model="name" type="text" class="form-control" id="name" placeholder="Name"
               pattern="[A-Za-z]+" title="Please enter English letters only." required>
      </div>

      <!-- ID & 중복확인 -->
      <div class="mb-3 col-12 col-md-4">
        <label for="id" class="form-label">ID</label>
        <div class="input-group">
          <input v-model="userId" type="text" class="form-control" id="id" placeholder="ID"
                 pattern="[A-Za-z0-9]+" title="Please enter English letters and numbers only." required>
          <button @click="validHandler" type="button" class="btn btn-outline-primary">Verify</button>
        </div>
        <div class="form-text text-success" v-if="isChecked">ID verified</div>
        <div class="form-text text-danger" v-else>Please verify the ID</div>
      </div>

      <!-- Password -->
      <div class="mb-3 col-12 col-md-6">
        <label for="password" class="form-label">Password</label>
        <input v-model="password" type="password" class="form-control" id="password" placeholder="Password"
               pattern="(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+" required>
      </div>

      <!-- Confirm Password -->
      <div class="mb-3 col-12 col-md-6">
        <label for="confirm-password" class="form-label">Confirm Password</label>
        <input v-model="confirmPassword" type="password" class="form-control" id="confirm-password" placeholder="Confirm Password" required>
        <div class="form-text" :class="isMatch ? 'text-success' : (confirmPassword ? 'text-danger' : '')">
          {{ message }}
        </div>
      </div>

      <!-- Nickname -->
      <div class="mb-3 col-12 col-md-3">
        <label for="nickname" class="form-label">Nickname</label>
        <input v-model="nickname" type="text" class="form-control" id="nickname" placeholder="Nickname"
               pattern="[A-Za-z0-9]+" title="Please enter English letters and numbers only." required>
      </div>

      <!-- Email -->
      <div class="mb-3 col-12 col-md-6">
        <label for="email" class="form-label">Email</label>
        <input v-model="email" type="email" class="form-control" id="email" placeholder="Email@email.com" required>
      </div>

      <!-- Birth -->
      <div class="mb-4 col-12 col-md-3">
        <label for="birth" class="form-label">Birth</label>
        <input v-model="birth" type="date" class="form-control" id="birth" required>
      </div>

      <!-- Submit -->
      <div class="col-12 d-flex justify-content-end">
        <button type="submit" class="btn btn-primary w-100 w-md-25">Register</button>
      </div>
    </div>
  </form>
</template>


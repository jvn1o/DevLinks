<template>
  <main class="container mt-5">
    <h2 class="mb-4 text-center">Create an account</h2>

    <form @submit.prevent="submitHandler">
      <!-- Name -->
      <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input v-model="name" type="text" class="form-control" id="name" placeholder="Name"
               pattern="[A-Za-z]+" title="Please enter English letters only." required>
      </div>

      <!-- ID & 중복확인 -->
      <div class="mb-3">
        <label for="id" class="form-label">ID</label>
        <div class="input-group">
          <input v-model="userId" type="text" class="form-control" id="id" placeholder="ID"
                 pattern="[A-Za-z0-9]+" title="Please enter English letters and numbers only." required>
          <button @click="validHandler" type="button" class="btn btn-outline-primary">Verify</button>
        </div>
        <div class="form-text text-success" v-if="isChecked">중복확인 완료</div>
        <div class="form-text text-danger" v-else>중복확인을 해주세요.</div>
      </div>

      <!-- Password -->
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input v-model="password" type="password" class="form-control" id="password" placeholder="Password"
               pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]+"
               title="Password must contain English letters, numbers, and special characters." required>
      </div>

      <!-- Confirm Password -->
      <div class="mb-3">
        <label for="confirm-password" class="form-label">Confirm Password</label>
        <input v-model="confirmPassword" type="password" class="form-control" id="confirm-password" required>
        <div class="form-text" :class="isMatch ? 'text-success' : (confirmPassword ? 'text-danger' : '')">
          {{ message }}
        </div>
      </div>

      <!-- Nickname -->
      <div class="mb-3">
        <label for="nickname" class="form-label">Nickname</label>
        <input v-model="nickname" type="text" class="form-control" id="nickname" placeholder="Nickname"
               pattern="[A-Za-z0-9]+" title="Please enter English letters and numbers only." required>
      </div>

      <!-- Email -->
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input v-model="email" type="email" class="form-control" id="email" placeholder="Your Email" required>
      </div>

      <!-- Birth -->
      <div class="mb-4">
        <label for="birth" class="form-label">Birth</label>
        <input v-model="birth" type="date" class="form-control" id="birth" required>
      </div>

      <!-- Submit -->
      <button type="submit" class="btn btn-primary w-100">Register</button>
    </form>
  </main>
</template>

<script setup>
import { ref, computed, watch } from "vue";
import axios from "axios";

const password = ref("");
const confirmPassword = ref("");
const name = ref("");
const userId = ref("");
const nickname = ref("");
const email = ref("");
const birth = ref("");
const isChecked = ref(false);

watch(userId, () => {
  isChecked.value = false;
});

const isMatch = computed(() => password.value === confirmPassword.value);

const message = computed(() => {
  if (!confirmPassword.value) return "";
  return isMatch.value ? "password correct" : "password incorrect";
});

const submitHandler = async () => {
  if (!isMatch.value || !isChecked.value) {
    alert("아이디 중복 확인을 해주세요.");
    return;
  }

  const memberData = {
    name: name.value,
    userId: userId.value,
    userPwd: password.value,
    nickname: nickname.value,
    email: email.value,
    birth: birth.value,
  };

  try {
    await axios.post("http://localhost:8083/api/v1/members/signup", memberData);
    navigateTo("/signup-success");
  } catch (error) {
    alert("입력값을 확인해주세요");
  }
};

const validHandler = async () => {
  try {
    await axios.post("http://localhost:8083/api/v1/members/idvalid", { userId: userId.value });
    alert("중복확인 완료");
    isChecked.value = true;
  } catch (error) {
    if (error.response && error.response.status === 409) {
      alert("이미 존재하는 ID입니다.");
    } else {
      alert("오류가 발생했습니다. 다시 시도해주세요.");
    }
    isChecked.value = false;
  }
};
</script>

<script setup>
import { ref } from 'vue';
import { jwtDecode } from "jwt-decode";

const userId = ref('');
const userPwd = ref('');
const route = useRoute();
const userDetails = useUserDetails();
// const rememberMe = ref(false);

const signInHandler = async () => {
  console.log("로그인 버튼 작동 중");

  try {
    let response = await useDataFetch("auth/signin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: {
        userId: userId.value,
        userPwd: userPwd.value
      }
    });

    // 응답받은 토큰을 jwt 디코딩을 통하여 사용자 정보를 추출
    let userInfo = jwtDecode(response.token);

    // 디코딩한 정보를 userDetails.login 으로 전역 저장
    userDetails.login({
      id: userInfo.id,
      userId: userInfo.userId,
      username: userInfo.name,
      birth: userInfo.birth,
      email: userInfo.email,
      profileImgSrc: userInfo.profileImgSrc,
      token: response.token
    });

    // 로그인 전 접근하려던 returnURL로 이동
    console.log('조각난 리턴url: ', route.query);
    let originalReturnURL = route.query.returnURL;
    Object.entries(route.query)
        .filter(([key]) => key !== 'returnURL')
        .forEach(([key, value]) => {
          originalReturnURL += `&${key}=${value}`;
        });
    console.log('originalReturnUrl: ', originalReturnURL);

    return navigateTo(originalReturnURL);

  } catch (error) {
    if (error.response && error.response.status === 401) {
      alert("존재하지 않는 아이디입니다.");
    } else {
      console.error("로그인 중 오류 발생:", error);
      alert("로그인에 실패했습니다. 다시 시도해주세요.");
    }
  }
};
</script>

<template>
  <main class="container mt-5">
    <div class="text-center mb-4">
      <h2>Login</h2>
    </div>

    <form @submit.prevent="signInHandler" class="mx-auto" style="max-width: 400px;">
      <!-- ID -->
      <div class="mb-3">
        <label for="id" class="form-label">ID</label>
        <input type="text" id="id" class="form-control" v-model="userId" placeholder="Enter your ID" required>
      </div>

      <!-- Password -->
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" id="password" class="form-control" v-model="userPwd" placeholder="Enter your Password" required>
      </div>

      <!-- 추가 옵션 -->
      <div class="d-flex justify-content-between mb-4 text-muted small">
        <!-- <div class="form-check">
          <input type="checkbox" class="form-check-input" id="rememberMe" v-model="rememberMe">
          <label class="form-check-label" for="rememberMe">Remember me</label>
        </div> -->
        <div>
          <a href="#" class="me-3">Forgot ID</a>
          <a href="#">Forgot Password</a>
        </div>
      </div>

      <!-- 로그인 버튼 -->
      <button type="submit" class="btn btn-primary w-100 mb-3">Login</button>

      <!-- 구분선 -->
      <div class="text-center text-muted my-3">
        <hr>
        <span class="position-relative px-3 bg-white" style="top: -18px;">or</span>
      </div>

      <!-- 회원가입 버튼 -->
      <RouterLink to="/signup" class="btn btn-outline-secondary w-100">Create an account</RouterLink>
    </form>
  </main>
</template>
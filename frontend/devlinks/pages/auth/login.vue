<script setup lang="ts">
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import { jwtDecode } from 'jwt-decode';
import { useMemberApi } from '~/composables/useMemberApi.ts';
import useMemberDetails from '~/composables/useMemberDetails';

const memberId = ref('');
const memberPwd = ref('');
const route = useRoute();
const memberDetails = useMemberDetails();

const logInHandler = async () => {
  console.log("로그인 버튼 작동 중");

  try {
    const api = useMemberApi();
    const response = await api.post('/auth/login', {
      memberId: memberId.value,
      memberPwd: memberPwd.value,
    });

    const token = response.data?.token;
    if (!token) {
      alert("로그인 응답에 문제가 있습니다.");
      return;
    }

    const memberInfo = jwtDecode(token) as {
      id: number;
      memberId: string;
      name: string;
      birth: string;
      email: string;
      profileImgSrc: string | null;
    };

    // 전역 상태에 로그인 정보 저장
    memberDetails.login({
      id: memberInfo.id,
      memberId: memberInfo.memberId,
      memberName: memberInfo.name,
      birth: memberInfo.birth,
      email: memberInfo.email,
      profileImgSrc: memberInfo.profileImgSrc,
      token,
    });

    // 로그인 전 URL 복원
    let returnURL = (route.query.redirect as string) || '/';
    Object.entries(route.query)
        .filter(([key]) => key !== 'redirect')
        .forEach(([key, value]) => {
          returnURL += `&${key}=${value}`;
        });

    return navigateTo(returnURL);

  } catch (error: any) {
    const status = error.response?.status || error.status || null;

    if (status === 401) {
      alert("아이디 또는 비밀번호가 올바르지 않습니다.");
    } else if (status === 400) {
      alert("요청 형식이 잘못되었습니다. 입력값을 다시 확인해 주세요.");
    } else if (status === 500) {
      alert("서버 오류가 발생했습니다. 잠시 후 다시 시도해 주세요.");
    } else {
      console.error("로그인 중 알 수 없는 오류:", error);
      alert("로그인에 실패했습니다. 네트워크 상태를 확인해주세요.");
    }
  }
};

definePageMeta({
  layout: false,
});
</script>

<template>
  <main class="container-sm mt-5 d-flex flex-column align-items-center">
    <div class="mb-5 w-100" style="max-width: 560px; width: 100%;">
      <h2 class="mb-4 fw-bold text-center">Login</h2>

      <form @submit.prevent="logInHandler" style="width: 100%;">
        <!-- ID -->
        <div class="mb-4">
          <label for="id" class="form-label fs-5">ID</label>
          <input
              type="text"
              id="id"
              class="form-control form-control-lg"
              v-model="memberId"
              placeholder="Enter your ID"
              required
          />
        </div>

        <!-- Password -->
        <div class="mb-4">
          <label for="password" class="form-label fs-5">Password</label>
          <input
              type="password"
              id="password"
              class="form-control form-control-lg"
              v-model="memberPwd"
              placeholder="Enter your Password"
              required
          />
        </div>

        <!-- 추가 옵션 -->
        <div class="d-flex justify-content-between mb-4 text-muted">
          <div>
            <a href="#" class="me-3" style="font-size: 1.1rem !important;">Forgot ID</a>
            <a href="#" style="font-size: 1.1rem !important;">Forgot Password</a>
          </div>
        </div>

        <!-- 로그인 버튼 -->
        <button type="submit"
                class="btn btn-primary btn-lg w-100"
                >Login</button>

        <!-- 구분선 -->
        <div class="text-center text-muted my-4 position-relative">
          <hr />
          <span
              class="position-absolute top-50 start-50 translate-middle px-3"
              style="font-weight: 600; font-size: 1rem;"
          >
            or
          </span>
        </div>

        <!-- 회원가입 버튼 -->
        <NuxtLink to="/member/signup"
                    class="btn btn-danger btn-lg w-100"
        >
          Create an account
        </NuxtLink>
      </form>
    </div>
  </main>
</template>
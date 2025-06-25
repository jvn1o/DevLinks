<script setup>
import { ref } from 'vue';
import { jwtDecode } from "jwt-decode";

const memberId = ref('');
const memberPwd = ref('');
const route = useRoute();
const memberDetails = useMemberDetails();
// const rememberMe = ref(false);

const logInHandler = async () => {
  console.log("로그인 버튼 작동 중");

  try {
    let response = await useDataFetch("auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: {
        memberId: memberId.value,
        memberPwd: memberPwd.value
      }
    });

    // 응답받은 토큰을 jwt 디코딩을 통하여 사용자 정보를 추출
    let memberInfo = jwtDecode(response.token);

    // 디코딩한 정보를 memberDetails.login 으로 전역 저장
    memberDetails.login({
      id: memberInfo.id,
      memberId: memberInfo.memberId,
      memberName: memberInfo.name,
      birth: memberInfo.birth,
      email: memberInfo.email,
      profileImgSrc: memberInfo.profileImgSrc,
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

definePageMeta({
  layout: false
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
        <button type="submit" class="btn btn-primary btn-lg w-100">Login</button>

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
        <RouterLink to="/signup"
                    class="btn btn-outline-secondary text-danger btn-lg w-100"
                    style="--bs-btn-hover-bg: var(--card-border); --bs-btn-active-bg: var(--card-hover-border)"
        >
          Create an account
        </RouterLink>
      </form>
    </div>
  </main>
</template>
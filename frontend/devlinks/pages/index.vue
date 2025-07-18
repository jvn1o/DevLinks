<script setup lang="ts">
import DevLinks from "~/components/logo/DevLinks.vue";
</script>

<template>
  <div class="container py-2 my-3">

    <!-- 소개 영역 -->
    <div class="row justify-content-center">
      <div class="col-lg-6">
        <div class="card-bg responsive-padding rounded-1 shadow-sm border text-center">
          <h1 class="fw-bold my-3" style="padding-right: 8px;">
            <span class="d-inline-flex align-items-center gap-2">
              <DevLinks/>
            </span>
          </h1>
          <p class="fw-bold intro-text-responsive">
            개발자가 꼭 알아야 할 링크를 한 곳에
          </p>
        </div>
      </div>
    </div>

    <!-- 화살표 애니메이션  -->
    <div class="scroll-down-indicator text-center mt-3 mb-4">
      <div class="d-flex justify-content-center arrows">
        <i class="bi bi-chevron-down fs-2 d-block"></i>
      </div>
    </div>

    <!-- 카드 목록 -->
    <div class="row mb-5 justify-content-center text-center">
      <div class="col-lg-7">
        <div class="card card-bg h-100 shadow-sm border hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/links/category/algorithm-data-structures`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary ps-4">
                Algorithm & Data Structures
                <i class="bi bi-box-arrow-up-right link-icon ps-1"></i>
              </h5>
              <p class="card-text card-text-responsive">
                효율적인 문제 해결을 위한 알고리즘과 자료구조에 대한 정보와 예제를 제공합니다. <br/>
                (예: 정렬, 탐색, 그래프, 트리 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-center text-center">
      <div class="col-lg-7">
        <div class="card card-bg h-100 shadow-sm border hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/links/category/algorithm-data-structures`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary ps-4">
                API & Documentation
                <i class="bi bi-box-arrow-up-right link-icon ps-1"></i>
              </h5>
              <p class="card-text card-text-responsive">
                다양한 외부 API 사용법과 문서화 가이드를 확인할 수 있습니다. <br/>
                (예: REST API, Swagger, OpenAPI 문서 작성 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-center text-center">
      <div class="col-lg-7">
        <div class="card card-bg h-100 shadow-sm border hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/links/category/cloud-devops`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary ps-4">
                Cloud & DevOps
                <i class="bi bi-box-arrow-up-right link-icon ps-1"></i>
              </h5>
              <p class="card-text card-text-responsive">
                클라우드 환경 설정부터 CI/CD 파이프라인 구축까지, 인프라 자동화에 대한 내용을 다룹니다. <br/>
                (예: AWS, Docker, Kubernetes, GitHub Actions 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-center text-center">
      <div class="col-lg-7">
        <div class="card card-bg h-100 shadow-sm border hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/links/category/testing-qa`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary ps-4">
                Testing & QA
                <i class="bi bi-box-arrow-up-right link-icon ps-1"></i>
              </h5>
              <p class="card-text card-text-responsive">
                코드 테스트 방법과 품질 보증(QA) 관련 자료를 모았습니다. <br/>
                (예: 단위 테스트, 통합 테스트, 테스트 자동화 도구 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-center text-center">
      <div class="col-lg-7">
        <div class="card card-bg h-100 shadow-sm border hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/links/category/ui-ux`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary ps-4">
                UI / UX
                <i class="bi bi-box-arrow-up-right link-icon ps-1"></i>
              </h5>
              <p class="card-text card-text-responsive">
                사용자 경험을 향상시키기 위한 UI 설계 및 디자인 시스템, 접근성 관련 정보를 제공합니다. <br/>
                (예: 디자인 패턴, 피그마, 사용자 흐름 분석 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<style scoped>
.intro-title-responsive {
  font-size: 1.3rem;
}
.intro-text-responsive {
  font-size: 1rem;
}
.card-title-responsive {
  font-size: 0.9rem;
}
.card-text-responsive {
  font-size: 0.85rem;
}

.card-bg {
  background-color: var(--bg-card);
}

.responsive-padding {
  padding: 1.25rem 1.75rem;
}

.logo-responsive {
  width: 36px;
  height: 36px;
}

.hover-card {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.hover-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
  border: 1px solid #495057;

  h5 {
    color: #0d6efd !important;
  }
}

.scroll-down-indicator .arrows i {
  animation: bounce 1.2s infinite;
}

.scroll-down-indicator .arrows i:nth-child(2) {
  animation-delay: 0.2s;
}

@keyframes bounce {
  0%, 100% {
    transform: translateY(0);
    opacity: 0.6;
  }
  50% {
    transform: translateY(8px);
    opacity: 1;
  }
}

@media (max-width: 768px) {
  .container {
    .py-5 {
      padding: 0 !important;
    }

    .card-text-responsive br {
      display: none;
    }

    .responsive-padding {
      padding: 1.25rem;
    }

    .intro-text-responsive {
      font-size: 0.8rem;
    }
    .card-title-responsive {
      font-size: 0.9rem;
    }
    .card-text-responsive {
      font-size: 0.75rem;
    }
  }
}
</style>
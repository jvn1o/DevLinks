<template>
  <div class="container py-5 my-4">

    <!-- 소개 영역 -->
    <div class="row justify-content-center">
      <div class="col-12 col-sm-12 col-md-12 col-lg-9 col-xl-9">
        <div class="responsive-padding rounded-4 mb-5 shadow-sm border text-center">
          <h2 class="fw-bold mb-3 intro-title-responsive">
        <span class="text-gradient d-inline-flex align-items-center gap-2">
          <img
              src="/assets/image/DevLinks.svg"
              alt="Logo"
              width="36"
              height="36"
              class="logo-responsive"
          />
          DevLinks
        </span>
          </h2>
          <p class="text-muted intro-text-responsive mb-2">
            개발자들이 자주 찾는 인기 사이트, 유용한 자료, 최신 트렌드를 <strong>한 곳에서 탐색</strong>할 수 있는 플랫폼입니다.
          </p>
          <p class="text-secondary intro-text-responsive">
            분야별로 정리된 링크들을 통해 더 똑똑한 개발을 시작하세요!
          </p>
        </div>
      </div>
    </div>

    <!-- 카드 목록 -->
    <div class="row mb-5 justify-content-start">
      <div class="col-md-7">
        <div class="card h-100 shadow-sm hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/category/algorithm-data-structures`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary">
                Algorithm & Data Structures
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

    <div class="row mb-5 justify-content-end">
      <div class="col-md-7">
        <div class="card h-100 shadow-sm hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/category/algorithm-data-structures`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary">
                API & Documentation
              </h5>
              <p class="card-text card-text-responsive">
                다양한 외부 API 사용법과 문서화 가이드를 확인할 수 있습니다. <br/>
                (REST API, Swagger, OpenAPI 문서 작성 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-start">
      <div class="col-md-7">
        <div class="card h-100 shadow-sm hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/category/cloud-devops`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary">
                Cloud & DevOps
              </h5>
              <p class="card-text card-text-responsive">
                클라우드 환경 설정부터 CI/CD 파이프라인 구축까지, 인프라 자동화에 대한 내용을 다룹니다. <br/>
                (AWS, Docker, Kubernetes, GitHub Actions 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-end">
      <div class="col-md-7">
        <div class="card h-100 shadow-sm hover-card">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/category/testing-qa`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary">
                Testing & QA
              </h5>
              <p class="card-text card-text-responsive">
                코드 테스트 방법과 품질 보증(QA) 관련 자료를 모았습니다. <br/>
                (단위 테스트, 통합 테스트, 테스트 자동화 도구 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-5 justify-content-start">
      <div class="col-md-7">
        <div class="card h-100 shadow-sm hover-card ms-md-auto">
          <div class="card-body py-4 px-4">
            <NuxtLink :to="`/category/ui-ux`" class="nav-link">
              <h5 class="card-title card-title-responsive fw-bold text-primary">
                UI / UX
              </h5>
              <p class="card-text card-text-responsive">
                사용자 경험을 향상시키기 위한 UI 설계 및 디자인 시스템, 접근성 관련 정보를 제공합니다. <br/>
                (디자인 패턴, 피그마, 사용자 흐름 분석 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<style scoped>

.card {
  background-color: #f8f9fa; /* 연한 회색 */
}

.responsive-padding {
  padding: 2.5rem;
}

.logo-responsive {
  width: 36px;
  height: 36px;
}

.text-gradient {
  background: linear-gradient(to right, #007cf0, #00dfd8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.bg-custom {
  background: #f8f9fa;
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

@media (max-width: 576px) {
  .responsive-padding {
    padding: 1.25rem;
  }
  .logo-responsive {
    width: 18px;
    height: 18px;
  }

  .intro-title-responsive {
    font-size: 0.95rem;
  }
  .intro-text-responsive {
    font-size: 0.65rem;
  }
  .card-title-responsive {
    font-size: 0.9rem;
  }
  .card-text-responsive {
    font-size: 0.75rem;
  }
}
</style>

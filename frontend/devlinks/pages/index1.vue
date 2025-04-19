<template>
  <div class="container py-5">

    <ul class="list-unstyled p-4">
      <li class="card-title fs-3 mb-3 fw-bold">
        <span class="fw-bold fs-2 text-primary">DevLinks</span>
        에 오신 것을 환영합니다!
      </li>
      <li class="card-text mb-2 fs-5 text-muted">
        DevLinks는 개발자들이 자주 찾는 인기 사이트, 유용한 자료, 최신 트렌드를 한 곳에서
        탐색할 수 있도록 돕는 플랫폼입니다.<br/>
        다양한 개발 분야의 필수 링크들을 모아두었으며, 커뮤니티에서 인기 있는 정보도 쉽게 찾아볼 수 있습니다.
      </li>
      <li class="card-text mb-3 fs-6 text-secondary">
        DevLinks를 통해 더 똑똑한 개발을 해보세요!
      </li>
    </ul>

    <div class="row mb-4 justify-content-start">
      <div class="col-md-6">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <NuxtLink
                :to="`/category/algorithm-data-structures`"
                class="nav-link"
            >
              <h5 class="card-title fw-bold text-primary">
                Algorithm & Data Structures
              </h5>
              <p class="card-text">
                효율적인 문제 해결을 위한 알고리즘과 자료구조에 대한 정보와 예제를 제공합니다. <br/>
                (예: 정렬, 탐색, 그래프, 트리 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-4 justify-content-end">
      <div class="col-md-6">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <NuxtLink
                :to="`/category/algorithm-data-structures`"
                class="nav-link"
            >
              <h5 class="card-title fw-bold text-primary">
                API & Documentation
              </h5>
              <p class="card-text">
                다양한 외부 API 사용법과 문서화 가이드를 확인할 수 있습니다. <br/>
                (REST API, Swagger, OpenAPI 문서 작성 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-4 justify-content-start">
      <div class="col-md-6">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <NuxtLink
                :to="`/category/cloud-devops`"
                class="nav-link"
            >
              <h5 class="card-title fw-bold text-primary">
                Cloud & DevOps
              </h5>
              <p class="card-text">
                클라우드 환경 설정부터 CI/CD 파이프라인 구축까지, 인프라 자동화에 대한 내용을 다룹니다. <br/>
                (AWS, Docker, Kubernetes, GitHub Actions 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-4 justify-content-end">
      <div class="col-md-6">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <NuxtLink
                :to="`/category/testing-qa`"
                class="nav-link"
            >
              <h5 class="card-title fw-bold text-primary">
                Testing & QA
              </h5>
              <p class="card-text">
                코드 테스트 방법과 품질 보증(QA) 관련 자료를 모았습니다. <br/>
                (단위 테스트, 통합 테스트, 테스트 자동화 도구 등)
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

    <div class="row mb-4 justify-content-start">
      <div class="col-md-6">
        <div class="card h-100 shadow-sm">
          <div class="card-body">
            <NuxtLink
                :to="`/category/ui-ux`"
                class="nav-link"
            >
              <h5 class="card-title fw-bold text-primary">
                UI / UX
              </h5>
              <p class="card-text">
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

<script setup>
// 별도 스크립트 없음
</script>

<style scoped>
/* 필요시 커스텀 스타일 추가 */
</style>

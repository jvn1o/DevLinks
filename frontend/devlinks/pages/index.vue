<template>
  <div class="container py-5">

    <!-- 소개 영역 -->
    <div class="bg-light rounded-4 p-5 mb-5 shadow-sm border text-center">
      <h2 class="fw-bold mb-3">
        <span class="text-gradient align-content-center">
          <img
            src="/assets/image/DevLinks.svg"
            alt="Logo"
            width="36"
            height="36"
        /> DevLinks</span> 에 오신 것을 환영합니다!
      </h2>
      <p class="text-muted fs-5 mb-2">
        개발자들이 자주 찾는 인기 사이트, 유용한 자료, 최신 트렌드를 <strong>한 곳에서 탐색</strong>할 수 있는 플랫폼입니다.
      </p>
      <p class="text-secondary fs-6">
        분야별로 정리된 링크들을 통해 더 똑똑한 개발을 시작하세요!
      </p>
    </div>

    <!-- 카드 영역 -->
    <div class="row g-4">
      <div class="col-md-6" v-for="(item, index) in categories" :key="index">
        <div
            class="card h-100 shadow-sm border-0 hover-card"
            :class="index % 2 === 0 ? 'ms-md-auto' : 'me-md-auto'"
        >
          <div class="card-body">
            <NuxtLink :to="`/category/${item.slug}`" class="nav-link">
              <h5 class="card-title fw-bold text-primary mb-2">
                {{ item.title }}
              </h5>
              <p class="card-text text-muted">
                {{ item.description }}<br />
                <small class="text-secondary">({{ item.examples }})</small>
              </p>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
const categories = [
  {
    title: 'Algorithm & Data Structures',
    slug: 'algorithm-data-structures',
    description: '효율적인 문제 해결을 위한 알고리즘과 자료구조에 대한 정보와 예제 제공',
    examples: '정렬, 탐색, 그래프, 트리 등',
  },
  {
    title: 'API & Documentation',
    slug: 'api-documentation',
    description: '다양한 외부 API 사용법과 문서화 가이드',
    examples: 'REST API, Swagger, OpenAPI 문서 등',
  },
  {
    title: 'Cloud & DevOps',
    slug: 'cloud-devops',
    description: '클라우드 설정부터 CI/CD 파이프라인 구축까지',
    examples: 'AWS, Docker, Kubernetes 등',
  },
  {
    title: 'Testing & QA',
    slug: 'testing-qa',
    description: '테스트 방법과 품질 보증(QA) 관련 자료',
    examples: '단위 테스트, 통합 테스트, 자동화 도구 등',
  },
  {
    title: 'UI / UX',
    slug: 'ui-ux',
    description: '사용자 경험을 높이는 UI 설계 및 접근성 정보',
    examples: '디자인 패턴, 피그마, 사용자 흐름 분석 등',
  },
];
</script>

<style scoped>
.text-gradient {
  background: linear-gradient(to right, #007cf0, #00dfd8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.hover-card {
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}
.hover-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
}
</style>

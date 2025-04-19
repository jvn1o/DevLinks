<template>
  <div class="index-guide">
    <!-- 사이트 설명 -->
    <h1 class="title">이 사이트는 아래와 같은 5개의 탭으로 구성되어 있습니다.</h1>

    <!-- 가상의 상단 탭들 (UI용) -->
    <nav class="tab-bar">
      <div class="tab">
        Algorithm & Data Structures
        <div>
        효율적인 문제 해결을 위한 알고리즘과 자료구조에 대한 정보와 예제를 제공합니다.
        (예: 정렬, 탐색, 그래프, 트리 등)
        </div>
      </div>
      <div class="tab">
        API & Documentation

        다양한 외부 API 사용법과 문서화 가이드를 확인할 수 있습니다.
        (REST API, Swagger, OpenAPI 문서 작성 등)
      </div>
      <div class="tab">
        Cloud & DevOps

        클라우드 환경 설정부터 CI/CD 파이프라인 구축까지, 인프라 자동화에 대한 내용을 다룹니다.
        (AWS, Docker, Kubernetes, GitHub Actions 등)
      </div>
      <div class="tab">
        Testing & QA

        코드 테스트 방법과 품질 보증(QA) 관련 자료를 모았습니다.
        (단위 테스트, 통합 테스트, 테스트 자동화 도구 등)
      </div>
      <div class="tab">
        UI / UX

        사용자 경험을 향상시키기 위한 UI 설계 및 디자인 시스템, 접근성 관련 정보를 제공합니다.
        (디자인 패턴, 피그마, 사용자 흐름 분석 등)
      </div>
    </nav>
  </div>
</template>

<script setup>

</script>

<style scoped>
.index-guide {
  padding: 10px;
  background-color: #fff;
  height: 100vh;
  box-sizing: border-box;
}

.title {
  font-size: 20px;
  margin-bottom: 40px;
  text-align: center;
}

.tab-bar {
  display: flex;
  justify-content: flex-start;
  gap: 10px;
  padding: 0 100px;
  margin-bottom: 60px;
}

.tab {
  font-weight: bold;
  font-size: 16px;
  padding: 8px 12px;
  background-color: #eee;
  border-radius: 6px;
}
</style>

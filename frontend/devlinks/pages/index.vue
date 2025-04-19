<template>
  <div class="index-guide">
    <!-- 사이트 설명 -->
    <h1 class="title">이 사이트는 아래와 같은 5개의 탭으로 구성되어 있습니다.</h1>

    <!-- 가상의 상단 탭들 (UI용) -->
    <nav class="tab-bar">
      <div class="tab">알고리즘</div>
      <div class="tab">API & 문서</div>
      <div class="tab">클라우드</div>
      <div class="tab">테스트</div>
      <div class="tab">UI/UX</div>
    </nav>

    <!-- 각 탭 설명 툴팁 -->
    <div class="tooltips">
      <div class="tooltip" v-for="(tip, index) in tooltips" :key="index" :style="getStyle(index)">
        <div class="arrow" />
        <p>{{ tip }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
const tooltips = [
  "이곳은 알고리즘 관련 자료 탭입니다.",
  "API & 문서 관련 정보를 볼 수 있어요.",
  "클라우드 및 DevOps 정보를 다룹니다.",
  "테스트 & QA 관련 문서입니다.",
  "UI/UX 관련된 자료를 모았어요.",
]

const getStyle = (index) => {
  const spacing = 140 // 각 탭 간 간격
  const baseLeft = 100
  return {
    left: `${baseLeft + spacing * index}px`,
  }
}
</script>

<style scoped>
.index-guide {
  padding: 40px;
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
  gap: 60px;
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

.tooltips {
  position: relative;
  height: 100px;
}

.tooltip {
  position: absolute;
  top: 0;
  background: #f9f9f9;
  color: #333;
  font-size: 14px;
  padding: 8px 12px;
  border-radius: 8px;
  text-align: center;
  width: max-content;
  max-width: 160px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.tooltip .arrow {
  width: 0;
  height: 0;
  border-left: 6px solid transparent;
  border-right: 6px solid transparent;
  border-top: 8px solid #f9f9f9;
  margin: 0 auto 6px auto;
}
</style>

<template>
  <div class="guide-wrapper">
    <div class="guide-container">
      <div
          v-for="(tab, index) in tabs"
          :key="index"
          :style="getStyle(tab)"
          class="tooltip"
      >
        <div class="arrow" />
        <p>{{ tab.text }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
const tabs = [
  {text: "이곳은 알고리즘 관련 자료 탭입니다.", top: 60, left: 140},
  {text: "API & 문서 관련 정보를 볼 수 있어요.", top: 60, left: 340},
  {text: "클라우드 및 DevOps 정보를 다룹니다.", top: 60, left: 580},
  {text: "테스트 & QA 관련 문서입니다.", top: 60, left: 780},
  {text: "UI/UX 관련된 자료를 모았어요.", top: 60, left: 960},
]

const getStyle = (tab) => ({
  top: `${tab.top}px`,
  left: `${tab.left}px`,
})
</script>

<style scoped>
.guide-wrapper {
  position: relative;
  width: 100%;
  min-height: 200px; /* 툴팁들이 보일 수 있게 공간 확보 */
  background-color: white;
  z-index: 1;
}

.guide-container {
  position: relative;
  width: 100%;
  height: 200px; /* 툴팁 영역 높이 지정 */
}

.tooltip {
  position: absolute;
  background: #f0f0f0;
  color: #333;
  font-size: 14px;
  padding: 8px 12px;
  border-radius: 8px;
  text-align: center;
  width: max-content;
  max-width: 200px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  z-index: 10;
}

.tooltip .arrow {
  width: 0;
  height: 0;
  border-left: 6px solid transparent;
  border-right: 6px solid transparent;
  border-bottom: 10px solid #f0f0f0;
  margin: 0 auto 4px auto;
}
</style>

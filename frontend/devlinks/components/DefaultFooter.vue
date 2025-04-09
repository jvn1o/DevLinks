<template>
  <footer class="n-footer bg-light py-4 mt-5">
    <h1 class="d-none">footer</h1>

    <div class="container">
      <div class="row">
        <!-- Customer Center Links -->
        <div class="col-md-4">
          <nav>
            <h5 class="fw-bold" style="color: #007bff;">Customer Center</h5>
            <ul class="list-unstyled">
              <li><a href="" class="text-decoration-none">Notice</a></li>
              <li><a href="" class="text-decoration-none">Q&A</a></li>
              <li><a href="" class="text-decoration-none">1:1 Counsel</a></li>
              <li><a href="" class="text-decoration-none">Terms and Conditions (이용약관)</a></li>
              <li><a href="" class="text-decoration-none">Privacy Policies (개인정보처리방침)</a></li>
            </ul>
          </nav>
        </div>

        <!-- Language Selector -->
        <div class="col-md-4 w-auto">
          <div class="language-selector n-icon n-deco">
            <select name="language" id="language" class="form-select">
              <option value="Korean">Korean</option>
              <option value="English">English</option>
            </select>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script setup>
// 추가적인 스크립트가 필요하지 않다면 그대로 두세요.
</script>

<style scoped>
.n-footer {
  background-color: #f8f9fa;
}
.n-footer a {
  color: #007bff;
}
.n-footer a:hover {
  color: #0056b3;
  text-decoration: underline;
}

.language-selector select {
  background-image: none; /* 일부 브라우저 화살표 제거 */
  border: none;
  box-shadow: none; /* 일부 브라우저에서 남는 그림자 제거 */
  background-color: transparent; /* 필요 시 배경 제거 */
}
</style>

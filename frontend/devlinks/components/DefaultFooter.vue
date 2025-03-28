<template>
  <footer class="n-footer bg-light py-4">
    <h1 class="d-none">footer</h1>

    <div class="container">
      <div class="row">
        <!-- Language Selector -->
        <div class="col-md-4">
          <div class="language-selector n-icon n-deco">
            <select name="language" id="language" class="form-select">
              <option value="Korean">Korean</option>
              <option value="English">English</option>
            </select>
          </div>
        </div>

        <!-- Customer Center Links -->
        <div class="col-md-4">
          <nav>
            <h5 class="fw-bold">Customer Center</h5>
            <ul class="list-unstyled">
              <li><a href="" class="text-decoration-none">Notice</a></li>
              <li><a href="" class="text-decoration-none">Q&A</a></li>
              <li><a href="" class="text-decoration-none">1:1 Counsel</a></li>
              <li><a href="" class="text-decoration-none">Terms and Conditions (이용약관)</a></li>
              <li><a href="" class="text-decoration-none">Privacy Policies (개인정보처리방침)</a></li>
            </ul>
          </nav>
        </div>

        <!-- Business Information -->
        <div class="col-md-4">
          <p class="line-height:2">
            사업자 등록번호: 000-00-00000<br />
            대표: 김준성<br />
            개인정보 관리 책임자: 김준성<br />
            대표번호: 010-8824-3171<br />
            이메일: junok0987@naver.com<br />
            주소: 경기도 고양시 덕양구 화중로 32-9<br />
            운영시간: (월~금) 오전 9:00 ~ 오후 6:00
          </p>

          <p class="line-height:1.5">
            Haru Seoul은 통신판매중개자로서 거래당사자가 아니며 호스트가 등록한
            상품정보 및 거래에 대해 Haru Seoul은 일체의 책임을 지지 않습니다.
          </p>
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
</style>

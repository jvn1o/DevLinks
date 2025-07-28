# ![image description](./frontend/devlinks/assets/image/DevLinks.svg) DevLinks

**DevLinks**는 개발자들에게 유용한 사이트들의 링크를 한 곳에 모아 저장하고, 추천하며, 리뷰 및 토론할 수 있는 웹 커뮤니티입니다. 
    공신력 있는 개발 문서, AI 도구, 학습 자료 등 다양한 웹 페이지를 공유하고 효율적인 사용법을 함께 논의할 수 있는 공간을 제공합니다.

---

## 🧩 프로젝트 개요

개발자가 자주 참고하는 개발 문서, 튜토리얼, 도구 사이트 등은 굉장히 많고, 대부분 영어로 되어 있어 처음 접하는 사람들에게 허들이 높습니다. 
DevLinks는 이러한 페이지를 기억하고 쉽게 접근할 수 있도록 도와주는 웹 서비스입니다. 
또한 AI 도구의 활용이 증가함에 따라 그 활용법과 검증을 유저들과 함께 공유하고 토론할 수 있는 기능도 포함되어 있습니다.

---

## 🛠 사용 기술

### 🌐 프론트엔드
- **Vue 3** (Composition API)
- **Nuxt.js**
- **Bootstrap** (모바일 반응형 UI 대응)
- **Axios** (비동기 통신, JSON 자동 변환 처리)

### 🧪 백엔드
- **Spring Boot**
- **Spring Data JPA**
- **RESTful API 설계 (GET, POST, PUT, DELETE)**
- **예외 처리 및 HTTP 상태 코드 반환 (200, 201, 400, 401, 409, 500 등)**

### 🗄 데이터베이스
- **OracleDB**  
  - 대량 데이터 처리 성능
  - 인덱스 최적화
  - 오라클 전용 함수 활용  
  *(과거 프로젝트에서는 MariaDB 사용 경험도 있음)*

### 📦 기타 기술
- **Git**
- **Node.js**
- **JSP / MyBatis (과거 학습 이력)**

---

## 📌 주요 기능

- 📚 **개발 사이트 링크 저장 및 관리**  
  사용자가 유용한 링크를 등록하고 카테고리별로 관리할 수 있습니다.

- 📝 **링크에 대한 사용자 리뷰 및 평점 작성**  
  각 사이트에 대한 사용자 의견을 기반으로 실질적인 정보 접근 가능

- 💬 **사용법 및 활용법 공유 커뮤니티**  
  AI 기반 도구, 공식 문서의 사용법에 대한 사용자 간 정보 공유 및 토론

- 🗃️ **유저 맞춤 링크 추천 기능 (예정)**  
  유저 활동 기반으로 추천 알고리즘 도입 예정

---

## 📂 프로젝트 구조 (요약)

📁 frontend/              # Nuxt 기반 프론트엔드  
    └─ composables/      # 공통 JS 로직 재사용  
    └─ components/       # UI 구성 요소  
    └─ layouts/          # 레이아웃 컴포넌트  
    └─ pages/            # 라우트 단위 구성  

📁 backend/               # Spring Boot 기반 백엔드  
    └─ controller/       # REST API 컨트롤러  
    └─ service/          # 비즈니스 로직  
    └─ entity/           # JPA 엔티티  
    └─ repository/       # 데이터 접근 계층  

📁 database/  
    └─ oracle-schema.sql  
    
---

## 🏁 실행 방법

### 1. 프론트엔드
```bash
cd .\devlinks\.nuxt\
npm install
npm run dev
```

### 2. 2. 백엔드
```bash
cd backend
./gradlew bootRun
```

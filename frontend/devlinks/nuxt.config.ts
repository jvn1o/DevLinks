// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  css: ['swiper/swiper-bundle.css'],
  compatibilityDate: '2024-11-01',
  devtools: { enabled: true },

  // Vite 서버 설정
  vite: {
    server: {
      proxy: {
        '/api': {
          target: 'http://localhost:8080', // 백엔드 API 주소
          changeOrigin: true,
          rewrite: path => path.replace(/^\/api/, ''),
        },
      },
    },
  },

  // auth 인증 우회 (개발용) ------------------------
  runtimeConfig: {
    public: {
      DEV_BYPASS_AUTH: 'true'
    }
  },
  // ----------------------------------------------
})
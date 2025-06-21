// // 인증이 필요한 페이지에 접근하려고 할 때, 막아주는 인증 middle
// export default defineNuxtRouteMiddleware(() => {
//     const member = useAuthMember(); // 로그인된 사용자 정보
//
//     // 인증이 필요한 경로
//     // "/member/links/**" → 로그인한 사람만 접근 가능
//     if (to.path.startsWith('/member/links')) {
//         if (!member.value) {
//             return navigateTo('/login');
//         }
//     }
//
//     // 본인만 접근 가능하게 제한
//     // 예: /member/index.vue || bookmarks.vue  본인이 아닐 경우 차단
//     if (to.path.startsWith('/member/links')) {
//         const routeMemberId = member.value?.id; // 예: 본인의 ID
//     }
// });

// 인증이 필요한 페이지에 접근하려고 할 때, 막아주는 인증 middle

import useAuthMember from '~/composables/useMemberDetails'
export default defineNuxtRouteMiddleware((to) => {

    const { isAnonymous, memberId } = useMemberDetails()

    // 인증이 필요한 경로
    // "/member/my/links/**" → 로그인한 사람만 접근 가능
    if (to.path.startsWith('/member/my/links')) {
        if (isAnonymous()) {
            return navigateTo('/login')
        }
    }

    const CurrentMemberId = memberId.value // 로그인된 유저 ID
    const routeMemberId = to.params.id     // URL 상의 :id

    // 본인만 접근 가능하게 제한
    // 예: /member/index.vue || bookmarks.vue  본인이 아닐 경우 차단
    if (to.path.startsWith('/member/my/links')) {
        if (routeMemberId && routeMemberId !== String(CurrentMemberId)) {
            return navigateTo('/403') // 접근 차단 페이지
        }
    }
});

// 인증이 필요한 페이지에 접근하려고 할 때, 막아주는 인증 middle
export default defineNuxtRouteMiddleware(() => {
    const { isAnonymous } = useMemberDetails()

    if (isAnonymous()) {
        return navigateTo('/login')
    }
})

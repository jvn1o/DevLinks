export const useAuthRedirect = (
    isAnonymous: () => boolean,
    modalRef: Ref<{ show: () => Promise<boolean> }>,
    toggleSidebar?: () => void
) => {
    const router = useRouter()
    const config = useRuntimeConfig()

    const navigateWithAuth = async (targetPath: string) => {
        console.log('[navigateWithAuth] called →', targetPath)

        // nuxt.config.ts 설정으로 강제 우회 (개발용)
        if (config.public.DEV_BYPASS_AUTH === 'true') {
            console.log('[navigateWithAuth] 개발 모드 인증 우회 중 →', targetPath)
            router.push(targetPath)
            return
        }
        
        if (isAnonymous()) {
            console.log('[navigateWithAuth] user is anonymous → showing modal...')
            const confirmed = await modalRef.value?.show?.()

            console.log('[navigateWithAuth] modal confirmed:', confirmed)

            if (confirmed) {
                const loginPath = `/auth/login?redirect=${encodeURIComponent(targetPath)}`
                console.log('[navigateWithAuth] redirecting to login:', loginPath)
                router.push(loginPath)
            }
            return
        }

        console.log('[navigateWithAuth] user is authenticated → navigating to:', targetPath)
        router.push(targetPath)
    }

    return { navigateWithAuth }
}

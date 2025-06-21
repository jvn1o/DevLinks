export const useAuthRedirect = (
    isAnonymous: () => boolean,
    modalRef: Ref<{ show: () => Promise<boolean> }>,
    toggleSidebar?: () => void
) => {
    const router = useRouter()

    const navigateWithAuth = async (targetPath: string) => {
        console.log('[navigateWithAuth] called →', targetPath)
        
        if (isAnonymous()) {
            console.log('[navigateWithAuth] user is anonymous → showing modal...')
            const confirmed = await modalRef.value?.show?.()

            console.log('[navigateWithAuth] modal confirmed:', confirmed)

            if (confirmed) {
                const loginPath = `/login?redirect=${encodeURIComponent(targetPath)}`
                console.log('[navigateWithAuth] redirecting to login:', loginPath)
                router.push(loginPath)
            }
            return
        }

        console.log('[navigateWithAuth] user is authenticated → navigating to:', targetPath)
        router.push(targetPath)

        if (toggleSidebar) toggleSidebar()
    }

    return { navigateWithAuth }
}

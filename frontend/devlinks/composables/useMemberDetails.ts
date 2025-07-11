interface LoginInfo {
    id: number
    memberId: string
    memberName: string
    email: string
    birth: string
    profileImgSrc: string | null
    token: string
}

export default () => {
    const id = useState<number | null>('id', () => null)
    const memberId = useState<string | null>('memberId', () => null)
    const email = useState<string | null>('email', () => null)
    const birth = useState<string | null>('birth', () => null)
    const memberName = useState<string | null>('memberName', () => null)
    const profileImgSrc = useState<string | null>('profileImgSrc', () => null)
    const token = useState<string | null>('token', () => null)

    const isAnonymous = () => {
        return !memberId.value || !token.value
    }

    const login = (loginInfo: LoginInfo) => {
        id.value = loginInfo.id
        memberId.value = loginInfo.memberId
        memberName.value = loginInfo.memberName
        email.value = loginInfo.email
        birth.value = loginInfo.birth
        profileImgSrc.value = loginInfo.profileImgSrc
        token.value = loginInfo.token

        if (process.client) {
            localStorage.setItem('id', JSON.stringify(loginInfo.id))
            localStorage.setItem('memberId', loginInfo.memberId)
            localStorage.setItem('email', loginInfo.email)
            localStorage.setItem('birth', loginInfo.birth)
            localStorage.setItem('memberName', loginInfo.memberName)
            localStorage.setItem('profileImgSrc', loginInfo.profileImgSrc ?? '')
            localStorage.setItem('token', loginInfo.token)
        }
    }

    const loadMemberFromStorage = async () => {
        if (process.client) {
            const storedMemberId = localStorage.getItem('memberId')
            const storedToken = localStorage.getItem('token')

            if (storedMemberId && storedToken) {
                memberId.value = storedMemberId
                token.value = storedToken

                const storedId = localStorage.getItem('id')
                if (storedId) id.value = JSON.parse(storedId)

                memberName.value = localStorage.getItem('memberName')
                birth.value = localStorage.getItem('birth')
                email.value = localStorage.getItem('email')
                profileImgSrc.value = localStorage.getItem('profileImgSrc')
            }
        }
    }

    const logout = () => {
        id.value = null
        memberId.value = null
        email.value = null
        birth.value = null
        memberName.value = null
        token.value = null
        profileImgSrc.value = null

        if (process.client) {
            localStorage.removeItem('id')
            localStorage.removeItem('memberId')
            localStorage.removeItem('email')
            localStorage.removeItem('birth')
            localStorage.removeItem('memberName')
            localStorage.removeItem('token')
            localStorage.removeItem('profileImgSrc')
        }
    }

    return {
        id,
        memberId,
        email,
        birth,
        memberName,
        token,
        profileImgSrc,
        isAnonymous,
        loadMemberFromStorage,
        login,
        logout,
    }
}

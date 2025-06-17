export default () => {
    const id = useState('id', () => null);
    const memberId = useState('memberId', () => null);
    const email = useState('email', () => null);
    const birth = useState('birth', () => null);
    const memberName = useState('memberName', () => null);
    const profileImgSrc = useState('profileImgSrc', () => null);
    const token = useState('token', () => null);

    const isAnonymous = () => {
        return !memberId.value || !token.value;
    }

    const login = (loginInfo) => {
        console.log("유저디테일에서 확인용:",loginInfo);
        id.value = loginInfo.id;
        memberId.value = loginInfo.memberId;
        memberName.value = loginInfo.memberName;
        email.value = loginInfo.email;
        birth.value = loginInfo.birth;
        profileImgSrc.value = loginInfo.profileImgSrc;
        token.value = loginInfo.token;

        if (process.client) {
            localStorage.setItem('id', JSON.stringify(loginInfo.id));
            localStorage.setItem('memberId', loginInfo.memberId);
            localStorage.setItem('email', loginInfo.email);
            localStorage.setItem('birth', loginInfo.birth);
            localStorage.setItem('memberName', loginInfo.memberName);
            localStorage.setItem('profileImgSrc', loginInfo.profileImgSrc);
            localStorage.setItem('token', loginInfo.token);
        }

    }

    const loadMemberFromStorage = async () => {
        if (process.client) {
            const storedMemberId = localStorage.getItem('memberId');
            const storedToken = localStorage.getItem('token');

            if (storedMemberId && storedToken) {
                memberId.value = storedMemberId;
                token.value = storedToken;
                id.value = JSON.parse(localStorage.getItem('id'));
                memberName.value = localStorage.getItem('memberName');
                birth.value = localStorage.getItem('birth');
                email.value = localStorage.getItem('email');
                profileImgSrc.value = localStorage.getItem('profileImgSrc');
            }
        }
    }

    const logout = () => {
        id.value = null;
        memberId.value = null;
        email.value = null;
        birth.value = null;
        memberName.value = null;
        token.value = null;
        if (process.client) {
            localStorage.removeItem('id');
            localStorage.removeItem('memberId');
            localStorage.removeItem('email');
            localStorage.removeItem('birth');
            localStorage.removeItem('memberName');
            localStorage.removeItem('token');
            localStorage.removeItem('profileImgSrc');
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
        loadMemberFromStorage: loadMemberFromStorage,
        login,
        logout
    }
}
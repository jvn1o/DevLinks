import { ref, computed, watch } from "vue";
import axios from "axios";

export function useSignupForm() {
    const profileImg = ref(null);
    const previewImage = ref(null);

    const onFileChange = (event) => {
        const file = event.target.files[0];
        if (!file) return;
        profileImg.value = file;

        const reader = new FileReader();
        reader.onload = e => previewImage.value = e.target.result;
        reader.readAsDataURL(file);
    };
    const password = ref("");
    const confirmPassword = ref("");
    const name = ref("");
    const memberId = ref("");
    const nickname = ref("");
    const birth = ref("");
    const isChecked = ref(false);
    const isMatch = computed(() => password.value === confirmPassword.value);
    const message = computed(() =>
        confirmPassword.value
            ? isMatch.value
                ? "Passwords match"
                : "Passwords do not match"
            : ""
    );
    const emailId = ref('');
    const emailDomain = ref('');
    const email = computed(() => {
        return emailId.value && emailDomain.value
            ? `${emailId.value}@${emailDomain.value}`
            : '';
    });

    // 유효성 검사 (에러)
    const nameError = ref("");
    const nicknameError = ref("");
    const passwordError = ref("");
    const memberIdError = ref("");
    const emailError = ref("");

    watch(memberId, () => {
        isChecked.value = false;
    });

    // 유효성 검사 함수
    const validate = () => {
        nameError.value = "";
        nicknameError.value = "";
        passwordError.value = "";
        memberIdError.value = "";
        emailError.value = "";

        let isValid = true;

        const nameRegex = /^(?=.*[가-힣a-zA-Z])[가-힣a-zA-Z\s]+$/;
        const passwordRegex = /(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}/;

        if (!nameRegex.test(name.value)) {
            nameError.value = "Name must contain only Korean or English letters.";
            isValid = false;
        }

        if (!nameRegex.test(nickname.value)) {
            nicknameError.value = "Nickname must contain only Korean or English letters.";
            isValid = false;
        }

        if (!passwordRegex.test(password.value)) {
            passwordError.value = "Password must be at least 8 characters with letters, numbers, and special characters.";
            isValid = false;
        }

        if (!isMatch.value) {
            passwordError.value = "Passwords do not match.";
            isValid = false;
        }

        if (!isChecked.value) {
            memberIdError.value = "Please verify your ID.";
            isValid = false;
        }

        if (!email.value.includes("@")) {
            emailError.value = "Please enter a valid email.";
            isValid = false;
        }

        return isValid;
    };

    // 제출 핸들러
    const submitHandler = async () => {
        if (!validate()) return;

        const formData = new FormData();
        formData.append("name", name.value);
        formData.append("memberId", memberId.value);
        formData.append("memberPwd", password.value);
        formData.append("nickname", nickname.value);
        formData.append("email", email.value);
        formData.append("birth", birth.value);

        if (profileImg.value) {
            formData.append("profileImg", profileImg.value);
        }

        try {
            await axios.post("http://192.168.0.11:8080/api/v1/member/signup", formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            });
            navigateTo("/signup/success");
        } catch (error) {
            alert("Please check your input.");
        }
    };

    // ID 중복 확인 핸들러
    const validHandler = async () => {
        try {
            await axios.post("http://192.168.0.11:8080/api/v1/member/idvalid", {
                memberId: memberId.value,
            });
            alert("ID verified");
            isChecked.value = true;
            memberIdError.value = ""; // 에러 초기화
        } catch (error) {
            isChecked.value = false;
            if (error.response && error.response.status === 409) {
                memberIdError.value = "This ID already exists.";
                alert("This ID already exists");
            } else {
                memberIdError.value = "An error occurred. Please try again.";
                alert("An error occurred. Please try again.");
            }
        }
    };

    return {
        profileImg,
        previewImage,
        onFileChange,
        name,
        memberId,
        nickname,
        emailId,
        emailDomain,
        birth,
        password,
        confirmPassword,
        isChecked,
        isMatch,
        message,
        submitHandler,
        validHandler,
        nameError,
        nicknameError,
        passwordError,
        memberIdError,
        emailError,
    };
}

import { ref, computed, watch } from "vue";
import axios from "axios";

export function useSignupForm() {
    const password = ref("");
    const confirmPassword = ref("");
    const name = ref("");
    const userId = ref("");
    const nickname = ref("");
    const email = ref("");
    const birth = ref("");
    const isChecked = ref(false);

    watch(userId, () => {
        isChecked.value = false;
    });

    const isMatch = computed(() => password.value === confirmPassword.value);

    const message = computed(() => {
        if (!confirmPassword.value) return "";
        return isMatch.value ? "password correct" : "password incorrect";
    });

    const submitHandler = async () => {
        if (!isMatch.value || !isChecked.value) {
            alert("Please verify the ID");
            return;
        }

        const memberData = {
            name: name.value,
            userId: userId.value,
            userPwd: password.value,
            nickname: nickname.value,
            email: email.value,
            birth: birth.value,
        };

        try {
            await axios.post("http://localhost:8080/api/v1/members/signup", memberData);
            navigateTo("/signup-success");
        } catch (error) {
            alert("Please check your input");
        }
    };

    const validHandler = async () => {
        try {
            await axios.post("http://localhost:8080/api/v1/members/idvalid", { userId: userId.value });
            alert("중복확인 완료");
            isChecked.value = true;
        } catch (error) {
            if (error.response && error.response.status === 409) {
                alert("This ID already exists");
            } else {
                alert("An error occurred. Please try again");
            }
            isChecked.value = false;
        }
    };

    return {
        name,
        userId,
        nickname,
        email,
        birth,
        password,
        confirmPassword,
        isChecked,
        isMatch,
        message,
        submitHandler,
        validHandler
    };
}

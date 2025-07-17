import AOS from 'aos'
import 'aos/dist/aos.css'
import { onMounted } from 'vue'

export default defineNuxtPlugin(() => {
    onMounted(() => {
        AOS.init({
            duration: 700,
            easing: 'ease-in-out',
            once: true,
        })
    })
})
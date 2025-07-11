import axios from 'axios'
import type { AxiosInstance, InternalAxiosRequestConfig } from 'axios'
import useMemberDetails from '~/composables/useMemberDetails.js'

export function useMemberApi(): AxiosInstance {
    const { token } = useMemberDetails()
    const config = useRuntimeConfig()

    // baseURL이 문자열이 아닐 경우 기본값을 빈 문자열로 지정
    const baseURL = typeof config.public.apiBase === 'string' ? config.public.apiBase : ''

    const api = axios.create({
        baseURL,
        headers: {
            'Content-Type': 'application/json',
        },
    })

    api.interceptors.request.use(
        (requestConfig: InternalAxiosRequestConfig) => {
            if (!requestConfig.headers) {
                requestConfig.headers = new axios.AxiosHeaders()
            }

            if (token.value) {
                requestConfig.headers.set('Authorization', `Bearer ${token.value}`)
            }

            return requestConfig
        },
        (error) => Promise.reject(error)
    )

    api.interceptors.response.use(
        (response) => response,
        (error) => {
            const status = error.response?.status

            if (status === 401) {
                alert('로그인이 만료되었습니다.')
            }

            return Promise.reject(error)
        }
    )

    return api
}

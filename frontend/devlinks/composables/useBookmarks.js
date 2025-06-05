import { ref } from 'vue'

export default function useBookmarks() {
    // 유저가 북마크한 리스트
    const bookmarks = ref([])
    const loading = ref(false)
    const error = ref(null)

    // userId로 북마크 리스트 가져오기
    const fetchBookmarks = async (userId) => {
        loading.value = true
        error.value = null
        try {
            const res = await fetch(`http://localhost:8080/api/v1/bookmarks`)
            if (!res.ok) throw new Error('Failed to fetch bookmarks')
            const data = await res.json()
            bookmarks.value = data.bookmarks || []
        } catch (err) {
            error.value = err.message
        } finally {
            loading.value = false
        }
    }

    // userId와 itemId로 북마크 토글
    const toggleBookmark = async (userId, itemId) => {
        try {
            const res = await fetch(`http://localhost:8080/api/v1/bookmarks`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userId, itemId }),
            })
            if (!res.ok) throw new Error('Failed to toggle bookmark')
            // toggle 후, 최신 북마크 리스트 다시 불러오기 권장
            await fetchBookmarks(userId)
        } catch (err) {
            error.value = err.message
        }
    }

    return {
        bookmarks,
        loading,
        error,
        fetchBookmarks,
        toggleBookmark,
    }
}

// 모든 특수문자와 공백 등을 안전한 slug로 변환하는 함수
export function slugify(text) {
    return text
        .toLowerCase()
        .trim()
        .replace(/ & /g, ' and ')   // 의미 보존 (예: API & Docs → api-and-docs)
        .replace(/ \/ /g, '-')      // 슬래시 제거 (예: UI / UX → ui-ux)
        .replace(/[^\w\s-]/g, '')   // 나머지 특수문자 제거
        .replace(/\s+/g, '-')
        .replace(/--+/g, '-')       // 연속된 하이픈 정리
        .replace(/^-+|-+$/g, '');   // 양쪽 끝 하이픈 제거
}

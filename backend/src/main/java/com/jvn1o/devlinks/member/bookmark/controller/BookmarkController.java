package com.jvn1o.devlinks.member.bookmark.controller;

import com.jvn1o.devlinks.member.bookmark.dto.BookmarkListDto;
import com.jvn1o.devlinks.member.bookmark.service.BookmarkService;
import com.jvn1o.devlinks.auth.principal.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("MemberBookmarkController")
@RequestMapping("/member/my/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // front 의 member/my/bookmarks 와 매핑
    @GetMapping
    public ResponseEntity<List<BookmarkListDto>> getBookmarks(
            @AuthenticationPrincipal MemberPrincipal memberPrincipal  // JWT 인증된 사용자 정보 자동 주입
    ) {
        Long memberId = memberPrincipal.getId();  // 토큰에서 memberId 추출
        List<BookmarkListDto> bookmarks = bookmarkService.getBookmarksByMemberId(memberId);
        return ResponseEntity.ok(bookmarks);
    }

    @PostMapping
    public ResponseEntity<Void> addBookmark(
            @AuthenticationPrincipal MemberPrincipal memberPrincipal,
            @RequestParam Long linkId  // 추가할 링크 ID
    ) {
        Long memberId = memberPrincipal.getId();
        bookmarkService.addBookmark(linkId, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBookmark(
            @AuthenticationPrincipal MemberPrincipal memberPrincipal,
            @RequestParam Long linkId  // 삭제할 링크 ID
    ) {
        Long memberId = memberPrincipal.getId();
        bookmarkService.removeBookmark(linkId, memberId);
        return ResponseEntity.noContent().build();
    }
}

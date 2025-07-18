package com.jvn1o.devlinks.member.bookmark.service;

import com.jvn1o.devlinks.entity.Bookmark;
import com.jvn1o.devlinks.member.bookmark.dto.BookmarkListDto;

import java.util.List;

public interface BookmarkService {

    void addBookmark(Long memberId, Long linkId);

    void removeBookmark(Long memberId, Long linkId);

    List<BookmarkListDto> getBookmarksByMemberId(Long memberId);
}
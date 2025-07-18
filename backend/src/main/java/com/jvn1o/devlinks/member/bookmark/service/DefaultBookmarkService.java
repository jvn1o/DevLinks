package com.jvn1o.devlinks.member.bookmark.service;

import com.jvn1o.devlinks.entity.Bookmark;
import com.jvn1o.devlinks.entity.BookmarkId;
import com.jvn1o.devlinks.entity.Link;
import com.jvn1o.devlinks.entity.Member;
import com.jvn1o.devlinks.member.bookmark.dto.BookmarkListDto;
import com.jvn1o.devlinks.repository.BookmarkRepository;
import com.jvn1o.devlinks.repository.LinkRepository;
import com.jvn1o.devlinks.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBookmarkService implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final LinkRepository linkRepository;
    private final MemberRepository memberRepository;

    @Override
    public void addBookmark(Long memberId, Long linkId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Link link = linkRepository.findById(linkId)
                .orElseThrow(() -> new RuntimeException("Link not found"));

        BookmarkId id = new BookmarkId(linkId, memberId);
        Bookmark bookmark = Bookmark.builder()
                .id(id)
                .link(link)
                .member(member)
                .build();

        bookmarkRepository.save(bookmark);
    }

    @Override
    public void removeBookmark(Long memberId, Long linkId) {
        BookmarkId id = new BookmarkId(linkId, memberId);
        bookmarkRepository.deleteById(id);
    }

    @Override
    public List<BookmarkListDto> getBookmarksByMemberId(Long memberId) {
        return bookmarkRepository.findAllByMemberId(memberId);
    }
}

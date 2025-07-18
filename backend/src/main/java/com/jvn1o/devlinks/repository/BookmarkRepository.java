package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.entity.Bookmark;
import com.jvn1o.devlinks.entity.BookmarkId;
import com.jvn1o.devlinks.member.bookmark.dto.BookmarkListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    @Query("""
        select new com.jvn1o.devlinks.member.bookmark.dto.BookmarkListDto(
            l.id,
            (select i.src from Image i where i.link.id = l.id and i.order = 1),
            l.title,
            l.category.id,
            l.priceType,
            size(l.bookmarks)
        )
        from Bookmark b
        join b.link l
        where b.member.id = :memberId
        order by l.regDate desc
    """)
    List<BookmarkListDto> findAllByMemberId(Long memberId);
}

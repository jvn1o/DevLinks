package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.entity.Link;
import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberLinkRepository extends JpaRepository<Link, Long> {
    @Query("""
        select new com.jvn1o.devlinks.member.link.dto.MemberLinkListDto(
            l.id,
            (select i.src from Image i where i.link.id = l.id and i.order = 1),
            l.title,
            l.category.id,
            l.priceType,
            size(l.bookmarks)
        )
        from Link l
        where l.member.id = :memberId
        order by l.regDate desc
    """)
    List<MemberLinkListDto> findAllByMemberId(Long memberId);

}

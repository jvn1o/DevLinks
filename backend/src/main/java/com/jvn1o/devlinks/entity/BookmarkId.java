package com.jvn1o.devlinks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookmarkId implements Serializable {

    /* 북마크의 id를 link_id & member_id의 슈퍼키로 두어서,
    한 회원이 동일한 링크를 중복 북마크하는 불상사를 막을 수 있다. */
    @Column(name = "link_id")
    private Long linkId;

    @Column(name = "member_id")
    private Long memberId;
}

package com.jvn1o.devlinks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookmark {

    @EmbeddedId
    private BookmarkId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("linkId")
    @JoinColumn(name = "link_id")
    @JsonBackReference(value = "link-bookmark")
    private Link link;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    @JsonBackReference(value = "member-bookmark")
    private Member member;
}

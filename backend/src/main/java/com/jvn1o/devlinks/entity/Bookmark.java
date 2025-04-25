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

    @ManyToOne
    @JoinColumn(name = "link_id")
    @JsonBackReference
    private Link link;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;


}

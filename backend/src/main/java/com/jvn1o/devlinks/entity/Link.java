package com.jvn1o.devlinks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jvn1o.devlinks.common.enums.PriceType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "reg_date", updatable = false)
    @CreationTimestamp
    private Instant regDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

    @Column(name = "language", nullable = false)
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name = "price", nullable = false)
    private PriceType priceType;

    // DB - LINK 에 BOOKMARK_ID 추가하기
    @OneToMany(mappedBy = "link", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "link-bookmark")
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "link", cascade = CascadeType.REMOVE)
    @JsonManagedReference(value = "link-image")
    private List<Image> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonBackReference(value = "member-link")
    private Member member;

//    @OneToMany(mappedBy = "link",cascade = CascadeType.REMOVE)
//    @JsonManagedReference
//    private List<Review> reviews;
}
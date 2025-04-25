package com.jvn1o.devlinks.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "member_id", nullable = false)
    private String memberId;

    @Column(name = "member_pwd", nullable = false)
    private String memberPwd;

    @Column(name = "email", nullable = false)
    private String email;

    @ColumnDefault("current_timestamp()")
    @Column(name = "reg_date", nullable = false)
    @CreationTimestamp
    private Instant regDate;

    @Column(name = "country")
    private String country;

    @Column(name = "profile_img_src")
    private String profileImgSrc;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Sns> sns;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Link> links;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Review> reviews;
}
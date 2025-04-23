package com.jvn1o.devlinks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reg_member_id")
    @JsonBackReference
    private Member member;

    @ColumnDefault("current_timestamp()")
    @Column(name = "reg_date", nullable = false)
    @CreationTimestamp
    private Instant regDate;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "link_id", nullable = false)
    @JsonBackReference
    private Link link;

    @Column(name = "content", length = 5000)
    private String content;

}
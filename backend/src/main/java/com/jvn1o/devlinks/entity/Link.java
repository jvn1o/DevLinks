package com.jvn1o.devlinks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jvn1o.devlinks.common.enums.PriceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @ColumnDefault("current_timestamp()")
    @Column(name = "reg_date")
    @CreationTimestamp
    private Instant regDate;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private PriceType priceType;

    @ColumnDefault("current_timestamp()")
    @Column(name = "update_date")
    @UpdateTimestamp
    private Instant updateDate;

    @Column(name = "language")
    private String language;

    @OneToMany(mappedBy = "link",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Image> images;

    @OneToMany(mappedBy = "link",cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Review> reviews;

    @Column(name = "delete_date")
    private Instant deleteDate;
}
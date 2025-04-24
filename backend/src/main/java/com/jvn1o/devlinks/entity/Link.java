package com.jvn1o.devlinks.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jvn1o.devlinks.common.enums.PriceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

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

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @Column(name = "reg_date", updatable = false)
    @CreationTimestamp
    private Instant regDate;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private PriceType priceType;

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

    // regDate 최초 생성 이후 수정되지 않도록 처리하는 메서드
    @PrePersist
    public void prePersist() {
        if (this.regDate == null) {
            this.regDate = Instant.now();
        }
    }
}
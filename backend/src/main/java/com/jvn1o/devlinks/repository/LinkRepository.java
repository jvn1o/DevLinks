package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.entity.Link;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

    // 필터링은 repository에서 담당하고, 정렬은 service에서 처리
    @Query(
            "SELECT l " +
            "FROM Link l " +
            "WHERE l.category.slug = :slug " +
            "AND (:price IS NULL OR l.priceType = :price)"
    )
    List<Link> findByCategorySlugAndPrice(
            @Param("slug") String slug,
            @Param("price") PriceType price
    );
}

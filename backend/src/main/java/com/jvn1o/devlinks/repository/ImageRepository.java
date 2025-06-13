package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.entity.Image;
import com.jvn1o.devlinks.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    // 특정 링크에 연결된 이미지들 조회
    List<Image> findAllByLinkId(Long linkId);
}

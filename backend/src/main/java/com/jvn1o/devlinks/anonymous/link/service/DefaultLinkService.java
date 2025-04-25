package com.jvn1o.devlinks.anonymous.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.entity.Link;
import com.jvn1o.devlinks.repository.LinkRepository;
import com.jvn1o.devlinks.anonymous.link.dto.LinkListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultLinkService implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public List<LinkListDto> findBySortAndPrice(String slug, String sort, PriceType price) {

        // repository에서 필터링된 링크들
        List<Link> links = linkRepository.findByCategorySlugAndPrice(slug, price);

        // 정렬
        links.sort((a, b) -> {
            switch (sort) {
                    // 최신순으로 정렬 (등록일 기준)
                case "newest":
                    return b.getRegDate().compareTo(a.getRegDate());
                    /* 인기순 정렬 (리뷰 개수 기준)
                       b.getReviews().size()가 a.getReviews().size()보다 크면 음수를 반환하고, b가 a보다 먼저 옴
                    */
                case "popular":
                    return Integer.compare(b.getReviews().size(), a.getReviews().size());
                default:
                    return 0;
            }
        });

        // DTO로 변환하여 반환
        return links.stream()
                .map(link -> LinkListDto.builder()
                        .id(link.getId())
                        .thumbnail(link.getImages() != null && !link.getImages().isEmpty() ? link.getImages().get(0).getUrl() : null)  // 썸네일 이미지 URL
                        .title(link.getTitle())
                        .categories(link.getCategory() != null ? List.of(link.getCategory().getSlug()) : null)  // 카테고리 이름
                        .price(link.getPriceType())  // 가격 타입
                        .bookmarkCount(link.getBookmarkCount())  // 북마크 수
                        .build())
                .collect(Collectors.toList());
    }
}
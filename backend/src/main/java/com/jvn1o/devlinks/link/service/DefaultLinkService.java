package com.jvn1o.devlinks.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.entity.Image;
import com.jvn1o.devlinks.entity.Link;
import com.jvn1o.devlinks.repository.LinkRepository;
import com.jvn1o.devlinks.link.dto.LinkListDto;
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
            int aBookmarkCount = a.getBookmarks() != null ? a.getBookmarks().size() : 0;
            int bBookmarkCount = b.getBookmarks() != null ? b.getBookmarks().size() : 0;

            return switch (sort) {
                // 최신순으로 정렬 (등록일 기준)
                case "newest" -> b.getRegDate().compareTo(a.getRegDate());
                    /* 인기순 정렬 (리뷰 개수 기준)
                       b.getReviews().size()가 a.getReviews().size()보다 크면 음수를 반환하고, b가 a보다 먼저 옴
                    */
                case "popular" -> Integer.compare(bBookmarkCount, aBookmarkCount);
                default -> 0;
            };
        });

        // DTO로 변환하여 반환
        return links.stream()
                .map(link -> {
                    int bookmarkCount = link.getBookmarks() != null ? link.getBookmarks().size() : 0;

                    String thumbnail = null;
                    if (link.getImages() != null && !link.getImages().isEmpty()) {
                        thumbnail = link.getImages().stream()
                                .filter(image -> image.getOrder() == 1)
                                .findFirst()
                                .map(Image::getSrc)
                                .orElse(null);
                    }

                    return LinkListDto.builder()
                            .id(link.getId())
                            .thumbnail(thumbnail)
                            .title(link.getTitle())
                            .categoryId(link.getCategory() != null ? link.getCategory().getId() : null)
                            .pricetype(link.getPriceType())
                            .bookmarkCount(bookmarkCount) // 북마크에 link 해당하는 개수
                            .build();
                })
                .collect(Collectors.toList());
    }
}
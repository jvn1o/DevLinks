package com.jvn1o.devlinks.anonymous.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.repository.LinkRepository;
import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultLinkService implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public List<MemberLinkListDto> findBySortAndPrice(String slug, String sort, PriceType price) {

        // repository에서 필터링된 링크들
        List<Link> links = linkRepository.findByCategorySlugAndPrice(slug, price);

        // 정렬
        links.sort((a, b) -> {
            switch (sort) {
                    // 최신순으로 정렬 (등록일 기준)
                case "newest":
                    return b.getRegDate().compareTo(a.getRegDate());
                    // 알파벳순으로 정렬 (제목 기준)
                case "alphabetical":
                    return a.getTitle().compareToIgnoreCase(b.getTitle());
                    // 인기순 정렬 (리뷰 개수 기준)
                case "popular":
                    return Integer.compare(b.getReviews().size(), a.getReviews().size());
                default:
                    return 0;
            }
        });

        // DTO로 변환하여 반환
        return links.stream()
                .map(link -> MemberLinkListDto.builder()
                        .id(link.getId())
                        .title(link.getTitle())
                        .priceType(link.getPriceType())
                        .regDate(link.getRegDate())
                        .build())
                .collect(Collectors.toList());
    }
}
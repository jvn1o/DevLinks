package com.jvn1o.devlinks.link.dto;

import com.jvn1o.devlinks.common.enums.PriceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkListDto {
    private Long id;
    // Image 에서 order 가 1인 src
    private String thumbnail;
    private String title;
    // CategoryId 에 해당하는 slug 를 가져와 필터링
    private Long categoryId;
    private PriceType pricetype;
    private Integer bookmarkCount;
}
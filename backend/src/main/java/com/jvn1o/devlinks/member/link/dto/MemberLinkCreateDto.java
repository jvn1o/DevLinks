package com.jvn1o.devlinks.member.link.dto;

import com.jvn1o.devlinks.common.enums.PriceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLinkCreateDto {
    private String title;
    private String categorySlug;
    private PriceType pricetype;
    private String description;
}
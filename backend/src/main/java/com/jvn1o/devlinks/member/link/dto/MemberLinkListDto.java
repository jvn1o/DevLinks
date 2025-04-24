package com.jvn1o.devlinks.member.link.dto;

import com.jvn1o.devlinks.common.enums.PriceType;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLinkListDto {
    private Long id;
    private String thumbnail;
    private String title;
    private List<String> categories;
    private PriceType price;
    private Integer bookmarkCount;
}
package com.jvn1o.devlinks.anonymous.link.dto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkListDto {
    private Long id;
    private String thumbnail;
    private String title;
    private List<String> categories;
    private PriceType price;
    private Integer bookmarkCount;
}
package com.jvn1o.devlinks.user.service;

@Service
public class DefaultLinkService implements LinkService {
    @GetMapping("/links")
    public List<LinkListDto> getLinksByPrice(@RequestParam(required = false) PriceType price) {
        if (price != null) {
            return linkService.findByPrice(price);
        } else {
            return linkService.findAll();
        }
    }
}

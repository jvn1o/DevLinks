package com.jvn1o.devlinks.user.link.controller;

@RestController("userLinkController")
@RequestMapping("/user/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public ResponseEntity<List<LinkListDto>> getUserLinks(@AuthenticationPrincipal UserPrincipal user) {
        return ResponseEntity.ok(linkService.getUserLinks(user.getId()));
    }
}

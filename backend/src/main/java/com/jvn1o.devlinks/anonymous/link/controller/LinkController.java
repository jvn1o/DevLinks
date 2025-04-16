@RestController
@RequestMapping("/anonymous/links")
@RequiredArgsConstructor
public class LinkController {

    private final AnonymousLinkService anonymousLinkService;

    @GetMapping
    public ResponseEntity<List<LinkListDto>> getLinks() {
        return ResponseEntity.ok(anonymousLinkService.getPublicLinks());
    }
}

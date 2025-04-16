package com.jvn1o.devlinks.anonymous.link.controller;

@Service
@RequiredArgsConstructor
public class AnonymousLinkService {

    private final LinkRepository linkRepository;

    public List<LinkListDto> getPublicLinks() {
        return linkRepository.findAllPublicLinks()
                .stream()
                .map(LinkMapper::toDto)
                .toList();
    }
}
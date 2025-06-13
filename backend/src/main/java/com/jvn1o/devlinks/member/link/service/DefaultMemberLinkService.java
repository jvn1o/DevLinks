package com.jvn1o.devlinks.member.link.service;

import com.jvn1o.devlinks.common.enums.PriceType;
import com.jvn1o.devlinks.entity.Category;
import com.jvn1o.devlinks.entity.Image;
import com.jvn1o.devlinks.entity.Link;
import com.jvn1o.devlinks.entity.Member;
import com.jvn1o.devlinks.member.link.dto.MemberLinkCreateDto;
import com.jvn1o.devlinks.member.link.dto.MemberLinkListDto;
import com.jvn1o.devlinks.repository.CategoryRepository;
import com.jvn1o.devlinks.repository.LinkRepository;
import com.jvn1o.devlinks.repository.MemberLinkRepository;
import com.jvn1o.devlinks.repository.MemberRepository;
import com.jvn1o.devlinks.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMemberLinkService implements MemberLinkService {

    private final LinkRepository linkRepository;
    private final MemberLinkRepository memberlinkRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<MemberLinkListDto> getMemberLinkList(Long memberId) {
        return memberlinkRepository.findAllByMemberId(memberId);
    }

    @Override
    public void createLink(MemberLinkCreateDto memberLinkCreateDto, MultipartFile imageFile, Long memberId) throws IOException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member does not exist."));

        Category category = categoryRepository.findBySlug(memberLinkCreateDto.getCategorySlug())
                .orElseThrow(() -> new IllegalArgumentException("Category does not exist."));

        Link link = Link.builder()
                .title(memberLinkCreateDto.getTitle())
                .description(memberLinkCreateDto.getDescription())
                .priceType(memberLinkCreateDto.getPricetype())
                .member(member)
                .category(category)
                .build();

        // 먼저 Link 저장 (ID 필요함)
        Link savedLink = linkRepository.save(link);

        if (imageFile != null && !imageFile.isEmpty()) {
            // 저장 로직 (예: local path)
            String filename = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            String imageSrc = "/images/" + filename;

            // TODO: 실제 파일 저장 필요 (예: imageFile.transferTo())

            Image image = Image.builder()
                    .src(imageSrc)
                    .link(savedLink)
                    .build();

            imageRepository.save(image);
            savedLink.setImages(Collections.singletonList(image));
        }
    }
}

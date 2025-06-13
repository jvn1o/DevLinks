package com.jvn1o.devlinks.member.member.service;

import com.jvn1o.devlinks.member.member.dto.MemberSignupRequestDto;
import com.jvn1o.devlinks.entity.Member;
import com.jvn1o.devlinks.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(MemberSignupRequestDto request) {
        // ID 중복 체크
        if (memberRepository.existsByMemberId(request.getMemberId())) {
            throw new IllegalArgumentException("User ID already exists");
        }

        // 간단 유효성 검사 (필요시 확장 가능)
        if (request.getMemberPwd() == null || request.getMemberPwd().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        // 비밀번호 암호화
        String encodedPwd = passwordEncoder.encode(request.getMemberPwd());

        // 프로필 이미지 저장
        String profileImgPath = null;
        if (request.getProfileImg() != null && !request.getProfileImg().isEmpty()) {
            profileImgPath = saveProfileImage(request.getProfileImg());
        }

        // Member 엔티티 변환 및 저장
        Member member =  Member.builder()
                .profileImgSrc(profileImgPath)
                .name(request.getName())
                .memberId(request.getMemberId())
                .memberPwd(encodedPwd)
                .nickname(request.getNickname())
                .email(request.getEmail())
                .birth(request.getBirth())
                .build();

        memberRepository.save(member);
    }

    @Override
    public boolean existsByMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    private String saveProfileImage(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            String newFilename = System.currentTimeMillis() + "_" + originalFilename;
            String uploadDir = "uploads/profile";

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            File dest = new File(dir, newFilename);
            file.transferTo(dest);

            return "/uploads/member/profile/" + newFilename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save profile image", e);
        }
    }
}

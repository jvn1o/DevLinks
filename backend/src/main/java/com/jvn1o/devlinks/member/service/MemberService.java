package com.jvn1o.devlinks.member.service;

import com.m2j2.haruseoul.entity.Member;
import com.jvn1o.devlinks.member.dto.MemberCreateDto;
import com.jvn1o.devlinks.member.dto.MemberListDto;
import com.jvn1o.devlinks.member.dto.MemberUpdateDto;
import org.springframework.web.multipart.MultipartFile;


public interface MemberService {
    Member save(MemberCreateDto memberCreateDto);
    String validateId(String userId);
    MemberListDto getList(Long id);

    void update(MemberUpdateDto memberUpdateDto, MultipartFile file);
    Member validatePwd(Long memberId,String currentPwd);

    void delete(Long id);
}

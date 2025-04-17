package com.jvn1o.devlinks.auth.service;

import com.jvn1o.devlinks.auth.entity.DevLinksUserDetails;
import com.m2j2.haruseoul.entity.Member;
import com.m2j2.haruseoul.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HaruSeoulUserDetailsService implements UserDetailsService {


    private MemberRepository memberRepository;

    public HaruSeoulUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId);

        return DevLinksUserDetails.builder()
                .id(member.getId())
                .username(member.getUserId())
                .password(member.getUserPwd())
                .name(member.getName())
                .birth(member.getBirth())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .profileImgSrc(member.getProfileImgSrc())
                .build();
    }
}

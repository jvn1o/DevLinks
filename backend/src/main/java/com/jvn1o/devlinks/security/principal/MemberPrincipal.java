package com.jvn1o.devlinks.security.principal;

import com.jvn1o.devlinks.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
public class MemberPrincipal implements UserDetails {

    private final Long id;
    private final String memberId;
    private final String memberPwd;
    private final String nickname;
    private final String name;
    private final String email;
    private final String profileImgSrc;
    private final LocalDate birth;
    private final Collection<? extends GrantedAuthority> authorities;

    public MemberPrincipal(Long id, String memberId, String memberPwd, String nickname, String name, String email,
                           String profileImgSrc, LocalDate birth, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profileImgSrc = profileImgSrc;
        this.birth = birth;
        this.authorities = Collections.unmodifiableCollection(authorities);
    }

    // Member 엔티티를 사용하여 UserPrincipal 객체를 생성하는 팩토리 메서드
    public static MemberPrincipal create(Member member) {
        // 권한을 'USER'로 설정한 예시입니다. 실제 권한은 필요에 따라 다르게 설정할 수 있습니다.
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        return new MemberPrincipal(
                member.getId(),
                member.getMemberId(),
                member.getMemberPwd(),
                member.getNickname(),
                member.getName(),
                member.getEmail(),
                member.getProfileImgSrc(),
                member.getBirth(),
                Collections.singletonList(authority) // 기본적으로 USER 권한을 부여
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberPwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부
    }

}

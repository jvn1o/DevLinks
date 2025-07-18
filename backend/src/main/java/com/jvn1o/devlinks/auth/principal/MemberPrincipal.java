package com.jvn1o.devlinks.auth.principal;

import com.jvn1o.devlinks.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
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
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public MemberPrincipal(Long id, String memberId, String memberPwd, String nickname, String name, String email,
                           String profileImgSrc, LocalDate birth, Collection<? extends GrantedAuthority> authorities,
                           boolean accountNonExpired, boolean accountNonLocked,
                           boolean credentialsNonExpired, boolean enabled) {
        this.id = id;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profileImgSrc = profileImgSrc;
        this.birth = birth;
        this.authorities = Collections.unmodifiableCollection(authorities);
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }


    // Member 엔티티를 사용하여 UserPrincipal 객체를 생성하는 팩토리 메서드
    public static MemberPrincipal create(Member member) {
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
                Collections.singletonList(authority),
                member.isAccountNonExpired(),
                member.isAccountNonLocked(),
                member.isCredentialsNonExpired(),
                member.isEnabled()
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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}

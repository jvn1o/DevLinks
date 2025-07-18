package com.jvn1o.devlinks.auth.entity;

import com.jvn1o.devlinks.entity.Member;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevLinksMemberDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String name;
    private LocalDate birth;
    private String profileImgSrc;

    private Collection<? extends GrantedAuthority> authorities;

    private Member member;

    // UserDetails 필수 메서드 구현

    @Override
    public boolean isAccountNonExpired() {
        return member.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return member.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return member.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return member.isEnabled();
    }
}

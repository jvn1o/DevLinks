package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByMemberId(String memberId);

    Optional<Member> findByMemberId(String memberId);
}

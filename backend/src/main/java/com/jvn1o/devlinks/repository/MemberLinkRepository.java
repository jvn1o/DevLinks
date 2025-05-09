package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberLinkRepository extends JpaRepository<Link, Long> {

}

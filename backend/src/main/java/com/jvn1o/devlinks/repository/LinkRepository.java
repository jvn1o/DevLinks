package com.jvn1o.devlinks.repository;

import com.jvn1o.devlinks.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

}

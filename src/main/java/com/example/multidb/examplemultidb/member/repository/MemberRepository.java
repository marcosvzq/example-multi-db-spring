package com.example.multidb.examplemultidb.member.repository;

import com.example.multidb.examplemultidb.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}

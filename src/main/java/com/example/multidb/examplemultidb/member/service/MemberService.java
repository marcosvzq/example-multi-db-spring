package com.example.multidb.examplemultidb.member.service;

import com.example.multidb.examplemultidb.member.model.Member;
import com.example.multidb.examplemultidb.member.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final Logger log = LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Page<Member> getAllMember(Pageable pageable){
        log.debug("Enter service to getAllMember");
        return memberRepository.findAll(pageable);
    }
}

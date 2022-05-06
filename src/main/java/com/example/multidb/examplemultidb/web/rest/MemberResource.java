package com.example.multidb.examplemultidb.web.rest;

import com.example.multidb.examplemultidb.member.model.Member;
import com.example.multidb.examplemultidb.member.service.MemberService;
import com.example.multidb.examplemultidb.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberResource {

    private final Logger log = LoggerFactory.getLogger(MemberResource.class);

    private final MemberService memberService;

    public MemberResource(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMember(Pageable pageable){
        log.debug("Enter REST to get a page of Member");
        Page<Member> page = memberService.getAllMember(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "members");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

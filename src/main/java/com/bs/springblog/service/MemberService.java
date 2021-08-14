package com.bs.springblog.service;


import com.bs.springblog.controller.dto.MemberResponseDto;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(Member member){
        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("회원이 없습니다!"));
        return new MemberResponseDto(member);
    }


}

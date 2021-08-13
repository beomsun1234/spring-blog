package com.bs.springblog.service;


import com.bs.springblog.controller.dto.PostForm;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(Member member){
        return memberRepository.save(member).getId();
    }


}

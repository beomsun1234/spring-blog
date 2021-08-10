package com.bs.springblog.controller;


import com.bs.springblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;


//    @GetMapping
//    public String join(){
//
//        return;
//    }

}

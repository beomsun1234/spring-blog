package com.bs.springblog.controller;


import com.bs.springblog.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {
    private final HttpSession httpSession;
    @GetMapping("/")
    public String home(Model model){
        SessionUser member = (SessionUser)httpSession.getAttribute("member");
        if(member!=null){
            model.addAttribute("member", member);
        }
        log.info("home open");
        return "home";
    }

}

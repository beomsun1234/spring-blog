package com.bs.springblog.controller;


import com.bs.springblog.config.auth.dto.SessionUser;
import com.bs.springblog.controller.dto.Guest;
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
            model.addAttribute("profile", member.getPicture());
        }
        else{
            Guest guest = Guest.builder().id( 00L).name("guest").build();
            model.addAttribute("member", guest);
        }
        log.info("home open");
        return "home";
    }

}

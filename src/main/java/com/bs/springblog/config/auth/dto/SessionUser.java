package com.bs.springblog.config.auth.dto;

import com.bs.springblog.domain.Member.AuthenticationProvider;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Member.Role;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Role role;
    private AuthenticationProvider authenticationProvider;



    //세션유저(dto) -> 엔티티로
    public SessionUser(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.picture = member.getPicture();
        this.role = member.getRole();
        this.authenticationProvider = member.getAuthenticationProvider();
    }
}

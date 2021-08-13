package com.bs.springblog.domain.Member;


import com.bs.springblog.domain.Reply.Reply;
import com.bs.springblog.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String picture;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private AuthenticationProvider authenticationProvider;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replys = new ArrayList<>();


    @Builder
    public Member(String name, String email, String picture, Role role, AuthenticationProvider authenticationProvider){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.authenticationProvider = authenticationProvider;
    }

    public Member update(String name, String picture){
        this.name= name;
        this.picture = picture;
        return this;
    }


}

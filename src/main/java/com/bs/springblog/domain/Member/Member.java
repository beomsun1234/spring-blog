package com.bs.springblog.domain.Member;


import com.bs.springblog.domain.posts.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    @Enumerated
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Post> posts = new ArrayList<>();


    @Builder
    public Member(String name, String email, Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }


}

package com.bs.springblog.domain.Reply;


import com.bs.springblog.domain.BaseTimeEntity;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "replys")
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Reply(String content, Post post ,Member member){
        this.content = content;
        this.setPost(post);
        this.setMember(member);
    }

    public void setMember(Member member){
        this.member = member;
        member.getReplys().add(this);
    }
    public void setPost(Post post){
        this.post = post;
        post.getReplys().add(this);
    }

    /**
     * update
     * @param content
     */
    public void update(String content){
        this.content = content;

    }

}

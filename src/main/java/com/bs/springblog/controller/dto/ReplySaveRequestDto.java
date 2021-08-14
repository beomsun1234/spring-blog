package com.bs.springblog.controller.dto;


import com.bs.springblog.config.auth.dto.SessionUser;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Reply.Reply;
import com.bs.springblog.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@NoArgsConstructor
@Getter
public class ReplySaveRequestDto {

    String content;
    Long postId;
    Long memberId;

    @Builder
    public ReplySaveRequestDto(String content, Long postId, Long memberId){
        this.content = content;
        this.postId = postId;
        this.memberId = memberId;
    }


    //포스트서비스에서 받은 dto를 엔티티로 변환하고, 댓글도 entity로 변환
    public Reply replyRequestDTOtoEntity(Member member,Post post){
        return Reply.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
    }
}

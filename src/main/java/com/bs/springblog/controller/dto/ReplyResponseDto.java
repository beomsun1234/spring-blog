package com.bs.springblog.controller.dto;

import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Reply.Reply;
import com.bs.springblog.domain.posts.Post;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
public class ReplyResponseDto {
    private Long id;

    private String content;

    private Long postId;
    private String author;
    private Long memberId;

    //디티오로 엔티티로
    public ReplyResponseDto(Reply entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.postId = entity.getPost().getId();
        this.memberId = entity.getMember().getId();
        this.author = entity.getMember().getName();
    }

}

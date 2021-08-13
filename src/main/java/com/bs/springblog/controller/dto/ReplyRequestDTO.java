package com.bs.springblog.controller.dto;


import com.bs.springblog.domain.Reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@NoArgsConstructor
@Getter
public class ReplyRequestDTO {

    String content;
    Long postId;
    Long memberId;

    @Builder
    public ReplyRequestDTO(String content,Long postId, Long memberId){
        this.content = content;
        this.postId = postId;
        this.memberId = memberId;
    }

    public Reply replyRequestDTOtoEntity(){
        return Reply.builder()
                .content(content)
                .build();
    }
}

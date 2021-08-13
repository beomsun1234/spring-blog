package com.bs.springblog.controller.dto;

import com.bs.springblog.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostReponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    private Long memberId;
    // 엔티티를 dto로
    public PostReponseDto(Post entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.memberId = entity.getMember().getId();
    }



}

package com.bs.springblog.controller.dto;

import com.bs.springblog.domain.posts.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String content;


    @Builder
    public PostUpdateRequestDto(Long id,String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }


}

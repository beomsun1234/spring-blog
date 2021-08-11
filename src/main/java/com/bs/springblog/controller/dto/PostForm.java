package com.bs.springblog.controller.dto;

import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.posts.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Getter
@NoArgsConstructor
public class PostForm {


    @NotNull
    @Size(max = 30, min = 2)
    private String title;

    @Lob
    private String content;

    private Long memberId;



    @Builder
    public PostForm(String title, String content,Long memberId){
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

}

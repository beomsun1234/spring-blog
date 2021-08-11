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

    @NotNull(message = "로그인을 해야 글을 작성할수 있습니다!")
    private Long memberId;

    @NotBlank(message = "로그인을 해야 글을 작성할수 있습니다!")
    private String author;



    @Builder
    public PostForm(String title, String content,String author,Long memberId){
        this.title = title;
        this.content = content;
        this.author = author;
        this.memberId = memberId;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

}

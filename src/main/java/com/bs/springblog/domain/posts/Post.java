package com.bs.springblog.domain.posts;

import com.bs.springblog.domain.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "Posts")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob //대용량 데이터
    private String content;  //섬머노트 라이브러리 <html>태그가 섞여서 디자인됨

    private String author;


    @Builder
    public Post(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }


}

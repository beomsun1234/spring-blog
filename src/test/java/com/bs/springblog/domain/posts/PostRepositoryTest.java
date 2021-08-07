package com.bs.springblog.domain.posts;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void cleanup() {
        postRepository.deleteAll();
    }

    @Test
    public void 저장시간확인하기(){
        //given
        LocalDateTime now = LocalDateTime.now();
        postInit();
        //when
        List<Post> posts = postRepository.findAll();
        //then
        Assertions.assertThat(posts.get(0).getCreatedDate()).isAfter(now);
    }
    @Test
    public void 게시글저장하고불러오기(){
        //given
        String title = postInit();
        //when
        List<Post> posts = postRepository.findAll();
        //then
        Assertions.assertThat(posts.get(0).getTitle()).isEqualTo(title);

    }

    public String postInit(){
        String title = "test";
        String content = "test";
        String author = "park";
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        postRepository.save(post);
        return title;
    }


}
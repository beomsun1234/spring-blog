package com.bs.springblog.service;

import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시글저장테스트(){
        //given
        Post post = postInit();
        //when
        Long findId = postService.save(post);
        //then
        Assertions.assertThat(postRepository.findById(findId)).isNotEmpty();
    }


    public Post postInit(){
        String title = "test";
        String content = "test";
        String author = "park";
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        return post;
    }

}
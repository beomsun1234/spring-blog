package com.bs.springblog.service;

import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    public void 게시글_저장_테스트(){
        //given
        Post post = postInit();
        //when
        Long findId = postService.save(post);
        //then
        Assertions.assertThat(postRepository.findById(findId)).isNotEmpty();
    }
    @Test
    public void 게시글_수정_테스트() throws Exception {
        //given
        Post post = postInit();
        Long saveId = postService.save(post);
        String title = "UpdateTest";
        String content = "UpdateTest";
        Post updateParam = Post.builder().title(title).content(content).build();
        //when
        Long updateId = postService.update(saveId, updateParam);
        //then
        Post updatedPost = postService.findOneById(updateId);
        Assertions.assertThat(updatedPost.getTitle()).isEqualTo(title);
    }

    @Test
    public void 게시글_한건_조회_테스트(){
        //given
        Post post = postInit();
        Long saveId = postService.save(post);
        //when
        Post findPost = postService.findOneById(saveId);
        //then
        Assertions.assertThat(findPost.getId()).isEqualTo(saveId);
    }
    @Test
    public void 게시글_여러건_조회_테스트(){
        //given
        Post post = postInit();
        Post post2 = postInit();
        Post post3 = postInit();
        Long saveId = postService.save(post);
        Long saveId2 = postService.save(post2);
        Long saveId3 = postService.save(post3);
        //when
        List<Post> posts = postService.findAll();
        //then
        Assertions.assertThat(posts.size()).isEqualTo(3);
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
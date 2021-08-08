package com.bs.springblog.service;

import com.bs.springblog.controller.dto.PostForm;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.controller.dto.PostUpdateRequestDto;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import com.bs.springblog.validator.PostValidator;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

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
        PostForm postForm = postInit();
        //when
        Long findId = postService.save(postForm);
        //then
        Assertions.assertThat(postRepository.findById(findId)).isNotEmpty();
    }
    @Test
    public void 게시글_수정_테스트() throws Exception {
        //given
        PostForm postForm = postInit();
        Long saveId = postService.save(postForm);
        String title = "UpdateTest";
        String content = "UpdateTest";
        PostUpdateRequestDto updateParam = PostUpdateRequestDto.builder().title(title).content(content).build();
        //when
        Long updateId = postService.update(saveId,updateParam);
        //then
        PostReponseDto updatedPost = postService.findOneById(updateId);
        Assertions.assertThat(updatedPost.getTitle()).isEqualTo(title);
    }

    @Test
    public void 게시글_한건_조회_테스트(){
        //given
        PostForm postForm = postInit();
        Long saveId = postService.save(postForm);
        //when
        PostReponseDto findPost = postService.findOneById(saveId);
        //then
        Assertions.assertThat(findPost.getId()).isEqualTo(saveId);
    }
    @Test
    public void 게시글_여러건_조회_테스트(){
        //given
        PostForm post = postInit();
        PostForm post2 = postInit();
        PostForm post3 = postInit();
        Long saveId = postService.save(post);
        Long saveId2 = postService.save(post2);
        Long saveId3 = postService.save(post3);
        //when
        List<PostReponseDto> posts = postService.findAll();
        //then
        Assertions.assertThat(posts.size()).isEqualTo(3);
    }

    public PostForm postInit(){
        String title = "test";
        String content = "test";
        String author = "park";
        PostForm postForm = PostForm.builder().title(title).content(content).build();
        return postForm;
    }



}
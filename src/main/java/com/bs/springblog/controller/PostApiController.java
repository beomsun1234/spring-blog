package com.bs.springblog.controller;


import com.bs.springblog.controller.dto.PostForm;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.controller.dto.PostUpdateRequestDto;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class PostApiController {
    private final PostService postService;
    /**
     * 게시글 저장
     * @return
     */
    @PostMapping("/v1/post")
    public Long save(@RequestBody PostForm postForm){
        return postService.save(postForm);
    }
    /**
     * 게시글 수정
     */
    @PutMapping("/v1/post/{id}")
    public Long update(@PathVariable Long id,@RequestBody PostUpdateRequestDto postUpdateRequestDtoForm) throws Exception {
        Long updatedId = postService.update(id, postUpdateRequestDtoForm);
        return updatedId;
    }

    /**
     * 게시글 단건 조회
     * dto로 반환하기
     */
    @GetMapping("/v1/post/{id}")
    public PostReponseDto findByOne(@PathVariable Long id) throws Exception {
        PostReponseDto findPost = postService.findOneById(id);
        return findPost;
    }
    /**
     * 여러건 조회
     * dto로 반환하기
     */
    @GetMapping("/v1/post")
    public List<PostReponseDto> findAll() throws Exception {
        List<PostReponseDto> posts = postService.findAll();
        return posts;
    }

}

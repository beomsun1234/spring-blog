package com.bs.springblog.controller;


import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
    public Long save(@RequestBody Post post){
        return postService.save(post);
    }

}

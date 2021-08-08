package com.bs.springblog.service;


import com.bs.springblog.controller.dto.PostForm;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.controller.dto.PostUpdateRequestDto;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostRepository postRepository;
    
    @Transactional
    public Long save(PostForm postForm){
        return postRepository.save(postForm.toEntity()).getId();
    }
    @Transactional
    public Long update(Long postId , PostUpdateRequestDto postUpdateRequestDto) throws Exception{
        Optional<Post> findPost = postRepository.findById(postId);
        if(!findPost.isPresent()){
            throw  new IllegalArgumentException("업데이트할 게시글이 없습니다");
        }
        findPost.get().update(postUpdateRequestDto.getTitle(),postUpdateRequestDto.getContent());
        return postId;
    }

    @Transactional(readOnly = true)
    public PostReponseDto findOneById(Long findParamId){
        Optional<Post> findPost = postRepository.findById(findParamId);
        if(!findPost.isPresent()){
            throw  new IllegalArgumentException("찾는 게시글이 없습니다");
        }
        return new PostReponseDto(findPost.get());
    }
    @Transactional(readOnly = true)
    public List<PostReponseDto> findAll(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostReponseDto::new).collect(Collectors.toList());
    }
    
}

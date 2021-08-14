package com.bs.springblog.service;


import com.bs.springblog.controller.dto.PostForm;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.controller.dto.PostUpdateRequestDto;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Member.MemberRepository;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional

    public Long save(PostForm postForm){
        Member member = memberRepository.findById(postForm.getMemberId()).orElseThrow(IllegalArgumentException::new);
        Post post = postForm.toEntity();
        post.setMember(member);
        return postRepository.save(post).getId();
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
    public Page<PostReponseDto> findAll(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        return posts.map(PostReponseDto::new);
    }
    @Transactional(readOnly = true)
    public Page<PostReponseDto> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable){
        Page<Post> findPost = postRepository.findByTitleContainingOrContentContaining(title, content, pageable);
        return findPost.map(PostReponseDto::new);
    }
    @Transactional(readOnly = true)
    public List<PostReponseDto> findAllFetch(){
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostReponseDto::new).collect(Collectors.toList());
    }

    
}

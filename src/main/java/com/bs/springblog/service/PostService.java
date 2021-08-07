package com.bs.springblog.service;


import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PostService {
    
    private final PostRepository postRepository;
    
    @Transactional
    public Long save(Post post){
        //dto로 바꾸기
        return postRepository.save(post).getId();
    }
    @Transactional
    public Long update(Long postId , Post post) throws Exception{
        Optional<Post> findPost = postRepository.findById(postId);
        if(!findPost.isPresent()){
            throw  new IllegalArgumentException("업데이트할 게시글이 없습니다");
        }
        findPost.get().update(post.getTitle(),post.getContent());
        return postId;
    }

    @Transactional(readOnly = true)
    public Post findOneById(Long findParamId){
        Optional<Post> findPost = postRepository.findById(findParamId);
        if(!findPost.isPresent()){
            throw  new IllegalArgumentException("찾는 게시글이 없습니다");
        }
        return findPost.get();
    }
    @Transactional(readOnly = true)
    public List<Post> findAll(){
        List<Post> posts = postRepository.findAll();
        return posts;
    }
    
}

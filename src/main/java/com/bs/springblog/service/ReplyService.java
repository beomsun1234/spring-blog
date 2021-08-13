package com.bs.springblog.service;


import com.bs.springblog.config.auth.dto.SessionUser;
import com.bs.springblog.controller.dto.PostReponseDto;
import com.bs.springblog.controller.dto.ReplyRequestDTO;
import com.bs.springblog.controller.dto.ReplyResponseDto;
import com.bs.springblog.domain.Member.Member;
import com.bs.springblog.domain.Member.MemberRepository;
import com.bs.springblog.domain.Reply.Reply;
import com.bs.springblog.domain.Reply.ReplyRepository;
import com.bs.springblog.domain.posts.Post;
import com.bs.springblog.domain.posts.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final HttpSession session;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(Long boardId,ReplyRequestDTO replyRequestDTO){
        Reply reply = replyRequestDTO.replyRequestDTOtoEntity();
        Long postId = replyRequestDTO.getPostId();
        Member member = memberRepository.findById(replyRequestDTO.getMemberId()).orElseThrow(()-> new IllegalArgumentException("유저가 없습니다"));
        Post post= postRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("글이 없습니다!"));;
        log.info("replyRequestDTO boardId={}",postId);
        log.info("boardId={}",boardId);
        reply.setMember(member);
        reply.setPost(post);
        return reply.getId();
    }
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAll(){
        List<Reply> replies = replyRepository.findAll();
        return replies.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findByPostId(Long id){
        List<Reply> replies = replyRepository.findByPostId(id);
        return replies.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }
}

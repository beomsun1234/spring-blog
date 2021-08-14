package com.bs.springblog.service;


import com.bs.springblog.controller.dto.ReplySaveRequestDto;
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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /**
     * 저장
     * @param boardId
     * @param replySaveRequestDto
     * @return
     */
    @Transactional
    public Long save(Long boardId, ReplySaveRequestDto replySaveRequestDto){
        //PostReponseDto post= postService.findOneById(boardId);
        Member member = memberRepository.findById(replySaveRequestDto.getMemberId())
                .orElseThrow(()-> new IllegalArgumentException("없음"));
        Post post = postRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("없음"));
        Reply reply = replySaveRequestDto.replyRequestDTOtoEntity(member,post);
        return replyRepository.save(reply).getId();
    }
    
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findAll(){
        List<Reply> replies = replyRepository.findAll();
        return replies.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }


    /**
     * 해당 게시판에 쓰여진 댓글 조회
     * @param boardId ->게시판 id
     * @return
     */
    @Transactional(readOnly = true)
    public List<ReplyResponseDto> findByPostId(Long boardId){
        List<Reply> replies = replyRepository.findByPostId(boardId);
        return replies.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }
}

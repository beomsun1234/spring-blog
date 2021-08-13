package com.bs.springblog.controller;

import com.bs.springblog.controller.dto.ReplyRequestDTO;
import com.bs.springblog.controller.dto.ReplyResponseDto;
import com.bs.springblog.domain.Reply.Reply;
import com.bs.springblog.service.ReplyService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("api")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/v1/post/{id}/reply")
    public Long save(@PathVariable Long id, @RequestBody ReplyRequestDTO replyRequestDTO){
        log.info("memberId={}",replyRequestDTO.getMemberId());
        log.info("postId={}",replyRequestDTO.getPostId());
        replyService.save(id,replyRequestDTO);
        return replyRequestDTO.replyRequestDTOtoEntity().getId();
    }

    @GetMapping("/v1/post/reply")
    public List<ReplyResponseDto> findAll(){
        List<ReplyResponseDto> dtoList = replyService.findAll();
        return dtoList;
    }

}

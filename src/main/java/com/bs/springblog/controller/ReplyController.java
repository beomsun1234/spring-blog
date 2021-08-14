package com.bs.springblog.controller;

import com.bs.springblog.controller.dto.ReplySaveRequestDto;
import com.bs.springblog.controller.dto.ReplyResponseDto;
import com.bs.springblog.service.ReplyService;
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
    public Long save(@PathVariable Long id, @RequestBody ReplySaveRequestDto replySaveRequestDto){
        log.info("memberId={}", replySaveRequestDto.getMemberId());
        log.info("postId={}", replySaveRequestDto.getPostId());
        return replyService.save(id, replySaveRequestDto);
    }

    @GetMapping("/v1/post/reply")
    public List<ReplyResponseDto> findAll(){
        List<ReplyResponseDto> replys = replyService.findAll();
        return replys;
    }

}
